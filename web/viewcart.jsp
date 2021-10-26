<%-- 
    Document   : viewcart
    Created on : Jun 26, 2021, 11:59:42 AM
    Author     : TUNGPT
--%>

<%@page import="tungpt.cartobj.CartObj"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>View Cart</h1>
        <c:set var="CartObject" value="${sessionScope.CART}"/>
        <c:if test="${not empty CartObject}">
            <c:set var="cart"  value="${CartObject.getCart()}"/>
            <c:if test="${not empty cart}">
                <form action="removeBook">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Book Name</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Total</th>
                                <th>Remove Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="proDTO" items="${cart}" varStatus="counter">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${proDTO.value.proName}</td>
                                    <td>${proDTO.value.proPrice}</td>
                                    <td>${proDTO.value.proQuantity}</td>
                                    <td>${proDTO.value.total}$</td>
                                    <td>
                                        <input type="checkbox" name="chkRemove" value="${proDTO.key}" />
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="5">
                                    <a href="loadBook">Continue buy book</a>
                                </td>
                                <td>
                                    <input type="submit" value="Remove Book" name="btnAction" />
                                </td>
                            <tr/>
                        </tbody>
                    </table>
                </form>
                <form action="checkOut">
                    <input type="submit" value="Check Out" name="btnAction" />
                </form>
            </c:if>  
            <c:if test="${empty cart}">
                You don't buy any book<br/>
                <a href="loadBook">Click to return book store</a>
            </c:if>
        </c:if>
        
        <c:if test="${empty CartObject}">
            Your Cart is lose<br/>
            <a href="loadBook">Click to return book store</a>
        </c:if>
    </body>
</html>
