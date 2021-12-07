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
    <title>Pagina principal</title>
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

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
        <div class="container">
            <div id="navbarSupportedContent">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/objects">Objects</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/settings">Settings</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/logout">Logout</a>
                    </li>

                </ul>
            </div>
        </div>
    </nav>
</header>
<main>

    <h2>${uri}</h2>

    <div>

        <form action="?" method="post" enctype="multipart/form-data">
            <label for="name">Nom de l'objecte</label>
            <input type="text" name="name">

            <label for="file">Select a file to upload</label>
            <input type="file" name="file"/>
            <input type="hidden" name="csrftoken" value="${csrftoken}">

            <button type="submit">Crear Objecte</button>

        </form>

    </div>

    <div>
        <form action="/deleteBucket/${bucket}" method="post">
            <input type="hidden" name="csrftoken" value="${csrftoken}">
            <button type="submit">Eliminar Bucket</button>
        </form>
    </div>

    <div>

        <ul>
            <c:forEach items="${nom}" var="obj">
                <c:set var="letter" value="${obj.charAt(0).toString()}" />

                <c:if test="${letter=='/'}">
                    <li><a href="/objects/${bucket}${obj}">${obj}</a></li>
                </c:if>
                <c:if test="${letter!='/'}">
                    <li><a href="/objects/${bucket}/${obj}">${obj}</a></li>
                </c:if>
            </c:forEach>

        </ul>
    </div>

</main>

</body>
</html>
