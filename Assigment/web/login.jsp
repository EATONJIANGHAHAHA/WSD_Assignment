<%--
  Created by IntelliJ IDEA.
  User: might
  Date: 26/09/2017
  Time: 11:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h1>Login</h1>
    <form action="loginAction.jsp" method="post">
        <table>
            <tr>
                <td>Email:</td>
                <td><input type="text" name="email"/></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password"/></td>
            </tr>
            <tr>
                <td>Login as:</td>
                <td><input type="radio" name="gender" value="student"/>
                <input type="radio" name="gender" value="tutor"/>
                </td>
            <tr>
                <td></td>
                <td><input type="submit" value="Login"/></td>
            </tr>
        </table>
    </form>

</body>
</html>
