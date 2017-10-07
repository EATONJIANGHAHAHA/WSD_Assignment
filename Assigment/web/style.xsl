<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="page">
        <html>
            <head>
                <link rel="stylesheet" type="text/css" href="page.css"/>
            </head>
            <title>
                <xsl:value-of select="@title"/>
            </title>
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


    <xsl:template match="body">
        <tbody>
            <xsl:apply-templates/>
        </tbody>
    </xsl:template>

    <xsl:template match="display">
        <table>
            <xsl:apply-templates/>
        </table>
    </xsl:template>

    <xsl:template match="output_row">
        <tr>
            <xsl:choose>
                <xsl:when test="@name!=''">
                    <td>
                        <xsl:value-of select="@name"/>
                    </td>
                    <td>
                        <xsl:value-of select="@value"/>
                    </td>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:apply-templates/>
                </xsl:otherwise>
            </xsl:choose>
        </tr>
    </xsl:template>

    <xsl:template match="header">
        <thead>
            <xsl:apply-templates/>
        </thead>
    </xsl:template>

    <xsl:template match="head">
        <th>
            <xsl:value-of select="@value"/>
        </th>
    </xsl:template>

    <xsl:template match="output">
        <td>
            <xsl:if test="@type='text'">
                <xsl:value-of select="@value"/>
            </xsl:if>
            <xsl:if test="@type='link'">
                <a href="{@link}">
                    <xsl:value-of select="@value"/>
                </a>
            </xsl:if>
        </td>
    </xsl:template>

    <xsl:template match="form">
        <form method="POST" action="{@link}">
            <xsl:apply-templates/>
        </form>
    </xsl:template>

    <xsl:template match="input">
        <td>
            <xsl:if test="@type='select'">
                <xsl:if test="@name='status'">
                    <select name="status">
                        <option>active</option>
                        <option>completed</option>
                        <option>cancelled</option>
                    </select>
                </xsl:if>
            </xsl:if>
            <xsl:if test="@type!='select'">
                <input type="{@type}" name="{@name}" value="{@value}"/>
            </xsl:if>
        </td>
    </xsl:template>

    <xsl:template match="input_row">
        <tr>
            <td>
                <xsl:value-of select="@name"/>
            </td>
            <td>
                <xsl:choose>
                    <xsl:when test="@type='select'">
                        <xsl:if test="@id='status'">
                            <select name="status" value="{@value}">
                                <option>active</option>
                                <option>complete</option>
                                <option>cancel</option>
                            </select>
                        </xsl:if>
                        <xsl:if test="@id='speciality'">
                            <select name="speciality">
                                <option>
                                    <xsl:value-of select="@value"/>
                                </option>
                                <option>WSD</option>
                                <option>SEP</option>
                                <option>AppProg</option>
                                <option>USP</option>
                                <option>MobileApp</option>
                            </select>
                        </xsl:if>
                        <xsl:if test="@id='searchType'">
                            <select name="searchType">
                                <option>Tutor name</option>
                                <option>Subject</option>
                                <option>Tutor status</option>
                            </select>
                        </xsl:if>
                    </xsl:when>
                    <xsl:otherwise>
                        <input type="{@type}" name="{@id}" value="{@value}"/>
                    </xsl:otherwise>
                </xsl:choose>
            </td>
        </tr>
    </xsl:template>


    <xsl:template match="result">
        <div class="{@type}">
            <xsl:choose>
                <xsl:when test="@type = 'error'">
                    <p>
                        Error:
                        <xsl:value-of select="content"/>
                    </p>
                </xsl:when>
                <xsl:otherwise>
                    <p>
                        Success: <xsl:value-of select="content"/>.
                    </p>
                </xsl:otherwise>
            </xsl:choose>
        </div>

    </xsl:template>


    <xsl:template match="navigation">
        <div class="menu">
            <xsl:if test="status = 'login'">
                <p>You now logged in as <xsl:value-of select="user_name"/>.
                </p>
                <a href="main.jsp">Main</a>
                <a href="account.jsp">Account</a>
                <a href="booking.jsp">Booking</a>
                <a href="index.jsp?logout">Logout</a>
            </xsl:if>
            <xsl:if test="status = 'logout'">
                Register
                <a href="register.jsp?student">as student</a>
                <a href="register.jsp?tutor">as tutor</a>
                Login
                <a href="login.jsp?student">as student</a>
                <a href="login.jsp?tutor">as tutor</a>
            </xsl:if>
        </div>
    </xsl:template>

</xsl:stylesheet>