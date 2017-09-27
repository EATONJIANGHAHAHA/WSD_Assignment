<%@ page import="model.Tutor" %><%--
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
                <td><input type = "text" name = "name"/></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type = "email" name = "email"/></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type = "password" name = "password"/></td>
            </tr>
            <tr>
                <td>Date of Birth</td>
                <td><input type="date" name="dob"/></td>
            </tr>
            <tr>
                <td>Usertype</td>
                <td>
                    <select name="type">
                        <option>student</option>
                        <option>tutor</option>
                    </select>
                </td>
            <tr>
                <td>Speciality: </td>
                <td><select>
                    <%
                        for(String subject: Tutor.SUBJECTS){
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
