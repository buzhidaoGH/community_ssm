<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ch">
  <head>
    <meta charset="UTF-8">
    <title>新闻页面</title>
  </head>
  <body>
    <h3>新闻标题：${news.ntitle}</h3>
    <h3>新闻作者：${news.nauthor}</h3>
    <h3>新闻内容：${news.ncontent}</h3>
    <h3>发布时间：${news.ntimeLongStr}</h3>
  </body>
</html>