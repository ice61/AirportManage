<%--
  Created by IntelliJ IDEA.
  User: WIN 10
  Date: 2018/11/27
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>停车场管理系统</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/parking/out.do" method="post">
    <table>
        <tr>
            <td>车辆牌照：<input type="text" name="license"></td>
        </tr>
        <tr>
            <td>${error}</td>
        </tr>
    </table>
    <input type="submit" value="离开">
</form>
</body>
</html>
