<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ch">
  <head>
    <link href="${pageContext.request.contextPath}/images/favicon.ico" rel="icon">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <meta charset="UTF-8">
    <title>社团修改页面</title>
    <style>
      *{
        margin: 0;
        padding: 0;
      }
      .context > div{
        float: left;
        margin-left: 20px;
        min-width: 400px;
        margin-top: 30px;
      }
      .clear_both{
        clear:both;
      }

      table {
        width: 100%;
        border-top: 1px solid #ccc;
        border-left: 1px solid #ccc;
        margin: 0;
        border-collapse: collapse;
        border-spacing: 0;
      }
      div > form{
        text-align: center;
      }
      /*控制cellspacing*/
      table td {
        padding: 10px;
        border-right: 1px solid #ccc;
        border-bottom: 1px solid #ccc;
      }
      .context{
        width: 80%;
        margin: 0 auto;
        margin-top: 10px;
      }
    </style>
    <script>
      $(function () {
        $(".head").load("${pageContext.request.contextPath}/pages/backs/backhead.jsp");
      });
    </script>
  </head>
  <body>
    <div class="head"></div>
    <div class="context">
      <div class="information">
        <h3>基本信息</h3>
        <table>
          <tr>
            <td>社团名</td>
            <td>${community.cname}</td>
          </tr>
          <tr>
            <td>社团编号</td>
            <td>${community.cnumber}</td>
          </tr>
          <tr>
            <td>社长</td>
            <td>${community.students.get(0).sname}</td>
          </tr>
          <tr>
            <td>社长联系电话</td>
            <td>${community.students.get(0).telephone}</td>
          </tr>
          <tr>
            <td>社团创建时间</td>
            <td>${community.creationtimeStr}</td>
          </tr>
          <tr>
            <td>社团人数</td>
            <td>${community.peoples}</td>
          </tr>
        </table>
      </div>

      <div>
        <form action="${pageContext.request.contextPath}/backs/change_communityNandI" method="post">
          <input type="hidden" name="cnumber" value="${community.cnumber}">
          <h3>社团简介</h3>
          <textarea name="introduction" style="resize:none;" cols="30" rows="10">${community.introduction}</textarea> <br>
          <h3>社团公告</h3>
          <textarea name="notice" style="resize:none;" cols="30" rows="5">${community.notice}</textarea> <br>
          <input type="submit" value="更新信息">
        </form>
      </div>

      <div>
        <h3>成员</h3>
        <table>
          <tr>
            <td>姓名</td>
            <td>学号</td>
            <td>性别</td>
            <td>联系电话</td>
            <td>操作</td>
          </tr>
          <c:forEach items="${community.peopleList}" var="people">
            <tr style="line-height: 20px;height: 20px;">
              <td>${people.sname}</td>
              <td>${people.snumber}</td>
              <td>${people.sgenderStr}</td>
              <td>${people.telephone}</td>
              <td>
                <c:if test="${not community.students.get(0).snumber.equals(people.snumber)}">
                  <a href="${pageContext.request.contextPath}/backs/quit_community?snumber=${people.snumber}&cnumber=${community.cnumber}">请出社团</a>
                </c:if>
                <c:if test="${community.students.get(0).snumber.equals(people.snumber)}">
                  社长
                </c:if>
              </td>
            </tr>
          </c:forEach>
        </table>
      </div>

      <div class="clear_both">
        <table>
          <tr>
            <td>社团logo</td>
            <td>社团封面</td>
          </tr>
          <tr>
            <td><img src="/grouppictures/${community.imgpath}" width="200px" alt="logo不存在"></td>
            <td><img src="/grouppictures/poster_${community.imgpath}" height="450px" alt="logo不存在"></td>
          </tr>
        </table>
      </div>

      <div>
        <form action="${pageContext.request.contextPath}/backs/change_communityLogoImage" method="post" enctype="multipart/form-data">
          <input type="hidden" name="cnumber" value="${community.cnumber}">
          <input type="file" name="imageLogo"/>
          <input type="submit" value="更改logo">
        </form>
        <br>
        <form action="${pageContext.request.contextPath}/backs/change_communityPosterImage" method="post" enctype="multipart/form-data">
          <input type="hidden" name="cnumber" value="${community.cnumber}">
          <input type="file" name="imagePoster"/>
          <input type="submit" value="更改封面">
        </form>
      </div>
    </div>
  </body>
</html>