<%@ page import="static model.User.*" %><%--
  Created by IntelliJ IDEA.
  User: might
  Date: 27/09/2017
  Time: 5:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <h1>Register</h1>
    <form action = "registerAction.jsp" method = "post">
        <table>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="<%=NAME%>"/></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type="email" name="<%=EMAIL%>"/></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="<%=PASSWORD%>"/></td>
            </tr>
            <tr>
                <td>Date of Birth</td>
                <td><input type="date" name="<%=DATE_OF_BIRTH%>"/></td>
            </tr>
            <tr>
                <td>Usertype</td>
                <td>
                    <select name="<%=TYPE%>">
                        <option><%=STUDENT%></option>
                        <option><%=TUTOR%></option>
                    </select>
                </td>
            <tr>
                <td>Speciality: </td>
                <td><select name="<%=SPECIALITY%>">
                    <%
                        for(String subject: SUBJECTS){
                            out.print("<option>" + subject +"</option>");
                        }
                    %>
                    </select>
                </td>

            </tr>
            <tr>
                <td></td>
                <td><input type = "submit" value = "Register"/></td>
            </tr>
        </table>
    </form>

</body>
</html>
