<%@page contentType="text/html; utf-8" pageEncoding="utf-8" %>
<div class="jumbotron" style="margin: 0 auto">
    <div class="container-fluid">
        <center>
            <p><h1>欢迎访问<span style="color: red">中国移动垃圾短信信息库</span></h1></p>
            <p>==========================================================================</p>
            <h3>
                <p>说明:</p>
                <p>欢迎使用相关 查询 下载 功能。</p>
                <p>垃圾短信病毒库为中国移动驻场垃圾短信工作小组内部负责数据的<span style="color: red">增删改</span>与维护，</p>
                <p>如果需要增加删除相关信息，请联系 中国移动驻场垃圾短信小组成员，谢谢合作！！！</p>
                <p>由于涉及到公司及移动数据，在未经允许的情况下，请勿对数据进行网络或拷贝传播！！！</p>
                <p>如相关网站有BUG 请提交给管理员。</p>
            </h3>
            <p>==========================================================================</p>
            <p>
                <a class="btn btn-primary btn-lg" role="button" href="#" onclick="loadHtml(this,'${ctx}/sms-md5/index')">样本MD5特征库</a>
                <a class="btn btn-primary btn-lg" role="button" href="#" onclick="loadHtml(this,'${ctx}/sms-down/index')">样本下载地址特征库</a>
                <a class="btn btn-primary btn-lg" role="button" href="#" onclick="loadHtml(this,'${ctx}/sms-md5/file')">样本文件管理库</a>
                <a class="btn btn-primary btn-lg" role="button" href="#" onclick="loadHtml(this,'${ctx}/sms-md5/top')">样本分析报表Top-Ten</a>
            </p>
        </center>
    </div>
</div>
