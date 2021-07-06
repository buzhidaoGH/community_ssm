<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ch">
  <head>
    <meta charset="UTF-8" />
    <link href="../images/favicon.ico" rel="icon">
    <title>注册</title>
    <style>
      * {
        margin: 0;
        padding: 0;
      }

      body {
        background: #fff url(../images/backgroud.png) 50% 50% no-repeat;
        background-size: cover;
      }

      form {
        position: relative;
        margin: 200px auto;
        width: 400px;
        height: 600px;
        text-align: center;
        border: 1px solid #ccc;
        transition: all 0.5s;
        border-radius: 10px;
        background-color: rgba(247, 0, 255, 0.336);
      }

      form:hover {
        transform: translate(10px);
        box-shadow: 10px 10px 10px 0 rgb(7, 49, 235);
      }

      input {
        margin-top: 25px;
        width: 200px;
        height: 40px;
        border-radius: 10px;
        outline: none;
        text-indent: 1em;
        border: 1px solid #ccc;
      }

      button {
        margin: 40px 10px;
        width: 80px;
        height: 40px;
        border: none;
        color: #fff;
        background-color: rgba(168, 9, 9, 0.705);
        border-radius: 10px;
        outline: none;
      }

      .content {
        position: absolute;
        right: -250px;
        height: 40px;
        width: 260px;
        text-align: center;
        line-height: 40px;
        /* border:1px solid red; */
        background-color: #a56cca;
        border-radius: 5px;
        opacity: 0;
        transition: all 0.9s;
      }

      .content1 {
        top: 20px;
      }

      .content2 {
        top: 82px;
      }

      p {
        color: #fff;
        font-size: 14px;
      }

      i {
        position: absolute;
        left: -24px;
        top: 8px;
        display: inline-block;
        width: 0;
        height: 0;
        border: 12px solid transparent;
        border-right-color: #a56cca;
      }

      input:focus ~ div {
        right: -200px;
        opacity: 1;
      }

      .sub {
        margin: 40px;
        width: 80px;
        height: 40px;
        border: none;
        color: #fff;
        border-radius: 10px;
        outline: none;
        text-indent: 0;
        background-color: rgb(236, 8, 46);
      }

      .sub:hover {
        background-color: red;
      }

      button:hover {
        background-color: red;
      }

      a {
        text-decoration: none;
        color: #fff;
        display: block;
        line-height: 40px;
        width: 100%;
        height: 100%;
      }
    </style>
  </head>

  <body>
    <!--可视化标签-->
    <!--引号使用英文状态下的引号单
    双引号都可以 但是别一单一双-->
    <form action="/student/register" method="post" enctype="multipart/form-data">
      <div style="text-align:center;">
        <h3>社团注册界面</h3>
      </div>
      <div>
        <b>学&emsp;&emsp;号：</b>
        <input type="text" placeholder="请输入学号" name="snumber" /><br />
      </div>
      <div>
        <b>姓&emsp;&emsp;名：</b>
        <input type="text" placeholder="请输入姓名" name="sname" /><br />
      </div>
      <div>
        <b>密&emsp;&emsp;码：</b>
        <input type="password" placeholder="请输入密码" name="spassword" />
      </div>
      <div>
        <b>密&emsp;&emsp;码：</b>
        <input type="password" name="apassword" placeholder="再次输入密码" />
      </div>
      <div>
        <b>性&emsp;&emsp;别：</b>
        <input type="radio" style="width: 85px;height: 25px;" name="sgender" value="1" checked="checked" />男
        <input type="radio" style="width: 85px;height: 25px;" name="sgender" value="0" />女
      </div>
      <div>
        <b>联系电话：</b>
        <input type="text" name="telephone" placeholder="联系电话" />
      </div>
      <div>
        <b>学院班级：</b>
        <input type="text" name="collegeClass" placeholder="学院班级" />
      </div>
      <input type="submit" value="注册" class="sub" />
      <button><a href="userlogin.jsp">登录页面</a></button>
    </form>
  </body>
</html>
