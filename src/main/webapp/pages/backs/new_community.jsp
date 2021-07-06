<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ch">
  <head>
    <meta charset="UTF-8">
    <title>创建社团</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <link href="${pageContext.request.contextPath}/images/favicon.ico" rel="icon">
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
        if(getQueryVariable("cnumber")){
          $("#cnumber").val("10" + getQueryVariable("cnumber"));
        }
      });
    </script>
    <style>
      *{
        margin: 0;
        padding: 0;
      }
      div form>span{
        font-size: 18px;
        font-weight: bold;
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
      <form style="margin: 0 auto;width: 500px;" action="${pageContext.request.contextPath}/backs/new_community"
            method="post" enctype="multipart/form-data">
        <br>
        <span>社团编号：<input type="text" id="cnumber" value="" name="cnumber"> </span>  <br>
        <br>
        <span>社团名称：<input type="text" name="cname"> </span><br>
        <h4>社团logo</h4>
        <input type="file" name="imageLogo"> <br>
        <span>
          社长学号：<input type="text" name="snumber"> <br>
        </span>
        <h3>社团简介</h3>
        <textarea name="introduction" style="resize:none;" cols="30" rows="10"></textarea> <br>
        <h3>社团公告</h3>
        <textarea name="notice" style="resize:none;" cols="30" rows="3"></textarea> <br>
        <input type="submit" value="创建社团">
        <a style="color: #EE0000;"
           href="${pageContext.request.contextPath}/show/communityPage?cnumber=${cnumber}">${cname}</a>
      </form>
    </div>
  </body>
</html>