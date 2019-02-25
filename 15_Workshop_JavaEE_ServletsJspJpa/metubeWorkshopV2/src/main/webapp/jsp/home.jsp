<%@ page import="metube.domain.models.view.UserProfileViewModel" %>
<%@ page import="metube.domain.models.view.UserTubesViewModel" %>
<%@ page import="metube.domain.models.view.UserHomeViewModel" %>
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
        <%
            List<UserHomeTubesViewModel> approvedTubes =
                (List<UserHomeTubesViewModel>) request.getAttribute("homeApprovedTubes");
            %>
        <hr class="my-3"/>
        <div class="jumbotron">
            <h4 class="h1 display-3">Welcome, <%=request.getSession().getAttribute("username")%>
            </h4>
        </div>
        <div class="container">
            <div class="row">

                <%
                    for (UserHomeTubesViewModel tube : approvedTubes) {

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

        <hr class="my-3"/>
    </main>
    <footer>
        <c:import url="templates/footer.jsp"/>
    </footer>
</div>

</body>
</html>
