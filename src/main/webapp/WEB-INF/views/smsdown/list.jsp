<%@page contentType="text/html; utf-8" pageEncoding="utf-8" %>
<div id="searchForm" class="form-inline" role="form">
    <div class="form-group">
        <span>病毒md5:</span>
        <input id="md5" class="form-control w70"/>
    </div>
    <div class="form-group">
        <span>研判时间: </span>
        <input id="inTime" class="form-control w70"/>
    </div>
    <button id="ok" type="button" class="btn btn-success">查询</button>
    <button id="cancel" type="button" class="btn btn-warning">清空</button>
</div>
<div id="SmsDownTable"></div>
<div id="toolbar">
    <div class="form-inline" role="form">
        <button id="add" class="btn btn-primary">添加</button>
        <button id="update" class="btn btn-warning">修改</button>
        <button id="delete" class="btn btn-danger">删除</button>
        <button id="import" class="btn btn-info">导入</button>
        <button id="export" class="btn btn-success">导出</button>
        <button id="download" class="btn btn-default">模板下载</button>
    </div>
</div>
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
            queryParams: function () {          //查询参数
                var temp = {
                    md5: $.trim($("#md5").val()),
                    inTime: $.trim($("#inTime").val())
                }
                return temp;
            },
            showRefresh: true,
            showToggle: true,
            showColumns: true,
            search: true,
            uniqueId: "id",
            pageNumber: 1,                      //初始化加载第一页，默认第一页
            pageSize: 50,                       //每页的记录行数（*）
            pageList: [10, 50, 100, 500, "All"],//可供选择的每页的行数（*）
            maintainSelected: true,             //分页记住checkbox
            clickToSelect: true,                //是否启用点击选中行
            columns: [{
                field: "id",
                title: "ID",
                checkbox: true
            }, {
                field: "inTime",
                title: "研判时间",
                sortable: true
            }, {
                field: "md5",
                title: "md5"
            }, {
                field: "malewareName",
                title: "名称"
            }, {
                field: "downloadUrl",
                title: "真实下载地址"
            }, {
                field: "downloadIp",
                title: "真实下载IP"
            }, {
                field: "downloadLocal",
                title: "归属地"
            }, {
                field: "downloadForm",
                title: "运营商"
            }, {
                field: "oldDownload",
                title: "原始下载地址"
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
        })
        $("#export").click(function () {
            var selectedRows = $("#SmsDownTable").bootstrapTable('getSelections');
            console.log(selectedRows);
            if (selectedRows.length == 0) {
                alert("请选中需要导出的数据！");
                return;
            }
            var idArr = new Array(selectedRows.length);
            for (var i = 0; i < selectedRows.length; i++) {
                idArr[i] = i;
            }
            location.href = "${ctx}/sms-down/export?id=" + idArr;
        });
        $("#import").click(function () {

        });
        $("#download").click(function () {
            location.href = "${ctx}/sms-down/templet";
        });
        $("#cancel").click(function () {
            $("#searchForm input").each(function () {
                $(this).val("");
            })
        });
        $("#ok").click(function () {
            $("#SmsDownTable").bootstrapTable("refresh");
        });
        $("#add").click(function () {
            alert("我还没有实现");
        });
        $("#update").click(function () {
            alert("我也没有实现");
        });
        $("#delete").click(function () {
            var selectedRows = $("#SmsDownTable").bootstrapTable('getSelections');
            if (selectedRows == null || selectedRows.length == 0) {
                alert("请选择需要删除的记录");
                return;
            }
            var idArr = new Array(selectedRows.length);
            for (var i = 0; i < selectedRows.length; i++) {
                idArr[i] = i;
            }
            $.ajax({
                url: "${ctx}/sms-down/delete",
                type: "post",
                dataType: "json",
                data: {
                    id: idArr
                },
                success: function (data) {
                    if (data.success) {
                        alert(data.message);
                        $("#SmsDownTable").bootstrapTable('refresh');
                    } else {
                        alert(data.message);
                    }
                },
                error: function () {
                    alert("网络异常，稍后重试");
                }
            })
        });

    });
</script>