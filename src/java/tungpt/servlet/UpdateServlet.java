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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tungpt.registration.RegistrationDAO;

/**
 *
 * @author TUNGPT
 */
public class UpdateServlet extends HttpServlet {
    private final String ERROR_PAGE = "errorHTML";
    private final String LOGIN_PAGE = "loginHTML";
    private final String SEARCH_CONTROLLER = "search";
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
        ServletContext context = request.getServletContext();
        Map<String,String> roadMap = (Map<String, String>) context.getAttribute("MAP");
        String urlRewriting = ERROR_PAGE;
        String searchValues = request.getParameter("txtSearchValue");
        String updateUsername = request.getParameter("txtUsernameUpdate");
        String updatePassword = request.getParameter("txtPasswordUpdate");
        String updateFullname = request.getParameter("txtFullnameUpdate");
        HttpSession session = request.getSession(false);
        try{
            if(session!=null){
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.updateAccount(updateUsername, updatePassword, updateFullname);
                if(result)
                    urlRewriting = roadMap.get(SEARCH_CONTROLLER);
            }else 
                urlRewriting = roadMap.get(LOGIN_PAGE);
        }catch(SQLException e){
            log("UpdateServlet : SQLException : " + e.getMessage());
        }catch(NamingException e){
            log("UpdateServlet : NamingException : " + e.getMessage());
        }finally{
            request.getRequestDispatcher(urlRewriting).forward(request, response);
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
