<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.text.SimpleDateFormat" %>
<html>
<head>
    <title>mypage</title>
</head>
<body>
    <form action="files" method="POST" align="right">
        <input type="submit" value="Выйти">
    </form>
    <%=LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))%>
    <h1>${curPath}</h1>
    <hr>
    <%
        String parPath = new File((String)request.getAttribute("curPath")).getParent();
        if(parPath == null){
            parPath = "C:\\";
        }
    %>
    <table>
        <tr>
        <th><a href="<%="files?path=" + parPath%>">Вверх</a></th></tr>
    </table>
    <table>
        <tr><th>Файл</th><th>Размер</th><th>Дата</th></tr>
        <%
                File[] directories =  (File[])request.getAttribute("dires");
                for(File directory : directories){
            %>
                <tr>

                <th align="left"><a href="<%="files?path=" + directory.getAbsolutePath()%>"><%=directory.getName() + "/"%></a></th>
                <th></th>
                <th><%=new SimpleDateFormat("dd/MM/yyyy, h:mm:ss a").format(new Date(directory.lastModified()))%></th>
                </tr>
        <%}%>

        <%
            File[] files =  (File[])request.getAttribute("files");
            for(File file : files){
        %>
            <tr>
            <th align="left"><a href="<%="download?path=" + file.getAbsolutePath()%>"><%=file.getName()%></a></th>
            <th><%=file.length() + " B"%></th>
            <th><%=new SimpleDateFormat("dd/MM/yyyy, h:mm:ss a").format(new Date(file.lastModified()))%></th>
            </tr>
        <%}%>
    </table>
</body>
</html>
