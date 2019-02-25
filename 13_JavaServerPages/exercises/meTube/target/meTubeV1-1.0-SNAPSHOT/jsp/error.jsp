<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<%int status = response.getStatus();%>
<div class="jumbotron">
    <div class="row">
        <div class="col col-md-12 d-flex justify-content-center">
            <h1>Something went wrong</h1>
        </div>

    </div>
    <hr>
    <div class="row">
        <div class="col col-md-12 d-flex justify-content-center">
            <p>
                <img src="https://http.cat/<%=status%>" alt="kitten">
            </p>
        </div>
    </div>
    <hr>

    <div class="row">
        <div class="col col-md-12 d-flex justify-content-center">
            <a href="/">Back to home</a>
        </div>
    </div>
</div>
</body>
</html>
