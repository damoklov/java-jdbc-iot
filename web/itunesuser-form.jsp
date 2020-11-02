<%--
  Created by IntelliJ IDEA.
  User: damoklov
  Date: 10/31/20
  Time: 1:38 AM
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
            <li><a href="<%=request.getContextPath()%>/itunesuser/list" class="nav-link">Users</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${itunesuser != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${itunesuser == null}">
                <form action="insert" method="post">
                    </c:if>
                    <caption>
                        <h2>
                            <c:if test="${itunesuser != null}">
                                Edit User
                            </c:if>
                            <c:if test="${itunesuser == null}">
                                Add New User
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${itunesuser != null}">
                        <input type="hidden" name="id" value="<c:out value='${itunesuser.id}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>Username</label> <input type="text" value="<c:out value='${itunesuser.username}' />" class="form-control" name="username" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Password</label> <input type="text" value="<c:out value='${itunesuser.password}' />" class="form-control" name="password" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Joined</label> <input type="text" value="<c:out value='${itunesuser.joinedDate}' />" class="form-control" name="joined_date" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Name</label> <input type="text" value="<c:out value='${itunesuser.name}' />" class="form-control" name="name" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Surname</label> <input type="text" value="<c:out value='${itunesuser.surname}' />" class="form-control" name="surname" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Gender</label> <input type="text" value="<c:out value='${itunesuser.gender}' />" class="form-control" name="gender" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Credit Card</label> <input type="text" value="<c:out value='${itunesuser.creditCard}' />" class="form-control" name="credit_card" required="required">
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>