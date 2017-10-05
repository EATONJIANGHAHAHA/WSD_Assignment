<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="style.xsl"?>
<%@ page contentType="text/xml;charset=UTF-8" language="java" %>
<%@ page import="static dao.BookingDAOImpl.*" %>
<%@ page import="model.Booking" %>
<%@ page import="jaxblist.Bookings" %>
<%
    String filePath = application.getRealPath(WEB_INF_BOOKINGS_XML);
    String schemaPath = application.getRealPath(WEB_IF_BOOKINGS_XSD);
%>
<jsp:useBean id="bookingDAO" class="dao.BookingDAOImpl" scope="page">
    <jsp:setProperty name="bookingDAO" property="filePath" value="<%=filePath%>"/>
    <jsp:setProperty name="bookingDAO" property="schemaPath" value="<%=schemaPath%>"/>
</jsp:useBean>
<page title="Booking">
    <%@include file="navigation.jsp"%>

        <%
            Bookings bookings = bookingDAO.searchByEmail(user.getEmail(), user.isStudent());

            if(bookings != null && bookings.getList() != null && bookings.getList().size() != 0){
                for(Booking booking: bookings.getList()){

        %>
    <bookinglist>
        <booking id="<%=booking.getId()%>">
            <user_type><%=user.getType()%></user_type>
            <student><%=booking.getStudentName()%></student>
            <student_email><%=booking.getStudentEmail()%></student_email>
            <tutor><%=booking.getTutorName()%></tutor>
            <tutor_email><%=booking.getTutorEmail()%></tutor_email>
            <subject><%=booking.getSubject()%></subject>
            <status><%=booking.getStatus()%></status>
        </booking>
        <%
            }
        %>
    </bookinglist>
    <%
        }
        else{
    %>
    <result type="simple">
        <content>No booking record for your account.</content>
    </result>
    <%
        }
    %>
</page>

