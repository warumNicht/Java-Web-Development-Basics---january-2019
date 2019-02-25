<%@ page import="application.domain.models.viewModels.AllChannelsViewModel" %>
<%@ page import="java.util.List" %>
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
    <main class="mt-3">
        <h1 class="text-center text-mishmash">Following</h1>
        <hr class="hr-2 bg-mishmash"/>
        <table class="table w-75 mx-auto table-hover">

            <%
                List<AllChannelsViewModel> viewModels =
                        (List<AllChannelsViewModel>) request.getAttribute("all");
                int index = 0;
            %>
            <thead>
            <tr class="row">
                <th class="col-md-1">#</th>
                <th class="col-md-5">Channel</th>
                <th class="col-md-1">Type</th>
                <th class="col-md-2">Followers</th>
                <th class="col-md-2">Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (AllChannelsViewModel viewModel : viewModels) {
            %>

            <tr class="row">
                <td class="col-md-1"><%=++index%>
                </td>
                <td class="col-md-5"><%=viewModel.getName()%>
                </td>
                <td class="col-md-1"><%=viewModel.getType()%>
                </td>
                <td class="col-md-2"><%=viewModel.getFollowersCount()%>
                </td>
                <td class="col-md-2">

                    <a href="/channels/unfollow/<%=viewModel.getId()%>" class="btn bg-mishmash text-white d-inline-block"
                       role="button">Unfollow</a>

                    <a href="/channels/details/<%=viewModel.getId()%>" class="btn bg-mishmash text-white d-inline-block" role="button">Details</a>

                </td>
            </tr>
            <%
                }%>
            </tbody>
        </table>
    </main>
    <footer>
        <c:import url="templates/footer.jsp"/>
    </footer>
</div>
</body>
</html>
