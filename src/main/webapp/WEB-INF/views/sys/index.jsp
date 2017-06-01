<%@page contentType="text/html; utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Dashboard Template for Bootstrap</title>
    <jsp:include page="/WEB-INF/views/common/common.jsp"/>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="/WEB-INF/views/common/left.jsp"/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        </div>
    </div>
</div>
</body>
</html>
