<%@ page contentType="text/xml;charset=UTF-8" language="java" %>
<%@ page import="model.User" %>
<%@ page import="static model.User.*" %>
<%
    String url = request.getQueryString();
    if(session != null){
    User user = (User) session.getAttribute(USER);
%>
<navigation>
    <%
        if(user != null){
    %>
    <status name="<%=user.getName()%>">login</status>
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