<%--
  Created by IntelliJ IDEA.
  User: WIN 10
  Date: 2018/11/27
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>一路平安</title>
</head>
<body>
<table>
    <tr>
        <td>车辆牌照：${Car.license}</td>
    </tr>
    <tr>
        <td>车辆当前位置：${Car.sort}${Car.location}号车位</td>
    </tr>
    <tr>
        <td>泊车时刻：${Car.date}</td>
    </tr>
    <tr>
        <td>停车场内泊车时间：${Car.time}</td>
    </tr>
    <tr>
        <td>本次收费:${Car.money}元</td>
    </tr>
    <tr>
        <td>祝您一路平安！</td>
    </tr>
</table>
</body>
</html>
