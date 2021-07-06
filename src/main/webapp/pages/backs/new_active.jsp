<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ch">
  <head>
    <meta charset="UTF-8">
    <title>新建活动</title>
    <link href="${pageContext.request.contextPath}/images/favicon.ico" rel="icon">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script>
      $(function () {
        $(function () {
          $(".head").load("${pageContext.request.contextPath}/pages/backs/backhead.jsp");
        });
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
        if (getQueryVariable("anumber")==false){
          $("#anumber").val("");
        }else {
          $("#anumber").val("10"+getQueryVariable("anumber"));
        }
      });
    </script>
  </head>
  <body>
    <div class="head"></div>
    <div class="context">
      <div>
        <form style="margin: 0 auto;width: 500px;" method="post" action="${pageContext.request.contextPath}/backs/new_activity">
          活动编号：<input id="anumber" name="anumber" type="text"> <br>
          活动标题：<input name="atitle" type="text"> <br>
          举办方：<input name="organizer" type="text"> <br>
          举办地点：<input name="alocation" type="text"> <br>
          负责人电话：<input name="telephone" type="text"> <br>
          开始时间：<input type="date" name="starttimeF"> <input type="time" name="starttimeB"> <br>
          结束时间：<input type="date" name="endtimeF"> <input type="time" name="endtimeB"><br>
          活动详情：<textarea name="details" cols="30" rows="10"></textarea><br>
          <input type="submit" value="提交活动">
          <h3 style="color: #EE0000">${msg}</h3>
        </form>
      </div>
    </div>
  </body>
</html>