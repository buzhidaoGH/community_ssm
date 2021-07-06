<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ch">
  <head>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/images/favicon.ico" rel="icon">
    <title>管理后台</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/back_basics.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script>
      $(function () {
        $(".head").load("${pageContext.request.contextPath}/pages/backs/backhead.jsp");

        $(".context_right .activity_search button").click(function () {
          var atitle = $("#atitle").val();
          var size = $("#size").val();
          $(location).attr("href", "management_activity?size="+size+"&atitle="+atitle);
        });
      });
    </script>

    <style>
      .context_right .activity_search {
        font-size: 20px;
      }

      .context_right .activity_search input {
        width: 180px;
        height: 30px;
        font-size: 18px;
        text-indent: 5px;
      }

      .context_right .activity_search button {
        width: 60px;
        height: 30px;
        font-size: 18px;

      }

      .context_right .activity_list {
        width: 100%;
      }

      .context_right .activity_list table {
        width: 100%;
        border-top: 1px solid #999;
        border-left: 1px solid #999;
        border-spacing: 0; /*去掉单元格间隙*/
      }

      .context_right .activity_list table td {
        padding: 10px 30px;
        border-bottom: 1px solid #999;
        border-right: 1px solid #999;
      }

      .activity_list_page {
        margin-top: 10px;
      }

      .activity_list_page ul {
        display: inline-block;
      }

      .activity_list_page ul li {
        display: inline;
      }

      .activity_list_page .pagination li a {
        color: black;
        float: left;
        padding: 8px 16px;
        text-decoration: none;
        border-radius: 5px;
        border: 1px solid #ddd;
      }

      .pagination_active {
        background-color: #4CAF50;
        color: white;
        border-radius: 5px;
      }

      .activity_list_page ul li a:hover:not(.pagination_active) {
        background-color: #ddd;
      }
    </style>
  </head>
  <body>
    <!--头部-->
    <div class="head">
    </div>

    <!--内容-->
    <div class="context">
      <!--左内容-->
      <div class="context_left">
        <ul>
          <li><a href="${pageContext.request.contextPath}/backs/backindex">首&emsp;&emsp;页</a></li>
          <li><a href="${pageContext.request.contextPath}/backs/management_user">用户管理</a></li>
          <li><a href="${pageContext.request.contextPath}/backs/management_community">社团管理</a></li>
          <li class="back_list"><a href="${pageContext.request.contextPath}/backs/management_activity">活动管理</a></li>
          <li><a href="${pageContext.request.contextPath}/backs/management_news">新闻管理</a></li>
          <li><a href="${pageContext.request.contextPath}/backs/management_message">留言管理</a></li>
        </ul>
      </div>

      <!--右内容-->
      <div class="context_right">
        <div class="activity_search">
          活动标题：<input type="text" value="${atitle}" name="atitle" id="atitle" placeholder="请输入活动标题">
          <select name="size" id="size">
            <c:if test="${pageInfo.pageSize==3}">
              <option value="3" selected>3</option>
              <option value="6">6</option>
            </c:if>
            <c:if test="${pageInfo.pageSize==6}">
              <option value="3" >3</option>
              <option value="6" selected>6</option>
            </c:if>
          </select>
          <button>查找</button>
          &emsp;&emsp;<a target="_blank" href="${pageContext.request.contextPath}/pages/backs/new_active.jsp?anumber=${pageInfo.total+1}" style="border: 1px solid #000">新建活动</a>
          &emsp;总共<span style="color: #FE7300">&ensp;${pageInfo.total}&ensp;</span>条
          &emsp;总共<span style="color: #FE7300">&ensp;${pageInfo.pages}&ensp;</span>页
        </div>
        <div class="activity_list">
          <table>
            <tr>
              <td>活动编号</td>
              <td>活动标题</td>
              <td>举办方</td>
              <td>负责人电话</td>
              <td>举办地点</td>
              <td>开始时间</td>
              <td>结束时间</td>
              <td>活动状态</td>
              <td>参与人数</td>
              <td>操作</td>
            </tr>
            <c:forEach items="${pageInfo.list}" var="activity" >
              <tr>
                <td>${activity.anumber}</td>
                <td><a style="color: #289228;" target="_blank" href="${pageContext.request.contextPath}/show/activityPage?anumber=${activity.anumber}">${activity.atitle}</a></td>
                <td>${activity.organizer}</td>
                <td>${activity.telephone}</td>
                <td>${activity.alocation}</td>
                <td>${activity.starttimeLongStr}</td>
                <td>${activity.endtimeLongStr}</td>
                <td>${activity.varStatus}</td>
                <td>${activity.peoples}</td>
                <td><a style="color: #001BA0;" target="_blank" href="${pageContext.request.contextPath}/backs/show_activity?anumber=${activity.anumber}">编辑</a></td>
              </tr>
            </c:forEach>
          </table>
        </div>
        <div class="activity_list_page">
          <ul class="pagination">
            <li><a href="${pageContext.request.contextPath}/backs/management_activity?size=${pageInfo.pageSize}&page=${pageInfo.prePage}&atitle=${atitle}">«</a></li>
            <c:forEach begin="1" end="${pageInfo.pages}" var="pageNow">
              <c:if test="${pageNow==pageInfo.pageNum}">
                <li><a class="pagination_active">${pageNow}</a></li>
              </c:if>
              <c:if test="${pageNow!=pageInfo.pageNum}">
                <li><a href="${pageContext.request.contextPath}/backs/management_activity?size=${pageInfo.pageSize}&page=${pageNow}&atitle=${atitle}">${pageNow}</a></li>
              </c:if>
            </c:forEach>
            <li><a href="${pageContext.request.contextPath}/backs/management_activity?size=${pageInfo.pageSize}&page=${pageInfo.nextPage}&atitle=${atitle}">»</a></li>
          </ul>
        </div>
      </div>
    </div>
  </body>
</html>