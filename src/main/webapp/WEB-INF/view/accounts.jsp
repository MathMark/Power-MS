<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="header.jsp"/>

    <style>
        .accountControlPanel{
            padding: 20px;
        }
        #accountTable{
            padding: 20px;
        }
    </style>

</head>
<body>

<%int rowNum = 1;%>
<div id="accountTable">
    <div class="accountControlPanel">
        <button type="button" class="btn btn-primary" onclick="location.href='accounts/addAccount'">Add account</button>
    </div>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">First Name</th>
            <th scope="col">Last Name</th>
            <th scope="col">Email</th>
            <th scope="col">Related campaigns</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${accounts}" var="account">
            <tr>
                <th scope="row"><%=rowNum%>
                </th>
                <td><c:out value="${account.firstName}"/></td>
                <td><c:out value="${account.lastName}"/></td>
                <td><c:out value="${account.email}"/></td>
                <td><c:out value="${account.campaigns.size()}"/></td>
                <td><button type="button" class="btn btn-primary" onclick="location.href='accounts/editAccount/${account.id}'">Edit account</button>
                    <button type="button" class="btn btn-primary" onclick="location.href='accounts/deleteAccount/${account.id}'">Delete account</button></td>
            </tr>
            <%rowNum++;%>
        </c:forEach>
        </tbody>
    </table>

</div>


</body>
</html>