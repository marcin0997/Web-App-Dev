<%-- 
    Document   : category
    Created on : 29-Oct-2021, 20:41:07
    Author     : Marcin Kubiak
--%>

<%@ page import="cart.ShoppingCart" %>
<%@ page import="entity.Category" %>
<%@ page import="entity.Product" %>
<%@ page import="java.util.List" %>

<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Expires" CONTENT="0">
        <meta http-equiv="Cache-Control" CONTENT="no-cache">
        <meta http-equiv="Pragma" CONTENT="no-cache">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>eCommerce Sample</title>
    </head>
    
    <body>
        
        <%
        List<Category> categories = (List<Category>)request.getAttribute("categories");
        List<Product> products = (List<Product>)request.getAttribute("products");
        ShoppingCart cart = (ShoppingCart)request.getSession().getAttribute("cart");


        %>
        
        
        <h2>Products of <%= request.getSession().getAttribute("categoryname")%>  </h2>
        <p><img src="img/cart.gif" alt="koszyk"/> <%= cart.getNumberOfItems()%> items
        <%
            if (cart.getNumberOfItems() != 0)
            {
        %>
                <a href="viewcart.do">View Cart</a>
                <br/>
                <a href="checkout.do">Proceed to checkout</a>
        <%
            }
        %>
        </p>
    <table width="50%" border="1" bordercolordark="#000000" bordercolorlight="#FFFFFF" cellpadding="3" cellspacing="0">
        <tr style="text-align: center">
                <td rowspan="6"><font size="2" face="Verdana">
                    <table align="center" border="1" bordercolordark="#000000" bordercolorlight="#FFFFFF">
                        

        <%
        

        for(Category category : categories){

        %>
        <tr>
        <td width="14%" valign="center" align="middle">
            <a href="category.do?categoryid=<%=category.getId()%>">
                <%=category.getName()%>
            </a>
        </td>
       </tr>
 
       <% } %>
                        
                    </table> </font>
                </td>

        
                <%
                    for(Product product : products){
                        
                    if (Integer.parseInt(request.getParameter("categoryid"))==product.getCategory().getId() ){
                    %>
        
        <td width="20%" height="100">
                <img src="img/products/<%=product.getName()%>.png"
                     alt="<%=product.getName()%>" >
            </a>
        </td>
        
        <td width="20%">
            <h4><%=product.getName()%></h4>
            <%=product.getDescription()%> 
        </td>
        
        <td width="20%">
            <p><%=product.getPrice()%> €</p>
        </td>
        
        <td width="20%">
           <form method="POST" action="neworder.do?productid=<%=product.getId()%>">
                <input type="submit" value="add to cart">
            </form>
           <!--< <a href="neworder.do?productid=<%=product.getId()%>"><button type="button">add to cart</button></a>-->
        </td>
            </tr>
            <tr style="text-align: center">
        <% } } %>
            </tr>
        </table>
    </body>
</html>
