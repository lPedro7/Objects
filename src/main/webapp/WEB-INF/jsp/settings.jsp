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
    <title>Settings</title>
    <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
            integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
            crossorigin="anonymous"
    />
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/settings.css">
</head>
<body>
<header>


    <nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
        <div class="container">
            <div id="navbarSupportedContent">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/objects">Objects</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="/settings">Settings</a>
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

    <c:if test="${not empty message}">
        <div class="error">
                ${message}
        </div>
    </c:if>

    <div id="settings">
        <div class="container">
            <div
                    id="settings-row"
                    class="row justify-content-center align-items-center"
            >
                <div id="setting-column" class="col-md-6">
                    <div id="setting-box" class="col-md-12">
                        <form id="setting-form" class="form" action="?" method="post">
                            <h3 class="text-center text-info">Settings</h3>
                            <div class="form-group">
                                <label for="password" class="text-info">Nova contrasenya:</label
                                ><br/>
                                <input
                                        type="password"
                                        name="password"
                                        id="password"
                                        class="form-control"
                                />
                            </div>
                            <div class="form-group">
                                <label for="firstName" class="text-info">Nom :</label
                                ><br/>
                                <input
                                        type="text"
                                        name="firstName"
                                        id="firstName"
                                        class="form-control"
                                        value="${user.name}"
                                />
                            </div>
                            <div class="form-group">
                                <label for="lastName" class="text-info">Last Name:</label
                                ><br/>
                                <input
                                        type="text"
                                        name="lastName"
                                        id="lastName"
                                        class="form-control"
                                        value="${user.surname}"
                                        required
                                />
                            </div>

                            <div class="form-group">
                                <label for="birthDate" class="text-info">Data de Naixement (dd-MM-yyyy) :</label
                                ><br/>
                                <input
                                        type="text"
                                        name="birthDate"
                                        id="birthDate"
                                        class="form-control"
                                        value="${birthDate}"
                                        required
                                />
                            </div>

                            <div class="form-group">
                                <label for="birthDate" class="text-info">Email :</label
                                ><br/>
                                <input
                                        type="text"
                                        name="email"
                                        id="email"
                                        class="form-control"
                                        value="${user.email}"
                                        required
                                />
                            </div>

                            <div class="form-group">
                                <label for="confirmPassword" class="text-info">Confirma la contrasenya :</label
                                ><br/>
                                <input
                                        type="password"
                                        name="confirmPassword"
                                        id="confirmPassword"
                                        class="form-control"
                                        required
                                />
                            </div>
                            <input type="hidden" name="csrftoken" value="${csrftoken}"/>


                            <div class="form-group">
                                <input
                                        type="submit"
                                        name="submit"
                                        class="btn btn-info btn-md"
                                        value="Desa"
                                />
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>


        <div id="deleteUser">
            <div class="container">
                <div
                        id="deleteUser-row"
                        class="row justify-content-center align-items-center"
                >
                    <div id="deleteUser-column" class="col-md-6">
                        <div id="deleteUser-box" class="col-md-12">
                            <form id="deleteUser-form" class="form" action="/deleteUser" method="post">
                                <h3 class="text-center text-info">Elimina el teu compte</h3>
                                <div class="form-group">
                                    <label for="password" class="text-info">Per seguretat posa la contrasenya:</label
                                    ><br/>
                                    <input
                                            type="password"
                                            name="password"
                                            class="form-control"
                                            required
                                    />
                                </div>

                                <input type="hidden" name="csrftoken" value="${csrftoken}"/>


                                <div class="form-group">
                                    <input
                                            type="submit"
                                            name="submit"
                                            class="btn btn-info btn-md"
                                            value="Eliminar Compte"
                                    />
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
</body>
</html>
