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
                    <th>Id</th>
                    <th>Subject</th>
                    <th>Status</th>
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
            <td>#<xsl:value-of select="id"/></td>
            <td><xsl:value-of select="subject"/></td>
            <td><xsl:value-of select="status"/></td>
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
                <p>Click <a href = "main.jsp">here</a>to the main page</p>
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
            <a href="logoutAction.jsp">Logout</a>
        </xsl:if>
        <xsl:if test="status = 'logout'">
            Register <a href="register.jsp?student">as student</a>
            <a href="register.jsp?tutor">as tutor</a>
            Login <a href="login.jsp?student"> as student</a>
            <a href="login.jsp?tutor"> as tutor</a>
        </xsl:if>
        </div>
    </xsl:template>

</xsl:stylesheet>