<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="style.xsl"?>
<%@ page contentType="text/xml;charset=UTF-8" language="java" %>
<%@ page import="static application.BookingApplication.*" %>
<%@ page import="model.Booking" %>
<%
    String filePath = application.getRealPath(WEB_INF_BOOKINGS_XML);
    String schemaPath = application.getRealPath(WEB_IF_BOOKINGS_XSD);
%>
<jsp:useBean id="bookingApp" class="application.BookingApplication" scope="page">
    <jsp:setProperty name="bookingApp" property="filePath" value="<%=filePath%>"/>
    <jsp:setProperty name="bookingApp" property="schemaPath" value="<%=schemaPath%>"/>
</jsp:useBean>
<page title="Booking">
    <bookinglist>
        <%
            for(Booking booking: bookingApp.getItems().getList()){
        %>
        <booking>
            <id><%=booking.getId()%></id>
            <subject><%=booking.getSubject()%></subject>
            <status><%=booking.getStatus()%></status>
        </booking>
        <%
            }
        %>
    </bookinglist>
</page>

