<%@ page contentType="text/xml;charset=UTF-8" language="java" %><%--
--%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="style.xsl"?>
<%@page import="model.User"%>
<%@page import="dao.UserDAOImpl"%>
<%@ page import="static model.User.*" %>
<%@ page import="static dao.UserDAOImpl.WEB_INF_TUTORS_XML" %>
<%@ page import="static dao.UserDAOImpl.WEB_INF_STUDENTS_XML" %>


<page title="Login">
    <%
        String progress = request.getParameter("progress");

        if (progress == null || progress.equals("")) {
    %>
    <%@ include file="navigation.jsp"%>
    <display>
        <form link="login.jsp?<%=url%>">
            <input_row id="<%=EMAIL%>" name="Email" type="text"/>
            <input_row id="<%=PASSWORD%>" name="Password" type="password"/>
            <input_row id="progress" type="submit" value="Confirm"/>
        </form>
    </display>
    <%
    }
    else {
        String password = request.getParameter(PASSWORD);
        String email = request.getParameter(EMAIL);
        String filePath;
        try {
            if (request.getQueryString().equals(STUDENT)) {
                filePath = application.getRealPath(WEB_INF_STUDENTS_XML);
            }
            else {
                filePath = application.getRealPath(WEB_INF_TUTORS_XML);
            }
            UserDAOImpl userApp = new UserDAOImpl(filePath);
            User result = userApp.getItems().login(email, password);
            if (result != null) {
                session.setAttribute(USER, result);
    %>
    <%@ include file="navigation.jsp"%>
    <result type="success">
        <content>You now login as <%=user.getName() %>.</content>
    </result>
    <%
    }
    else{
    %>
    <%@ include file="navigation.jsp"%>
    <result type="error">
        <content>Wrong email or password.</content>
    </result>
    <%
        }
    }
    catch(NullPointerException e){
        e.printStackTrace();
    %>
    <%@ include file="navigation.jsp"%>
    <result type="error">
        <content>The information that you entered is incomplete.</content>
    </result>
    <%
            }
        }
    %>
</page>