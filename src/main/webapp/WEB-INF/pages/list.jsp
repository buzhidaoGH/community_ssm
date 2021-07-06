<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <h3>查询所有的账户信息</h3>
        <c:forEach items="${list}" var="news">
            ${news.nnumber} <br>
        </c:forEach>
        <img src="${pageContext.request.contextPath}/images/logo-weixin220-100.png" alt="">
    </body>
</html>
