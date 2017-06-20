<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; utf-8" pageEncoding="utf-8" %>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"></c:set>
<table id="sortTable"></table>
<div id="toolbar">
    <div class="form-inline" role="form">
        <div class="btn-group">
            <button id="md5Sort" onclick="sortTable(this,0)" class="btn btn-success">MD5-TOP</button>
            <button id="phoneSort" onclick="sortTable(this,1)" class="btn btn-default">手机号-TOP</button>
            <button id="ipSort" onclick="sortTable(this,2)" class="btn btn-default">真实下载IP-TOP</button>
        </div>
    </div>
</div>
<style>
    #sortTable td {
        white-space: nowrap;
    }
</style>
<script>

    $('#sortTable').bootstrapTable({
        url: "${ctx}/sms-md5/top/md5",
        //striped: true,
        toolbar: "#toolbar",
        //pageList: [20, 50, 100, 500],
        //pagination: true,
        showRefresh: true,
        showToggle: true,
        showColumns: true,
        search: true,
        rowAttributes: function (row, index) {
            if (index < 10) {
                return {class: "success"};
            }
        },
        columns: [{
            field: "status",
            title: "序号",
            align: "center",
            formatter: function (value, row, index) {
                return index + 1;
            }
        }, {
            field: "md5",
            title: "数据",
            align: "center"
        }, {
            field: "total",
            title: "数量",
            align: "center"
        }]

    });

    function sortTable(btn, sort) {
        var sortField = ["md5", "phoneNum", "ip"];
        var newUrl = "${ctx}/sms-md5/top/";
        if (sort == 0) {
            newUrl += sortField[0];
        } else if (sort == 1) {
            newUrl += sortField[1];
        } else if (sort == 2) {
            newUrl += sortField[2];
        }
        $(".btn-group button").removeClass("btn-success").addClass("btn-default");
        $(btn).addClass("btn-success");
        $("#sortTable").bootstrapTable("refresh", {url: newUrl});
    }
</script>
