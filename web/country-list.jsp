<%--
  Created by IntelliJ IDEA.
  User: damoklov
  Date: 10/31/20
  Time: 1:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>ITunes Management Application</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: mediumpurple">
        <div>
            ITunes Management Application
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/" class="nav-link">Home</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="row">
    <div class="container">
        <h3 class="text-center">List of Countries</h3>
        <hr>
        <div class="container text-left">
            <a href="<%=request.getContextPath()%>/country/new" class="btn btn-success">Add New Country</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="country" items="${listCountry}">
                <tr>
                    <td>
                        <c:out value="${country.id}" />
                    </td>
                    <td>
                        <c:out value="${country.name}" />
                    </td>
                    <td><a href="${pageContext.request.contextPath}/country/edit?id=<c:out value='${country.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="${pageContext.request.contextPath}/country/delete?id=<c:out value='${country.id}' />">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>