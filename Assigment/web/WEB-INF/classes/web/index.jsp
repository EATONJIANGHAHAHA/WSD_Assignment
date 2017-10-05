<%@ page contentType="text/xml;charset=UTF-8" language="java" %><%--
--%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="style.xsl"?>
<page title="Index">

    <%
        if(request.getQueryString()!=null && request.getQueryString().equals("logout")){
            session.invalidate();
    %>
    <%@include file="navigation.jsp" %>
    <result type="simple">
        <content>You logged out successfully.</content>
    </result>
    <%
        }
        else {
    %>
    <%@include file="navigation.jsp"%>
    <%
        }
    %>
</page>
