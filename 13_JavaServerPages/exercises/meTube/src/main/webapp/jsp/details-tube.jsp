<%@ page import="metube.domain.models.view.TubeDetailsViewModel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<%TubeDetailsViewModel detailsViewModel = (TubeDetailsViewModel) request.getAttribute("tubeDetailsViewModel");%>
<div class="container">
    <main>
        <div class="jumbotron">
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <h1><%=detailsViewModel.getName()%></h1>
                </div>

            </div>
            <hr>
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <div style="white-space: pre-line"><%=detailsViewModel.getDescription()%></div>
                </div>
            </div>
            <hr>

            <div class="row">
                <div class="col col-md-6 d-flex justify-content-center">
                    <a href="<%=detailsViewModel.getYouTubeLink()%>">Link to video</a>
                </div>
                <div class="col col-md-6 d-flex justify-content-center">
                    <p><%=detailsViewModel.getUploader()%></p>
                </div>
            </div>
            <hr>

            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
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
