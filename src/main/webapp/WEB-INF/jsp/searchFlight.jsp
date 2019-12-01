<%--
  Created by IntelliJ IDEA.
  User: WIN 10
  Date: 2018/11/30
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>航班管理</title>
    <link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.15/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8" src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" charset="utf8" src="http://cdn.datatables.net/1.10.15/js/jquery.dataTables.js"></script>


    <script>
        $(document).ready(function() {
            var table = $("#flight").DataTable({
                ajax: "${pageContext.request.contextPath}/manage/search.do",
                lengthMenu: [
                    [5, 10, 20, 40],
                    ['5页', '10页', '20页', '40页']
                ],
                bStateSave: true,
                language: {
                    "sProcessing": "处理中...",
                    "sLengthMenu": "显示_MENU_项结果",
                    "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                    "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                    "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                    "sInfoPostFix": "",
                    "sSearch": "搜索:",
                    "sEmptyTable": "暂无航班",
                    "sLoadingRecords": "载入中...",
                    "sInfoThousands": ",",
                    "oPaginate": {
                        "sFirst": "首页",
                        "sPrevious": "上页",
                        "sNext": "下页",
                        "sLast": "末页"
                    },
                    "oAria": {
                        "sSortAscending": ": 以升序排列此列",
                        "sSortDescending": ": 以降序排列此列"
                    }
                },
                columnDefs: [{
                    targets: 9,
                    data: null,
                    defaultContent: "<button id='del' type='button'>删除</button>"
                }],
                columns: [
                    {data: "flightNumber"},
                    {data: "planeNumber"},
                    {data: "startPoint"},
                    {data: "endPoint"},
                    {data: "startTime"},
                    {data: "endTime"},
                    {data: "day"},
                    {data: "sum"},
                    {data: "rest"},
                    {data: null, orderable: false}
                ],
            });
            $("#flight").on("click","#del",function () {
                var data = table.row($(this).parents("tr")).data();
                var s = "flightNumber=" + data.flightNumber + "&planeNumber=" + data.planeNumber;
                if(confirm("是否删除航班号为：" + data.flightNumber + "飞机号为：" + data.planeNumber + "的航班")) {
                    $.ajax({
                        type:"post",
                        url:"${pageContext.request.contextPath}/manage/del.do",
                        data: s,
                        success:function (date) {
                            if(date == "success") {
                                window.alert("删除成功");
                                table.row().remove();
                                window.location.reload();
                            }else
                                window.alert("删除失败");
                        }
                    })
                }
            })
        });
    </script>
</head>
<body>
<table id="flight" class="display cell-border">
    <thead>
    <tr>
        <th>航班号</th>
        <th>飞机号</th>
        <th>起点站</th>
        <th>终点站</th>
        <th>起飞时间</th>
        <th>到达时间</th>
        <th>星期</th>
        <th>总票额</th>
        <th>票额剩余</th>
        <th>编辑</th>
    </tr>
    </thead>
</table>
</body>
</html>
