<%@ page import="metube.domain.models.view.UserHomeTubesViewModel" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>

<div class="container-fluid">
    <header>
        <c:import url="templates/navbar.jsp"/>
    </header>
    <main>
        <hr class="my-3"/>
        <div class="jumbotron">
            <p class="h1 display-3">Not found!</p>
            <p class="h3">The tube does not exists</p>
            <hr class="my-3">
            <a href="/" class="btn btn-info" role="button">Go to index</a>
        </div>
    </main>
    <footer>
        <c:import url="templates/footer.jsp"/>
    </footer>
</div>

</body>
</html>
