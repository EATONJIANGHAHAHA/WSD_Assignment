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

    <xsl:template match="booking">
        <tr>
            <td><xsl:value-of select="id"/></td>
            <td><xsl:value-of select="subject"/></td>
            <td><xsl:value-of select="status"/></td>
        </tr>
    </xsl:template>

    <xsl:template match="result">
        <xsl:if test="@type = error">
            <div class="error">
                <p>
                    <xsl:value-of select="content"/>
                </p>
                <p>Click here <a href = "login.jsp">here</a>to try again.</p>
                <p>Click <a href="register.jsp">here</a> to register.</p>
            </div>
        </xsl:if>
        <xsl:if test="@type = success">
            <div>
                <p>
                    Success, you now login as <bold><xsl:value-of select="content"/></bold>.
                </p>
                <p>Click here <a href = "main.jsp">here</a>to the main page</p>
            </div>
        </xsl:if>

    </xsl:template>
</xsl:stylesheet>