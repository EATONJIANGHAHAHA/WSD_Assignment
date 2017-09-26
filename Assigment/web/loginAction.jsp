<%@ page import="static application.BookingApplication.BOOKING_FILE_PATH" %>
<%@ page import="static model.User.STUDENT" %>
<%@ page import="static application.StudentApplication.STUDENT_FILE_PATH" %>
<%@ page import="application.StudentApplication" %>
<%@ page import="model.Student" %>
<%@ page import="application.TutorApplication" %>
<%@ page import="static application.TutorApplication.TUTOR_FILE_PATH" %>
<%@ page import="model.Tutor" %>
<%@ page import="static model.User.TUTOR" %>
<%@ page import="static model.User.TYPE" %><%--
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
        boolean isSuccess = false;
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        try {
            if (request.getParameter("type").equals(STUDENT)) {
                String filePath = application.getRealPath(STUDENT_FILE_PATH);
                out.print("<p>filepath = " + filePath+"</p>");
                StudentApplication studentApp = new StudentApplication();
                studentApp.setFilePath(filePath);
                Student student = studentApp.getItems().login(email, password);
                if (student != null) {
                    session.setAttribute(TYPE, STUDENT);
                    session.setAttribute(STUDENT, student);
                    isSuccess = true;
                }
            } else {
                String filePath = application.getRealPath(TUTOR_FILE_PATH);
                TutorApplication tutorApp = new TutorApplication();
                tutorApp.setFilePath(filePath);
                Tutor tutor = tutorApp.getItems().login(email, password);
                if (tutor != null) {
                    session.setAttribute(TYPE, TUTOR);
                    session.setAttribute(TUTOR, tutor);
                    isSuccess = true;
                }
            }
        }
        catch (NullPointerException e){
            e.printStackTrace();


    %>
    <p>Please choose user type.</p>
    <%
        }
        if(isSuccess){
    %>
    <p>Login successful, click <a href="main.jsp">here</a> to go to the main page</p>
    <%
        }
        else{
    %>
    <p>Invalid information. Click here <a href = "login.jsp">here</a>to try again</p>
    <p>Click <a href="register.jsp">here</a> to register</p>
    <%
        }
    %>
</body>
</html>
