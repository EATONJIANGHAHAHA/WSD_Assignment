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
<%@ page import="util.StringUtil" %>
<%@ page import="dao.UserDAO" %>
<%@ page import="dao.BookingDAO" %>
<%@ page import="exception.DataValidationException" %>
<page title="Account">
<%@include file="navigation.jsp"%>
    <%
        if(user == null){
    %>
    <result type="error">
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
            if(action == null || action.equals("") || action.equals("Cancel")){
    %>
    <display>
            <output_row name="Name" value="<%=user.getName()%>"/>
            <output_row name="Email" value="<%=user.getEmail()%>"/>
            <output_row name="Password" value="****************"/>
            <output_row name="Date of birth" value="<%=user.getDateOfBirth()%>"/>
            <%
                if(!user.isStudent()){
            %>
            <output_row name="Speciality" value="<%=user.getSpeciality()%>"/>
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
            }
            else if(action.equals("Edit account")){
    %>
    <display>
            <form link="account.jsp">
                <output_row name="Email" value="<%=user.getEmail()%>"/>
                <input_row id="<%=PASSWORD%>" name="Password" type="password" value="<%=user.getPassword()%>"/>
                <input_row id="<%=NAME%>" name="Name" type="text" value="<%=user.getName()%>"/>
                <input_row id="<%=DATE_OF_BIRTH%>" name="Date of birth" type="date" value="<%=user.getDateOfBirth()%>"/>
        <% if(!user.isStudent()) {%>
                <input_row id="<%=SPECIALITY%>" name="Speciality" type="select" value="<%=user.getSpeciality()%>"/>
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
    <result type="success">
        <content>Your account has been successfully cancelled.</content>
    </result>
    <%
        }
        else if(action.equals("Confirm")){
            String name = request.getParameter(NAME);
            String password = request.getParameter(PASSWORD);
            String dateOfBirth = request.getParameter(DATE_OF_BIRTH);
            String speciality = request.getParameter(SPECIALITY);
            try{
                if(!user.getPassword().equals(password)) password = DigestUtil.encryptPWD(password);
                User changeUser = new User(user.getId(), user.getEmail(), name, password, dateOfBirth, user.isStudent(),
                        speciality);
                userDAO.update(user, changeUser);
                session.setAttribute("user", changeUser);
    %>
    <result type="success">
        <content>New information is stored.</content>
    </result>
    <%
        }
        catch (DataValidationException e){
            e.printStackTrace();
    %>

    <result type="error">
        <content>you may have entered invalid <%=StringUtil.readExceptionCause(e.getMessage())%>%>.
            Please check your input and try again.
        </content>
    </result>
    <%
            }
        catch (NullPointerException e){
            e.printStackTrace();
        %>
    <result type="error">
        <content>there might be something wrong with the system. Please log out and try again.</content>
    </result>
        <%
        }
        }
        }
    %>
</page>