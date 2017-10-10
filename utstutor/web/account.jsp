<%@ page contentType="text/xml;charset=UTF-8" language="java" %><%--
--%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="style.xsl"?>
<%@ page import="dao.BookingDAOImpl" %>
<%@ page import="jaxblist.Bookings" %>
<%@ page import="model.Booking" %>
<%@ page import="static dao.BookingDAOImpl.*" %>
<%@ page import="dao.UserDAOImpl" %>
<%@ page import="static dao.UserDAOImpl.*" %>
<%@ page import="util.EncryptUtil" %>
<%@ page import="util.StringUtil" %>
<%@ page import="dao.UserDAO" %>
<%@ page import="dao.BookingDAO" %>
<%@ page import="exception.DataValidationException" %>
<page title="Account">
    <%
        User currentUser = (User)session.getAttribute(USER);
        if (currentUser == null) {
    %>
    <%@include file="navigation.jsp" %>
    <result type="error">
        <content>Please log in first</content>
    </result>
    <%
    } else {
        UserDAO userDAO;
        if (currentUser.isStudent()) userDAO = new UserDAOImpl(application.getRealPath(WEB_INF_STUDENTS_XML),
                application.getRealPath(WEB_INF_USERS_XSD));
        else userDAO = new UserDAOImpl(application.getRealPath(WEB_INF_TUTORS_XML), application.getRealPath(
                WEB_INF_USERS_XSD));
        BookingDAO bookingDAO = new BookingDAOImpl(application.getRealPath(WEB_INF_BOOKINGS_XML),
                application.getRealPath(WEB_IF_BOOKINGS_XSD));
        Bookings bookings = bookingDAO.searchByEmail(currentUser.getEmail(), currentUser.isStudent());
        String action = request.getParameter("button");
        if (action == null || action.equals("") || action.equals("Cancel")) {
    %>

    <%@include file="navigation.jsp" %>
    <display>
        <output_row name="Name" value="<%=currentUser.getName()%>"/>
        <output_row name="Email" value="<%=currentUser.getEmail()%>"/>
        <output_row name="Password" value="****************"/>
        <output_row name="Date of birth" value="<%=currentUser.getDateOfBirth()%>"/>
        <%
            if (!currentUser.isStudent()) {
        %>
        <output_row name="Speciality" value="<%=currentUser.getSpeciality()%>"/>
        <%
            }
        %>
        <output_row>
            <form link="account.jsp">
                <input type="submit" name="button" value="Edit account"/>
                <input type="submit" name="button" value="Cancel account"/>
            </form>
        </output_row>
    </display>
    <%
    } else if (action.equals("Edit account")) {
    %>

    <%@include file="navigation.jsp" %>
    <display>
        <form link="account.jsp">
            <output_row name="Email" value="<%=currentUser.getEmail()%>"/>
            <input_row id="<%=PASSWORD%>" name="Password" type="password" value="<%=currentUser.getPassword()%>"/>
            <input_row id="<%=NAME%>" name="Name" type="text" value="<%=currentUser.getName()%>"/>
            <input_row id="<%=DATE_OF_BIRTH%>" name="Date of birth" type="date" value="<%=currentUser.getDateOfBirth()%>"/>
            <% if (!currentUser.isStudent()) {%>
            <input_row id="<%=SPECIALITY%>" name="Speciality" type="select" value="<%=currentUser.getSpeciality()%>"/>
            <%
                }
            %>
            <output_row>
                <input name="button" type="submit" value="Cancel"/>
                <input name="button" type="submit" value="Confirm"/>
            </output_row>
        </form>
    </display>
    <%
    } else if (action.equals("Cancel account")) {
        //Find the corresponding bookings.
        for (Booking booking : bookings.getList()) {
            if (booking.getStatus().equals(Booking.ACTIVE)) booking.setStatus(Booking.CANCELLED);
        }
        ;
//            bookingDAO.read().updateList(bookings);
        bookingDAO.save();
        //Cancel account.
        userDAO.delete(userDAO.searchById(currentUser.getId()));
        session.invalidate();

    %>
    <%@include file="navigation.jsp"%>
    <result type="success">
        <content>Your account has been successfully cancelled.</content>
    </result>
    <%
    } else if (action.equals("Confirm")) {
        String name = request.getParameter(NAME);
        String password = request.getParameter(PASSWORD);
        String dateOfBirth = request.getParameter(DATE_OF_BIRTH);
        String speciality = request.getParameter(SPECIALITY);
        try {
            if (!currentUser.getPassword().equals(password)) password = EncryptUtil.encryptPWD(password);
            User changeUser = new User(currentUser.getId(), currentUser.getEmail(), name, password, dateOfBirth, currentUser.isStudent(),
                    speciality);
            userDAO.update(currentUser, changeUser);
            session.setAttribute("user", changeUser);
    %>

    <%@include file="navigation.jsp" %>
    <result type="success">
        <content>New information is stored.</content>
    </result>
    <%
    } catch (DataValidationException e) {
        e.printStackTrace();
    %>
    <%@include file="navigation.jsp" %>
    <result type="error">
        <content>you may have entered invalid <%=StringUtil.readExceptionCause(e.getMessage())%>%>.
            Please check your input and try again.
        </content>
    </result>
    <%
    } catch (NullPointerException e) {
        e.printStackTrace();
    %>
    <%@include file="navigation.jsp" %>
    <result type="error">
        <content>there might be something wrong with the system. Please log out and try again.</content>
    </result>
    <%
                }
            }
        }
    %>
</page>