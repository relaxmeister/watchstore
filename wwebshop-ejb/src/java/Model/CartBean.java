/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;

/**
 *
 * @author stevi
 */
@Stateful
@LocalBean
public class CartBean {
    private List<Watches> cart = new ArrayList();
    
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void addToCart(Watches watch) {
        cart.add(watch);
    }

    public void deleteFromCart(Watches watch) {
        cart.remove(watch);
    }

    public void clearCart() {
        cart.clear();
    }

    public List<Watches> getCart() {
        return cart;
    }
}
