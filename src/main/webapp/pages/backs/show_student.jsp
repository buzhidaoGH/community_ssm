<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/images/favicon.ico" rel="icon">
    <title>用户详情信息</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <style>
      *{
        margin: 0;
        padding: 0;
      }
      .information,.my_club,.my_active{
        width: 45%;
        float: left;
        margin-left: 20px;
        min-width: 400px;
      }
      .overflow_scroll{
        max-height: 400px;
        overflow:scroll;
      }
      table {
        width: 100%;
        border-top: 1px solid #ccc;
        border-left: 1px solid #ccc;
        margin: 0;
        border-collapse: collapse;
        border-spacing: 0;
      }

      /*控制cellspacing*/
      table td {
        padding: 10px;
        border-right: 1px solid #ccc;
        border-bottom: 1px solid #ccc;
      }
      .context{
        width: 80%;
        margin: 0 auto;
        margin-top: 10px;
      }
    </style>
    <script>
    $(function () {
      $(".head").load("${pageContext.request.contextPath}/pages/backs/backhead.jsp");
    });
  </script>
  </head>
  <body>
    <!--头部-->
    <div class="head"></div>
    <div class="context">
      <div class="information">
        <h3>基本信息</h3>
        <table>
          <tr>
            <td>学号</td>
            <td>${student.snumber}</td>
          </tr>
          <tr>
            <td>姓名</td>
            <td>${student.sname}</td>
          </tr>
          <tr>
            <td>性别</td>
            <td>${student.sgenderStr}</td>
          </tr>
          <tr>
            <td>学院班级</td>
            <td>${student.collegeClass}</td>
          </tr>
          <tr>
            <td>联系电话</td>
            <td>${student.telephone}</td>
          </tr>
          <tr>
            <td>个性签名</td>
            <td>${student.signature}</td>
          </tr>
          <tr>
            <td>头像</td>
            <td><img src="${pageContext.request.contextPath}/userpictures/${student.imgpath}" height="100px"></td>
          </tr>
        </table>
      </div>

      <div class="my_club">
        <h3>${student.snumber}参加的社团</h3>
        <div class="overflow_scroll">
          <table border="1" cellspacing="0">
            <tr>
              <td>社团名</td>
              <td>社长</td>
              <td>社长电话</td>
              <td>角色</td>
            </tr>
            <c:forEach items="${student.communities}" var="community">
              <tr>
                <td><a target="_blank" href="/show/communityPage?cnumber=${community.cnumber}">${community.cname}</a></td>
                <td>${community.students.get(0).sname}</td>
                <td>${community.students.get(0).telephone}</td>
                <c:if test="${community.students.get(0).snumber==cookie.community.value}">
                  <td>社长</td>
                </c:if>
                <c:if test="${community.students.get(0).snumber!=cookie.community.value}">
                  <td>社员</td>
                </c:if>
              </tr>
            </c:forEach>
          </table>
        </div>
      </div>

      <div class="my_active">
        <h3>${student.snumber}参加的活动</h3>
        <div class="overflow_scroll">
          <table border="1" cellspacing="0">
            <tr>
              <td>活动名</td>
              <td>活动策划</td>
              <td>联系电话</td>
              <td>操作</td>
            </tr>
            <c:forEach items="${student.activities}" var="activity">
              <tr>
                <td><a target="_blank" href="/show/activityPage?anumber=${activity.anumber}">${activity.atitle}</a></td>
                <td>${activity.organizer}</td>
                <td>${activity.telephone}</td>
                <td>
                  <c:forEach items="${stagingTableList}" var="stagingTable">
                    <c:if test="${stagingTable.anumber.equals(activity.anumber)}">
                      <c:if test="${stagingTable.signing==0}">
                        <p>未签到</p>
                      </c:if>
                      <c:if test="${stagingTable.signing==1}">
                        <p>已签到</p>
                      </c:if>
                    </c:if>
                  </c:forEach>
                </td>
              </tr>
            </c:forEach>
          </table>
        </div>
      </div>

      <div class="my_active">
        <h3>${student.snumber}的留言</h3>
        <div class="overflow_scroll">
          <table border="1" cellspacing="0">
            <tr>
              <td>序号</td>
              <td>留言内容</td>
              <td>留言时间</td>
            </tr>
            <c:forEach items="${student.messages}" var="message" varStatus="status">
              <tr>
                <td>${status.index+1}</td>
                <td>&emsp;&emsp;${message.mecontext}</td>
                <td>${message.metimeStr}</td>
              </tr>
            </c:forEach>
          </table>
        </div>
      </div>
    </div>
  </body>
</html>
