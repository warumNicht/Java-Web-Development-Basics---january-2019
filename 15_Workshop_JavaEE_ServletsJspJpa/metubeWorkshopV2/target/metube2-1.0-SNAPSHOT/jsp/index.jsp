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
            <p class="h1 display-3">Welcome to MeTube&trade;!</p>
            <p class="h3">The simplest, easiest to use, most comfortable Multimedia Application.</p>
            <hr class="my-3">
            <p><a href="/login">Login</a> if you have an account or <a href="/register">Register</a> now and start tubing.</p>
        </div>
        <hr class="my-3"/>
        <div class="container">
            <div class="row">

                <%List<UserHomeTubesViewModel> tubes =
                        (List<UserHomeTubesViewModel>) request.getAttribute("approvedTubes");

                    for (UserHomeTubesViewModel tube : tubes) {

                %>
                <div class="col col-4">
                    <div class="embed-responsive embed-responsive-16by9">
                        <iframe class="embed-responsive-item" src="https://www.youtube.com/embed/<%=tube.getYouTubeId()%>" allowfullscreen frameborder="0"></iframe>
                    </div>
                    <div class="text-info text-center"><%=tube.getTitle()%></div>
                    <div class="text-info text-center"><%=tube.getAuthor()%></div>
                </div>

                <%  }%>

            </div>
        </div>
    </main>
    <footer>
        <c:import url="templates/footer.jsp"/>
    </footer>
</div>

</body>
</html>
