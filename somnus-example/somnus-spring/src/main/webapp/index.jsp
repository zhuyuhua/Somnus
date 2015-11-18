<html>
<body>
<h2>Hello World!</h2>
<%
request.getRequestDispatcher("/WEB-INF/views/login/login.jsp").forward(request,response); 
// request.getRequestDispatcher("/WEB-INF/views/index/main.jsp").forward(request,response);

%>
</body>
</html>
