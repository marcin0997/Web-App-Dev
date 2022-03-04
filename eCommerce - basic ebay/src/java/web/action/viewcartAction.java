/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.action;

import cart.ShoppingCart;
import entity.Category;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CategoryModel;
import web.ViewManager;

/**
 *
 * @author Marcin Kubiak
 */
public class viewcartAction implements Action {
    
    CategoryModel categoryModel;
    
    public viewcartAction(CategoryModel categoryModel)
    {
        this.categoryModel = categoryModel;
    }
    
    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        ShoppingCart cart = (ShoppingCart) req.getSession().getAttribute("cart");
        req.getSession().setAttribute("cart", cart); 
        
        ViewManager.nextView(req, resp, "/view/viewcart.jsp");
    }
}
