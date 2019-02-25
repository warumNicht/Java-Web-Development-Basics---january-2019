<%@ page import="metube.domain.models.binding.TubeAllBindingModel" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<div class="container">
    <main>
        <div class="jumbotron">

            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <h1>There are no Tubes</h1>
                </div>
            </div>
            <hr>

            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <a href="/tubes/create">Create some</a>
                </div>
            </div>
            <hr>

        </div>
    </main>
    <footer>
        <c:import url="templates/footer.jsp"/>
    </footer>
</div>
</body>
</html>
