<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ch">
  <head>
    <meta charset="UTF-8">
    <title>更改密码</title>
    <link href="${pageContext.request.contextPath}/images/favicon.ico" rel="icon">
  </head>
  <body>
    <div style="width: 100%">
      <div style="margin: 0 auto;width: 500px;" >
        <form action="${pageContext.request.contextPath}/change/changePassword" method="post">
          原&ensp;密&ensp;码：<input type="password" name="old_pwd"><br>
          新&ensp;密&ensp;码：<input type="password" name="new_pwd"><br>
          重复密码：<input type="password" name="again_pwd" ><br>
          <input type="submit" value="更改密码">
          <h3 style="color: #EE0000;">${msg}</h3>
        </form>
      </div>
    </div>
  </body>
</html>