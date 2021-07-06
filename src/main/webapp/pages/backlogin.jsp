<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>后台登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/back_login.css">
    <link href="${pageContext.request.contextPath}/images/favicon.ico" rel="icon">
  </head>

  <body>
    <form action="${pageContext.request.contextPath}/backs/backLogin" method="post">
      <div class="login">
        <div class="center">
          <h1>校园社团管理后台系统</h1>
          <h1>Login</h1><span style="color: #EE0000;">${msg}</span>
          <div class="inputLi">
            <strong>账户</strong>
            <input type="text" name="username" placeholder="请输入">
          </div>
          <div class="inputLi">
            <strong>密码</strong>
            <input type="password" name="password" placeholder="请输入">
          </div>
          <div class="inputLi">
            <strong>验证码</strong>
            <input type="text" name="checkcode" placeholder="请输入验证码">
            <img src="/checkCode" onclick="this.src=this.src+'?'+Math.random()" id="img"><a href="#" onclick="document.getElementById('img').onclick()">换一张?</a><br/>
          </div>
          <div class="inputLi">
            <input type="submit" class="button_su" value="登录">
          </div>
        </div>
      </div>
    </form>

  </body>

</html>