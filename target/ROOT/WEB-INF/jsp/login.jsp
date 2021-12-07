<%-- Created by IntelliJ IDEA. User: pedro Date: 19/11/2021 Time: 15:49 To
change this template use File | Settings | File Templates. --%>
<%@ page
        isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
           prefix="c" %>
<%@ page contentType="text/html;charset=ISO-8859-1" language="java" %>
<html>
<head>
    <title>Login</title>
    <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
            integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
            crossorigin="anonymous"
    />
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/login.css">
</head>
<body>


<div id="login">
    <div class="container">
        <div
                id="login-row"
                class="row justify-content-center align-items-center"
        >
            <div id="login-column" class="col-md-6">
                <div id="login-box" class="col-md-12">
                    <form id="login-form" class="form" action="/login" method="post">
                        <h3 class="text-center text-info">Login</h3>
                        <div class="form-group">
                            <label for="username" class="text-info">Username:</label
                            ><br/>
                            <input
                                    type="text"
                                    name="username"
                                    id="username"
                                    class="form-control"
                            />
                        </div>
                        <div class="form-group">
                            <label for="password" class="text-info">Password:</label
                            ><br/>
                            <input
                                    type="password"
                                    name="password"
                                    id="password"
                                    class="form-control"
                            />
                        </div>
                        <div class="form-group">
                            <label for="remember-me" class="text-info"
                            ><span>Remember me</span> <span
                            ><input
                                    id="remember-me"
                                    name="remember-me"
                                    type="checkbox"/></span></label
                            ><br/>
                            <input
                                    type="submit"
                                    name="submit"
                                    class="btn btn-info btn-md"
                                    value="submit"
                            />
                        </div>
                        <input type="hidden" name="csrftoken" value="${csrftoken}"/>

                        <div id="register-link" class="text-right">
                            <a href="/signup" class="text-info">Register here</a>
                        </div>
                    </form>
                    <c:if test="${not empty message}"> ${message} </c:if>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>
