<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    //1.協議： request.getScheme() 它可以獲取請求的協議
    //2.服務器 ip：request.getServerName() 獲取請求的服務器 ip 或域名
    //3.服務器端口：request.getServerPort() 獲取請求的服務器端口號
    //4.獲取工程路徑：getContextPath() 獲取當前工程路徑
    //5.獲取請求方法：request.getMethod() 獲取請求的方式（ GET 或 POST ）
    //6.獲取客戶端 ip 地址：request.getRemoteHost() 獲取客戶端的 ip 地址
    //7.獲取會話的 id 編號：session.getId() 獲取會話的唯一標識

    String url = request.getScheme()
            + "://"
            + request.getRemoteHost()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/";
%>
<base href="<%= url %>">
<script src="static/script/jquery-1.7.2.min.js"></script>
