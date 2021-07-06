<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ch">
  <head>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/images/favicon.ico" rel="icon">
    <title>管理后台</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/back_basics.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/back_community.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script>
      $(function () {
        $(".head").load("${pageContext.request.contextPath}/pages/backs/backhead.jsp");

        $(".context_right .community_search button").click(function () {
          var cname = $("#cname").val();
          //alert(sname+":"+snumber);
          $(location).attr("href", "management_community?cname="+cname);
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
          <li><a href="${pageContext.request.contextPath}/backs/management_user">用户管理</a></li>
          <li class="back_list"><a href="${pageContext.request.contextPath}/backs/management_community">社团管理</a></li>
          <li><a href="${pageContext.request.contextPath}/backs/management_activity">活动管理</a></li>
          <li><a href="${pageContext.request.contextPath}/backs/management_news">新闻管理</a></li>
          <li><a href="${pageContext.request.contextPath}/backs/management_message">留言管理</a></li>
        </ul>
      </div>

      <!--右内容-->
      <div class="context_right">
        <div class="community_search">
          社团名称：<input type="text" value="${cname}" name="cname" id="cname" placeholder="请输入社团名称">
          <button>查找</button>
          &emsp;&emsp;<a target="_blank" href="${pageContext.request.contextPath}/pages/backs/new_community.jsp?cnumber=${pageInfo.total+1}" style="border: 1px solid #000">新建社团</a>
          总共<span style="color: #FE7300">&ensp;${pageInfo.total}&ensp;</span>条
          &emsp;总共<span style="color: #FE7300">&ensp;${pageInfo.pages}&ensp;</span>页
        </div>
        <div class="community_list">
          <table>
            <tr>
              <td>编号</td>
              <td>社团名称</td>
              <td>社团logo</td>
              <td>社长名称</td>
              <td>成立时间</td>
              <td>社员数量</td>
              <td>操作</td>
            </tr>
            <c:forEach items="${pageInfo.list}" var="community" varStatus="status">
              <tr>
                <td>${community.cnumber}</td>
                <td><a target="_blank" href="${pageContext.request.contextPath}/show/communityPage?cnumber=${community.cnumber}">${community.cname}</a></td>
                <td><img src="${pageContext.request.contextPath}/grouppictures/${community.imgpath}" height="100"></td>
                <td>${community.students.get(0).sname}</td>
                <td>${community.creationtimeStr}</td>
                <td>${community.peoples}</td>
                <td><a target="_blank" href="${pageContext.request.contextPath}/backs/show_community?cnumber=${community.cnumber}">编辑</a>
                  <a href="${pageContext.request.contextPath}/backs/delete_community?cnumber=${community.cnumber}" onclick="return window.confirm('您确认删除该社团吗？');">删除</a></td>
              </tr>
            </c:forEach>
          </table>
        </div>
        <div class="community_list_page">
          <ul class="pagination">
            <li><a href="${pageContext.request.contextPath}/backs/management_community?page=${pageInfo.prePage}&cname=${cname}">«</a></li>
            <c:forEach begin="1" end="${pageInfo.pages}" var="pageNow">
              <c:if test="${pageNow==pageInfo.pageNum}">
                <li><a class="pagination_active">${pageNow}</a></li>
              </c:if>
              <c:if test="${pageNow!=pageInfo.pageNum}">
                <li><a href="${pageContext.request.contextPath}/backs/management_community?page=${pageNow}&cname=${cname}">${pageNow}</a></li>
              </c:if>
            </c:forEach>
            <li><a href="${pageContext.request.contextPath}/backs/management_community?page=${pageInfo.nextPage}&cname=${cname}">»</a></li>
          </ul>
        </div>
      </div>
    </div>
  </body>
</html>