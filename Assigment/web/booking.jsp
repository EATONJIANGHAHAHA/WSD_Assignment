<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="style.xsl"?>
<%@ page contentType="text/xml;charset=UTF-8" language="java" %>
<%@ page import="static dao.BookingDAOImpl.*" %>
<%@ page import="model.Booking" %>
<%@ page import="jaxblist.Bookings" %>
<%@ page import="static model.Booking.ACTIVE" %>
<%@ page import="util.StringUtil" %>
<%@ page import="java.lang.String" %>
<%@ page import="static dao.UserDAOImpl.WEB_INF_TUTORS_XML" %>
<%@ page import="dao.UserDAO" %>
<%@ page import="dao.UserDAOImpl" %>
<%
    String bookingFilePath = application.getRealPath(WEB_INF_BOOKINGS_XML);
%>
<jsp:useBean id="bookingDAO" class="dao.BookingDAOImpl" scope="page">
    <jsp:setProperty name="bookingDAO" property="filePath" value="<%=bookingFilePath%>"/>
</jsp:useBean>
<page title="Booking">
    <%!
        public static final String TUTOR_EMAIL = "tutor_email";
    %>
    <%@include file="navigation.jsp" %>

    <%
        if (user == null) {
    %>
    <result type="error">
        <content>Please login first.</content>
    </result>
    <%
    } else {
        if (user.isStudent()) {
            UserDAO tutorDao = new UserDAOImpl(application.getRealPath(WEB_INF_TUTORS_XML));
    %>
    <display>
        <form link="booking.jsp">
            <%

                try {
                    String tutorEmail = StringUtil.readQueryString(url);
            %>
            <input_row id="<%=TUTOR_EMAIL%>" type="text" name="Tutor email" value="<%=tutorEmail%>"/>
            <%
                } catch (Exception e) {
                    e.printStackTrace();
                }

            %>
            <input_row id="progress" type="submit" value="Confirm"/>
        </form>
    </display>
    <%
        if (request.getParameter("progress") != null && !(request.getParameter("progress").equals(""))) {
            String tutorEmail = request.getParameter(TUTOR_EMAIL);
            try {
                User tutor = tutorDao.searchByEmail(tutorEmail);
                if (tutor.isAvailable()) {
                    Booking booking = new Booking(user.getName(), user.getEmail(), tutor.getName(), tutorEmail, tutor.getSpeciality());
                    bookingDAO.create(booking);
    %>
    <result type="success">
        <content>You have created a new booking.</content>
    </result>
    <%
    } else {
    %>
    <result type="error">
        <content>The tutor is currently unavailable.</content>
    </result>
    <%
        }
    } catch (NullPointerException e) {
        e.printStackTrace();
    %>
    <result type="error">
        <content>No such tutor. Please check your input and try again.</content>
    </result>
    <%
                }
            }

        }

        Bookings bookings = bookingDAO.searchByEmail(user.getEmail(), user.isStudent());

        if (bookings != null && bookings.getList() != null && bookings.getList().size() != 0) {
    %>
    <display>
        <header>
            <head value="Id"/>
            <head value="Student name"/>
            <head value="Student email"/>
            <head value="Tutor name"/>
            <head value="Tutor email"/>
            <head value="Subject"/>
            <head value="Status"/>
            <head/>
        </header>
        <body>
            <%
                for (Booking booking : bookings.getList()) {

            %>
            <output_row>
                <output type="text" value="<%=booking.getId()%>"/>
                <output type="text" value="<%=booking.getStudentName()%>"/>
                <output type="text" value="<%=booking.getStudentEmail()%>"/>
                <output type="text" value="<%=booking.getTutorName()%>"/>
                <output type="text" value="<%=booking.getTutorEmail()%>"/>
                <output type="text" value="<%=booking.getSubject()%>"/>
                <%
                    if (booking.getStatus().equals(ACTIVE)) {
                        if (user.isStudent()) {
                %>
                <output type="text" value="<%=booking.getStatus()%>"/>
                <form link="booking.jsp?bookingId=<%=booking.getId()%>">
                    <input type="submit" value="Cancel" name="button"/>
                </form>
                <%
                } else {
                %>
                <form link="booking.jsp?bookingId=<%=booking.getId()%>">
                    <input type="select" name="status"/>
                    <input type="submit" value="Change" name="button"/>
                </form>
                <%
                    }
                } else {
                %>
                <output type="text" value="<%=booking.getStatus()%>"/>
                <output/>
                <%
                    }
                %>
            </output_row>
            <%
                }
            %>
        </body>
    </display>
    <%
    } else {
    %>
    <result type="error">
        <content>No booking record for your account.</content>
    </result>
    <%
            }
        }
    %>
</page>

