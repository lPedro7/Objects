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
    <h2> Benvingut, ${username} !</h2>

    <div>
        <button><a href="/private/objects/newBucket">Crear nou Bucket</a></button>
    </div>

    <div>

        <ul>
            <c:forEach items="${buckets}" var="b">
            <li>
                <button>
                    <a href="/private/objects/${b.uri}">
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
