<%--
  Created by IntelliJ IDEA.
  User: WIN 10
  Date: 2018/12/1
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script type="text/javascript" charset="utf8" src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>个人信息</title>
</head>
<body>
<div class="alert alert-success">
    <div>成功！很好地完成了提交。</div>
    <div class="alert alert-info">座号为 -1 的旅客正处于等候队列，请耐心等待</div>
    <table>
        <tr>
            <td>姓名：</td>
            <td>${order.name}</td>
        </tr>
        <tr>
            <td>身份证号：</td>
            <td>${order.number}</td>
        </tr>
        <tr>
            <td>航班号：</td>
            <td>${order.flightNumber}</td>
        </tr>
        <tr>
            <td>飞机号：</td>
            <td>${order.planeNumber}</td>
        </tr>
        <tr>
            <td>座位种类：</td>
            <td>${order.sort}</td>
        </tr>
        <tr>
            <td>座位号：</td>
            <td>${order.location}</td>
        </tr>
    </table>
</div>
</body>
</html>
