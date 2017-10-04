<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="style.xsl"?>
<%@ page contentType="text/xml;charset=UTF-8" language="java" %>
<page title="Account">
<%@include file="navigation.jsp"%>
    <%
        if(user == null){
    %>
    <result type="simple">
        <content>Please log in first</content>
    </result>
    <%
        }
        else {
    %>
    <account_info user_tyoe="<%=user.getType()%>">
        <name><%=user.getName()%></name>
        <email><%=user.getEmail()%></email>
        <date_of_birth><%=user.getDateOfBirth()%></date_of_birth>
        <%
            if(!user.isStudent()){
        %>
        <speciality><%=user.getSpeciality()%></speciality>
        <%
            }
        %>
    </account_info>
    <%
        }
    %>
</page>