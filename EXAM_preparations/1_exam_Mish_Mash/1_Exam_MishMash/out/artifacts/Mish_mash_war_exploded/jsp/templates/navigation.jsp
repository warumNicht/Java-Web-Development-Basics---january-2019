<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<nav class="navbar navbar-expand-lg navbar-dark bg-mishmash">
    <a class="navbar-brand nav-link-white" href="/">Mish Mash</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse d-flex justify-content-between" id="navbarNav">
        <ul class="navbar-nav">
            <%String username = (String) request.getSession().getAttribute("username");
            String admin = (String) request.getSession().getAttribute("admin");
                System.out.println();
            %>

            <%
                if (username == null) {
            %>

            <li class="nav-item">
                <a class="nav-link nav-link-white active" href="/">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link nav-link-white active" href="/login">Login</a>
            </li>
            <li class="nav-item">
                <a class="nav-link nav-link-white active" href="/register">Register</a>
            </li>
            <%
            } else {
            %>
            <li class="nav-item">
                <a class="nav-link nav-link-white active" href="/home">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link nav-link-white active" href="/channels/followed">My Channels</a>
            </li>

            <%if(admin!=null){
                %>
            <li class="nav-item">
                <a class="nav-link nav-link-white active" href="/channels/create">Create Channel</a>
            </li>

            <%
            }%>

            <li class="nav-item">
                <a class="nav-link nav-link-white active" href="/logout">Logout</a>
            </li>

            <%
                }%>


        </ul>
    </div>
</nav>
</html>
