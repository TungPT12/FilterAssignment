/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungpt.db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author TUNGPT
 */
public class MyConnection implements Serializable{
    public static Connection getConnection() throws NamingException, SQLException{
        Context currentContext = new InitialContext();
        Context tomcatContext = (Context) currentContext.lookup("java:comp/env");
        DataSource db = (DataSource) tomcatContext.lookup("TungPT");
        Connection con = db.getConnection();
        
        return con;
    }
}
