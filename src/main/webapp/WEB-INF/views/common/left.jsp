<%@page contentType="text/html; utf-8" pageEncoding="utf-8" %>
<div class="col-sm-3 col-md-2 sidebar">
    <ul class="nav nav-sidebar">
        <li class="active"><a href="#" onclick="loadHtml('${ctx}/sms-down/index')">SmsDown</a></li>
        <li><a href="#">SmsMD5</a></li>
    </ul>
</div>
<script>
    function loadHtml(url) {
        $(".nav li a").click(function () {
            $.ajax({
                url: url,
                global: false,
                dataType: "html",
                async: false,
                success: function (msg) {
                    $(".main").html(msg);
                }
            })
        })
    }
</script>