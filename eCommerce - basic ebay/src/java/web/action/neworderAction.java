package web.action;

import cart.ShoppingCart;
import entity.Category;
import entity.Product;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CategoryModel;
import model.ProductModel;
import web.ViewManager;

/**
 *
 * @author Marcin Kubiak
 */
public class neworderAction implements Action {
    
    ProductModel productModel;
    CategoryModel categoryModel;
    ShoppingCart shoppingCart;
    
    public neworderAction(CategoryModel categoryModel, ProductModel productModel){
        this.categoryModel = categoryModel;
        this.productModel = productModel;
    }
    
    @Override
    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        int productid = Integer.parseInt(req.getParameter("productid"));
        ShoppingCart cart = (ShoppingCart) req.getSession().getAttribute("cart");
        cart.addItem(productModel.retrieve(productid));
        req.getSession().setAttribute("cart", cart);
        
        
        
       
        
        
        int categoryid = 1;
        if (req.getSession().getAttribute("categoryid") != null) {
            categoryid = (Integer) req.getSession().getAttribute("categoryid");
        }
        Category cat = categoryModel.retrieve(categoryid);
        req.setAttribute("products", productModel.retrieveByCategory(cat));
        req.setAttribute("categoryname", cat.getName());
        req.setAttribute("categories", categoryModel.retrieveAll());
        
        
        ViewManager.nextView(req, resp, "/view/init.jsp");
    }
}