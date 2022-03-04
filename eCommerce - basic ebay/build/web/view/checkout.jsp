<%-- 
    Document   : checkout
    Created on : 30-Oct-2021, 03:02:22
    Author     : Marcin Kubiak
--%>

<%@ page import="entity.Product" %>
<%@ page import="cart.ShoppingCart" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="cart.ShoppingCartItem" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        ShoppingCart cart = (ShoppingCart)request.getSession().getAttribute("cart");
        %>
        <h1>Proceed to checkout</h1>
        
        <form action="https://www.paypal.com/cgi-bin/webscr" method="post">
            <input type="hidden" name="cmd" value="_xclick">
            <input type="hidden" name="business" value="marcinidzie@gmail.com">
            <input type="hidden" name="item_name" value="Shopping list">
            <input type="hidden" name="currency_code" value="EUR">
            <input type="hidden" name="amount" value="<%=cart.getTotal()%>">
            <input type="image" src="http://www.paypal.com/en_US/i/btn/x-click-but01.gif" name="submit" alt="Make payments with PayPal - it's fast, free and secure!">
        </form>
        
        
    </body>
</html>
