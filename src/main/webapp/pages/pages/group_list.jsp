<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/group_list.css" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/group_list.js"></script>
<div class="group_list">
  <div class="group_list_left">
    <h2>社团列表</h2>
    <ul>
      <c:forEach items="${communities}" var="community" varStatus="status">
        <c:if test="${status.first}">
          <li class="group_active" cnumber="${community.cnumber}">${community.cname}</li>
        </c:if>
        <c:if test="${not status.first}">
          <li cnumber="${community.cnumber}">${community.cname}</li>
        </c:if>
      </c:forEach>
      <%--<li class="group_active" cid="wudaoshe">舞蹈社</li>
      <li cid="valuevalue">围棋社</li>
      <li>武术社</li>
      <li>青年社团</li>
      <li>篮球社团</li>
      <li>书法社团</li>
      <li>舞蹈社</li>
      <li>围棋社</li>
      <li>武术社</li>
      <li>青年社团</li>
      <li>篮球社团</li>
      <li>书法社团</li>--%>
    </ul>
  </div>

  <div class="group_list_right">
    <span>首页-社团列表-社团详情</span>
    <div class="club_details">
      <h2>${communityOne.cname}</h2>
      <span>社长：${president.sname}</span>
      <br>
      <span>成立时间：${communityOne.creationtimeStr}</span>
      <br>
      <a target="_blank" href="${pageContext.request.contextPath}/show/communityPage?cnumber=${communityOne.cnumber}">社团首页</a>
      <br>
      <a href="grouppictures/poster_${communityOne.imgpath}" target="_Blank"><img src="grouppictures/poster_${communityOne.imgpath}" height="300px"></a>
      <p>&emsp;&emsp;${communityOne.introduction}</p>
    </div>
  </div>
</div>