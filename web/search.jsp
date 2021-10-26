<%-- 
    Document   : search
    Created on : Jun 1, 2021, 9:31:20 AM
    Author     : TUNGPT
--%>

<%@page import="tungpt.registration.RegistrationDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Search Page</h1>
        <c:if test="${not empty sessionScope}">
            <font color="green">
                Welcome, ${sessionScope.USER.fullname}
            </font>
            <form action="search">
                Search: <input type="text" name="txtSearch" value="${param.txtSearch}" />
                <input type="submit" value="Search" name="btnAction" />
            </form>
            <c:set var="searchValue" value="${param.txtSearch}"/>
            <c:if test="${not empty searchValue}">
                <c:set var="result" value="${requestScope.ACCOUNT}"/>
                <c:if test="${ not empty requestScope.ACCOUNT}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>User Name</th>
                                <th>Password</th>
                                <th>Full Name</th>
                                <th>Role</th>
                                <th>Update</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${result}" varStatus="counter">
                                <form action="update">
                                    <tr>
                                        <td>${counter.count}.</td>                                        
                                        <td>
                                            ${dto.username}
                                            <input type="hidden" name="txtUsernameUpdate" value="${dto.username}"/>
                                        </td>
                                        <td>
                                            <input type="text" name="txtPasswordUpdate" value="${dto.password}"/>
                                        </td>
                                        <td>
                                            <input type="text" name="txtFullnameUpdate" value="${dto.fullname}" />
                                        </td>
                                        <td>
                                            <input type="checkbox" name="chkRole" value="ON"
                                                <c:if test="${dto.role == true}">
                                                    checked="checked"
                                                </c:if>
                                            />
                                        </td>
                                        <td> 
                                            <input type="hidden" name="txtSearch" value="${searchValue}" />
                                            <input type="submit" value="Update" name="btnAction" />
                                        </td>
                                        <td> 
                                            <c:if test="${(sessionScope.USER.username) eq dto.username }">
                                                <font color="red">
                                                    This is you
                                                </font>
                                            </c:if  >
                                            <c:if test="${(sessionScope.USER.username) ne dto.username}">
                                                <c:url var="linkDelete" value="delete">
                                                    <c:param name="btnAction" value="Delete" />
                                                    <c:param name="txtLastSearch" value ="${searchValue}" />
                                                    <c:param name="pk" value="${dto.username}" />
                                                    <c:param name="chkIsAdmin" value="${dto.role}" />
                                                </c:url>
                                                <a href="${linkDelete}">Delete</a>
                                            </c:if>
                                        </td>
                                    </tr>
                                </form>
                            </c:forEach> 
                        </tbody>
                    </table>
                </c:if>
                <c:if test="${empty requestScope.ACCOUNT}">
                    No Record<br/>
                </c:if>
            </c:if>
            <a href="logout">Log Out</a> &nbsp
            <a href="loadBook">Book Store</a>
        </c:if>
        <c:if test="${empty sessionScope}">
            <c:redirect url="login.html" />
        </c:if>
    </body>
</html>
