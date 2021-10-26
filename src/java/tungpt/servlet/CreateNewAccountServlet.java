/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungpt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tungpt.registration.RegistrationDAO;
import tungpt.registration.RegistrationErrorDTO;

/**
 *
 * @author TUNGPT
 */
@WebServlet(name = "CreateNewAccountServlet", urlPatterns = {"/CreateNewAccountServlet"})
public class CreateNewAccountServlet extends HttpServlet {
    private final String ERROR_PAGE = "errorHTML";
    private final String ERRORNEWACCOUNT = "createNewAccountJSP";
    private final String LOGIN_PAGE = "loginHTML";
    
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
        String username = request.getParameter("txtNewUsername");
        String password = request.getParameter("txtNewPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtNewFullName");
        RegistrationErrorDTO errorDTO = new RegistrationErrorDTO();
        try{
            
            boolean errFound = false;
            boolean errPasswordFound = false;
            if(username.trim().length()<6 || username.trim().length()>20){
                errFound = true;
                errPasswordFound = true;
                errorDTO.setUsernameLengErr("Username must be from 6-20");
            }
            if(password.trim().length()<6 || password.trim().length()>30){
                errFound = true;
                errPasswordFound = true;
                errorDTO.setPasswordLengErr("Password must be from 6-30");
            }
            if(fullname.trim().length()<2 || fullname.trim().length()>50){
                errFound = true;
                errorDTO.setFullnameLengErr("Full Name must be from 2-50");
            }
            if(!errPasswordFound){
                if(!confirm.equals(password)){
                    errFound = true;
                    errorDTO.setConfirmNotMatch("Wrong Password");
                }               
            }
            if(errFound){
                request.setAttribute("ERROR", errorDTO);
                 url = roadMap.get(ERRORNEWACCOUNT);
            }
            else{
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.createNewAccount(username, password, fullname);
                if(result)
                     url = roadMap.get(LOGIN_PAGE);
            }
        }catch(SQLException e){
            String msg = e.getMessage();
            log("CreateNewAccount : SQLException : " + e.getMessage());
            
            if(msg.contains("duplicate")){
                errorDTO.setUsernameDupplicate("This username is exist");
                request.setAttribute("ERROR", errorDTO);
                 url = roadMap.get(ERRORNEWACCOUNT);
            }
        }catch(NamingException e){
            log("CreateNewAccount : NamingException : " + e.getMessage());
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
