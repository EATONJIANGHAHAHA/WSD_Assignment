<%@ page import="static model.User.*" %>
<%@ page import="application.TutorApplication" %>
<%@ page import="static application.TutorApplication.TUTOR_FILE_PATH" %>
<%@ page import="java.util.Date" %>
<%@ page import="static model.Tutor.SPECIALITY" %>
<%@ page import="model.Tutor" %>
<%@ page import="util.DateUtil" %><%--
  Created by IntelliJ IDEA.
  User: might
  Date: 27/09/2017
  Time: 5:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>RegisterAction</title>
    </head>
    <body>
    <%
        try{
            String type = request.getParameter(TYPE);
            String email = request.getParameter(EMAIL);
            String password = request.getParameter(PASSWORD);
            String name = request.getParameter(NAME);
            String date = request.getParameter(DATE_OF_BIRTH);
            String filePath;%>
    <p><%=request.getParameter(DATE_OF_BIRTH)%></p>
        <%
            if(type.equals(TUTOR)){
                filePath = application.getRealPath(TUTOR_FILE_PATH);
                TutorApplication tutorApp = new TutorApplication(filePath);
                if(tutorApp.getItems().isRegistered(email)){

                }
                else {
                    //Date date = request.getParameter(DATE_OF_BIRTH);
                    String speciality = request.getParameter(SPECIALITY);
                    tutorApp.getItems().add(new Tutor(2, email, name, password, DateUtil.stringToDate(date), speciality, false));
                    tutorApp.save();

                }
            }
            else{

            }

        }
        catch (NullPointerException e){
            e.printStackTrace();

        }
    %>



    </body>
</html>
