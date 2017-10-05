<%@ page contentType="text/xml;charset=UTF-8" language="java" %><%--
--%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="style.xsl"?>
<%@ page import="dao.BookingDAOImpl" %>
<%@ page import="jaxblist.Bookings" %>
<%@ page import="model.Booking" %>
<%@ page import="static dao.BookingDAOImpl.*" %>
<%@ page import="dao.UserDAOImpl" %>
<%@ page import="static dao.UserDAOImpl.*" %>
<%@ page import="util.DigestUtil" %>
<%@ page import="javax.xml.bind.ValidationException" %>
<%@ page import="javax.xml.bind.MarshalException" %>
<%@ page import="util.StringUtil" %>
<%@ page import="jaxblist.Users" %>
<%@ page import="dao.UserDAO" %>
<%@ page import="dao.BookingDAO" %>
<%@ page import="exception.DataValidationException" %>
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
            UserDAO userDAO;
            if(user.isStudent()) userDAO = new UserDAOImpl(application.getRealPath(WEB_INF_STUDENTS_XML),
                    application.getRealPath(WEB_INF_USERS_XSD));
            else userDAO = new UserDAOImpl(application.getRealPath(WEB_INF_TUTORS_XML), application.getRealPath(
                    WEB_INF_USERS_XSD));
            BookingDAO bookingDAO = new BookingDAOImpl(application.getRealPath(WEB_INF_BOOKINGS_XML),
                    application.getRealPath(WEB_IF_BOOKINGS_XSD));
            Bookings bookings = bookingDAO.searchByEmail(user.getEmail(), user.isStudent());
            String action = request.getParameter("button");
            if(action == null || action.equals("") || action.equals("cancel")){
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
            else if(action.equals("Edit account")){
    %>
    <edit_account user_type="<%=user.getType()%>">
        <email><%=user.getEmail()%></email>
        <password><%=user.getPassword()%></password>
        <name><%=user.getName()%></name>
        <date_of_birth><%=user.getDateOfBirth()%></date_of_birth>
        <% if(!user.isStudent()) {%>
        <speciality><%=user.getSpeciality()%></speciality>
        <%
            }
        %>
    </edit_account>
    <%
        }
        else if(action.equals("Cancel account")){
            //Find the corresponding bookings.
            for(Booking booking: bookings.getList()) {
                if(booking.getStatus().equals(Booking.ACTIVE)) booking.setStatus(Booking.CANCELLED);
            };
//            bookingDAO.read().updateList(bookings);
            bookingDAO.save();
            //Cancel account.
            userDAO.delete(userDAO.searchById(user.getId()));
            session.invalidate();

    %>
    <result type="simple">
        <content>Your account has been successfully cancelled.</content>
    </result>
    <%
        }
        else if(action.equals("confirm")){
            String name = request.getParameter(NAME);
            String password = request.getParameter(PASSWORD);
            String dateOfBith = request.getParameter(DATE_OF_BIRTH);
            try{
                User changeUser = userDAO.searchById(user.getId());
                changeUser.setName(name);
                changeUser.setDateOfBirth(dateOfBith);
                if(!changeUser.getPassword().equals(password)) changeUser.setPassword(DigestUtil.encryptPWD(password));
                if(!changeUser.isStudent()) changeUser.setSpeciality(request.getParameter(SPECIALITY));
                userDAO.update(user, changeUser);
                session.setAttribute("user", changeUser);
    %>
    <result type="simple">
        <content>New information is stored.</content>
    </result>
    <%
        }
        catch (DataValidationException e){
            e.printStackTrace();
    %>

    <result type="simple">
        <content>Edition failed: you may have entered invalid <%=StringUtil.readExceptionCause(e.getMessage())%>.
             Please check your input and try again.
        </content>
    </result>
    <%
            }
        catch (NullPointerException e){
            e.printStackTrace();
        %>
    <result type="simple">
        <content>Edition failed: there might be something wrong with the system, please log out and try again.</content>
    </result>
        <%
        }
        }
        }
    %>
</page>