<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="page">
        <html>
            <title><xsl:value-of select="@title"/></title>
            <body>
                <h1><xsl:value-of select="@title"/></h1>
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

    <xsl:template match="navigation">
        <a href=""><xsl:value-of select="link"/></a>
    </xsl:template>


</xsl:stylesheet>