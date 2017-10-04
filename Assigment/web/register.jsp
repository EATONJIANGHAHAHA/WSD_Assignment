<%@ page contentType="text/xml;charset=UTF-8" language="java" %><%--
--%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="style.xsl"?>
<%@ page import="static application.UserApplication.WEB_INF_USERS_XSD" %>
<%@ page import="static application.UserApplication.WEB_INF_TUTORS_XML" %>
<%@ page import="util.DigestUtil" %>
<%@ page import="application.UserApplication" %>
<%@ page import="static application.UserApplication.WEB_INF_STUDENTS_XML" %>
<%@ page import="javax.xml.bind.ValidationException" %>

<page title="Register">
    <%@ include file="navigation.jsp"%>
    <%
        String progress = request.getParameter("progress");

        if(progress == null || progress.equals("")){
    %>
    <form_table user_type="<%=url%>">
        <category>register</category>>
    </form_table>
    <%
        }
        else {
            try{
                String email = request.getParameter(EMAIL);
                String password = request.getParameter(PASSWORD);
                String name = request.getParameter(NAME);
                String dateOfBirth = request.getParameter(DATE_OF_BIRTH);
                String schemaPath = application.getRealPath(WEB_INF_USERS_XSD);
                String filePath;

                if(url.equals(TUTOR)) {
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
    <result type="error">
        <content>
            Register failed: The email address already exists.
        </content>
    </result>
    <%
                }
                else {
                    userApp.getItems().add(user);
                    userApp.save();
                    session.setAttribute(USER, user);

    %>
    <result type="success">
        <content><%=user.getName()%></content>
    </result>
    <%
                }
            }
            catch (NullPointerException e){
                e.printStackTrace();
    %>
    <result type="error">
        <content>
            Register failed: The information you entered may be incomplete.
        </content>
    </result>
    <%
        }
        catch (ValidationException e){
    %>
    <result type="error">
        <content>
            Register failed: you may entered invalid name, password or empty date.
        </content>
    </result>
    <%
        }
        }
    %>

</page>

