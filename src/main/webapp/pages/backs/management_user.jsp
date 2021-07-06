<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ch">
  <head>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/images/favicon.ico" rel="icon">
    <title>管理后台</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/back_basics.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/back_user.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script>
      $(function () {
        $(".head").load("${pageContext.request.contextPath}/pages/backs/backhead.jsp");

        $(".context_right .user_search button").click(function () {
          var snumber = $("#snumber").val();
          var sname = $("#sname").val();
          var size = $("#size").val();
          //alert(sname+":"+snumber);
          $(location).attr("href", "management_user?size="+size+"&sname="+sname+"&snumber="+snumber);
        });
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
          <li><a href="${pageContext.request.contextPath}/backs/backindex">首&emsp;&emsp;页</a></li>
          <li class="back_list"><a href="${pageContext.request.contextPath}/backs/management_user">用户管理</a></li>
          <li><a href="${pageContext.request.contextPath}/backs/management_community">社团管理</a></li>
          <li><a href="${pageContext.request.contextPath}/backs/management_activity">活动管理</a></li>
          <li><a href="${pageContext.request.contextPath}/backs/management_news">新闻管理</a></li>
          <li><a href="${pageContext.request.contextPath}/backs/management_message">留言管理</a></li>
        </ul>
      </div>

      <!--右内容-->
      <div class="context_right">
        <div class="user_search">
          学号：<input type="text" id="snumber" name="snumber" placeholder="请输入学号" value="${snumber}">
          &emsp;姓名：<input type="text" id="sname" name="sname" placeholder="请输入姓名" value="${sname}">&emsp;
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
          &emsp;<button>查找</button>
          &emsp;总共<span style="color: #FE7300">&ensp;${pageInfo.total}&ensp;</span>条
          &emsp;总共<span style="color: #FE7300">&ensp;${pageInfo.pages}&ensp;</span>页
        </div>
        <div class="user_list">
          <table>
            <tr>
              <td>序号</td>
              <td>学号</td>
              <td>姓名</td>
              <td>性别</td>
              <td>电话</td>
              <td>班级</td>
              <td>操作</td>
            </tr>
            <c:forEach items="${pageInfo.list}" var="student" varStatus="status">
              <tr>
                <td>${status.index+1}</td>
                <td>${student.snumber}</td>
                <td>${student.sname}</td>
                <td>${student.sgenderStr}</td>
                <td>${student.telephone}</td>
                <td>${student.collegeClass}</td>
                <td><a style="color: #001BA0;" target="_blank" href="/backs/show_student?snumber=${student.snumber}">详情</a></td>
              </tr>
            </c:forEach>
          </table>
        </div>
        <div class="user_list_page">
          <ul class="pagination">
            <li><a href="${pageContext.request.contextPath}/backs/management_user?size=${pageInfo.pageSize}&page=${pageInfo.prePage}&sname=${sname}&snumber=${snumber}">«</a></li>
            <c:forEach begin="1" end="${pageInfo.pages}" var="pageNow">
              <c:if test="${pageNow==pageInfo.pageNum}">
                <li><a class="pagination_active">${pageNow}</a></li>
              </c:if>
              <c:if test="${pageNow!=pageInfo.pageNum}">
                <li><a href="${pageContext.request.contextPath}/backs/management_user?size=${pageInfo.pageSize}&page=${pageNow}&sname=${sname}&snumber=${snumber}">${pageNow}</a></li>
              </c:if>
            </c:forEach>
            <li><a href="${pageContext.request.contextPath}/backs/management_user?size=${pageInfo.pageSize}&page=${pageInfo.nextPage}&sname=${sname}&snumber=${snumber}">»</a></li>
          </ul>
        </div>
      </div>
    </div>


  </body>
</html>