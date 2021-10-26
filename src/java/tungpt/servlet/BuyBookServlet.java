/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@WebServlet(name = "BuyBookServlet", urlPatterns = {"/BuyBookServlet"})
public class BuyBookServlet extends HttpServlet {
    private final  String ERROR_PAGE = "errorHTML";
    private final  String LOADBOOK_CONTROLLER = "loadBook";
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
        String proId = request.getParameter("txtProId");
        String proName = request.getParameter("txtProName");
        String price = request.getParameter("txtPrice");
        Float proPrice = Float.parseFloat(price);
        
        try{
            HttpSession session = request.getSession();
            CartObj cart = (CartObj) session.getAttribute("CART");
            if(cart==null) // loi !=
                cart = new CartObj();

            ProductDTO dto = new ProductDTO(proId, proName, proPrice);
            cart.addItemToCart(proId, dto);
            
            session.setAttribute("CART", cart);
            url = roadMap.get(LOADBOOK_CONTROLLER);
        }catch(NumberFormatException e){
            log("BuyBookServlet : NumberFormatException : " + e.getMessage());
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
