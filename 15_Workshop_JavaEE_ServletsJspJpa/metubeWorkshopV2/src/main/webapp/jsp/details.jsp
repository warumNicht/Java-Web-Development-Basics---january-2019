<%@ page import="metube.domain.models.view.UserProfileViewModel" %>
<%@ page import="metube.domain.models.view.UserTubesViewModel" %>
<%@ page import="metube.domain.models.view.TubeDetailsViewModel" %>
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
        <%TubeDetailsViewModel detailView = (TubeDetailsViewModel) request.getAttribute("detailViewModel");%>
        <hr class="my-2">
        <div class="container-fluid">
            <h2 class="text-center"><%=detailView.getTitle()%></h2>
            <div class="row">
                <div class="col-md-6 my-5">
                    <div class="embed-responsive embed-responsive-16by9">
                        <iframe class="embed-responsive-item" src="https://www.youtube.com/embed/<%=detailView.getYouTubeId()%>" allowfullscreen frameborder="0"></iframe>
                    </div>
                </div>
                <div class="col-md-6 my-5">
                    <h1 class="text-center text-info"><%=detailView.getAuthor()%></h1>
                    <h3 class="text-center text-info"><%=detailView.getViews()%> Views</h3>
                    <div class="h5 my-5 text-center">
                        <div style="white-space: pre-wrap; text-align: left">
                            <%=detailView.getDescription()%>
                        </div>
                    </div>

                </div>
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
