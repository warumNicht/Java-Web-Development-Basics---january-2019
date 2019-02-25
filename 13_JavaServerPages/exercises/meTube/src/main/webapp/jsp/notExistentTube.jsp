<%@ page import="metube.domain.models.binding.TubeAllBindingModel" %>
<%@ page import="java.util.List" %>
<%@ page import="java.net.URLEncoder" %>
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
            <%String name = request.getParameter("name");
                System.out.println();%>
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <h1>Tube with name: <strong><%=name%></strong> does not exists</h1>
                </div>
            </div>
            <hr>


            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <a href="/">Back to home</a>
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
