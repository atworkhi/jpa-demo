<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html lang="zh-CN">
<head>

    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap-table.min.css">
    <script src="${ctx}/static/js/jquery.min.js"></script>
    <script src="${ctx}/static/js/bootstrap.min.js"></script>
    <script src="${ctx}/static/js/bootstrap-table.min.js"></script>
    <script src="${ctx}/static/js/bootstrap-table-zh-CN.min.js"></script>

    <script type="text/javascript">
        $(function () {
            $("#SmsDownTable").bootstrapTable({
                url: "${ctx}/sms-down/list",        //请求后台的URL（*）
                method: "get",                      //请求方式（*）
                toolbar: "#toolbar",                //工具按钮用哪个容器
                striped: true,                      //是否显示行间隔色
                cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: true,                   //是否显示分页（*）
                sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
                responseHandler: function (data) {
                    return data.rows;
                },
                search: true,
                uniqueId: "id",
                pageNumber: 1,                      //初始化加载第一页，默认第一页
                pageSize: 50,                       //每页的记录行数（*）
                pageList: [10, 50, 100, 500, "all"],//可供选择的每页的行数（*）
                maintainSelected: true,             //分页记住checkbox
                clickToSelect: true,                //是否启用点击选中行
                columns: [{
                    field: "id",
                    title: "ID",
                    checkbox: true
                }, {
                    field: "inTime",
                    title: "研判时间"
                }, {
                    field: "md5",
                    title: "病毒md5"
                }, {
                    field: "malewareName",
                    title: "病毒名称"
                }, {
                    field: "downloadUrl",
                    title: "病毒真实下载地址"
                }, {
                    field: "downloadIp",
                    title: "病毒真实下载IP"
                }, {
                    field: "downloadLocal",
                    title: "病毒真实下载归属地"
                }, {
                    field: "downloadForm",
                    title: "病毒真实下载运营商"
                }, {
                    field: "oldDownload",
                    title: "病毒原始下载地址"
                }, {
                    field: "category",
                    title: "病毒属性"
                }, {
                    field: "platform",
                    title: "影响平台"
                }, {
                    field: "description",
                    title: "备注"
                }]
            });


            $("#export").click(function () {
                var selectedRows = $("#SmsDownTable").bootstrapTable('getSelections');
                if (selectedRows.length == 0) {
                    alert("请选中需要导出的数据！");
                    return;
                }
                console.log(selectedRows);
                var idArr = new Array(selectedRows.length);
                for (var i = 0; i < selectedRows.length; i++) {
                    idArr[i] = i;
                }
                console.log(idArr);
                location.href = "${ctx}/sms-down/export?id=" + idArr;

            });


            $("#import").click(function () {

            });


            $("#download").click(function () {

            });

        })

    </script>
</head>
<body>
<div class="container-fluid">
    <div class="form-inline" role="form">
        <div class="form-group">
            <span>病毒md5:</span>
            <input name="offset" class="form-control w70"/>
        </div>
        <div class="form-group">
            <span>研判时间: </span>
            <input name="limit" class="form-control w70"/>
        </div>
        <button id="ok" type="submit" class="btn btn-default">OK</button>
    </div>
    <div id="SmsDownTable"></div>
    <div id="toolbar">
        <div class="form-inline" role="form">
            <button id="add" class="btn btn-default">添加</button>
            <button id="update" class="btn btn-default">修改</button>
            <button id="delete" class="btn btn-default">删除</button>
            <button id="import" class="btn btn-default">导入</button>
            <button id="export" class="btn btn-default">导出</button>
            <button id="download" class="btn btn-default">模板下载</button>
        </div>
    </div>
</div>
</body>
</html>
