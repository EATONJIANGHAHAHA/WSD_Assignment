<%@ page contentType="text/xml;charset=UTF-8" language="java" %><%--
--%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="style.xsl"?>
<%@ page import="static dao.UserDAOImpl.WEB_INF_USERS_XSD" %>
<%@ page import="static dao.UserDAOImpl.WEB_INF_TUTORS_XML" %>
<%@ page import="util.DigestUtil" %>
<%@ page import="dao.UserDAOImpl" %>
<%@ page import="static dao.UserDAOImpl.WEB_INF_STUDENTS_XML" %>
<%@ page import="javax.xml.bind.ValidationException" %>
<%@ page import="javax.xml.bind.MarshalException" %>
<%@ page import="util.StringUtil" %>
<%@ page import="dao.UserDAO" %>

<page title="Register">
    <%
        String progress = request.getParameter("progress");

        if(progress == null || progress.equals("")){
    %>
    <%@ include file="navigation.jsp"%>
    <form_table user_type="<%=url%>">
        <category>register</category>>
    </form_table>
    <%
        }
        else {
            try{
                String email = request.getParameter(EMAIL);
                String password = request.getParameter(PASSWORD);
                String name = request.getParameter(NAME);
                String dateOfBirth = request.getParameter(DATE_OF_BIRTH);
                String schemaPath = application.getRealPath(WEB_INF_USERS_XSD);
                String filePath;
                User newUser;

                if(request.getQueryString().equals(TUTOR)) {
                    String speciality = request.getParameter(SPECIALITY);
                    filePath = application.getRealPath(WEB_INF_TUTORS_XML);
                    newUser = new User(email, name, DigestUtil.encryptPWD(password),
                            dateOfBirth, speciality, AVAILABLE);
                }
                else {
                    filePath = application.getRealPath(WEB_INF_STUDENTS_XML);
                    newUser = new User(email, name, DigestUtil.encryptPWD(password),
                            dateOfBirth);
                }
                UserDAO userDao = new UserDAOImpl(filePath, schemaPath);

                if(userDao.isRegistered(email)){

    %>
    <%@ include file="navigation.jsp"%>
    <result type="error">
        <content>
            Register failed: The email address already exists.
        </content>
    </result>
    <%
                }
                else {
                    userDao.create(newUser);
                    session.setAttribute(USER, newUser);

    %>
    <%@ include file="navigation.jsp"%>
    <result type="success">
        <content><%=user.getName()%></content>
    </result>
    <%
                }
            }
            catch (NullPointerException e){
                e.printStackTrace();
    %>
    <%@ include file="navigation.jsp"%>
    <result type="error">
        <content>
            Register failed: The information you entered may be incomplete.
        </content>
    </result>
    <%
        }
        catch (MarshalException e){
                e.printStackTrace();
    %>
    <%@ include file="navigation.jsp"%>
    <result type="error">
        <content>
            Register failed: you may entered invalid <%=StringUtil.readExceptionCause(e.getMessage())%>.
        </content>
    </result>
    <%
        }
        }
    %>

</page>

