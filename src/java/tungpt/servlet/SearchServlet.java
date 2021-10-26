/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungpt.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
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
public class SearchServlet extends HttpServlet {
    private final String ERROR_PAGE = "errorHTML";
    private final String SEARCH_PAGE = "searchJSP";
    private final String LOGIN_PAGE  = "loginHTML";
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
        String searchKey = request.getParameter("txtSearch");
        HttpSession session = request.getSession();
        try{
            if(session!=null){
                if(!searchKey.trim().isEmpty()){
                    RegistrationDAO dao = new RegistrationDAO();
                    dao.searchName(searchKey);
                    List<RegistrationDTO> listAccount = dao.getListAccount();
                    request.setAttribute("ACCOUNT", listAccount);
                }
                url = roadMap.get(SEARCH_PAGE);
            }else if(session==null){
                url = roadMap.get(LOGIN_PAGE);
            }
        }catch(NamingException e){
            log("NamingException : SearchServlet : " + e.getMessage());
        }catch(SQLException e){
            log("SQLException : SearchServlet :" + e.getMessage());
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
