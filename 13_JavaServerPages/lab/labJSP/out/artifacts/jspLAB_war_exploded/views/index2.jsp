<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello on <span><%=new Date()%></span>!</h1>
<h2>I am <%=request.getAttribute("name")%>
</h2>
<br>
<h2><%=request.getAttribute("currentDate")%></h2>
</body>
</html>
