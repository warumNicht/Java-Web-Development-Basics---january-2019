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

            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <h1>All Tubes</h1>
                </div>
            </div>
            <hr>

            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <h3>Check our tubes below.</h3>
                </div>
            </div>
            <hr>


            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">

                    <%
                        List<TubeAllBindingModel> allTubes =
                                (List<TubeAllBindingModel>) request.getAttribute("allTubesBindingModel");
                    %>
                    <ul>
                        <%
                            for (TubeAllBindingModel tube : allTubes) {
                                String name = tube.getName();
                                String encodedName = URLEncoder.encode(name, "utf-8");
                        %>
                        <li>
                            <a href="<%="/tubes/details?name="+encodedName%>"><%=name%></a>
                        </li>

                        <%
                            }%>

                    </ul>

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
