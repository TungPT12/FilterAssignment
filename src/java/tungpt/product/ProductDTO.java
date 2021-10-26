/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungpt.product;

import java.io.Serializable;

/**
 *
 * @author TUNGPT
 */
public class ProductDTO implements Serializable{
    private String proId;
    private String proName;
    private float proPrice;
    private int proQuantity;
    private float total;

    public ProductDTO() {
    }

    public ProductDTO(String proId, String proName, float proPrice, int proQuantity) {
        this.proId = proId;
        this.proName = proName;
        this.proPrice = proPrice;
        this.proQuantity = proQuantity;
    }

    public ProductDTO(String proId, String proName, float proPrice, int proQuantity, float total) {
        this.proId = proId;
        this.proName = proName;
        this.proPrice = proPrice;
        this.proQuantity = proQuantity;
        this.total = total;
    }

    public ProductDTO(String proId, String proName, float proPrice) {
        this.proId = proId;
        this.proName = proName;
        this.proPrice = proPrice;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public float getProPrice() {
        return proPrice;
    }

    public void setProPrice(float proPrice) {
        this.proPrice = proPrice;
    }

    public int getProQuantity() {
        return proQuantity;
    }

    public void setProQuantity(int proQuantity) {
        this.proQuantity = proQuantity;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    
}
