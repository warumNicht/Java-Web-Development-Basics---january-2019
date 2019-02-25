<%@ page import="metube.domain.models.view.UserProfileViewModel" %>
<%@ page import="metube.domain.models.view.UserTubesViewModel" %>
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
        <%UserProfileViewModel user =
                (UserProfileViewModel) request.getAttribute("userProfileViewModel");
        int index=0;%>
        <hr class="my-2"/>
        <div class="text-center mt-3">
            <h4 class="text-info text-center">@<%=user.getUsername()%></h4>
            <h4 class="text-info text-center">(<%=user.getEmail()%>)</h4>
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
                        <th scope="col">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        for (UserTubesViewModel tube : user.getTubes()) {
                            %>
                    <tr>
                        <td><%=++index%></td>
                        <td><%=tube.getTitle()%></td>
                        <td><%=tube.getAuthor()%></td>
                        <td>
                            <a href="/tube/details/<%=tube.getId()%>">Details</a>
                        </td>
                    </tr>

                    <%
                        }%>
                    ${myTubes}
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
