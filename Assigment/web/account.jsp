<%@ page contentType="text/xml;charset=UTF-8" language="java" %><%--
--%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="style.xsl"?>
<%@ page import="application.BookingApplication" %>
<%@ page import="jaxblist.Bookings" %>
<%@ page import="model.Booking" %>
<%@ page import="static application.BookingApplication.*" %>
<%@ page import="application.UserApplication" %>
<%@ page import="static application.UserApplication.*" %>
<%@ page import="util.DigestUtil" %>
<%@ page import="javax.xml.bind.ValidationException" %>
<%@ page import="javax.xml.bind.MarshalException" %>
<%@ page import="util.StringUtil" %>
<%@ page import="jaxblist.Users" %>
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
            UserApplication userApp;
            if(user.isStudent()) userApp = new UserApplication(application.getRealPath(WEB_INF_STUDENTS_XML),
                    application.getRealPath(WEB_INF_USERS_XSD));
            else userApp = new UserApplication(application.getRealPath(WEB_INF_TUTORS_XML), application.getRealPath(
                    WEB_INF_USERS_XSD));
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
            BookingApplication bookApp = new BookingApplication(application.getRealPath(WEB_INF_BOOKINGS_XML),
                    application.getRealPath(WEB_IF_BOOKINGS_XSD));
            Bookings bookings;
            //Find the corresponding bookings.
            if(user.isStudent()) bookings = bookApp.getItems().findByStudentEmail(user.getEmail());
            else bookings = bookApp.getItems().findByTutorEmail(user.getEmail());
            for(Booking booking: bookings.getList()) {
                if(booking.getStatus().equals(Booking.ACTIVE)) booking.setStatus(Booking.CANCELLED);
            };
            bookApp.getItems().updateList(bookings);
            bookApp.save();
            //Cancel account.
            userApp.getItems().remove(userApp.getItems().findById(user.getId()));
            userApp.save();
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
                userApp.getItems().setOldItem(user);
                User oldUser = userApp.getItems().findById(user.getId());
                oldUser.setName(name);
                oldUser.setDateOfBirth(dateOfBith);
                if(!oldUser.getPassword().equals(password)) oldUser.setPassword(DigestUtil.encryptPWD(password));
                if(!oldUser.isStudent()) oldUser.setSpeciality(request.getParameter(SPECIALITY));
                userApp.save();
                session.setAttribute("user", oldUser);
    %>
    <result type="simple">
        <content>New information is stored.</content>
    </result>
    <%
        }        catch (MarshalException e){
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