<%--
  Created by IntelliJ IDEA.
  User: Renhai
  Date: 2016/10/28
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Layout</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
    <!-- 引入ztree资源文件 -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/js/ztree/zTreeStyle.css" type="text/css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/ztree/jquery.ztree.all-3.5.js"></script>


</head>
<body class="easyui-layout">
<div data-options="region:'north'" style="height:100px;" title="XXX管理系统">

    北部
</div>
<div data-options="region:'south'" style="height:100px;">

    南部
</div>

<div data-options="region:'center'">
    <div id="tabs" class="easyui-tabs" data-options="fit:true">
        <!-- 每个子div是一个选项卡 -->
        <div data-options="closable:true,iconCls:'icon-reload'" title="选项卡一">

        </div>
        <div title="选项卡二">
            选项卡二
        </div>
        <div title="选项卡三">
            选项卡三
        </div>
    </div>
</div>


<div data-options="region:'west'" style="width:300px;">
    <!--折叠面板-->
    <div class="easyui-accordion" data-options="fit:true">
        <!--每一个子div是一个面板-->
        <div title="面板一" data-options="iconCls:'icon-search'">
            <script type="text/javascript">
                function addTabs() {
                    //添加选项ka
                    $("#tabs").tabs("add",{
                       iconCls:'icon-help',
                        closable:true,
                        title:'百度',
                        content:'<iframe frameborder="0" width="100%" height="100%" src="http://www.baidu.com"></iframe>'
                    });
                }
            </script>
            <a onclick="addTabs();" id="baidulink" class="easyui-linkbutton">百度</a>
        </div>
        <div title="面板二">
            内容二
        </div>
        <div title="面板三">
            <script type="text/javascript">
                function onClick(event,treeId,treeNode,clickFlag) {
                    var page=treeNode.page;
                    //判断当前选项卡是否存在
                    var ex=$("#tabs").tabs("exists",treeNode.name);
                    if (ex){
                        //已经存在  选中选项卡
                        $("#tabs").tabs("select",treeNode.name);
                    }else {
                        //不存在添加
                        //动态添加选项卡
                        //添加选项ka
                        $("#tabs").tabs("add",{
                            iconCls:'icon-help',
                            closable:true,
                            title:treeNode.name,
                            content:'<iframe frameborder="0" width="100%" height="100%" src="'+page+'"></iframe>'
                        });
                    }

                }

                var setting2 = {
                    data: {
                        simpleData: {
                            enable: true
                        }
                    },
                    callback: {
                        onClick: onClick
                    }
                };
                //使用简单json数据
                //构造节点数据----使用简单json数据
                var zNodes2=[

                    {id:1,pId:0,name:"父节点1-展开",open:true},
                    {id:11,pId:1,name:"父节点11-折叠"},
                    {id:111,pId:11,name:"叶子节点111"},
                    {id:112,pId:11,name:"叶子节点112"},
                    {id:113,pId:11,name:"叶子节点113"},
                    {id:114,pId:11,name:"百度",page:'http://www.baidu.com'}
                ];

                //初始化ztree树
                $(function(){
                    $.fn.zTree.init($("#myTree2"), setting2, zNodes2);
                });
            </script>
            <ul id="myTree2" class="ztree"></ul>
            内容三
        </div>
    </div>
</div>

</body>
</html>
