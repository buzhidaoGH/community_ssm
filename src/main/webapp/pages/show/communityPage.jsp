<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ch">
  <head>
    <meta charset="UTF-8">
    <title>社团页面</title>
    <link href="${pageContext.request.contextPath}/images/favicon.ico" rel="icon">
    <style>
      table {
        width: 200px;
        border-top: 1px solid #999;
        border-left: 1px solid #999;
      }
      table td {
        border-bottom: 1px solid #999;
        border-right: 1px solid #999;
      }
      .images{
        float: left;
      }
      .clear_both{
        clear:both;
      }
    </style>
  </head>
  <body>
    <h3>社团名：${community.cname}</h3>
    <h3>社长：${community.students.get(0).sname}</h3>
    <h3>社长联系电话：${community.students.get(0).telephone}</h3>
    <h3>社团创建时间：${community.creationtimeStr}</h3>
    <h3>社团人数：${community.peoples}</h3>
    <c:if test="${not dataExist}">
      <a href="/change/addJoinClub?cnumber=${community.cnumber}">加入${community.cname}</a>
    </c:if>
    <c:if test="${dataExist}">
      <table>
        <tr>
          <td>姓名</td>
          <td>性别</td>
          <td>联系电话</td>
        </tr>
        <c:forEach items="${community.peopleList}" var="people">
          <tr style="line-height: 20px;height: 20px;">
            <td>${people.sname}</td>
            <td>${people.sgenderStr}</td>
            <td>${people.telephone}</td>
          </tr>
        </c:forEach>
      </table>
    </c:if>
    <div class="images">
      <h3>社团logo</h3>
      <img src="/grouppictures/${community.imgpath}" alt="logo不存在">
    </div>
    <div class="images">
      <h3>招新海报</h3>
      <img height="550px" src="/grouppictures/poster_${community.imgpath}" alt="logo不存在">
    </div>
    <div class="clear_both"></div>
    <h3>社团公告：${community.notice}</h3>
    <h3>社团简介：</h3>
    <p>${community.introduction}</p>

  </body>
</html>