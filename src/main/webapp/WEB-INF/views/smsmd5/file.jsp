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
                var down = "<a href='${ctx}/download/upload/" + row.fileName + "'>"
                        + "<span class='glyphicon glyphicon-download-alt' aria-hidden='true'></span></a>";
                var del = "<a href='javascript:void(0)' onclick='deleteFile(\"" + row.fileName + "\")'>"
                        + "<span class='glyphicon glyphicon-remove' aria-hidden='true'></span></a>"
                return down + "&nbsp;&nbsp;&nbsp;&nbsp;" + del;
            }
        }]
    });


    function deleteFile(fileName) {
        if (confirm("确认删除文件：" + fileName + "?")) {
            $.ajax({
                url: "${ctx}/delete/upload/" + fileName,
                dataType: "json",
                type: "get",
                success: function (data) {
                    if (data.success) {
                        alert(data.message);
                        $("#table").bootstrapTable("refresh");
                    } else {
                        alert(data.message);
                    }
                },
                error: function () {
                    alert("删除失败");
                }
            })
        }
        return false;
    }
</script>
