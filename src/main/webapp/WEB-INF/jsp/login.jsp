<%--
  Created by IntelliJ IDEA.
  User: pedro
  Date: 19/11/2021
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<h2>Logueja't !</h2>

<form action="/login" method="post">

    <label for="username">Usuari</label>
    <input type="text" name="username">

    <label for="password">Contrassenya</label>
    <input type="password" name="password">

    <button type="submit">Login</button>

</form>

<div>
    Encara no tens compte? <a href="/signup">Registra't !</a>
</div>

<c:if test="${not empty message}">
    ${message}
</c:if>


</body>
</html>
