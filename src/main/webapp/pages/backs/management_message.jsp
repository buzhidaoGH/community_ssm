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

        $(".context_right .message_search button").click(function () {
          var peopleid = $("#peopleid").val();
          var status = $("#status").val();
          var size = $("#size").val();
          $(location).attr("href", "management_message?size=" + size + "&peopleid=" + peopleid + "&status=" + status);
        });
      });
    </script>
    <style>
      .context_right .message_search {
        font-size: 20px;
      }

      .context_right .message_search input {
        width: 180px;
        height: 30px;
        font-size: 18px;
        text-indent: 5px;
      }

      .context_right .message_search button {
        width: 60px;
        height: 30px;
        font-size: 18px;

      }

      .context_right .message_list {
        width: 100%;
      }

      .context_right .message_list table {
        width: 100%;
        border-top: 1px solid #999;
        border-left: 1px solid #999;
        border-spacing: 0; /*去掉单元格间隙*/
      }

      .context_right .message_list table td {
        padding: 10px 30px;
        border-bottom: 1px solid #999;
        border-right: 1px solid #999;
      }

      .context_right .message_list table td a {
        border-radius: 5px;
        border: 1px solid #ddd;
        background-color: #00A1D6;
        text-align: center;
        display: block;
        width: 70px;
      }

      .message_list_page {
        margin-top: 10px;
      }

      .message_list_page ul {
        display: inline-block;
      }

      .message_list_page ul li {
        display: inline;
      }

      .message_list_page .pagination li a {
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

      .message_list_page ul li a:hover:not(.pagination_active) {
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
          <li><a href="${pageContext.request.contextPath}/backs/management_activity">活动管理</a></li>
          <li><a href="${pageContext.request.contextPath}/backs/management_news">新闻管理</a></li>
          <li class="back_list"><a href="${pageContext.request.contextPath}/backs/management_message">留言管理</a></li>
        </ul>
      </div>

      <!--右内容-->
      <div class="context_right">
        <div class="message_search">
          留言人：<input type="text" name="peopleid" id="peopleid" value="${peopleid}" placeholder="请输入留言人学号">
          <select name="size" id="size">
            <c:if test="${pageInfo.pageSize==3}">
              <option value="3" selected>3</option>
              <option value="6">6</option>
            </c:if>
            <c:if test="${pageInfo.pageSize==6}">
              <option value="3">3</option>
              <option value="6" selected>6</option>
            </c:if>
          </select>
          <select name="status" id="status">
            <c:if test="${''.equals(status)}">
              <option value="" selected>全看</option>
              <option value="0">未查看</option>
              <option value="1">已查看</option>
            </c:if>
            <c:if test="${'0'.equals(status)}">
              <option value="">全看</option>
              <option value="0" selected>未查看</option>
              <option value="1">已查看</option>
            </c:if>
            <c:if test="${'1'.equals(status)}">
              <option value="0">未查看</option>
              <option value="1" selected>已查看</option>
            </c:if>
          </select>
          <button>查找</button>
          &emsp;总共<span style="color: #FE7300">&ensp;${pageInfo.total}&ensp;</span>条
          &emsp;总共<span style="color: #FE7300">&ensp;${pageInfo.pages}&ensp;</span>页
        </div>
        <div class="message_list">
          <table>
            <tr>
              <td>学号</td>
              <td>留言人</td>
              <td>留言内容</td>
              <td>状态</td>
              <td>留言时间</td>
              <td>操作</td>
            </tr>
            <c:forEach items="${pageInfo.list}" var="message">
              <tr>
                <td>${message.peopleid}</td>
                <td>${message.sname}</td>
                <td>${message.mecontext}</td>
                <td>${message.statusStr}</td>
                <td>${message.metimeStr}</td>
                <c:if test="${message.status==0}">
                  <td><a href="${pageContext.request.contextPath}/backs/checkMessage?meid=${message.meid}&size=${pageInfo.pageSize}&page=${pageInfo.pageNum}&peopleid=${peopleid}&status=${status}" onclick="return window.alert('您成功查看该留言');">查看留言</a></td>
                </c:if>
                <c:if test="${message.status==1}">
                  <td><a href="${pageContext.request.contextPath}/backs/deleteMessage?meid=${message.meid}&size=${pageInfo.pageSize}&page=${pageInfo.pageNum}&peopleid=${peopleid}&status=${status}" onclick="return window.confirm('您确认删除此条留言吗？');">删除留言</a></td>
                </c:if>
              </tr>
            </c:forEach>
          </table>
        </div>
        <div class="message_list_page">
          <ul class="pagination">
            <li><a
              href="${pageContext.request.contextPath}/backs/management_message?size=${pageInfo.pageSize}&page=${pageInfo.prePage}&peopleid=${peopleid}&status=${status}">«</a>
            </li>
            <c:forEach begin="1" end="${pageInfo.pages}" var="pageNow">
              <c:if test="${pageNow==pageInfo.pageNum}">
                <li><a class="pagination_active">${pageNow}</a></li>
              </c:if>
              <c:if test="${pageNow!=pageInfo.pageNum}">
                <li>
                  <a href="${pageContext.request.contextPath}/backs/management_message?size=${pageInfo.pageSize}&page=${pageNow}&peopleid=${peopleid}&status=${status}">${pageNow}</a>
                </li>
              </c:if>
            </c:forEach>
            <li><a
              href="${pageContext.request.contextPath}/backs/management_message?size=${pageInfo.pageSize}&page=${pageInfo.nextPage}&peopleid=${peopleid}&status=${status}">»</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </body>
</html>