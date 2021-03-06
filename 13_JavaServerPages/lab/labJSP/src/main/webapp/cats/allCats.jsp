
<%@ page import="fdmc.domain.entities.Cat" %>
<%@ page import="java.util.Map" %>
<%@ page import="fdmc.domain.services.CatService" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>AllCats</title>
    <link rel="stylesheet" href="/style.css">
</head>
<body>

<section>
    <h1>All Cats</h1>
    <hr>

<%
    CatService catService = (CatService) application.getAttribute("catsDB");
    List<Cat> cats = catService.getAll();
    if (cats.isEmpty()) {
%>
<div>
    <h2>There are no Cats!</h2>
    <button type="button" onclick="location.href='/cats/create'">Create some!</button>
</div>
<%
}else {

    for (Cat cat : cats) {
        String catProfileUrl = String.format("/cats/profile?catName=%s", cat.getName());
%>
<div>
    <button class="catProfile" type="button" onclick="location.href='<%=catProfileUrl%>'"><%=cat.getName()%></button>
</div>

<%}}%>

    <button type="button" onclick="location.href='/'">Back to Home</button>
</section>

</body>
</html>
