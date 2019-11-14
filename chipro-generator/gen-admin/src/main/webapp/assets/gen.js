layui.use(['layer', 'ax', 'form', 'laydate', 'element', 'table'], function () {
    var $ = layui.$;
    var $ax = layui.ax;
    var layer = layui.layer;
    var form = layui.form;
    var laydate = layui.laydate;
    var element = layui.element;
    var table = layui.table;

    var Code = {
        tableNames: "",
        tables: {}
    };

    $('#code_gen').click(function () {
        window.location.href = Feng.ctxPath + "/";
    });

    $('#db_config').click(function () {
        window.location.href = Feng.ctxPath + "/db";
    });

    $('#add_db').click(function () {
        window.location.href = Feng.ctxPath + "/db/add";
    });

    table.render({
        elem: '#dbTableList'
        , url: Feng.ctxPath + '/db/tableList'
        , page: false
        , cols: [[
            {type: 'checkbox'}
            , {field: 'tableName', title: '表的名称'}
            , {field: 'tableComment', title: '表的名称注释'}
        ]]
    });

    table.on('checkbox(dbTableList)', function (obj) {
        var checkStatus = table.checkStatus('dbTableList');
        var tableNames = "";
        for (var tableItem in checkStatus.data) {
            tableNames += "CAT" + checkStatus.data[tableItem].tableName;
        }
        Code.tableNames = tableNames;
    });

    form.on('select(dataSourceId)', function (data) {
        var dbId = data.value;
        table.reload("dbTableList", {where: {dbId: dbId}});
    });

    $('#execute').on('click', function () {

        var author = $("#author").val();
        var proPackage = $("#proPackage").val();
        var removePrefix = $("#removePrefix").val();
        var dataSourceId = $("#dataSourceId").val();

        window.location.href = Feng.ctxPath + "/execute?dataSourceId=" + dataSourceId + "&author="
            + author + "&proPackage=" + proPackage + "&removePrefix=" + removePrefix + "&tables=" + Code.tableNames;
    });

});
