/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class LoginServlet extends HttpServlet {
    private final String INVALID_PAGE = "invalidHTML";
    private final String ERROR_PAGE = "errorHTML";
    private final String SEARCH_PAGE = "searchJSP";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext context =  request.getServletContext();
        Map<String,String> roadMap = (Map<String, String>) context.getAttribute("MAP");
        String url = roadMap.get(ERROR_PAGE);
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        Cookie[] c = request.getCookies();
        try{
            RegistrationDAO dao = new RegistrationDAO();
            RegistrationDTO dto = dao.checkLogin(username, password);
            if(dto!=null){
                if(c!=null){
                    for(Cookie c1 : c){
                        if(c1.getName().equals(dto.getUsername())){
                            Cookie tmp = c1;
                            tmp.setMaxAge(0);
                            response.addCookie(tmp);
                        }
                    }
                }
                Cookie cookie = new Cookie(username, password);
                cookie.setMaxAge(60*1);
                response.addCookie(cookie);
                HttpSession session =  request.getSession();
                session.setAttribute("USER", dto);
                url = roadMap.get(SEARCH_PAGE);
            }else{
                url = roadMap.get(INVALID_PAGE);
            }
        }catch(NamingException ex){
            log("LoginServlet : NamingException : " + ex.getMessage());
        }catch(SQLException ex){
            log("LoginServlet : SQLException : " + ex.getMessage());
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
