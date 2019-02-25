<%@ page import="metube.domain.models.view.UserProfileViewModel" %>
<%@ page import="metube.domain.models.view.UserTubesViewModel" %>
<%@ page import="metube.domain.models.view.UserHomeTubesViewModel" %>
<%@ page import="java.util.List" %>
<%@ page import="metube.domain.models.view.TubePendingView" %>
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
        <%List<TubePendingView> pendingTubes =
                (List<TubePendingView>) request.getAttribute("pending");
            int index=0;%>
        <hr class="my-2"/>
        <div class="text-center mt-3">
            <h4 class="text-info text-center">Pending tubes</h4>
        </div>
        <hr>

        <div class="container-fluid">
            <div class="row d-flex flex-column">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Title</th>
                        <th scope="col">Author</th>
                        <th scope="col">Uploader</th>
                        <th scope="col">Status</th>
                        <th scope="col">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        for (TubePendingView tube : pendingTubes) {
                    %>
                    <tr>
                        <td><%=++index%></td>
                        <td><%=tube.getTitle()%></td>
                        <td><%=tube.getAuthor()%></td>
                        <td><%=tube.getUploader()%></td>
                        <td>
                            <a href="/admin/tube/approve/<%=tube.getId()%>" class="btn btn-success" role="button">Approve</a>
                            <a href="/admin/tube/decline/<%=tube.getId()%>" class="btn btn-danger" role="button">Decline</a>
                        </td>
                        <td>
                            <a href="/tube/details/<%=tube.getId()%>">Details</a>
                        </td>
                    </tr>

                    <%
                        }%>

                    </tbody>
                </table>
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
