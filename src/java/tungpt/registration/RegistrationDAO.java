/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungpt.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import tungpt.db.MyConnection;

/**
 *
 * @author TUNGPT
 */
public class RegistrationDAO implements Serializable{
    public RegistrationDTO checkLogin(String username,String password) throws NamingException,SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = MyConnection.getConnection();
            if(con!=null){
                String sql = "Select username, fullname,role "
                    + "From Registration "
                    + "Where username=? and password=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2,password);
                rs = stm.executeQuery();
                if(rs.next()){
                    String fullname = rs.getString("fullname");
                    boolean role = rs.getBoolean("role");
                    RegistrationDTO dto = new RegistrationDTO(username, role, fullname);
                    return dto;
                }
            }
        }finally{
            if(rs!=null)
                rs.close();
            if(stm!=null)
                stm.close();
            if(con!=null)
                con.close();
        }
        return null;
    }
    
    private List<RegistrationDTO> listAccount;
    public List<RegistrationDTO> getListAccount(){
        return  listAccount;
    }
    
    public void searchName(String key) throws NamingException,SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = MyConnection.getConnection();
            if(con!=null){
                String sql = "Select username, password,fullname, role "
                        + "From Registration "
                        + "Where fullname Like ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + key + "%");
                rs = stm.executeQuery();
                while(rs.next()){
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("fullname");
                    boolean role = rs.getBoolean("role");
                    RegistrationDTO dto = new RegistrationDTO(username, password, role, fullname);
                    if(this.listAccount==null)
                        this.listAccount = new ArrayList<>();
                    listAccount.add(dto);
                }
            }
        }finally{
            if(rs!=null)
                rs.close();
            if(stm!=null)
                stm.close();
            if(con!=null)
                con.close();
        }   
    }
    
    public boolean deleteAccount(String username) throws SQLException,NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con = MyConnection.getConnection();
            if(con!=null){
                String sql = "Delete From Registration "
                        + "Where username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                int effectRow = stm.executeUpdate();
                if(effectRow>0)
                    return true;
            }
        }finally{
            if(stm!=null)
                stm.close();
            if(con!=null)
                con.close();
        }
        return false;
    }
    
    public boolean updateAccount(String username, String password, String fullname) throws SQLException,NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con = MyConnection.getConnection();
            if(con!=null){
                String sql = "Update Registration "
                        + "Set password=?, fullname=? "
                        + "Where username=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setString(2, fullname);
                stm.setString(3, username);
                int effecRows = stm.executeUpdate();
                if(effecRows>0)
                    return  true;
            }
        }finally{
            if(stm!=null)
                stm.close();
            if(con!=null)
                con.close();
        }
        return false;
    }
    
    public boolean createNewAccount(String username, String password, String fullname) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con = MyConnection.getConnection();
            if(con!=null){
                String sql = "Insert into Registration "
                        + "Values(?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, fullname);
                stm.setBoolean(4, false);
                int effectRows = stm.executeUpdate();
                if(effectRows>0)
                    return true;
            }
        }finally{
            if(stm!=null)
                stm.close();
            if(con!=null)
                con.close();
        }
        return false;
    }
}
    


