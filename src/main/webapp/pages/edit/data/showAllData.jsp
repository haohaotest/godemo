<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>showDate</title>

    <!-- 靜態包含 base標籤動態獲取值 -->
    <%@ include file="../../common/head.jsp" %>

    <style>
        /* 版心 */
        #wrapper {
            margin: auto;
            width: 1200px;

            text-align: center;

            /*border: 1px black solid;*/
        }

        a {
            text-decoration: none;
        }

        /* 展示資訊的表格 */
        #dateInfo {
            margin: 20px auto 20px auto;

            border-collapse: collapse;

            text-align: center;

            width: 1200px;
        }
        /* 展示資訊表格內的tr, th, td */
        #dateInfo tr, #dateInfo th, #dateInfo td {
            border: 1px black solid;
        }

        /* id欄位 - th */
        #id {
            width: 30px;
        }
        /* english欄位 - th */
        #english {
            width: 150px;
        }
        /* translation欄位 - th */
        #translation {
            /*width: 140px;*/
        }
        /* createTime欄位 - th */
        #createTime {
            width: 140px;
        }



    </style>
</head>
<body>

<!-- 靜態包含 頂端導航欄 -->
<%@ include file="../../common/title.jsp"%>

    <!-- 版心 -->
    <div id="wrapper">

        <!-- 回首頁 -->
        <a href="pages/index/index.jsp">回首頁</a>
        <a href="pages/edit/data/crud.jsp">回修改頁面</a>

        <!-- JavaWord表格-->
        <table id="dateInfo">
            <tr>
                <th id="id">ID</th>
                <th id="english">English</th>
                <th id="translation">Translation</th>
                <th id="remarks">Remarks</th>
                <th id="createTime">CreateTime</th>
            </tr>
            <c:forEach items="${ requestScope.words }" var="word">
                <tr>
                    <td>${ word.id }</td>
                    <td>${ word.word }</td>
                    <td>${ word.translation }</td>
                    <td>${ word.remarks }</td>
                    <td>${ word.createTime }</td>
                </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>
