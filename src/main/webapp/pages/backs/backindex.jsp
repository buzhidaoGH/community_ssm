<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ch">
  <head>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/images/favicon.ico" rel="icon">
    <title>管理后台</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/back_basics.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/back_index.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script>
      $(function () {
        $(".head").load("${pageContext.request.contextPath}/pages/backs/backhead.jsp");
      });
    </script>
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
          <li class="back_list"><a href="${pageContext.request.contextPath}/backs/backindex">首&emsp;&emsp;页</a></li>
          <li><a href="${pageContext.request.contextPath}/backs/management_user">用户管理</a></li>
          <li><a href="${pageContext.request.contextPath}/backs/management_community">社团管理</a></li>
          <li><a href="${pageContext.request.contextPath}/backs/management_activity">活动管理</a></li>
          <li><a href="${pageContext.request.contextPath}/backs/management_news">新闻管理</a></li>
          <li><a href="${pageContext.request.contextPath}/backs/management_message">留言管理</a></li>
        </ul>
      </div>

      <!--右内容-->
      <div class="context_right">
        <div class="index_left1 index_left">
          <span>社团总数</span><br>
          <span style="color: #FE7300">${countCommunity}</span>
        </div>

        <div class="index_left2 index_left">
          <span>活动总数</span><br>
          <span style="color: #FE7300">${countActivity}</span>
        </div>

        <div class="index_left3 index_left">
          <span>新闻总数</span><br>
          <span style="color: #FE7300">${countNews}</span>
        </div>

        <div class="index_left4 index_left">
          <span>用户总数</span><br>
          <span style="color: #FE7300">${countStudent}</span>
        </div>

        <div class="index_left5 index_left">
          <span>留言总数</span><br>
          <span style="color: #FE7300">${countMessage}</span>
        </div>

        <div class="news_now">
          <form method="post" action="${pageContext.request.contextPath}/backs/change_notice">
            <h3>当前轮播的公告</h3>
            <textarea name="ncontent" style="font-size: 16px;margin: 0px; height: 42px;width: 500px;">${newsOne.ncontent}</textarea><br>
            <input type="submit" style="width:140px;height:36px;line-height:18px;font-size:18px;color:#000;padding-bottom:4px" value="更改公告">
          </form>
        </div>
      </div>
    </div>


  </body>
</html>