
package tungpt.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tungpt.cartobj.CartObj;
import tungpt.orderdetail.OrderDetailDTO;
import tungpt.orderdetail.OrderDetailDAO;
import tungpt.product.ProductDTO;

/**
 *
 * @author TUNGPT
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {
    private final String ERROR_PAGE = "errorHTML";
    private final String BOOKSTORE = "loadBook";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       ServletContext context =  request.getServletContext();
        Map<String,String> roadMap = (Map<String, String>) context.getAttribute("MAP");
        String url = roadMap.get(ERROR_PAGE);
        try{
            HttpSession session = request.getSession(false);
            if(session!=null){
                CartObj cart = (CartObj) session.getAttribute("CART");
                if(cart!=null){
                    Map<String,ProductDTO> books = cart.getCart();
                    if(books!=null){
                        for(String id : books.keySet()){
                            String proId = books.get(id).getProId();
                            String title = books.get(id).getProName();
                            int quantity = books.get(id).getProQuantity();
                            float total = books.get(id).getTotal();
                            OrderDetailDTO dto = new OrderDetailDTO(proId, title, quantity, total);
                            OrderDetailDAO dao = new OrderDetailDAO();
                            boolean result = dao.checkOut(dto);
                            if(!result){
                                break;
                            }else{
                                session.removeAttribute("CART");
                                url = roadMap.get(BOOKSTORE);
                            }
                            
                        }
                    }
                }
            }
        }catch(SQLException e){
            log("CheckOutServlet : SQLException : " + e.getMessage());
        }catch(NamingException e){
            log("CheckOutServlet : NamingException : " + e.getMessage());
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
