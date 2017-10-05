<%@ page contentType="text/xml;charset=UTF-8" language="java" %><%--
--%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="style.xsl"?>
<page title="Logout">
    <%
        session.invalidate();
    %>
    <%@include file="navigation.jsp" %>
    <result type="simple">
        <content>You logged out successfully.</content>
    </result>
</page>
