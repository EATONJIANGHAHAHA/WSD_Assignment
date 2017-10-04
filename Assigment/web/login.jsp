<%@ page contentType="text/xml;charset=UTF-8" language="java" %><%--
--%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="style.xsl"?>
<%@page import="model.User"%>
<%@page import="application.UserApplication"%>
<%@ page import="static model.User.*" %>
<%@ page import="static application.UserApplication.WEB_INF_TUTORS_XML" %>
<%@ page import="static application.UserApplication.WEB_INF_STUDENTS_XML" %>


<page title="Login">
    <%@ include file="navigation.jsp"%>
    <%
        String progress = request.getParameter("progress");

        if (progress == null || progress.equals("")) {
    %>
    <form_table user_type="<%=url%>">
        <category>login</category>>
    </form_table>
    <%
    }
    else {
        String password = request.getParameter(PASSWORD);
        String email = request.getParameter(EMAIL);
        String filePath;
        try {
            if (url.equals(STUDENT)) {
                filePath = application.getRealPath(WEB_INF_STUDENTS_XML);
            }
            else {
                filePath = application.getRealPath(WEB_INF_TUTORS_XML);
            }
            UserApplication userApp = new UserApplication(filePath);
            user = userApp.getItems().login(email, password);
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