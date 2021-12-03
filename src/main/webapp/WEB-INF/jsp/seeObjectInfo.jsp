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
    <title>Informaci� del objecte</title>
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
                <li class="nav-item active"><a class="nav-link" href="/objects">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="/settings">Settings</a></li>
                <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
            </ul>
        </div>
    </nav>
</header>
<main>

    <h2>${object.uri}</h2>

        <div class="lastPath">
            <button><a href="${lastPath}">Enrere</a></button>
        </div>
        <div>
            <form action="/deleteObj/${object.version}" method="post">
                <input type="hidden" name="csrftoken" value="${csrftoken}">
                <button type="submit">Elimina l'objecte</button>
            </form>
        </div>

    <div>
        Informaci� del objeto
    </div>


    <ul>
        <li>
            Bucket: ${object.bucketUri}
        </li>
        <li>
            Propietari : ${object.username_owner}
        </li>
        <li>
            Data de Creaci� : ${object.createdDate}
        </li>

        <li>
            <table>
                <tr>
                    <th>Versi�</th>
                    <th>Nom Arxiu</th>
                    <th>Hash</th>
                    <th>Data de Creaci�</th>
                    <th>Propietari</th>
                    <th>Descarrega</th>
                    <th>Elimina</th>
                </tr>
                <c:forEach items="${versions}" var="version">
                    <tr>
                        <td>
                                ${version.getVersion()}
                        </td>
                        <td>
                                ${version.getName()}
                        </td>
                        <td>
                                ${version.getHash()}
                        </td>
                        <td>
                                ${version.getCreatedDate()}
                        </td>
                        <td>
                                ${version.getUsername_owner()}
                        </td>
                        <td>
                            <form action="/download/${version.id}" method="post">
                                <input type="hidden" name="csrftoken" value="${csrftoken}">
                                <button type="submit">Descarregar</button>
                            </form>
                        </td>
                        <td>
                            <form action="/delete/${version.id}" method="post">
                                <input type="hidden" name="csrftoken" value="${csrftoken}">
                                <button type="submit">Elimina</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>

        </li>

    </ul>

</main>

</body>
</html>
