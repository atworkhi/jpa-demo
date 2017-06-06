<%@page contentType="text/html; utf-8" pageEncoding="utf-8" %>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">我的病毒库</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul id="menu_ul" class="nav navbar-nav navbar-right">
                <li><a href="#" onclick="loadHtml(this,'${ctx}/sms-down/index')">SmsDown</a></li>
                <li><a href="#" onclick="loadHtml(this,'${ctx}/sms-md5/index')">SmsMD5</a></li>
            </ul>
        </div>
    </div>
</nav>
<script>
    function loadHtml(obj, url) {
        $("#menu_ul li").removeClass("active");
        $(obj).parent("li").addClass("active");
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
