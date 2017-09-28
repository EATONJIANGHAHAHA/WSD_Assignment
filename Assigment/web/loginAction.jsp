
<%@ page import="static model.User.*" %>
<%@ page import="static application.UserApplication.*" %>
<%@ page import="application.UserApplication" %>
<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: might
  Date: 26/09/2017
  Time: 11:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LoginAction</title>
</head>
<body>
    <%

        String password = request.getParameter(PASSWORD);
        String email = request.getParameter(EMAIL);
        String filePath;
        try {
            if (request.getParameter(TYPE).equals(STUDENT)) {
                filePath = application.getRealPath(WEB_INF_STUDENTS_XML);
            }
            else {
                filePath = application.getRealPath(WEB_INF_TUTORS_XML);
            }
            UserApplication userApp = new UserApplication();
            userApp.setFilePath(filePath);
            User user = userApp.getItems().login(email, password);
            if (user != null) {
                session.setAttribute(TUTOR, user);
    %>
    <p>Login successful, click <a href="main.jsp">here</a> to go to the main page</p>
    <%
            }
            else {
    %>
    <p>Invalid information. Click here <a href = "login.jsp">here</a>to try again</p>
    <p>Click <a href="register.jsp">here</a> to register</p>
    <%
            }
        }
        catch (NullPointerException e){
            e.printStackTrace();


    %>
    <p>The information that you entered is incomplete. Click <a href="login.jsp">here</a> to try again.</p>
    <%
        }
    %>
</body>
</html>
