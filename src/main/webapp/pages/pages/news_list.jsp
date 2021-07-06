<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/new_list.css" type="text/css" />
<script type="text/javascript" src="js/new_list.js"></script>
<div class="news_list">
  <div class="news_list_search">
    <span>标题：</span>
    <input type="text" name="news_title" id="news_title" value="${ntitle}" placeholder="请输入新闻标题">
    <input type="button" id="news_search" value="搜索">
  </div>

  <div class="news_list_list">
    <table>
      <tr>
        <th>新闻标题</th>
        <th>发布人</th>
        <th>发布时间</th>
      </tr>
      <c:forEach items="${pageInfo.list}" var="news">
        <tr>
          <td><a target="_blank" href="/show/newsPage?nnumber=${news.nnumber}">${news.ntitle}</a></td>
          <td>${news.nauthor}</td>
          <td>${news.ntimeLongStr}</td>
        </tr>
      </c:forEach>
      <%--<tr>
        <td><a href="#">大学生挑战杯</a></td>
        <td>克里</td>
        <td>2018-10-10 22:10</td>
      </tr>--%>
    </table>
  </div>

  <div class="news_list_page">
    <ul class="pagination">
      <li><a path="${pageContext.request.contextPath}/pages/news_list?page=${pageInfo.prePage}&size=${pageInfo.pageSize}&atitle=${atitle}">«</a></li>
        <c:forEach begin="1" end="${pageInfo.pages}" var="pageNow">
          <c:if test="${pageNow==pageInfo.pageNum}">
            <li><a class="pagination_news">${pageNow}</a></li>
          </c:if>
          <c:if test="${pageNow!=pageInfo.pageNum}">
            <li><a path="${pageContext.request.contextPath}/pages/news_list?page=${pageNow}&size=${pageInfo.pageSize}&atitle=${atitle}">${pageNow}</a></li>
          </c:if>
        </c:forEach>
      <li><a path="${pageContext.request.contextPath}/pages/news_list?page=${pageInfo.pageNum+1}&size=${pageInfo.pageSize}&atitle=${atitle}">»</a></li>
    </ul>
    &emsp;&emsp;总页数：${pageInfo.pages}&emsp;&emsp;总条数：${pageInfo.total}
  </div>
</div>