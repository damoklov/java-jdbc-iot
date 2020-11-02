<%--
  Created by IntelliJ IDEA.
  User: damoklov
  Date: 10/27/20
  Time: 11:42 PM
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
            <li><a href="<%=request.getContextPath()%>/usersong/list" class="nav-link">User-Song</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${usersong != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${usersong == null}">
                <form action="insert" method="post">
                    </c:if>
                    <caption>
                        <h2>
                            <c:if test="${usersong != null}">
                                Edit User-Song
                            </c:if>
                            <c:if test="${usersong == null}">
                                Add New User-Song
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${usersong != null}">
                        <input type="hidden" name="id" value="<c:out value='${usersong.id}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>User ID</label> <input type="text" value="<c:out value='${usersong.idUser}' />" class="form-control" name="id_user" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Song ID</label> <input type="text" value="<c:out value='${usersong.idSong}' />" class="form-control" name="id_song" required="required">
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>
