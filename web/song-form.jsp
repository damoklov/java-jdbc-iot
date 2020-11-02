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
            <li><a href="<%=request.getContextPath()%>/song/list" class="nav-link">Songs</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${song != null}">
            <form action="update" method="post">
            </c:if>
            <c:if test="${song == null}">
            <form action="insert" method="post">
            </c:if>
                <caption>
                    <h2>
                        <c:if test="${song != null}">
                            Edit Song
                        </c:if>
                        <c:if test="${song == null}">
                            Add New Song
                        </c:if>
                    </h2>
                </caption>

                <c:if test="${song != null}">
                    <input type="hidden" name="id" value="<c:out value='${song.id}' />" />
                </c:if>

                <fieldset class="form-group">
                    <label>Song Name</label> <input type="text" value="<c:out value='${song.name}' />" class="form-control" name="name" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>Genre</label> <input type="text" value="<c:out value='${song.genre}' />" class="form-control" name="genre" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>Author</label> <input type="text" value="<c:out value='${song.author}' />" class="form-control" name="author" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>Price</label> <input type="text" value="<c:out value='${song.price}' />" class="form-control" name="price" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>Record Label</label> <input type="text" value="<c:out value='${song.recordLabel}' />" class="form-control" name="record_label" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>Album</label> <input type="text" value="<c:out value='${song.album}' />" class="form-control" name="album" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>Downloads</label> <input type="text" value="<c:out value='${song.downloadCount}' />" class="form-control" name="download_count" required="required">
                </fieldset>

                <button type="submit" class="btn btn-success">Save</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
