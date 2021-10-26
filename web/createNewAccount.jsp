<%-- 
    Document   : createNewAccount
    Created on : Jun 29, 2021, 8:02:58 AM
    Author     : TUNGPT
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Create New Account Page</h1>
        <form action="createNewAccount" method="POST">
            
            <c:set var="error" value="${requestScope.ERROR}" />
            Username: <input type="text" name="txtNewUsername" value="${param.txtNewUsername}" />
            <c:if test="${not empty error.usernameLengErr}">
                <font color="red"/>
                    ${error.usernameLengErr}
                </font>
            </c:if>
            <c:if test="${not empty error.usernameDupplicate}">
                <font color="red"/>
                    ${error.usernameDupplicate}
                </font>
            </c:if><br/>
            
            Password: <input type="password" name="txtNewPassword" value="" />
            <c:if test="${not empty error.passwordLengErr}">
                <font color="red">
                    ${error.passwordLengErr}
                </font>
            </c:if><br/>
            
            Confirm: <input type="password" name="txtConfirm" value="" />
            <c:if test="${not empty error.confirmNotMatch}">
                <font color="red">
                    ${error.confirmNotMatch}
                </font>
            </c:if><br/>
            
            Full Name: <input type="text" name="txtNewFullName" value="${param.txtNewFullName}" />
            <c:if test="${not empty error.fullnameLengErr}">
                <font color="red">
                    ${error.fullnameLengErr}
                </font>
            </c:if><br/>
            <input type="submit" value="Register" name="btnAction"/>
            <input type="reset" value="Reset" />
            
        </form>
            
    </body>
</html>
