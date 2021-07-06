<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ch">
  <head>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/images/favicon.ico" rel="icon">
    <title>编辑新闻页面</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script>
      $(function () {
        $(".head").load("${pageContext.request.contextPath}/pages/backs/backhead.jsp");
      });
    </script>
    <style>
      *{
        padding: 0;
        margin: 0;
      }
      .context{
        width: 80%;
        margin: 0 auto;
        margin-top: 10px;
      }

    </style>
  </head>
  <body>
    <div class="head"></div>
    <div class="context">
      <form style="margin: 0 auto;width: 50%" action="${pageContext.request.contextPath}/backs/change_news" method="post">
        新闻编号：<span>${news.nnumber}</span><br>
        <input type="text" hidden name="nnumber" value="${news.nnumber}" />
        新闻标题：<input type="text" name="ntitle"  value="${news.ntitle}"> <br>
        新闻内容：<textarea name="ncontent" style="resize: none;" cols="30" rows="10">${news.ncontent}</textarea>
        <br>
        改编作者：<input type="text" name="nauthor" value="${news.nauthor}"/><br>
        <input type="submit" value="更新新闻">
      </form>
    </div>
  </body>
</html>