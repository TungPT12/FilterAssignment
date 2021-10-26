/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungpt.orderdetail;

/**
 *
 * @author TUNGPT
 */
public class OrderDetailDTO {
    private int id;
    private String proId;
    private String title;
    private int quantity;
    private float totalPrice;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String proId, String title, int quantity, float totalPrice) {
        this.proId = proId;
        this.title = title;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public OrderDetailDTO(int id, String proId, String title, int quantity, float totalPrice) {
        this.id = id;
        this.proId = proId;
        this.title = title;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
    
}
