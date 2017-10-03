<%@ page import="model.User" %>
<%@ page import="static model.User.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) session.getAttribute(USER);
    String url = request.getQueryString();
%>
<div class="menu">
    <%
        if(user != null){
    %>
    <p>You now logged in as <bold>user.getName()</bold>.</p>
    <a href="main.jsp">Main</a>
    <a href="account.jsp">Account</a>
    <a href="booking.jsp">Booking</a>
    <%
        }
        else {
    %>
    Register <a href="login.jsp?<%=STUDENT%>"> as student</a>
    <a href="login.jsp?<%=TUTOR%>"> as tutor</a>
    Login <a href="login.jsp?<%=STUDENT%>">Register as student</a>
    <a href="login.jsp?<%=TUTOR%>"> as tutor</a>
</div>
<%
        }
%>