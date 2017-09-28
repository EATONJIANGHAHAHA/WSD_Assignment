<%@ page import="static model.User.*" %>
<%@ page import="util.DigestUtil" %>
<%@ page import="static application.UserApplication.WEB_INF_STUDENTS_XML" %>
<%@ page import="static application.UserApplication.WEB_INF_TUTORS_XML" %>
<%@ page import="application.UserApplication" %>
<%@ page import="model.User" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="static application.UserApplication.WEB_INF_USERS_XSD" %>
<%@ page import="javax.xml.bind.MarshalException" %>
<%@ page import="javax.xml.bind.ValidationException" %>
<%--
  Created by IntelliJ IDEA.
  User: might
  Date: 27/09/2017
  Time: 5:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>RegisterAction</title>
    </head>
    <body>

    <%
        try{
            String type = request.getParameter(TYPE);
            String email = request.getParameter(EMAIL);
            String password = request.getParameter(PASSWORD);
            String name = request.getParameter(NAME);
            String dateOfBirth = request.getParameter(DATE_OF_BIRTH);
            String schemaPath = application.getRealPath(WEB_INF_USERS_XSD);
            String filePath;
            User user;

            if(type.equals(TUTOR)) {
                String speciality = request.getParameter(SPECIALITY);
                filePath = application.getRealPath(WEB_INF_TUTORS_XML);
                user = new User(email, name, DigestUtil.encryptPWD(password),
                        dateOfBirth, speciality, AVAILABLE);
            }
            else {
                filePath = application.getRealPath(WEB_INF_STUDENTS_XML);
                user = new User(email, name, DigestUtil.encryptPWD(password),
                        dateOfBirth);
            }
            UserApplication userApp = new UserApplication(filePath, schemaPath);

            if(userApp.getItems().isRegistered(email)){
    %>
    <p>Register failed: The email address already exists.</p>
    <%
            }
            else {
                userApp.getItems().add(user);
                session.setAttribute(USER, user);
                userApp.save();
    %>
    <p>Succeed! You now login as <%=user.getName()%></p>
    <%
            }
        }
        catch (NullPointerException e){
            e.printStackTrace();
    %>
    <p>Register failed: Some of the entered information may be incomplete.</p>
    <%
        }
        catch (ValidationException e){
            e.printStackTrace();

    %>
    <p>Register failed: you may entered invalid name, password or empty date.</p>
    <%
        }
    %>



    </body>
</html>
