package tungpt.servlet;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tungpt.cartobj.CartObj;
import tungpt.product.ProductDTO;

/**
 *
 * @author TUNGPT
 */
@WebServlet(name = "RemoveBookServlet", urlPatterns = {"/RemoveBookServlet"})
public class RemoveBookServlet extends HttpServlet {
    private final String ERROR_PAGE = "errorHTML";
    private final String VIEWCART_PAGE = "viewCart";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext context =  request.getServletContext();
        Map<String,String> roadMap = (Map<String, String>) context.getAttribute("MAP");
        String url = roadMap.get(ERROR_PAGE);
        try {
            HttpSession session = request.getSession(false);
            if(session!=null){
                CartObj cart = (CartObj) session.getAttribute("CART");
                if(cart!=null){
                    Map<String,ProductDTO> pros = cart.getCart();
                    if(pros!=null){
                        String[] removePros = request.getParameterValues("chkRemove");
                        if(removePros!=null){
                            for(String proId : removePros){
                                cart.removeItem(proId);
                            }
                            session.setAttribute("CART", cart);
                        }
                        url = roadMap.get(VIEWCART_PAGE);
                    }
                }
            }
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
