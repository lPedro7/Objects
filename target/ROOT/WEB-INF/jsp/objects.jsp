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
    <link rel="stylesheet" type="text/css" href="/resources/css/objects.css">
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
    <h2> Benvingut, ${username} !</h2>

    <div class="newBucket">

        <form action="/objects" method="post" class="form-inline">
            <div class="col-auto">
                <label for="name" class="form-label">Nom del Bucket</label>
            </div>
            <div class="col-auto">
                <input type="text" name="name" class="form-control">
            </div>
            <input type="hidden" name="csrftoken" value="${csrftoken}">
            <div class="col-auto">
                <button type="submit" class="btn btn-primary">Crear Bucket</button>
            </div>

        </form>
    </div>

    <div class="buckets">

        <table>

        </table>

        <ul class="list-group">
            <c:forEach items="${buckets}" var="b">
                <li class="list-group-item">
                    <button>
                        <a href="/objects/${b.uri}">
                                ${b.uri}
                        </a>
                    </button>

                </li>

            </c:forEach>
        </ul>
    </div>

</main>
</body>
</html>
