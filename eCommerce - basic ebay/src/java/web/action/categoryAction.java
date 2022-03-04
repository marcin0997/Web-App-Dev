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
public class categoryAction implements Action {
    CategoryModel categoryModel;
    ProductModel productModel;

    public categoryAction(CategoryModel categoryModel, ProductModel productModel){
        this.categoryModel = categoryModel;
        this.productModel = productModel;
    }

    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("categories", categoryModel.retrieveAll());
        String category = req.getParameter("categoryid");
        int categoryid = 1;
        if (category != null) {
            categoryid = Integer.parseInt(category);
        }
        req.getSession().setAttribute("categoryid", categoryid);
        
        req.setAttribute("categoryname", categoryModel.retrieve(categoryid).getName());
        req.setAttribute("products", productModel.retrieveByCategory(categoryModel.retrieve(categoryid)));
        if (req.getSession().getAttribute("cart") == null) {
            req.getSession().setAttribute("cart", new ShoppingCart());
        }
        
        req.setAttribute("categoryid", category);
        req.getSession().setAttribute("categories", categoryModel.retrieveAll());
        int category_id = Integer.parseInt(category);
        Category cat= categoryModel.retrieve(category_id);
        req.getSession().setAttribute("categoryname", cat.getName());
        
        ViewManager.nextView(req, resp, "/view/category.jsp");
    }
}
