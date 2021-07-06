<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!Doctype html>
<html lang="ch">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>个人中心</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/personal_center.css" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/personal_center.js"></script>
    <link href="${pageContext.request.contextPath}/images/favicon.ico" rel="icon">
  </head>
  <body>
    <!--头部-->
    <div class="head">
      <div class="head-top">
        <div class="head-top-wrap">
          <span>&emsp;欢迎访问学生社团工作网站！</span>
          <div class="top-link">
            <a href="">官方QQ
              <div id="qq_QRcode"><img src="${pageContext.request.contextPath}/images/qq_QRcode.png" width="150px" height="150px"></div>
            </a>
            <span>/</span>
            <a href="">官方微信
              <div id="wx_QRcode"><img src="${pageContext.request.contextPath}/images/wx_QRcode.png" width="150px" height="150px"></div>
            </a>
          </div>
          <!--公告-->
          <div class="notice">
            <marquee behavior="1">校园公告校园公告校园公告校园公告校园公告校园公告</marquee>
          </div>
        </div>
      </div>
      <div class="head-wrap">
        <div class="head-wrap-left">
          <img src="../../images/logo-transparent.png" height="80px">
        </div>
        <div class="head-wrap-right">
          <a href="../userlogin.jsp">登录</a>
          <span>/</span>
          <a href="../userregister.jsp">注册</a>
        </div>
      </div>
    </div>

    <!--导航-->
    <div class="nav">
      <div class="navigation">
        <ul>
          <li id="base_index"><a href="../main.jsp">首页</a></li>
          <li id="personal_center" class="navigation_active"><a href="javascript:void(0)">个人中心</a></li>
        </ul>
      </div>
    </div>

    <!--内容/导入网页-->
    <div class="content">
      <div class="loading">
        <div class="personal_center">
          <div class="personal_center_left">
            <ul>
              <li><span>个人中心</span></li>
              <li><a href="../show/changepwd.jsp">更改密码</a></li>
              <li><img src="../../images/header.png" width="100%"></li>
            </ul>
          </div>
          <div class="personal_center_context">
            <div class="information">

              <h3>基本信息</h3>
              <form action="#" method="post" enctype="multipart/form-data">
                学&emsp;&emsp;号：<input type="text" name="" readonly unselectable="on" value="XXXXXX"><br>
                姓&emsp;&emsp;名：<input type="text"  readonly unselectable="on"value="XXXXXX" ><br>
                性&emsp;&emsp;别：<input type="text"  readonly value="XXXXXX"><br>
                学院班级：<input type="text" readonly value="XXXXXX"><br>
                联系电话：<input type="text" readonly value="XXXXXX"><br>
                个性签名：<input type="text" value="我的个性签名我的个性签名我的个性签名我的个性签名" ><br>
                <input type="submit" value="更改信息" />
              </form>
              <form action="#" method="post" enctype="multipart/form-data">
                头像：<input type="file" name="image"><br>
                <input type="submit" value="更改头像" />
              </form>
            </div>
            <div class="my_club">
              <h3>我的社团</h3>
              <table border="1" cellspacing="0">
                <tr>
                  <td>社团名</td>
                  <td>社长</td>
                  <td>操作</td>
                </tr>
                <tr>
                  <td><a href="#">舞蹈社</a></td>
                  <td>苏二</td>
                  <td><a href="#">退社</a></td>
                </tr>
                <tr>
                  <td><a href="#">舞蹈社</a></td>
                  <td>苏二</td>
                  <td><a href="#">退社</a></td>
                </tr>
                <tr>
                  <td><a href="#">舞蹈社</a></td>
                  <td>苏二</td>
                  <td><a href="#">退社</a></td>
                </tr>
              </table>
            </div>

            <div class="my_active">
              <h3>我的活动</h3>
              <table border="1" cellspacing="0">
                <tr>
                  <td>活动名</td>
                  <td>活动策划</td>
                  <td>联系电话</td>
                </tr>
                <tr>
                  <td><a href="#">青春舞动</a></td>
                  <td>苏二</td>
                  <td>13333333333</td>
                </tr>
                <tr>
                  <td><a href="#">青春舞动</a></td>
                  <td>苏二</td>
                  <td>13333333333</td>
                </tr>
                <tr>
                  <td><a href="#">青春舞动</a></td>
                  <td>苏二</td>
                  <td>13333333333</td>
                </tr>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>

  </body>
</html>

