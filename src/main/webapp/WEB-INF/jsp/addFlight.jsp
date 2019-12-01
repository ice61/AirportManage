<%--
  Created by IntelliJ IDEA.
  User: WIN 10
  Date: 2018/11/30
  Time: 12:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加航班</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/manage/add.do" method="post">
    <table>
        <tr>
            <td>航班号：</td>
            <td><input type="text" name="flightNumber" required="required"/></td>
        </tr>
        <tr>
            <td>飞机号：</td>
            <td><input type="text" name="planeNumber" required="required"/></td>
        </tr>
        <tr>
            <td>起点站：</td>
            <td><input type="text" name="startPoint" required="required"/></td>
        </tr>
        <tr>
            <td>终点站：</td>
            <td><input type="text" name="endPoint" required="required"/></td>
        </tr>
        <tr>
            <td>起飞日期：</td>
            <td><input type="date" name="startDate" required="required"/></td>
            <td>起飞时间：</td>
            <td><input type="time" name="startTime" required="required"/></td>
        </tr>
        <tr>
            <td>到达日期：</td>
            <td><input type="date" name="endDate" required="required"/></td>
            <td>到达时间：</td>
            <td><input type="time" name="endTime" required="required"/></td>
        </tr>
        <tr>
            <td>头等舱票额：</td>
            <td><input type="number" name="firstNum" required="required"/></td>
        </tr>
        <tr>
            <td>公务舱票额：</td>
            <td><input type="number" name="secondNum" required="required"/></td>
        </tr>
        <tr>
            <td>经济舱票额：</td>
            <td><input type="number" name="thirdNum" required="required"/></td>
        </tr>
        <tr>
            <td>${error}</td>
        </tr>
        <tr>
            <td><input type="submit" value="添加"></td>
        </tr>
    </table>
</form>
</body>
</html>
