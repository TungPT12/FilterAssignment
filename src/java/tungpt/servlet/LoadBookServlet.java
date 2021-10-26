
package tungpt.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tungpt.product.ProductDAO;
import tungpt.product.ProductDTO;

/**
 *
 * @author TUNGPT
 */
@WebServlet(name = "LoadBookServlet", urlPatterns = {"/LoadBookServlet"})
public class LoadBookServlet extends HttpServlet {
    private final String ERROR_PAGE = "errorHTML";
    private final String BOOKSTORE_PAGE = "bookStore";
    private final String LOGIN_PAGE = "loginHTML";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext context =  request.getServletContext();
        Map<String,String> roadMap = (Map<String, String>) context.getAttribute("MAP");
        String url = roadMap.get(ERROR_PAGE);
        HttpSession session = request.getSession(false);
        try{
            if(session!=null){
                ProductDAO dao = new ProductDAO();
                dao.loadPro();
                List<ProductDTO> listBook = dao.getProList();
                request.setAttribute("BOOKSTORE", listBook);
                url = roadMap.get(BOOKSTORE_PAGE);
            }else{
                url = roadMap.get(LOGIN_PAGE);
            }
        }catch(SQLException e){
            log("BuyBookServlet : SQLException : " + e.getMessage());
        }catch(NamingException e){
            log("BuyBookServlet : NamingException : " + e.getMessage());
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
