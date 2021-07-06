<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/basics.css" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/basics.js"></script>
<div class="basics">
  <!--活动和新闻-->
  <div class="basics_active_news">
    <div class="active_left">
      <h3>最新活动</h3>
      <ul>
        <c:forEach items="${activities}" var="activitie">
          <c:if test="${activitie.atitle.length() > 27}">
            <li><span>${activitie.starttimeStr}</span> <a target="_blank" href="/show/activityPage?anumber=${activitie.anumber}">${fn:substring(activitie.atitle,0,26)}...</a></li>
          </c:if>
          <c:if test="${activitie.atitle.length() <= 27}">
            <li><span>${activitie.starttimeStr}</span> <a target="_blank" href="/show/activityPage?anumber=${activitie.anumber}">${activitie.atitle}</a></li>
          </c:if>
        </c:forEach>
      </ul>
    </div>
    <div class="news_right">
      <h3>最新新闻</h3>
      <ul>
        <c:forEach items="${newsList}" var="news">
          <li><span>${news.ntimeStr}</span><a target="_blank" href="/show/newsPage?nnumber=${news.nnumber}">${news.ntitle}</a></li>
        </c:forEach>
       <%--<li><span>10-23</span><a href="#">百团大战 | 百社集锦，桂月争鸣</a></li>
        <li><span>10-23</span><a href="#">我校辩论队舌战群儒 摘获国家级赛事桂冠</a></li>--%>
      </ul>
    </div>
  </div>

  <!--社团列表-->
  <div class="basics_list">
    <h3>社团列表</h3>
    <div class="basics_list_auto" id="basics_list_auto">
      <div id="indemo">
        <div id="demo1">
          <c:forEach items="${communities}" var="community">
            <a target="_blank" href="/show/communityPage?cnumber=${community.cnumber}"><img src="${pageContext.request.contextPath}/grouppictures/${community.imgpath}" /><span>${community.cname}</span></a>
          </c:forEach>
          <%--<a href="#"><img src="../../grouppictures/OIP%20(1).png" /><span>药社</span></a>
          <a href="#"><img src="../../grouppictures/OIP%20(2).png" /><span>浙大读书会</span></a>
          <a href="#"><img src="../../grouppictures/OIP%20(8).png" /><span>卿云社</span></a>--%>
        </div>
        <div id="demo2"></div>
      </div>
    </div>
  </div>
</div>
