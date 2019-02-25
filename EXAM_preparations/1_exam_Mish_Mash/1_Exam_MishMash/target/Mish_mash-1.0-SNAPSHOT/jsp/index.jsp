<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="templates/header.jsp"/>
</head>
<body>
<div class="container-fluid">
    <header>
        <nav class="navbar navbar-expand-lg navbar-dark bg-mishmash">
            <a class="navbar-brand nav-link-white" href="/">Mish Mash</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse d-flex justify-content-between" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link nav-link-white active" href="/">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link nav-link-white active" href="/login">Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link nav-link-white active" href="/register">Register</a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <main>
        <div class="jumbotron mt-3 bg-mishmash">
            <h1 class="text-white">Welcome to Mish-Mash Media Channels</h1>
            <hr class="bg-white hr-2"/>
            <h3 class="text-white"><a href="/login">Login</a> if you have an account.</h3>
            <h3 class="text-white"><a href="/register">Register</a> if you don't.</h3>
        </div>
    </main>
    <footer>
        <div class="container-fluid bg-mishmash">
            <div class="text-white p-2 text-center">&copy; CopyRight Sanity Web Design Studios 2018. All rights reserved.
            </div>
        </div>
    </footer>
</div>
</body>
</html>
