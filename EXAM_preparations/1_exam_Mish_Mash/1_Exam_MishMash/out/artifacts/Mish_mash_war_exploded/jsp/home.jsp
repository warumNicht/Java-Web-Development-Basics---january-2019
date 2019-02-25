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
        <%String username = (String) request.getSession().getAttribute("username");
            String admin = (String) request.getSession().getAttribute("admin");
            if(admin!=null){
                username="Admin-"+username;
            }
        %>
        <h1 class="text-center text-mishmash">Welcome,<%=username%>!</h1>

        <c:import url="templates/your.jsp"/>
        <c:import url="templates/suggested.jsp"/>
        <c:import url="templates/seeOthers.jsp"/>
    </main>
    <footer>
        <c:import url="templates/footer.jsp"/>
    </footer>
</div>
</body>
</html>
