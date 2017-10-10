<%@ page contentType="text/xml;charset=UTF-8" language="java" %><%--
--%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="style.xsl"?>
<%@page import="model.User" %>
<%@page import="dao.UserDAOImpl" %>
<%@ page import="static model.User.*" %>
<%@ page import="static dao.UserDAOImpl.WEB_INF_TUTORS_XML" %>
<%@ page import="static dao.UserDAOImpl.WEB_INF_STUDENTS_XML" %>
<%@ page import="dao.UserDAO" %>
<%@ page import="util.StringUtil" %>


<page title="Login">

    <%
        String password = request.getParameter(PASSWORD);
        String email = request.getParameter(EMAIL);
        String type = request.getParameter(TYPE);
        String filePath;
        String[] ids = {EMAIL, PASSWORD, TYPE};
        String[] names = {"Email", "Password", "Log in as"};
        String[] types = {"text", "password", "select"};
        String[] errors = {"", "", ""};
        if (!StringUtil.isEmpty(type)) {
            if (type.equals("0")) {
                type = STUDENT;
                filePath = application.getRealPath(WEB_INF_STUDENTS_XML);
            } else {
                type = TUTOR;
                filePath = application.getRealPath(WEB_INF_TUTORS_XML);
            }
            UserDAO userDAO = new UserDAOImpl(filePath);
            User result = userDAO.login(email, password);
            boolean isRegistered = userDAO.isRegistered(email);
            if (result != null) {
                session.setAttribute(USER, result);
                response.sendRedirect("main.jsp");
            } else {
                if (!isRegistered)
                    errors[0] = "The email address \' " + email + "\' is not registered for " + type + ".";
                if (isRegistered) errors[1] = "Please check your password carefully.";

            }
        }
    %>
    <%@ include file="navigation.jsp" %>
    <display>
        <form link="login.jsp">
            <%
                for (int i = 0; i < ids.length; i++) { %>
            <input_row id="<%=ids[i]%>" name="<%=names[i]%>" type="<%=types[i]%>" error="<%=errors[i]%>"/>
            <%
                }
            %>
            <input_row id="progress" type="submit" value="Confirm"/>
        </form>
    </display>

</page>