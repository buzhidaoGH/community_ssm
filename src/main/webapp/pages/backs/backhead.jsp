<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<style>
  body{
    background-color: #F1F8FF;
  }

  .head {
    margin: 0 auto;
    background: linear-gradient(90deg, #0470B2, #C1E071);
    height: 60px;
    min-width: 1200px;
  }

  .head h3 {
    margin-top: 10px;
    float: left;
    line-height: 40px;
    height: 40px;
    margin-left: 20px;
    border-radius: 5px;
  }

  .head h3 a {
    color: #fff;
  }

  .head .head_title {
    margin: 0 auto;
    width: 200px;
    height: 60px;
    line-height: 60px;
    font-size: 22px;
    color: #fff;
    font-family: 黑体;
  }

  #datetime {
    float: right;
    font-size: 20px;
    line-height: 60px;
    height: 60px;
  }
</style>
<div class="head">
  <h3>当前账号：${cookie.back_community.value}</h3>
  <h3 style="background-color: #FA7298;width: 100px;text-align: center"><a href="${pageContext.request.contextPath}/backs/backLogOut">退出登录</a></h3>
  <h3 style="background-color: #B6DA75;width: 100px;text-align: center"><a target="_blank" href="${pageContext.request.contextPath}/index">门户首页</a></h3>
  <div id="datetime"></div>
  <script>
    setInterval("document.getElementById('datetime').innerHTML=new Date().toLocaleString();", 1000);
  </script>
  <div class="head_title">
    大学生社团后台管理
  </div>
</div>