<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ch">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="${pageContext.request.contextPath}/images/favicon.ico" rel="icon">
    <title>活动页面</title>
  </head>
  <body>
    <h3>活动标题：${activity.atitle}</h3>
    <h3>活动编号：${activity.anumber}</h3>
    <h3>举办单位：${activity.organizer}</h3>
    <h3>活动时间：${activity.starttimeLongStr}至${activity.endtimeLongStr}</h3>
    <h3>活动状态：${activity.varStatus}</h3>
    <h3>活动内容：</h3>
    <c:if test="${activity.anumber==1011}">
      <a href="${activity.details}">${activity.atitle}</a>
    </c:if>
    <c:if test="${activity.anumber!=1011}">
      <p>${activity.details}</p>
    </c:if>
    <h3>宣传图：</h3>
    <img height="500px" src="${pageContext.request.contextPath}/activityImages/${activity.anumber}.png" alt="暂无宣传图" />
    <h3>活动地点：${activity.alocation}</h3>
    <h3>报名人数：${activity.peoples}人</h3>
    <c:if test="${not empty cookie.community}">
      <c:if test="${empty stagingTable.signingStr}">
        <h3><a href="${pageContext.request.contextPath}/change/activityEnroll?anumber=${activity.anumber}" onclick="return window.alert('您成功报名该活动!');">报名参加</a></h3>
        <h3>签到状态：无</h3>
      </c:if>
      <c:if test="${not empty stagingTable.signingStr}">
        <h3>签到状态：${stagingTable.signingStr}</h3>
      </c:if>
      <c:if test="${not empty stagingTable.signingStr}">
        <c:if test="${stagingTable.signing==0}">
          <a target="_blank" onclick="return window.alert('您成功签到!');" href="${pageContext.request.contextPath}/change/signing?anumber=${activity.anumber}">马上签到</a>
        </c:if>
      </c:if>
      </h3>
    </c:if>
    <c:if test="${empty cookie.community}">
      <h3><a href="${pageContext.request.contextPath}/change/activityEnroll?anumber=${activity.anumber}" >报名参加</a></h3>
    </c:if>
  </body>
</html>