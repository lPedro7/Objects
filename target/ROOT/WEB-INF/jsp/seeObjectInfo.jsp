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

    <h2>${object.uri}</h2>

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
            Content Type : ${object.contentType}
        </li>
        <li>
            Darrera Modificació : ${object.lastModified}
        </li>
        <li>
            Data de Creació : ${object.createdDate}
        </li>

        <li>
            <table>
                <tr>
                    <th>Versió</th>
                    <th>Hash</th>
                    <th>Data de Creació</th>
                    <th>Propietari</th>
                </tr>
                <c:forEach items="${versions}" var="version">
                    <tr>
                        <td>
                                ${version.getVersion()}
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
                    </tr>
                </c:forEach>
            </table>

        </li>

    </ul>

</main>

</body>
</html>
