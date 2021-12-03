<%--
  Created by IntelliJ IDEA.
  User: pedro
  Date: 19/11/2021
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=ISO-8859-1" language="java" %>
<html>
<head>
    <title>Registra't</title>
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
          <form id="login-form" class="form" action="/signup" method="post">
            <h3 class="text-center text-info">Signup</h3>
            <div class="form-group">
              <label for="username" class="text-info">Username:</label
              ><br />
              <input
                type="text"
                name="username"
                id="username"
                class="form-control"
              />
            </div>
            <div class="form-group">
              <label for="password" class="text-info">Password:</label
              ><br />
              <input
                type="password"
                name="password"
                id="password"
                class="form-control"
              />
            </div>
           
            <div class="form-group">
              <label for="firstName" class="text-info">First Name:</label
              ><br />
              <input
                type="text"
                name="firstName"
                id="firstName"
                class="form-control"
              />
            </div>

            <div class="form-group">
              <label for="lastName" class="text-info">Last Name:</label
              ><br />
              <input
                type="text"
                name="lastName"
                id="lastName"
                class="form-control"
              />
            </div>

            <div class="form-group">
              <label for="birthDate" class="text-info">Birth Date (dd-MM-yyyy):</label
              ><br />
              <input
                type="text"
                name="birthDate"
                id="birthDate"
                class="form-control"
              />
            </div>

            <div class="form-group">
              <label for="email" class="text-info">Email:</label
              ><br />
              <input
                type="text"
                name="email"
                id="email"
                class="form-control"
              />
            </div>

            <div class="form-group">
              <input
                type="submit"
                name="submit"
                class="btn btn-info btn-md"
                value="submit"
              />
            </div>
            <input type="hidden" name="csrftoken" value="${csrftoken}" />

            <div id="login-link" class="text-right">
              <a href="/login" class="text-info">Login here</a>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>

<c:if test="${not empty message}">
  ${message}
</c:if>

</body>
</html>
