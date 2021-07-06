<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ch">
  <head>
    <meta charset="UTF-8">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <link href="${pageContext.request.contextPath}/images/favicon.ico" rel="icon">
    <title>发布新闻</title>
    <script>
      $(function () {
        $(".head").load("${pageContext.request.contextPath}/pages/backs/backhead.jsp");
        function getQueryVariable(variable) {
          var query = window.location.search.substring(1);
          var vars = query.split("&");
          for (var i = 0; i < vars.length; i++) {
            var pair = vars[i].split("=");
            if (pair[0] == variable) {
              return pair[1];
            }
          }
          return (false);
        }
        if (getQueryVariable("nnumber")==false){
          $("#nnumber").val("");
        }else {
          $("#nnumber").val("100"+getQueryVariable("nnumber"));
        }
      });
    </script>
    <style>
      *{margin: 0;padding: 0;}
    </style>
  </head>
  <body>
    <div class="head"></div>
    <div class="context">
      <div>
        <form method="post" action="${pageContext.request.contextPath}/backs/new_news" style="margin: 0 auto;width: 500px;" >
          新闻编号：<input id="nnumber" name="nnumber" type="text"> <br>
          新闻标题：<input type="text" name="ntitle"/> <br>
          新闻内容：<textarea name="ncontent" style="resize:none" cols="20" rows="10"></textarea> <br>
          编&ensp;辑&ensp;人：<input name="nauthor" type="text"><br>
          <input type="submit" value="发布新闻">
          <h3 style="color: #EE0000;">${msg}</h3>
        </form>
      </div>
    </div>
  </body>
</html>