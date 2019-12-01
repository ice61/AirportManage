<%--
  Created by IntelliJ IDEA.
  User: WIN 10
  Date: 2018/12/1
  Time: 6:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.15/css/jquery.dataTables.css">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script type="text/javascript" charset="utf8" src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" charset="utf8" src="http://cdn.datatables.net/1.10.15/js/jquery.dataTables.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <script>
        $(document).ready(function() {
            var table = $("#flight").DataTable({
                ajax: "${pageContext.request.contextPath}/order/search.do",
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
                    defaultContent: '<button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModal" id="order">订票</button> ' +
                    '<button class="btn btn-danger btn-sm" data-toggle="modal" data-target="#delOrder" id="delOrder">退票</button>'

                }
                ],
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
             $("#flight").on('click','#order',function () {
                 var date = table.row($(this).parents('tr')).data();
                 var obj = {};
                 var k = date.flightNumber;
                 var hehe = "flightNumber";
                 obj[hehe] = k;
                 k = date.planeNumber;
                 hehe = "planeNumber";
                 obj[hehe] = k;
                 console.log(JSON.stringify(obj));
                 $.ajax({
                     type:"post",
                     url:"${pageContext.request.contextPath}/order/detail.do",
                     contentType:"application/json;charset=utf-8",
                     data:JSON.stringify(obj) ,
                     success:function (data) {
                         $("#flightNumber").val(data.flightNumber);
                         $("#planeNumber").val(data.planeNumber);
                         $("#startPoint").val(data.startPoint);
                         $("#endPoint").val(data.endPoint);
                         $("#startTime").val(data.startTime);
                         $("#endTime").val(data.endTime);
                         $("#firstRest").val(data.firstRest);
                         $("#secondRest").val(data.secondRest);
                         $("#thirdRest").val(data.thirdRest);
                     },
                 });
             });

            $("#flight").on('click','#delOrder',function () {
                   var date =  table.row($(this).parents('tr')).data();
                   $("#fn").val(date.flightNumber);
                   $("#pn").val(date.planeNumber);
            })
        });
    </script>
    <script>
        function del() {
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}/order/del.do",
                data:$("#form1").serialize(),
                success: function (data) {
                    if(data == "success") {
                        window.alert("成功！")
                    }else {
                        window.alert("您未预订此航班！")
                    }
                }
            });
        }
    </script>
</head>
<body>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    机票购买
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-info">选择剩余票额为零的机票将自动进入候补队列</div>
                <form action="${pageContext.request.contextPath}/order/add.do" method="post">
                    <table>
                        <tr>
                            <td>航班号：</td>
                            <td><input type="text" name="flightNumber" id="flightNumber" readonly="readonly" required="required"/></td>
                        </tr>
                        <tr>
                            <td>飞机号：</td>
                            <td><input type="text" name="planeNumber" id="planeNumber" readonly="readonly" required="required"/></td>
                        </tr>
                        <tr>
                            <td>起点站：</td>
                            <td><input type="text" id="startPoint" readonly="readonly" required="required"/></td>
                        </tr>
                        <tr>
                            <td>终点站：</td>
                            <td><input type="text" id="endPoint" readonly="readonly" required="required"/></td>
                        </tr>
                        <tr>
                            <td>起飞时间：</td>
                            <td><input type="text" id="startTime" readonly="readonly" required="required"/></td>
                        </tr>
                        <tr>
                            <td>到达时间：</td>
                            <td><input type="text" id="endTime" readonly="readonly" required="required"/></td>
                        </tr>
                        <tr>
                            <td>头等舱剩余票额：</td>
                            <td><input type="number" id="firstRest" readonly="readonly" required="required"/></td>
                        </tr>
                        <tr>
                            <td>公务舱剩余票额：</td>
                            <td><input type="number" id="secondRest" readonly="readonly" required="required"/></td>
                        </tr>
                        <tr>
                            <td>经济舱剩余票额：</td>
                            <td><input type="number" id="thirdRest" readonly="readonly" required="required"/></td>
                        </tr>
                        <tr>
                            <td>姓名：</td>
                            <td><input type="text" name="name" required="required"/></td>
                        </tr>
                        <tr>
                            <td>身份证号码：</td>
                            <td><input type="text" name="number" required="required"/></td>
                        </tr>
                        <tr>
                            <td>种类：</td>
                            <td>
                                头等舱：<input type="radio" name="sort" value="头等舱" required="required"/>
                                公务舱：<input type="radio" name="sort" value="公务舱" required="required"/>
                                经济舱：<input type="radio" name="sort" value="经济舱" required="required"/>
                            </td>
                        </tr>
                    </table>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消
                </button>
                <input type="submit" value="提交" class="btn btn-primary"/>

            </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="modal fade" id="delOrder" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="ModalLabel">机票退订</h4>
            </div>
            <div class="modal-body">
                <form id="form1">
                    <table>
                        <tr>
                            <td>航班号：</td>
                            <td><input type="text" name="flightNumber" id="fn" readonly="readonly" required="required"/></td>
                        </tr>
                        <tr>
                            <td>飞机号：</td>
                            <td><input type="text" name="planeNumber" id="pn" readonly="readonly" required="required"/></td>
                        </tr>
                        <tr>
                            <td>身份证号：</td>
                            <td><input type="text" name="number" id="number" required="required"/></td>
                        </tr>
                    </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-danger" onclick="del()">提交更改</button>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

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
<div class="alert alert-danger">${error}</div>
</body>
</html>
