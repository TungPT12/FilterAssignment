/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungpt.cartobj;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import tungpt.product.ProductDTO;

/**
 *
 * @author TUNGPT
 */
public class CartObj implements Serializable{
    private Map<String,ProductDTO> cart;

    public CartObj() {
    }

    public CartObj(Map<String, ProductDTO> cart) {
        this.cart = cart;
    }
    
    
   
    public void addItemToCart(String proId, ProductDTO dto){
        if(this.cart==null)
            this.cart = new HashMap<>();

        if(this.cart == null)
            this.cart = new HashMap<>();
        if(this.cart.containsKey(proId)){
            int quantity  = this.cart.get(proId).getProQuantity()+1;
            float price = this.getCart().get(proId).getProPrice();
            float total = price*quantity;
            
            this.cart.get(proId).setProQuantity(quantity);
            this.cart.get(proId).setTotal(total);
        }
        if(!this.cart.containsKey(proId)){
            dto.setProQuantity(1);
            dto.setTotal(dto.getProPrice());
            this.cart.put(proId, dto);
        } 
    }
    
    public Map<String,ProductDTO> getCart(){
        return cart;
    }
    
    public void removeItem(String id){
        if(this.cart==null){
            return;
        }
        if(this.cart.containsKey(id)){
            this.cart.remove(id);
            if(this.cart.isEmpty())
                return;
        }
    }
}