<%--
  Created by IntelliJ IDEA.
  User: damoklov
  Date: 10/30/20
  Time: 10:29 PM
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
        <h3 class="text-center">List of Songs</h3>
        <hr>
        <div class="container text-left">
            <a href="<%=request.getContextPath()%>/song/new" class="btn btn-success">Add New Song</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Downloads</th>
                <th>Genre</th>
                <th>Author</th>
                <th>Label</th>
                <th>Album</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="song" items="${listSong}">
                <tr>
                    <td>
                        <c:out value="${song.id}" />
                    </td>
                    <td>
                        <c:out value="${song.name}" />
                    </td>
                    <td>
                        <c:out value="${song.price}" />
                    </td>
                    <td>
                        <c:out value="${song.downloadCount}" />
                    </td>
                    <td>
                        <c:out value="${song.genre}" />
                    </td>
                    <td>
                        <c:out value="${song.author}" />
                    </td>
                    <td>
                        <c:out value="${song.recordLabel}" />
                    </td>
                    <td>
                        <c:out value="${song.album}" />
                    </td>
                    <td><a href="${pageContext.request.contextPath}/song/edit?id=<c:out value='${song.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="${pageContext.request.contextPath}/song/delete?id=<c:out value='${song.id}' />">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>