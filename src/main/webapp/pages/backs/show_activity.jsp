<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>活动修改页面</title>
    <link href="${pageContext.request.contextPath}/images/favicon.ico" rel="icon">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script>
      $(function () {
        $(".head").load("${pageContext.request.contextPath}/pages/backs/backhead.jsp");
      });
    </script>
    <style>
      .context{
        width: 80%;
        margin: 0 auto;
        margin-top: 10px;
      }
      .context >div {
        float: left;
        margin-left: 20px;
        width: 600px;
      }

      .clear_both {
        clear: both;
      }

      .context div > form{
        margin: 0 auto;
        width: 60%;
      }

      .context  table {
        width: 100%;
        border-top: 1px solid #ccc;
        border-left: 1px solid #ccc;
        margin: 0;
        border-collapse: collapse;
        border-spacing: 0;
      }

      /*控制cellspacing*/
      .context table td {
        padding: 10px;
        border-right: 1px solid #ccc;
        border-bottom: 1px solid #ccc;
      }
    </style>
  </head>
  <body>
    <div class="head"></div>
    <div class="context">
      <div>
        <h3>活动基本信息</h3>
        <table>
          <tr>
            <td>编号</td>
            <td>${activity.anumber}</td>
          </tr>
          <tr>
            <td>标题</td>
            <td>${activity.atitle}</td>
          </tr>
          <tr>
            <td>发起团体/发起人</td>
            <td>${activity.organizer}</td>
          </tr>
          <tr>
            <td>活动地点</td>
            <td>${activity.alocation}</td>
          </tr>
          <tr>
            <td>活动负责人电话</td>
            <td>${activity.telephone}</td>
          </tr>
          <tr>
            <td>开始时间</td>
            <td>${activity.starttimeLongStr}</td>
          </tr>
          <tr>
            <td>结束时间</td>
            <td>${activity.endtimeLongStr}</td>
          </tr>
          <tr>
            <td>参与人数</td>
            <td>${activity.peoples}</td>
          </tr>
        </table>
      </div>

      <div>
        <form action="/backs/change_activity" method="post">
          <h3>信息修改</h3>
          <input type="text" hidden name="anumber" value="${activity.anumber}" />
          活动标题: <input type="text" value="${activity.atitle}" name="atitle" /> <br>
          活动地点: <input type="text" value="${activity.alocation}" name="alocation" /> <br>
          活动内容: <textarea name="details" style="resize:none;" cols="30" rows="10">${activity.details}</textarea> <br>
          <input type="submit" value="更改信息">
        </form>
      </div>
      <div>
        <form action="/backs/change_activityImg" method="post" enctype="multipart/form-data">
          <h3>活动宣传图</h3>
          <img height="450px" src="${pageContext.request.contextPath}/activityImages/${activity.anumber}.png" alt="暂无宣传图">
          <input type="text" hidden name="anumber" value="${activity.anumber}" />
          <input type="file" name="activityImg" />
          <input type="submit" value="更改宣传图">
        </form>
      </div>
      <div>
        <h3>参与活动人员</h3>
        <div style="max-height: 300px;overflow-y: scroll;">
          <table>
            <tr>
              <td>学号</td>
              <td>姓名</td>
              <td>性别</td>
              <td>联系方式</td>
            </tr>
            <c:forEach items="${activity.students}"  var="student">
              <tr>
                <td>${student.snumber}</td>
                <td>${student.sname}</td>
                <td>${student.sgenderStr}</td>
                <td>${student.telephone}</td>
              </tr>
            </c:forEach>
          </table>
        </div>
      </div>
    </div>
  </body>
</html>
