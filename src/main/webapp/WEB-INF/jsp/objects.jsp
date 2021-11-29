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
    <title>Pagina principal</title>
    <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
            integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
            crossorigin="anonymous"
    />
    <link rel="stylesheet" href="resources/css/main.css">
</head>
<body>
<header>
    <nav>
        <ul>
            <li><a href="/objects">Home</a></li>
            <li><a href="/settings">Settings</a></li>
            <li><a href="/logout">Logout</a></li>
        </ul>
    </nav>
</header>
<main>
    <h2> Benvingut, ${username} !</h2>

    <div>
        <form action="/objects" method="post">
            <label for="name">Nom del Bucket</label>
            <input type="text" name="name">
            <input type="hidden" name="csrftoken" value="${csrftoken}">
            <button type="submit">Crear Bucket</button>

        </form>
    </div>

    <div>

        <ul>
            <c:forEach items="${buckets}" var="b">
            <li>
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
