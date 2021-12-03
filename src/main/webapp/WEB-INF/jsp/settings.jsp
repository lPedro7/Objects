<%--
  Created by IntelliJ IDEA.
  User: pedro
  Date: 19/11/2021
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=ISO-8859-1" language="java" %>
<html>
<head>
    <title>Settings</title>
    <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
            integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
            crossorigin="anonymous"
    />
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
</head>
<body>
<header>

    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <ul class="nav navbar-nav">
                <li class="nav-item"><a class="nav-link" href="/objects">Home</a></li>
                <li class="nav-item active"><a class="nav-link" href="/settings">Settings</a></li>
                <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
            </ul>
        </div>
    </nav>

</header>
<main>
    <h2> Canvia les teves dades, ${username} !</h2>
</main>

<form action="?" method="post">

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
    <input type="hidden" name="csrftoken" value="${csrftoken}">
    <button type="submit">Edita</button>

</form>


<form action="/deleteUser" method="post">
    <label for="password">Per seguretat posa la contrassenya</label>
    <input type="password" name="password" required>
    <button type="submit">Eliminar compte</button>
</form>

</body>
</html>
