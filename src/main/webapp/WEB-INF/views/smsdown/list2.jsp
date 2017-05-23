<div class="form-inline" role="form">
    <div class="form-group">
        <span>MD5:</span>
        <input name="offset" class="form-control w70" type="number" value="0">
    </div>
    <div class="form-group">
        <span>INTIME: </span>
        <input name="limit" class="form-control w70" type="number" value="5">
    </div>
    <button id="ok" type="submit" class="btn btn-default">OK</button>
</div>
<div id="toolbar">
    <div class="form-inline" role="form">
        <button id="add" class="btn btn-default">添加</button>
        <button id="update" class="btn btn-default">修改</button>
        <button id="delete" class="btn btn-default">删除</button>
        <button id="export" class="btn btn-default">导入</button>
        <button id="upload" class="btn btn-default">导出</button>
    </div>
</div>
<table id="table"
       data-toggle="table"
       data-toolbar="#toolbar"
       data-show-columns="true"
       data-show-refresh="true"
       data-show-toggle="true"
       data-query-params-type="limit"
       data-side-pagination="server"
       data-pagination="true"
       data-url="/sms-down/list">
    <thead>
        <tr>
            <th data-field="id">ID</th>
            <th data-field="inTime">inTime</th>
            <th data-field="md5">MD5</th>
        </tr>
    </thead>
</table>

