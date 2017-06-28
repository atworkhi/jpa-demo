<%@page contentType="text/html; utf-8" pageEncoding="utf-8" %>
<div class="panel panel-default">
    <div class="panel-heading">查询</div>
    <div class="panel-body">
        <div id="searchForm" class="form-inline" role="form">
            <div class="form-group">
                <span>病毒md5:</span>
                <input id="md5" class="form-control w70"/>
            </div>
            <div class="form-group">
                <span>研判时间:</span>
                <input id="inTime" class="form-control w70"/>
            </div>
            <div class="form-group">
                <span>主控号码:</span>
                <input id="phone" class="form-control w70"/>
            </div>
            <div class="form-group">
                <span>邮箱:</span>
                <input id="mail" class="form-control w70"/>
            </div>
            <button id="ok" type="button" class="btn btn-success">查询</button>
            <button id="cancel" type="button" class="btn btn-warning">清空</button>
        </div>
    </div>
</div>
<div id="SmsMD5Table"></div>
<div id="toolbar">
    <div class="form-inline" role="form">
        <div class="btn-group">
            <button id="add" class="btn btn-primary">添加</button>
            <button id="update" class="btn btn-warning">修改</button>
            <button id="delete" class="btn btn-danger">删除</button>
            <button id="import" class="btn btn-info" data-toggle="modal" data-target="#myModal">
                导入
            </button>
            <button id="export" class="btn btn-success">导出</button>
            <button id="download" class="btn btn-default">模板下载</button>
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
                <h4 class="modal-title" id="myModalLabel">数据导入</h4>
            </div>
            <div class="modal-body">
                <form id="uploadForm" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="inputFile">选择文件</label>
                        <input id="inputFile" name="uploadFile" type="file">
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
<script type="text/javascript">
    $("#SmsMD5Table").bootstrapTable({
        url: "${ctx}/sms-md5/list",        //请求后台的URL（*）
        method: "get",                      //请求方式（*）
        toolbar: "#toolbar",                //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
        queryParams: function () {          //查询参数
            var temp = {
                md5: $.trim($("#md5").val()),
                phone: $.trim($("#phone").val()),
                mail: $.trim($("#mail").val()),
                inTime: $.trim($("#inTime").val())
            }
            return temp;
        },
        showRefresh: true,
        showToggle: true,
        showColumns: true,
        search: true,
        pageNumber: 1,                      //初始化加载第一页，默认第一页
        pageSize: 50,                       //每页的记录行数（*）
        pageList: [10, 50, 100, 500, "All"],//可供选择的每页的行数（*）
        maintainSelected: true,             //分页记住checkbox
        clickToSelect: true,                //是否启用点击选中行
        columns: [{
            field: "status",
            checkbox: true
        }, {
            field: "inTime",
            title: "研判时间",
            sortable: true
        }, {
            field: "md5",
            title: "样本md5"
        }, {
            field: "malewareName",
            title: "病毒名称"
        }, {
            field: "category",
            title: "病毒属性"
        }, {
            field: "platform",
            title: "影响平台"
        }, {
            field: "name",
            title: "安装名称"
        }, {
            field: "phoneNum",
            title: "恶意主控号码"
        }, {
            field: "phoneLocal",
            title: "恶意主控号码归属地"
        }, {
            field: "phoneForm",
            title: "域名"
        }, {
            field: "email",
            title: "恶意邮箱"
        }, {
            field: "emailPass",
            title: "邮箱密码"
        }, {
            field: "details",
            title: "病毒描述"
        }]
    });
    $("#export").click(function () {
        var selectedRows = $("#SmsMD5Table").bootstrapTable('getSelections');
        if (selectedRows.length == 0) {
            alert("请选中需要导出的数据！");
            return;
        }
        var idArr = new Array(selectedRows.length);
        for (var i = 0; i < selectedRows.length; i++) {
            idArr[i] = selectedRows[i].id;
        }
        location.href = "${ctx}/sms-md5/export?id=" + idArr;
    });
    $("#import").click(function () {

    });
    $("#download").click(function () {
        location.href = "${ctx}/sms-md5/templet";
    });
    $("#cancel").click(function () {
        $("#searchForm input").each(function () {
            $(this).val("");
        })
    });
    $("#ok").click(function () {
        $("#SmsMD5Table").bootstrapTable("refresh");
    });
    $("#add").click(function () {
        alert("我还没有实现");
    });
    $("#update").click(function () {
        alert("我也没有实现");
    });
    $("#delete").click(function () {
        var selectedRows = $("#SmsMD5Table").bootstrapTable('getSelections');
        if (selectedRows == null || selectedRows.length == 0) {
            alert("请选择需要删除的记录");
            return;
        }
        var idArr = new Array(selectedRows.length);
        for (var i = 0; i < selectedRows.length; i++) {
            idArr[i] = selectedRows[i].id;
        }
        $.ajax({
            url: "${ctx}/sms-md5/delete",
            type: "post",
            dataType: "json",
            data: {
                id: idArr
            },
            success: function (data) {
                if (data.success) {
                    alert(data.message);
                    $("#SmsMD5Table").bootstrapTable('remove', {field: 'id', values: idArr});
                } else {
                    alert(data.message);
                }
            },
            error: function () {
                alert("网络异常，稍后重试");
            }
        })
    });
    $("#fileUploadBtn").click(function () {
        var form = new FormData($("#uploadForm")[0]);
        $.ajax({
            url: "${ctx}/sms-md5/import",
            type: "POST",
            data: form,
            contentType: false,
            processData: false,
            success: function (data) {
                if (data.success) {
                    alert(data.message);
                    $('#uploadForm')[0].reset();
                    $("#myModal").modal("hide");
                    $("#SmsMD5Table").bootstrapTable('refresh');
                } else {
                    alert(data.message);
                }
            },
            error: function () {
                alert("网络异常，稍后重试");
            }
        });
    })
</script>