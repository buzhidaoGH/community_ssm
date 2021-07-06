<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ch">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="${pageContext.request.contextPath}/images/favicon.ico" rel="icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base.js"></script>
    <title>学生社团首页</title>
  </head>
  <body>
    <!--头部-->
    <div class="head">
      <div class="head-top">
        <div class="head-top-wrap">
          <span>&emsp;&emsp;欢迎访问学生社团工作网站！</span>
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
            <marquee behavior="1">${newsOne.ncontent}</marquee>
          </div>
        </div>
      </div>
      <div class="head-wrap">
        <div class="head-wrap-left">
          <img src="${pageContext.request.contextPath}/images/logo-transparent.png" height="80px">
        </div>
        <c:if test="${not empty cookie.community}">
          <div class="head-wrap-right">
            <a href="javascript:void(0)" id="personal_center">个人中心</a>
            <span>/</span>
            <a href="${pageContext.request.contextPath}/pages/userLogout">退出登录</a>
          </div>
        </c:if>
        <c:if test="${empty cookie.community}">
          <div class="head-wrap-right">
            <a href="pages/userlogin.jsp">登录</a>
            <span>/</span>
            <a href="pages/userregister.jsp">注册</a>
          </div>
        </c:if>

      </div>
    </div>

    <!--导航-->
    <div class="nav">
      <div class="navigation">
        <ul>
          <li id="base_index" class="navigation_active"><a href="javascript:void(0)">首页</a></li>
          <li id="group_list"><a href="javascript:void(0)">社团列表</a></li>
          <li id="group_active"><a href="javascript:void(0)">社团活动</a></li>
          <li id="news_list"><a href="javascript:void(0)">新闻列表</a></li>
          <!--<li id="personal_center"><a href="javascript:void(0)">个人中心</a></li>-->
          <c:if test="${not empty cookie.community}">
            <li id="message_list" snumber="${cookie.community.value}"><a href="javascript:void(0)">留言</a></li>
          </c:if>
          <li id="introduction"><a href="javascript:void(0)">系统简介</a></li>
        </ul>
      </div>
    </div>

    <!--校园公告
     <div class="notice2">
       校园公告
     </div>
     -->

    <!--内容/导入网页-->
    <div class="content">
      <div class="loading">
      </div>
    </div>

    <!--页脚-->
    <div class="foot">
      <div class="foot_wrap">
        <div class="wrap_left">
          <p style="margin-top: 10px; color: #95BEFD;">友情链接：</p>
          <br>
          <a href="http://www.hnsyu.edu.cn/" target="_blank">学校官网</a>
          <a href="http://www.hnsyu.edu.cn//syxybgdh.html" target="_blank">办公电话</a>
          <a href="http://www.gqt.org.cn/" target="_blank">中国共青团</a>
          <a href="http://ygpt.hnsyu.net:8010/index.shtml" target="_blank">阳光服务</a>
          <br><br>
          <a href="http://www.hnsyu.edu.cn/jyjx/" target="_blank">学校教务处</a>
          <a href="http://lib.hnsyu.net/" target="_blank">图书馆</a>
          <a href="${pageContext.request.contextPath}/pages/backlogin.jsp">后台管理</a>
        </div>
        <div class="wrap_logo">
          <img src="${pageContext.request.contextPath}/images/logo-weixin220-100.png">
        </div>
        <div class="wrap_right">
          <!-- 版权内容请在本组件"内容配置-版权"处填写 -->
          <p>Copyright © 2020XX大学社团联合会 版权所有.</p>
          <p>地址：XX大学校区团委办公室</p>
          <c:forEach items="${managers}" var="manager" begin="0" end="1" varStatus="status">
          <p>管理员_${manager.mname}电话：${manager.telephone}</p>
          </c:forEach>
        </div>
      </div>
    </div>
  </body>
</html>