<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- 靜態包含 base標籤動態獲取值 -->
<%@ include file="pages/common/head.jsp"%>

<% request.getRequestDispatcher("pages/index/index.jsp").forward(request, response); %>
