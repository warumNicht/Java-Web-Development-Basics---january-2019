<%@ page import="javax.validation.ConstraintViolation" %>
<%@ page import="java.util.Set" %>
<%@ page import="metube.domain.models.service.TubeServiceModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<div class="container">
    <main>
        <% Set<ConstraintViolation<TubeServiceModel>> violations =
                (Set<ConstraintViolation<TubeServiceModel>>) request.getAttribute("errors");%>
        <div class="jumbotron">
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <h1 style="color: red; font-weight: bold">Incorrect form data:</h1>
                </div>

            </div>
            <hr>
            <%
                for (ConstraintViolation<TubeServiceModel> violation : violations) {

            %>
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <p style="color: red"><%=violation.getMessage()%></p>
                </div>
            </div>
            <hr>

            <%
                }%>


            <div class="row">
                <div class="col col-md-6 d-flex justify-content-center">
                    <a href="/tubes/create" class="btn btn-primary">Back to Create Tube</a>
                </div>

                <div class="col col-md-6 d-flex justify-content-center">
                    <a href="/">Back to home</a>
                </div>

            </div>
        </div>
    </main>
    <footer>
        <c:import url="templates/footer.jsp"/>
    </footer>
</div>
</body>
</html>
