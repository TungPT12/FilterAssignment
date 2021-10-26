
package tungpt.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tungpt.registration.RegistrationDAO;
import tungpt.registration.RegistrationDTO;

/**
 *
 * @author TUNGPT
 */
public class CookieServlet extends HttpServlet {
    private final String LOGIN_PAGE = "loginHTML";
    private final String ERROR_PAGE = "errorHTML";
    private final String SEARCH_PAGE = "searchJSP";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext context =  request.getServletContext();
        Map<String,String> roadMap = (Map<String, String>) context.getAttribute("MAP");
        String url = roadMap.get(ERROR_PAGE);
        Cookie[] c = request.getCookies(); 
        try{
            if(c!=null){
                Cookie lastCookie = c[c.length-1];
                lastCookie.setMaxAge(60);
                String username = lastCookie.getName();
                String password = lastCookie.getValue();
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO dto = dao.checkLogin(username, password);
                if(dto!=null){
                    response.addCookie(lastCookie);
                    HttpSession session = request.getSession(); 
                    session.setAttribute("USER", dto);
                    url = roadMap.get(SEARCH_PAGE);
                }else{
                    url = roadMap.get(LOGIN_PAGE);
                }
            }else{
                url = roadMap.get(LOGIN_PAGE);   
            }
        }catch(SQLException e){
            log("CookieServlet : SQLException : " + e.getMessage());
        }catch(NamingException e){
            log("NamingException : SQLException : " + e.getMessage());
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
