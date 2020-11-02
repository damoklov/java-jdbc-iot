<%--
  Created by IntelliJ IDEA.
  User: damoklov
  Date: 11/2/20
  Time: 6:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
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
            <li><a href="<%=request.getContextPath()%>/album/list" class="nav-link">Albums</a></li>
            <li><a href="<%=request.getContextPath()%>/artist/list" class="nav-link">Artists</a></li>
            <li><a href="<%=request.getContextPath()%>/country/list" class="nav-link">Countries</a></li>
            <li><a href="<%=request.getContextPath()%>/creditcard/list" class="nav-link">Credit Cards</a></li>
            <li><a href="<%=request.getContextPath()%>/gender/list" class="nav-link">Genders</a></li>
            <li><a href="<%=request.getContextPath()%>/genre/list" class="nav-link">Genres</a></li>
            <li><a href="<%=request.getContextPath()%>/itunesuser/list" class="nav-link">Users</a></li>
            <li><a href="<%=request.getContextPath()%>/recordlabel/list" class="nav-link">Record Labels</a></li>
            <li><a href="<%=request.getContextPath()%>/song/list" class="nav-link">Songs</a></li>
            <li><a href="<%=request.getContextPath()%>/usersong/list" class="nav-link">User-Songs</a></li>
        </ul>
    </nav>
</header>
<br>
</body>
</html>
