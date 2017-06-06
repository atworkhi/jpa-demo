<%@page contentType="text/html; utf-8" pageEncoding="utf-8" %>
<div class="col-sm-3 col-md-2 sidebar">
    <ul class="nav nav-sidebar">
        <li class="active"><a href="#" onclick="loadHtml('${ctx}/sms-down/index')">SmsDown</a></li>
        <li><a href="#" onclick="loadHtml('${ctx}/sms-md5/index')">SmsMD5</a></li>
    </ul>
</div>
<script>
    function loadHtml(url) {
        $.ajax({
            url: url,
            global: false,
            dataType: "html",
            async: false,
            success: function (msg) {
                $(".main").html(msg);
            },
            error: function (msg) {
                alert("加载页面失败");
            }
        })
    }
</script>