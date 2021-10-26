/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungpt.product;

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
public class ProductDAO implements Serializable{
    public List<ProductDTO> proList;
    public List<ProductDTO> getProList(){
        return proList;
    }
    
    public void loadPro() throws SQLException,NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = MyConnection.getConnection();
            if(con!=null){
                String sql = "Select ProId,ProName,ProPrice "
                        + "From Product";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while(rs.next()){
                    String ProId = rs.getString("ProId");
                    String ProName = rs.getString("ProName");
                    float ProPrice = rs.getFloat("ProPrice");
                    ProductDTO pro = new ProductDTO(ProId, ProName, ProPrice);
                    if(this.proList==null)
                        this.proList = new ArrayList<>();
                    this.proList.add(pro);
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
}
