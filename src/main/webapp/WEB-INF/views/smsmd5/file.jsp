<%@page contentType="text/html; utf-8" pageEncoding="utf-8" %>
<table id="table"></table>
<div id="toolbar">
    <div class="form-inline" role="form">
        <div class="btn-group">
            <button id="import" class="btn btn-info" data-toggle="modal" data-target="#myModal">
                导入
            </button>
        </div>
    </div>
</div>
<style>
    #SmsMD5Table td {
        white-space: nowrap;
    }
</style>

<script>
    $('#table').bootstrapTable({
        url: "${ctx}/sms-md5/fileList",
        striped: true,
        toolbar: "#toolbar",
        pageList: [20, 50, 100, 500],
        pagination: true,
        showRefresh: true,
        showToggle: true,
        showColumns: true,
        search: true,
        columns: [{
            field: "id",
            title: "序号",
            align: "center"
        }, {
            field: "fileName",
            title: "文件名"
        }, {
            title: "操作",
            align: "center",
            formatter: function (value, row, index) {
                var link = "<a href='${ctx}/sms-md5/fileDown?fileName=" + row.fileName + "'>"
                        + "<span class='glyphicon glyphicon-download-alt' aria-hidden='true'></span></a>"
                return link;
            }
        }]
    });
</script>
