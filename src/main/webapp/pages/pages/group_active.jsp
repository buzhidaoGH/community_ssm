<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/group_active.css" type="text/css" />
<script type="text/javascript" src="js/group_active.js"></script>
<div class="group_active">
  <div class="group_active_search">
    <span>标题：</span>
    <input type="text" name="active_title" id="active_title" value="${atitle}" placeholder="请输入活动标题">
    <input type="button" id="active_search" value="搜索">
  </div>

  <div class="group_active_list">
    <table>
      <tr>
        <th>活动主题</th>
        <th>活动开始时间</th>
        <th>活动结束时间</th>
        <th>活动状态</th>
        <th>活动地点</th>
        <th>举办单位</th>
      </tr>
      <c:forEach items="${pageInfo.list}" var="activity">
        <tr>
          <td><a target="_blank" href="/show/activityPage?anumber=${activity.anumber}">${activity.atitle}</a></td>
          <td>${activity.starttimeLongStr}</td>
          <td>${activity.endtimeLongStr}</td>
          <td>${activity.varStatus}</td>
          <td>${activity.alocation}</td>
          <td>${activity.organizer}</td>
        </tr>
      </c:forEach>
      <%--<tr>
        <td><a href="#">大学生挑战杯</a></td>
        <td>2018-10-10 22:10</td>
        <td>2018-12-12 22:10</td>
        <td>克里</td>
        <td><a href="#">舞蹈社</a></td>
      </tr>--%>
    </table>
  </div>

  <div class="group_active_page">
    <ul class="pagination">
      <li><a path="${pageContext.request.contextPath}/pages/group_active?page=${pageInfo.prePage}&size=${pageInfo.pageSize}&atitle=${atitle}">«</a></li>
      <c:forEach begin="1" end="${pageInfo.pages}" var="pageNow">
        <c:if test="${pageNow==pageInfo.pageNum}">
          <li><a class="pagination_active">${pageNow}</a></li>
        </c:if>
        <c:if test="${pageNow!=pageInfo.pageNum}">
          <li><a path="${pageContext.request.contextPath}/pages/group_active?page=${pageNow}&size=${pageInfo.pageSize}&atitle=${atitle}">${pageNow}</a></li>
        </c:if>
      </c:forEach>
      <li><a path="${pageContext.request.contextPath}/pages/group_active?page=${pageInfo.pageNum+1}&size=${pageInfo.pageSize}&atitle=${atitle}">»</a></li>
    </ul>
      &emsp;&emsp;总页数：${pageInfo.pages}&emsp;&emsp;总条数：${pageInfo.total}
  </div>
</div>