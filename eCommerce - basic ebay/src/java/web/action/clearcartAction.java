/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.action;

import cart.ShoppingCart;
import entity.Category;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CategoryModel;
import model.ProductModel;
import web.ViewManager;

/**
 *
 * @author Marcin Kubiak
 */
public class clearcartAction implements Action {
    
    CategoryModel categoryModel;
    ProductModel productModel;   
    ShoppingCart shoppingCart;
    
    public clearcartAction(CategoryModel categoryModel, ProductModel productModel) {
        this.categoryModel = categoryModel;
        this.productModel = productModel;
    }
    
    @Override
    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        
        req.getSession().setAttribute("cart", new ShoppingCart());
        int categoryid = 1;
        if (req.getSession().getAttribute("categoryid") != null) {
            categoryid = (Integer) req.getSession().getAttribute("categoryid");
        }
        Category cat = categoryModel.retrieve(categoryid);
        req.setAttribute("products", productModel.retrieveByCategory(cat));
        req.setAttribute("categories", categoryModel.retrieveAll());
        req.setAttribute("categoryname", cat.getName());
        
        ViewManager.nextView(req, resp, "/view/viewcart.jsp");

    }
}
