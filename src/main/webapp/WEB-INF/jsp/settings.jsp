<%--
  Created by IntelliJ IDEA.
  User: pedro
  Date: 19/11/2021
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Settings</title>
</head>
<body>
<header>
    <nav>
        <ul>
            <li><a href="/private/main">Home</a></li>
            <li><a href="/private/settings">Settings</a></li>
            <li><a href="/logout">Logout</a></li>
        </ul>
    </nav>
</header>
<main>
    <h2> Canvia les teves dades, ${username} !</h2>
</main>

<form action="/private/settings" method="post">

    <label for="password">Contrassenya</label>
    <input type="password" name="password" required>

    <label for="firstName">Nom</label>
    <input type="text" name="firstName" value="${user.name}" required>

    <label for="lastName">Llinatges</label>
    <input type="text" name="lastName" value="${user.surname}" required>

    <label for="birthDate">Data de Naixement</label>
    <input type="text" name="birthDate" value="${birthDate}" required>

    <label for="email">Correu electrònic</label>
    <input type="email" name="email" value="${user.email}" required>


    <label for="confirmPassowrd">Torna a posar la teva contrasenya per confirmar els canvis</label>
    <input type="password" name="confirmPassowrd" required>
    <button type="submit">Edita</button>

</form>


<form action="/private/deleteUser" method="post">
    <label for="password">Per seguretat posa la contrassenya</label>
    <input type="password" name="password" required>
    <button type="submit">Eliminar compte</button>
</form>

<button formaction="/private/deleteUser">
    Eliminar compte
</button>

</body>
</html>
