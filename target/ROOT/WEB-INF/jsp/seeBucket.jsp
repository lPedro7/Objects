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
</head>
<body>
<header>
    <nav>
        <ul>
            <li><a href="/private/objects">Home</a></li>
            <li><a href="/private/settings">Settings</a></li>
            <li><a href="/logout">Logout</a></li>
        </ul>
    </nav>
</header>
<main>

    <h2>${uri}</h2>

    <div>

        <form action="/private/objects/${bucket}" method="post" enctype="multipart/form-data">
            <label for="name">Nom de l'objecte</label>
            <input type="text" name="name">

            <label for="file">Select a file to upload</label>
            <input type="file" name="file"/>
            <input type="hidden" name="csrftoken" value="${csrftoken}">

            <button type="submit">Crear Objecte</button>

        </form>

    </div>

    <div>
        <form action="/private/objects/${bucket}/eliminar" method="post">
            <input type="hidden" name="csrftoken" value="${csrftoken}">
            <button type="submit">Eliminar Bucket</button>
        </form>
    </div>

    <div>

        <ul>
            <c:forEach items="${nom}" var="obj">
                <c:set var="letter" value="${obj.charAt(0).toString()}" />

                <c:if test="${letter=='/'}">
                    <li><a href="/private/objects/${bucket}${obj}">${obj}</a></li>
                </c:if>
                <c:if test="${letter!='/'}">
                    <li><a href="/private/objects/${bucket}/${obj}">${obj}</a></li>
                </c:if>
            </c:forEach>

        </ul>
    </div>

</main>

</body>
</html>
