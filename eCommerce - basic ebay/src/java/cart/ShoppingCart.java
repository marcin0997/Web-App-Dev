package cart;

import entity.Product;
import java.util.*;

/**
 *
 * @author juanluis
 */
public class ShoppingCart {
    
    List<ShoppingCartItem> shoppingList;
    
    public ShoppingCart()
    {
        shoppingList = new ArrayList<ShoppingCartItem>();
    }

    public synchronized void addItem(Product product) 
    {
        boolean itemExists = false;
        for (ShoppingCartItem item : shoppingList) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + 1);
                itemExists = true;
                break;
            }
        }
        if (!itemExists) {
            shoppingList.add(new ShoppingCartItem(product));            
        }
    }
    
    public synchronized void update(Product product, int quantity)
    {
        for (ShoppingCartItem item : shoppingList) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(quantity);
                break;
            }
        }
    }
    
    public synchronized List<ShoppingCartItem> getItems() 
    {
        return shoppingList;
        
    }
    
    public synchronized int getNumberOfItems() 
    {
        int number=0;
        for (ShoppingCartItem item : shoppingList) {
            number += item.getQuantity();
        }
        return number;
        
    }
    
    public synchronized double getTotal()
    {
        double total=0;
        for (ShoppingCartItem item : shoppingList) {
            total += item.getTotal();
        }
        
        return total;
        
    }
    
    public synchronized void clear()
    {
        this.shoppingList.removeAll(shoppingList);
        shoppingList.clear();
    }

}