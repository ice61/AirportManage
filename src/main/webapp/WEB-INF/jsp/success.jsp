<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>欢迎泊车</title>
</head>
<body>
<table>
    <tr>
        <td>车辆牌照：${Car.license}</td>
    </tr>
    <tr>
        <td>请前往<b>${Car.sort}${Car.location}号</b>车位</td>
    </tr>
    <tr>
        <td>当前时间：${Car.date}</td>
    </tr>
</table>
<a href="${pageContext.request.contextPath}/parking/exit.do">离开</a>
</body>
</html>
