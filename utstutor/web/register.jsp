<%@ page contentType="text/xml;charset=UTF-8" language="java" %><%--
--%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="style.xsl"?>
<%@ page import="static dao.UserDAOImpl.WEB_INF_USERS_XSD" %>
<%@ page import="static dao.UserDAOImpl.WEB_INF_TUTORS_XML" %>
<%@ page import="util.DigestUtil" %>
<%@ page import="dao.UserDAOImpl" %>
<%@ page import="static dao.UserDAOImpl.WEB_INF_STUDENTS_XML" %>
<%@ page import="util.StringUtil" %>
<%@ page import="dao.UserDAO" %>
<%@ page import="exception.DataValidationException" %>
<%@ page import="model.User" %>
<%@ page import="static model.User.*" %>

<page title="Register">
    <%
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String name = request.getParameter(NAME);
        String dateOfBirth = request.getParameter(DATE_OF_BIRTH);
        String schemaPath = application.getRealPath(WEB_INF_USERS_XSD);
        String type = request.getParameter(TYPE);
        String filePath;
        String[] ids = {EMAIL, PASSWORD, NAME, DATE_OF_BIRTH, TYPE};
        String[] names = {"Email", "Password", "Name", "Date of birth", "Register as"};
        String[] types = {"text", "password", "text", "date", "select"};
        String[] errors = {"", "", "", "", ""};
        String s = "";

        if (!StringUtil.isEmpty(type)) {

            User newUser;

            if (type.equals("1")) {
                type = TUTOR;
                String speciality = request.getParameter(SPECIALITY);
                filePath = application.getRealPath(WEB_INF_TUTORS_XML);
                newUser = new User(email, name, DigestUtil.encryptPWD(password),
                        dateOfBirth, speciality, AVAILABLE);
            } else {
                type = STUDENT;
                filePath = application.getRealPath(WEB_INF_STUDENTS_XML);
                newUser = new User(email, name, DigestUtil.encryptPWD(password),
                        dateOfBirth);
            }
            UserDAO userDao = new UserDAOImpl(filePath, schemaPath);

            if (userDao.isRegistered(email)) {
                errors[0] = "The email address \'" + email + "\' is already registered for user type " + type;
            } else {
                try {
                    userDao.create(newUser);
                    session.setAttribute(USER, newUser);
                    response.sendRedirect("main.jsp");

                } catch (DataValidationException e) {
                    e.printStackTrace();
                    s = StringUtil.readExceptionCause(e.getMessage());
                    if (s.equals(NAME)) errors[2] = "Please enter a valid name";
                    if (s.equals(EMAIL)) errors[0] = "Please enter a valid email";
                    if (s.equals("date")) errors[3] = "Please enter a valid date";

                }
            }
        }
    %>
    <%@ include file="navigation.jsp" %>
    <display>
        <form link="register.jsp">
            <%
                for (int i = 0; i < ids.length; i++) {
            %>
            <input_row id="<%=ids[i]%>" name="<%=names[i]%>" type="<%=types[i]%>" error="<%=errors[i]%>"/>
            <%}%>
            <type_tr>
                <output type="text" value="Speciality"/>
                <input name="speciality" type="select"/>
            </type_tr>
            <input_row id="progress" type="submit" value="Confirm"/>
        </form>
    </display>
</page>

