<%--
  Created by IntelliJ IDEA.
  User: pedro
  Date: 19/11/2021
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registra't</title>
</head>
<body>

<h2>Registra't!</h2>

<form action="/signup" method="post">

  <label for="username">Usuari</label>
  <input type="text" name="username" required>

  <label for="password">Contrassenya</label>
  <input type="password" name="password" required>

  <label for="firstName">Nom</label>
  <input type="text" name="firstName" required>

  <label for="lastName">Llinatges</label>
  <input type="text" name="lastName" required>

  <label for="birthDate">Data de Naixement ( dd-MM-yyyy )</label>
  <input type="text" name="birthDate" required>

  <label for="email">Correu electrònic</label>
  <input type="email" name="email" required>

  <button type="submit">Registrar</button>

</form>

<div>
  Ja tens compte?<a href="/login"> Logueja't!</a>
</div>

<c:if test="${not empty message}">
  ${message}
</c:if>


<ul>
  <li>L'usuari ha de tenir mínim 6 caràcters i 20 com a màxim</li>
  <li>La contrassenya ha de tenir com a mínim 8 caràcters</li>
</ul>

</body>
</html>
