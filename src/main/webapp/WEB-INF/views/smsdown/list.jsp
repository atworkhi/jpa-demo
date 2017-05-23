<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/static/css/bootstrap-table.min.css" rel="stylesheet">
    <script src="${ctx}/static/js/jquery.min.js"></script>
    <script src="${ctx}/static/js/bootstrap.min.js"></script>
    <script src="${ctx}/static/js/bootstrap-table.min.js"></script>
    <script src="${ctx}/static/js/bootstrap-table-zh-CN.min.js"></script>
</head>
<body>
<div class="form-inline" role="form">
    <div class="form-group">
        <span>MD5:</span>
        <input name="offset" class="form-control w70" type="number" value="0">
    </div>
    <div class="form-group">
        <span>INTIME: </span>
        <input name="limit" class="form-control w70" type="number" value="5">
    </div>
    <button id="ok" type="submit" class="btn btn-default">OK</button>
</div>
<div id="toolbar">
    <div class="form-inline" role="form">
        <button id="add" class="btn btn-default">添加</button>
        <button id="update" class="btn btn-default">修改</button>
        <button id="delete" class="btn btn-default">删除</button>
        <button id="export" class="btn btn-default">导入</button>
        <button id="upload" class="btn btn-default">导出</button>
    </div>
</div>
<table id="table"
       data-toggle="table"
       data-toolbar="#toolbar"
       data-show-columns="true"
       data-show-refresh="true"
       data-show-toggle="true"
       data-query-params-type="limit"
       data-side-pagination="server"
       data-pagination="true"
       data-url="/sms-down/list">
    <thead>
    <tr>
        <th data-field="id">ID</th>
        <th data-field="inTime">inTime</th>
        <th data-field="md5">MD5</th>
    </tr>
    </thead>
</table>
</body>
</html>
