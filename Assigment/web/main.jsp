<%@ page contentType="text/xml;charset=UTF-8" language="java" %><%--
--%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="style.xsl"?>
<%@ page import="dao.UserDAOImpl" %>
<%@ page import="static dao.UserDAOImpl.WEB_INF_TUTORS_XML" %>
<%@ page import="jaxblist.Users" %>
<page title = "Main">
<%@ include file="navigation.jsp"%>
    <%
        if(user == null){
    %>
    <result type="simple">
        <content>Please log in first.</content>
    </result>
    <%
        }
        else if( user.isStudent() ) {

    %>
    <search_form/>
    <%
        String progress = request.getParameter("progress");
        if(progress != null){
            try
            {
                UserDAOImpl tutorApp = new UserDAOImpl(application.getRealPath(WEB_INF_TUTORS_XML));
                String searchType = request.getParameter("searchType");
                String keyWord = request.getParameter("keyWord");
                Users tutors = null;
                if(searchType.equals("Tutor name")){
                    tutors = tutorApp.getItems().findTutorByName(keyWord);
                }
                else if(searchType.equals("Subject")){
                    tutors = tutorApp.getItems().findTutorBySubject(keyWord);
                }
                else {
                    tutors = tutorApp.getItems().findTutorByStatus(keyWord);
                }
                if(tutors == null || tutors.getList() == null || tutors.getList().size() == 0){

                %>
    <result type="simple">
        <content>No such result, please change your keyword and try again.</content>
    </result>
                <%
                }
                else {
                %>
    <tutorlist>
        <%
            for(User tutor: tutors.getList()){
        %>
        <tutor id="<%=tutor.getId()%>">
            <name><%=tutor.getName()%></name>
            <subject><%=tutor.getSpeciality()%></subject>
            <email><%=tutor.getEmail()%></email>
            <status><%=tutor.getAvailability()%></status>
        </tutor>
        <%
            }
        %>
    </tutorlist>

    <%
            }}
            catch (NullPointerException e){
                e.printStackTrace();
    %>
    <result type="simple">
        <content>Please enter correct information.</content>
    </result>
    <%
        }
        }
        }

    %>
</page>