<%@ page contentType="text/xml;charset=UTF-8" language="java" %><%--
--%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="style.xsl"?>
<%@page import="model.User"%>
<%@page import="application.UserApplication"%>
<%@ page import="static model.User.*" %>
<%@ page import="static application.UserApplication.WEB_INF_TUTORS_XML" %>
<%@ page import="static application.UserApplication.WEB_INF_STUDENTS_XML" %>
<%!
    public boolean isEmpty(String s) {
        return s == null || s.equals("");
    }
%>
<page title="Login">
    <%
        String userType = request.getQueryString();
        String password = request.getParameter(PASSWORD);
        String email = request.getParameter(EMAIL);
        if (isEmpty(email) && isEmpty(password)) {
    %>
    <form action="login.jsp?<%=userType%>" method="post">
        <table>
            <tr>
                <td>Email:</td>
                <td><input type="text" name="<%=EMAIL%>"/></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="<%=PASSWORD%>"/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Login"/></td>
            </tr>
        </table>
    </form>
    <%
    }
    else {
        String filePath;
        try {
            if (request.getParameter(TYPE).equals(STUDENT)) {
                filePath = application.getRealPath(WEB_INF_STUDENTS_XML);
            }
            else {
                filePath = application.getRealPath(WEB_INF_TUTORS_XML);
            }
            UserApplication userApp = new UserApplication(filePath);
            User user = userApp.getItems().login(email, password);
            if (user != null) {
                session.setAttribute(USER, user);
    %>
    <result type="success">
        <content><%=user.getName() %></content>
    </result>
    <%
    }
    else{
    %>
    <result type="error">
        <content>Wrong email or password</content>
    </result>
    <%
        }
    }
    catch(NullPointerException e){
        e.printStackTrace();
    %>
    <result type="error">
        <content>The information that you entered is incomplete.</content>
    </result>
    <%
            }
        }
    %>
</page>