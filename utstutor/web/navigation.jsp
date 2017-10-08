<%@ page contentType="text/xml;charset=UTF-8" language="java" %>
<%@ page import="model.User" %>
<%@ page import="static model.User.*" %>
<navigation>
<%
    String url = request.getQueryString();
    User user = null;
    HttpSession httpSession = request.getSession(false);
    if(httpSession != null){
     user = (User) httpSession.getAttribute(USER);
%>
    <%
        if(user != null){
    %>
    <status>login</status>
    <user_name><%=user.getName()%></user_name>
    <%
        }
        else {
    %>
    <status>logout</status>
<%
        }
    }
    else {
%>
    <status>logout</status>
    <%
        }
    %>
</navigation>