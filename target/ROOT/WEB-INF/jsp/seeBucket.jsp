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

    <button><a href="/private/objects/${uri}/newObject">Nou Objecte</a></button>

    </div>

    <div>
        <table>
            <tr>
                <th>
                    Nom
                </th>
                <th>
                    Tipus
                </th>
                <th>
                    Versió
                </th>
                <th>
                    Ultima Actualització
                </th>
                <th>
                    Data de creació
                </th>
            </tr>
            <c:forEach items="${objs}" var="ob">
                <tr>
                    <td>
                        ${ob.uri}
                    </td>
                    <td>
                        ${ob.contentType}
                    </td>
                    <td>
                        ${ob.version}
                    </td>
                    <td>
                        ${ob.lastModified}
                    </td>
                    <td>
                        ${ob.createdDate}
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

</main>

</body>
</html>
