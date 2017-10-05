<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="page">
        <html>
            <head>
                <link rel="stylesheet" type="text/css" href="page.css"/>
            </head>
            <title><xsl:value-of select="@title"/></title>
            <body>
                <div>
                    <!--                    <img src="image/UTSLogo.png"></img>-->
                    <p1>
                        <xsl:value-of select="@title"/>
                    </p1>
                </div>
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>



    <xsl:template match="bookinglist">
        <table>
            <thead>
                <tr>
                    <th>Student</th>
                    <th>Student email</th>
                    <th>Tutor</th>
                    <th>Tutor email</th>
                    <th>Subject</th>
                    <th>Status</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <xsl:apply-templates/>
            </tbody>
        </table>
    </xsl:template>

    <xsl:template match="tutorlist">
        <table>
            <thead>
                <tr>
                    <th>Tutor</th>
                    <th>Subject</th>
                    <th>Email</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <xsl:apply-templates/>
            </tbody>
        </table>
    </xsl:template>

    <xsl:template match="tutor">
        <tr>
            <td>
            <xsl:if test="status = 'unavailable' ">
                <xsl:value-of select="name"/>
            </xsl:if>
            <xsl:if test="status ='available'">
                <a>
                    <xsl:attribute name="href">booking.jsp?tutorId=<xsl:value-of select="@id"/></xsl:attribute>
                    <xsl:value-of select="name"/>
                </a>
            </xsl:if>
            </td>
            <td><xsl:value-of select="subject"/></td>
            <td><xsl:value-of select="email"/></td>
            <td><xsl:value-of select="status"/></td>
        </tr>
    </xsl:template>

    <xsl:template match="booking">
        <tr>
            <td><xsl:value-of select="student"/></td>
            <td><xsl:value-of select="student_email"/></td>
            <td><xsl:value-of select="tutor"/></td>
            <td><xsl:value-of select="tutor_email"/></td>
            <td><xsl:value-of select="subject"/></td>
            <xsl:if test="user_type = 'student' ">
            <td><xsl:value-of select="status"/></td>
            <td>
                <form method="post">
                    <xsl:attribute name="action">booking.jsp?bookingId=<xsl:value-of select="@id"/></xsl:attribute>
                    <input type="submit" value="cancel" name="button"/>
                </form>
            </td>
            </xsl:if>
            <xsl:if test="user_type = 'tutor' ">
                <xsl:if test=" status = 'active' ">
                    <form method="post">
                        <xsl:attribute name="action">booking.jsp?bookingId=<xsl:value-of select="@id"/></xsl:attribute>
                    <td>
                        <select name="status">
                            <option>active</option>
                            <option>complete</option>
                            <option>cancel</option>
                        </select>
                    </td>
                        <td>
                            <input type="submit" value="Change" name="button"/>
                        </td>
                </form>
                </xsl:if>
                <xsl:if test="status != 'active'">
                    <td><xsl:value-of select="status"/></td>
                </xsl:if>
            </xsl:if>
        </tr>
    </xsl:template>

    <xsl:template match="search_form">
        <div>
            <form action="main.jsp" method="post">
                <table>
                    <tr>
                        <td>Search by</td>
                        <td>
                            <select name="searchType">
                                <option>Tutor name</option>
                                <option>Subject</option>
                                <option>Tutor status</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Please type in the full key word:</td>
                        <td><input type="search" name="keyWord"/></td>
                    </tr>
                    <tr>
                        <td><input type="hidden" name="progress" value="progress"/></td>
                        <td><input type="submit" value="Search"/></td>
                    </tr>
                </table>
            </form>
        </div>
    </xsl:template>

    <xsl:template match="form_table">
        <div>
            <form method="post">
            <xsl:attribute name="action"><xsl:value-of select="category"/>.jsp?<xsl:value-of select="@user_type"/></xsl:attribute>
            <table>
                <tr>
                    <td>Email:</td>
                    <td><input type="text" name="email"/></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password"/></td>
                </tr>
                <xsl:if test="category != 'login' ">
                    <tr>
                        <td>Name:</td>
                        <td><input type="text" name="name"/></td>
                    </tr>
                    <tr>
                        <td>Date of Birth</td>
                        <td><input type="date" name="dateOfBirth"/></td>
                    </tr>
                    <xsl:choose>
                        <xsl:when test="@user_type = 'tutor'">
                        <tr>
                            <td>Speciality: </td>
                            <td>
                                <select name="speciality">
                                    <option>WSD</option>
                                    <option>SEP</option>
                                    <option>AppProg</option>
                                    <option>USP</option>
                                    <option>MobileApp</option>
                                </select>
                            </td>
                        </tr>
                    </xsl:when>
                    </xsl:choose>
                </xsl:if>
                <tr>
                    <td><input type="hidden" name="progress" value="validate"/></td>
                    <td>
                        <input type="submit">
                            <xsl:attribute name="value">
                                <xsl:value-of select="category"/>
                            </xsl:attribute>
                        </input>
                    </td>
                </tr>
            </table>
        </form>
        </div>
    </xsl:template>


    <xsl:template match="result">
        <div>
        <xsl:if test="@type = 'error'">
            <div class="error">
                <p>
                    <xsl:value-of select="content"/>
                </p>
                <p>Please try again</p>
            </div>
        </xsl:if>
        <xsl:if test="@type = 'success'">
            <div>
                <p>
                    Success, you now login as <b><xsl:value-of select="content"/></b>.
                </p>
                <p>Click <a href = "main.jsp">here</a> to the main page</p>
            </div>
        </xsl:if>
        <xsl:if test="@type = 'simple' ">
            <p><xsl:value-of select="content"/></p>
        </xsl:if>
        </div>
    </xsl:template>


    <xsl:template match="navigation">
        <div class="menu">
        <xsl:if test="status = 'login'">
            <p>You now logged in as <xsl:value-of select="user_name"/>.</p>
            <a href="main.jsp">Main</a>
            <a href="account.jsp">Account</a>
            <a href="booking.jsp">Booking</a>
            <a href="index.jsp?logout">Logout</a>
        </xsl:if>
        <xsl:if test="status = 'logout'">
            Register <a href="register.jsp?student">as student</a>
            <a href="register.jsp?tutor">as tutor</a>
            Login <a href="login.jsp?student"> as student</a>
            <a href="login.jsp?tutor"> as tutor</a>
        </xsl:if>
        </div>
    </xsl:template>

    <xsl:template match="edit_account">
        <div>
            <form method="post" action="account.jsp">
                <table>
                    <tr>
                        <td>Email:</td>
                        <td><xsl:value-of select="email"/></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="password" name="password">
                            <xsl:attribute name="value">
                            <xsl:value-of select="password"/>
                            </xsl:attribute>
                        </input></td>
                    </tr>
                    <tr>
                        <td>Name:</td>
                        <td><input type="text" name="name">
                            <xsl:attribute name="value"><xsl:value-of select="name"/>
                            </xsl:attribute>
                        </input></td>
                    </tr>
                    <tr>
                        <td>Date of birth:</td>
                        <td><input type="date" name="dateOfBirth">
                            <xsl:attribute name="value">
                            <xsl:value-of select="date_of_birth"/>
                            </xsl:attribute>
                        </input></td>
                    </tr>
                    <xsl:if test="@user_type = 'tutor' ">
                        <tr>
                            <td>Speciality: </td>
                            <td>
                                <select name="speciality">
                                    <option>WSD</option>
                                    <option>SEP</option>
                                    <option>AppProg</option>
                                    <option>USP</option>
                                    <option>MobileApp</option>
                                </select>
                            </td>
                        </tr>
                    </xsl:if>
                    <tr>
                        <td><input type="submit" name="button" value="cancel"/></td>
                        <td>
                            <input type="submit" name="button" value="confirm"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>

    </xsl:template>

    <xsl:template match="account_info">
        <table>
            <tr>
                <td>name</td>
                <td><xsl:value-of select="name"/></td>
            </tr>
            <tr>
                <td>email</td>
                <td><xsl:value-of select="email"/></td>
            </tr>
            <tr>
                <td>Date of birth</td>
                <td><xsl:value-of select="date_of_birth"/></td>
            </tr>
            <xsl:if test="user_type = 'tutor'">
                <tr>
                    <td>Speciality</td>
                    <td><xsl:value-of select="speciality"/></td>
                </tr>
            </xsl:if>
            <tr>
                <form action="account.jsp" method="post">
                <td><input type="submit" name="button" value="Edit account"/></td>
                <td><input type="submit" name="button" value="Cancel account"/></td>
                </form>
            </tr>
        </table>
    </xsl:template>

    <xsl:template match="booking_template">
        <form method="POST" action="booking.jsp">
            <table>
            <tr>
               <td>Tutor name: </td>
                <td><input type="text" name="tutor_name">
                    <xsl:attribute name="value">
                        <xsl:value-of select="tutor_name"/>
                    </xsl:attribute>
                </input>
                </td>
            </tr>
            <tr>
                <td>Tutor email: </td>
                <td><input type="text" name="tutor_email">
                    <xsl:attribute name="value">
                        <xsl:value-of select="tutor_name"/>
                    </xsl:attribute>
                </input></td>
            </tr>
            <tr>
                <td>Subject: </td>
                <xsl:if test="subject = '' ">
                <td>
                    <select name="subject">
                        <option>WSD</option>
                        <option>SEP</option>
                        <option>AppProg</option>
                        <option>USP</option>
                        <option>MobileApp</option>
                </select>
                </td>
                </xsl:if>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" name="button" value="create"/></td>
            </tr>
            </table>
        </form>
    </xsl:template>

</xsl:stylesheet>