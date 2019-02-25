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
        <h1 class="text-center text-mishmash">Login</h1>
        <hr class="bg-mishmash w-50 hr-2"/>
        <form class="mx-auto w-50" method="post" action="/login">
            <div class="form-group">
                <label for="username" class="text-mishmash">Username</label>
                <input type="text" class="form-control" id="username" placeholder="Username..." name="username">
            </div>
            <div class="form-group">
                <label for="password" class="text-mishmash">Password</label>
                <input type="password" class="form-control" id="password" placeholder="Password..." name="password">
            </div>
            <div class="button-holder d-flex justify-content-center">
                <button type="submit" class="btn bg-mishmash text-white">Login</button>
            </div>
        </form>
    </main>
    <footer>
        <c:import url="templates/footer.jsp"/>
    </footer>
</div>
</body>
</html>
