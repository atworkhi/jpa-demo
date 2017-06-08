<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; utf-8" pageEncoding="utf-8" %>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"></c:set>
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
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">MD5文件上传</h4>
            </div>
            <div class="modal-body">
                <form id="uploadForm" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="inputFile">选择文件</label>
                        <input id="inputFile" name="file" type="file" multiple="multiple">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button id="fileUploadBtn" type="button" class="btn btn-primary">上传</button>
            </div>
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
                        + "<span class='glyphicon glyphicon-remove' aria-hidden='true'></span></a>";
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

    $("#fileUploadBtn").click(function () {
        var form = new FormData($("#uploadForm")[0]);
        $.ajax({
            url: "${ctx}/upload",
            type: "POST",
            data: form,
            contentType: false,
            processData: false,
            success: function (data) {
                if (data.success) {
                    alert(data.message);
                    $('#uploadForm')[0].reset();
                    $("#myModal").modal("hide");
                    $("#table").bootstrapTable('refresh');
                } else {
                    alert(data.message);
                }
            },
            error: function () {
                alert("网络异常，稍后重试");
            }
        })
    })

</script>
