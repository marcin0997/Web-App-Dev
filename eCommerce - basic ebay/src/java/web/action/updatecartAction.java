/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.action;

import cart.ShoppingCart;
import cart.ShoppingCartItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProductModel;
import web.ViewManager;

/**
 *
 * @author Marcin Kubiak
 */
public class updatecartAction implements Action {
    
    ProductModel productModel;
    
    public updatecartAction(ProductModel productModel){
        this.productModel = productModel;
    }
    
    @Override
    public void perform(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        ShoppingCart cart;
        if (req.getSession().getAttribute("cart") == null) {
            cart = new ShoppingCart();
        } else {
            cart = (ShoppingCart) req.getSession().getAttribute("cart");
        }
        for (ShoppingCartItem item : cart.getItems()) {
            String quantity_str = req.getParameter(item.getProduct().getId().toString());
            
            if (quantity_str != null) {
                int quantity = Integer.parseInt(quantity_str);
                item.setQuantity(quantity);
            }
        }
        System.out.println(req.getParameterMap().toString());
        System.out.println(req.getMethod());
        
        
        req.getSession().setAttribute("cart", cart);
        ViewManager.nextView(req, resp, "/view/viewcart.jsp");
    }
}
