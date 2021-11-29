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
    <title>Informació del objecte</title>
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

    <h2>${object.uri}</h2>

        <div class="lastPath">
            <button><a href="${lastPath}">Enrere</a></button>
        </div>


    <div>
        Informació del objeto
    </div>

    <ul>
        <li>
            Bucket: ${object.bucketUri}
        </li>
        <li>
            Propietari : ${object.username_owner}
        </li>
        <li>
            Data de Creació : ${object.createdDate}
        </li>

        <li>
            <table>
                <tr>
                    <th>Versió</th>
                    <th>Nom Arxiu</th>
                    <th>Hash</th>
                    <th>Data de Creació</th>
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
                            <form action="?" method="post">
                                <input type="hidden" name="action" value="download">
                                <input type="hidden" name="csrftoken" value="${csrftoken}">
                                <input type="hidden" name="version" value="${version.version}">
                                <button type="submit">Descarregar</button>
                            </form>
                        </td>
                        <td>
                            <form action="?" method="post">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="csrftoken" value="${csrftoken}">
                                <input type="hidden" name="version" value="${version.version}">
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
