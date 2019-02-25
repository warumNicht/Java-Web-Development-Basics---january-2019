<%@ page import="application.domain.models.viewModels.DetailChannelsViewModel" %>
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
    <main class="mt-3 mb-5">
        <%DetailChannelsViewModel channel= (DetailChannelsViewModel) request.getAttribute("details");%>
        <hr class="hr-2 bg-mishmash"/>
        <div class="channel-main-data-holder w-75 mx-auto d-flex justify-content-between">
            <h3 class="text-center text-mishmash">Channel: <%=channel.getName()%></h3>
            <h3 class="text-center text-mishmash">Type: <%=channel.getType()%></h3>
            <h3 class="text-center text-mishmash">Followers: <%=channel.getFollowersCount()%></h3>
        </div>
        <hr class="hr-2 bg-mishmash"/>
        <h3 class="text-center text-mishmash">Tags</h3>
        <div class="channel-tags-holder w-75 mx-auto">
            <h6 class="text-center text-mishmash"><%=channel.getTags()%></h6>
        </div>
        <hr class="hr-2 bg-mishmash"/>
        <h3 class="text-center text-mishmash">Description</h3>
        <div class="product-description-holder">
            <p class="text-center text-mishmash mt-4">
                <%=channel.getDescription()%>
            </p>
        </div>
        <hr class="hr-2 bg-mishmash"/>
        <%String referer = request.getHeader("Referer");%>
        <div class="button-holder d-flex justify-content-center">
            <a href="<%=referer%>" class="btn bg-mishmash text-white d-inline-block" role="button">Back</a>
        </div>
    </main>
    <footer>
        <c:import url="templates/footer.jsp"/>
    </footer>
</div>
</body>
</html>
