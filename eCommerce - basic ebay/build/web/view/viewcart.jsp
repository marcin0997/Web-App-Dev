<%-- 
    Document   : viewcart
    Created on : 29-Oct-2021, 20:44:26
    Author     : Marcin Kubiak
--%>

<%@ page import="cart.ShoppingCart" %>
<%@ page import="cart.ShoppingCartItem" %>
<%@ page import="entity.Category" %>
<%@ page import="entity.Product" %>
<%@ page import="java.util.List" %>

<%@page contentType="text/html" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
    <head>
            <head>
        <meta http-equiv="Expires" CONTENT="0">
        <meta http-equiv="Cache-Control" CONTENT="no-cache">
        <meta http-equiv="Pragma" CONTENT="no-cache">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>eCommerce Sample</title>
    </head>
    </head>
    <body>
        <%
            ShoppingCart cart = (ShoppingCart)request.getSession().getAttribute("cart");
        %>
        <p><img src="img/cart.gif" alt="koszyk"/> <%= cart.getNumberOfItems()%> items</p>
        <h1>Your shopping cart contains <%= cart.getNumberOfItems()%> items</h1>
        
        <a href="clearcart.do">Clear cart</a>
        <br/><br/>
        <div><a href="category.do?categoryid=1">Continue shopping</a></div>
        <br/>
        <div><a href="checkout.do">Proceed to checkout</a></div>
        <br/>
        
        <table width="50%" border="1" bordercolordark="#000000" bordercolorlight="#FFFFFF" cellpadding="3" cellspacing="0">
            <tr>
                <th>Product</th>
                <th>Name</th>
                <th>Price</th>
                <th>Quantity</th>
            </tr>
            
            <%
                for(ShoppingCartItem item : cart.getItems()){
            %>
            <tr style="text-align: center;">
                <td><img src="img/products/<%=item.getProduct().getName()%>.png"</td>
                <td>
                    <h3><%=item.getProduct().getName()%></h3>
                    <p><%=item.getProduct().getDescription()%></p>
                </td>
                <td><%=item.getProduct().getPrice()%> € / unit</td>
                <td>
                    <form method="POST" action="updatecart.do">
                        <input type="text" name="<%=item.getProduct().getId()%>" value="<%=item.getQuantity()%>">
                        <br/>
                        <input type="submit" value="update">
                    </form>
                </td>
            </tr>
            
            
            <% } %>
        </table>
        
        
    </body>
</html>
