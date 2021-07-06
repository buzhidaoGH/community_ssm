<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/personal_center.css" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/personal_center.js"></script>
<div class="personal_center">
  <div class="personal_center_left">
    <ul>
      <li><span>个人中心</span></li>
      <li><a target="_blank" href="${pageContext.request.contextPath}/pages/show/changepwd.jsp">更改密码</a></li>
      <li><img src="${pageContext.request.contextPath}/userpictures/${student.imgpath}" width="100%" height="296px"></li>
    </ul>
  </div>
  <div class="personal_center_context">
    <div class="information">
      <h3>基本信息</h3>
      <form action="/change/signature" target="the_iframe" method="post">
        学&emsp;&emsp;号：<span>${student.snumber}</span><br>
        姓&emsp;&emsp;名：<span>${student.sname}</span><br>
        性&emsp;&emsp;别：<span>${student.sgenderStr}</span><br>
        学院班级：<span>${student.collegeClass}</span><br>
        联系电话：<span>${student.telephone}</span><br>
        个性签名：<br>
        <textarea name="signature" id="signature" cols="30" rows="3">${student.signature}</textarea> <br>
        <input type="submit" value="更改信息" />
      </form>
      <form action="change/head_image" method="post" enctype="multipart/form-data">
        头像：<input type="file" name="image"><br>
        <input type="submit" value="更改头像" />
      </form>
      <iframe id="is_iframe" name="the_iframe" style="display:none;"></iframe>
    </div>
    <div class="my_club">
      <h3>我参加的社团</h3>
      <div class="overflow_scroll">
        <table border="1" cellspacing="0">
          <tr>
            <td>社团名</td>
            <td>社长</td>
            <td>社长电话</td>
            <td>操作</td>
          </tr>
          <c:forEach items="${student.communities}" var="community">
            <tr>
              <td><a target="_blank" href="/show/communityPage?cnumber=${community.cnumber}">${community.cname}</a></td>
              <td>${community.students.get(0).sname}</td>
              <td>${community.students.get(0).telephone}</td>
              <c:if test="${community.students.get(0).snumber==cookie.community.value}">
                <td><a>你是社长</a></td>
              </c:if>
              <c:if test="${community.students.get(0).snumber!=cookie.community.value}">
                <td><a style="color: #0000EE" cnumber="${community.cnumber}">退社</a></td>
              </c:if>
            </tr>
          </c:forEach>
          <%--<tr>
            <td><a href="#">舞蹈社</a></td>
            <td>苏二</td>
            <td>13333333333</td>
            <td><a href="#">退社</a></td>
          </tr>--%>
        </table>
      </div>
    </div>

    <div class="my_active">
      <h3>我参加的活动</h3>
      <div class="overflow_scroll">
        <table border="1" cellspacing="0">
          <tr>
            <td>活动名</td>
            <td>活动策划</td>
            <td>联系电话</td>
            <td>操作</td>
          </tr>
          <c:forEach items="${student.activities}" var="activity">
            <tr>
              <td><a target="_blank" href="/show/activityPage?anumber=${activity.anumber}">${activity.atitle}</a></td>
              <td>${activity.organizer}</td>
              <td>${activity.telephone}</td>
              <td>
                <c:forEach items="${stagingTableList}" var="stagingTable">
                  <c:if test="${stagingTable.anumber.equals(activity.anumber)}">
                    <c:if test="${stagingTable.signing==0}">
                      <a style="color:#0000EE" anumber="${activity.anumber}">未签到</a>
                    </c:if>
                    <c:if test="${stagingTable.signing==1}">
                      <p>已签到</p>
                    </c:if>
                  </c:if>
                </c:forEach>
              </td>
            </tr>
          </c:forEach>
          <%--<c:forEach items="${stagingTableList}" var="stagingTable">
            <p>${stagingTable.anumber}:${stagingTable.snumber}:${stagingTable.signing}:${stagingTable.signingStr}</p>
          </c:forEach>--%>
          <%--<tr>
            <td><a href="#">青春舞动</a></td>
            <td>苏二</td>
            <td>13333333333</td>
            <td><a href="#">签到</a></td>
          </tr>--%>
        </table>
      </div>
    </div>

    <div class="my_active">
      <h3>我的留言</h3>
      <div class="overflow_scroll">
        <table border="1" cellspacing="0">
          <tr>
            <td>序号</td>
            <td>留言内容</td>
            <td>留言时间</td>
          </tr>
          <c:forEach items="${student.messages}" var="message" varStatus="status">
            <tr>
              <td>${status.index+1}</td>
              <td>&emsp;&emsp;${message.mecontext}</td>
              <td>${message.metimeStr}</td>
            </tr>
          </c:forEach>
        </table>
      </div>
    </div>
  </div>
</div>

