<%-- 
    Document   : buybook
    Created on : Jun 9, 2021, 7:40:49 PM
    Author     : TUNGPT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Book Store</h1>
        <c:set var="listBook" value="${requestScope.BOOKSTORE}" />
        <c:if test="${ not empty listBook}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Book Name</th>
                        <th>Price</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="prodto" items="${listBook}" varStatus="counter" >
                        <form action="buyBook">
                            <tr>
                                <td>${counter.count}.</td>
                                <td>
                                    ${prodto.proName}
                                    <input type="hidden" name="txtProId" value="${prodto.proId}" />
                                    <input type="hidden" name="txtProName" value="${prodto.proName}" />
                                </td>
                                <td>
                                    ${prodto.proPrice}
                                    <input type="hidden" name="txtPrice" value="${prodto.proPrice}" />
                                </td>
                                <td>
                                    <input type="submit" value="Buy" name="btnAction" />
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
            <a href="viewCart">View Cart</a>
        </c:if>
        <c:if test="${ empty listBook}">
            Sorry we are update new book for store
        </c:if>
        
    </body>
</html>
