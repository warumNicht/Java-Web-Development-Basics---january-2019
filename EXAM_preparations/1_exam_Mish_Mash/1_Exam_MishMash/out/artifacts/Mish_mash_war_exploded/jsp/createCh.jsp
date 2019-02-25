<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.stream.Collectors" %>
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
        <h1 class="text-center text-mishmash">Create Channel</h1>
        <hr class="bg-mishmash w-50 hr-2"/>
        <form class="mx-auto w-50" method="post" action="/channels/create">
            <div class="form-group">
                <label for="name" class="text-mishmash">Name</label>
                <input type="text" class="form-control" id="name" placeholder="Name..." name="name">
            </div>
            <div class="form-group">
                <label for="description" class="text-mishmash">Description</label>
                <textarea class="form-control" id="description" placeholder="Description..." name="description">

                </textarea>
            </div>

            <div class="form-group">
                <label for="tags" class="text-mishmash">Tags</label>
                <input type="text" class="form-control" id="tags" placeholder="Tags..." name="tags">
            </div>

            <h4 class="text-center text-mishmash">Channel Type</h4>

            <hr class="bg-mishmash w-25 hr-2"/>

            <div class="form-group" style="text-align: center">
                <%
                    List<String> radios =
                            Arrays.stream(((String) this.getServletConfig().getServletContext().getAttribute("radio"))
                                    .split(",\\s+"))
                            .collect(Collectors.toList());
                    for (String radio : radios) {
                        %>
                <label style="margin-right: 50px">
                    <input  type="radio" name="type" value="<%=radio%>"/><%=radio%>
                </label>

                <%
                    }
                %>

            </div>


            <hr class="bg-mishmash w-25 hr-2"/>

            <div class="button-holder mt-4 d-flex justify-content-center">
                <button type="submit" class="btn bg-mishmash text-white">Create</button>
            </div>
        </form>
    </main>
    <footer>
        <c:import url="templates/footer.jsp"/>
    </footer>
</div>
</body>
</html>
