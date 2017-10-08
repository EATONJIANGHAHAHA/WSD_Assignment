<%@ page contentType="text/xml;charset=UTF-8" language="java" %><%--
--%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="style.xsl"?>
<%@ page import="dao.UserDAOImpl" %>
<%@ page import="static dao.UserDAOImpl.WEB_INF_TUTORS_XML" %>
<%@ page import="jaxblist.Users" %>
<page title="Main">
    <%@ include file="navigation.jsp" %>
    <%
        if (user == null) {
    %>
    <result type="error">
        <content>Please log in first.</content>
    </result>
    <%
    } else if (user.isStudent()) {

    %>
    <display>
        <form link="main.jsp">
            <input_row id="searchType" name="Search by" type="select"/>
            <input_row id="keyWord" name="Key word" type="search"/>
            <input_row id="progress" value="Search" type="submit"/>
        </form>
    </display>
    <%
        String progress = request.getParameter("progress");
        if (progress != null) {
            try {
                UserDAOImpl tutorApp = new UserDAOImpl(application.getRealPath(WEB_INF_TUTORS_XML));
                String searchType = request.getParameter("searchType");
                String keyWord = request.getParameter("keyWord");
                Users tutors = null;
                if (searchType.equals("Tutor name")) {
                    tutors = tutorApp.getItems().findTutorByName(keyWord);
                } else if (searchType.equals("Subject")) {
                    tutors = tutorApp.getItems().findTutorBySubject(keyWord);
                } else {
                    tutors = tutorApp.getItems().findTutorByStatus(keyWord);
                }
                if (tutors == null || tutors.getList() == null || tutors.getList().size() == 0) {

    %>
    <result type="error">
        <content>No such result, please change your keyword.</content>
    </result>
    <%
    } else {
    %>
    <display>
        <header>
            <head value="Tutor"/>
            <head value="Email"/>
            <head value="Subject"/>
            <head value="Status"/>
        </header>
        <body>
            <%
                for (User tutor : tutors.getList()) {
            %>
            <output_row>
                <%
                    if (tutor.isAvailable()) {
                %>
                <output type="link" value="<%=tutor.getName()%>" link="booking.jsp?tutorEmail=<%=tutor.getEmail()%>"/>
                <%
                } else {
                %>
                <output type="text" value="<%=tutor.getName()%>"/>
                <%
                    }
                %>
                <output type="text" value="<%=tutor.getEmail()%>"/>
                <output type="text" value="<%=tutor.getSpeciality()%>"/>
                <output type="text" value="<%=tutor.getAvailability()%>"/>
            </output_row>
            <%
                }
            %>
        </body>
    </display>

    <%
        }
    } catch (NullPointerException e) {
        e.printStackTrace();
    %>
    <result type="error">
        <content>Please enter correct information.</content>
    </result>
    <%
                }
            }
        }

    %>
</page>