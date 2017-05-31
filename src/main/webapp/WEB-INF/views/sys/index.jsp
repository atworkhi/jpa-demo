<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@page contentType="text/html; utf-8" pageEncoding="utf-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>我的病毒库</title>
    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/static/css/dashboard.css" rel="stylesheet">
    <script src="${ctx}/static/js/jquery.min.js"></script>
    <script src="${ctx}/static/js/bootstrap.min.js"></script>
    <script >
        $(document).ready(function() {
            $(".nav-sidebar li a").click(function () {
                $.ajax({
                    url: "${ctx}/sms-down/index",
                    dataType: "html",
                    success: function(msg) {
                        $("#content").html(msg);
                    }
                })
            })
        });
    </script>


    <style>

        #content{

            padding: 20px;

        }

    </style>
</head>
<body>
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
                <a class="navbar-brand" href="${ctx}">病毒分析平台</a>
            </div>
        </div>
    </nav>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-2">
                <ul class="nav nav-sidebar">
                    <li class="active"><a href="#">SMS-DOWN</a></li>
                    <li><a href="#">Reports</a></li>
                </ul>
            </div>
            <div class="col-md-10">
                <div id="content"></div>
            </div>
        </div>
    </div>
</body>
</html>
