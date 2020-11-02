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
            <li><a href="<%=request.getContextPath()%>/recordlabel/list" class="nav-link">Record Labels</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${recordlabel != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${recordlabel == null}">
                <form action="insert" method="post">
                    </c:if>
                    <caption>
                        <h2>
                            <c:if test="${recordlabel != null}">
                                Edit Record Label
                            </c:if>
                            <c:if test="${recordlabel == null}">
                                Add New Record Label
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${recordlabel != null}">
                        <input type="hidden" name="id" value="<c:out value='${recordlabel.id}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>Title</label> <input type="text" value="<c:out value='${recordlabel.title}' />" class="form-control" name="title" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Year Established</label> <input type="text" value="<c:out value='${recordlabel.yearEstablished}' />" class="form-control" name="year_established" required="required">
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>