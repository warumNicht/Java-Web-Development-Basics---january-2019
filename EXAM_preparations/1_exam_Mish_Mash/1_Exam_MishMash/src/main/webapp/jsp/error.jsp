<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="templates/header.jsp"/>
</head>
<body>
<div class="container-fluid">
    <header>
        <c:import url="templates/navigation.jsp"/>
    </header>
    <main class="mt-3">
        <div class="jumbotron mt-3 bg-mishmash">
            <h1 class="text-white">Errors:</h1>
            <hr class="bg-white hr-2"/>
            <h3 class="text-white"><a href="/login">Login</a> if you have an account.</h3>
            <h3 class="text-white"><a href="/register">Register</a> if you don't.</h3>
        </div>
    </main>
    <footer>
        <c:import url="templates/footer.jsp"/>
    </footer>
</div>
</body>
</html>
