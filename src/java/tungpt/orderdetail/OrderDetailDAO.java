/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungpt.orderdetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import tungpt.db.MyConnection;

/**
 *
 * @author TUNGPT
 */
public class OrderDetailDAO implements Serializable{
    public boolean checkOut(OrderDetailDTO dto) throws SQLException,NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        
        try{
            con = MyConnection.getConnection();
            if(con!=null){
                String sql = "insert into OrderDetail "
                        + "Values (?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getProId());
                stm.setString(2, dto.getTitle());
                stm.setInt(3, dto.getQuantity());
                stm.setFloat(4, dto.getTotalPrice());
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
