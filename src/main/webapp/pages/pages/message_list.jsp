<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/message_list.css" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/message_list.js"></script>
<div class="message_list">
  <span>留言</span>
  <form id="addMessage" action="/change/addMessage" method="post" target="iframeForm">
    <textarea id="mecontext" name="mecontext" cols="80" rows="10"></textarea>
    <br>
    <input type="submit" value="提交">
  </form>
  <iframe id="iframeForm" name="iframeForm" style="display:none;"></iframe>
</div>
