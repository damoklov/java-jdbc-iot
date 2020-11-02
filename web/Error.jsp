<%--
  Created by IntelliJ IDEA.
  User: damoklov
  Date: 10/30/20
  Time: 11:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
</head>
<body>
<div style="text-align: center;background: bisque">
    <h1>Error</h1>
    <h2><%=exception.getMessage() %><br/> </h2>
</div>
</body>
</html>
