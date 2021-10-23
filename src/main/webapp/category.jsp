<%--
  Created by IntelliJ IDEA.
  User: 23351
  Date: 2021/10/11
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>分类管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="css/css.css" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="css/menu.css" />
    <link rel="stylesheet" type="text/css" href="css/address.css" />
    <script type="text/javascript"
            src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script
            src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
    <script src="layer/layer.js" type="text/javascript"></script>
    <script src="js/main.js" type="text/javascript"></script>
    <script type="text/javascript">

        function select() {
            $.get("CategoryServlet?method=category", function(data) {

                var data = JSON.parse(data);

                //获取需要放数据的容器
                var container = $('#container');
                //获取我们定义的模板的dom对象。主要是想获取里面的内容
                var templateDom = $('#template');
                //编译模板的里的内容
                var template = Handlebars.compile(templateDom.html());
                //把后台获取到的数据渲染到页面
                container.html(template(data));
            });
        }I

        function del(id){
            layer.confirm('确定要删除吗？', {
                btn: ['确定','取消'] //按钮
            }, function(){
                $.get("CategoryServlet?method=del",{"categoryId":id},function (data) {
                    if(data==-1){
                        layer.msg("改分类下还有商品");
                        return;
                    }
                    if (data==0){
                        layer.msg("删除失败");
                    }else {
                        layer.alert("删除成功");
                        top.location.reload();
                    }
                })
                layer.msg('删除成功');
            });
        }

        function update(e) {
            let categoryName = $.trim($("#categoryName"+e).val());
            let uCategoryName = $.trim($("#uCategoryName"+e).text());
            if (categoryName==uCategoryName){
                layer.msg("请修改分类");
                return;
            }
            $.get("CategoryServlet?method=update",{"categoryName":categoryName,"categoryId":e},function (data) {
                if (data==-2){
                    layer.msg("请将信息填写完整");
                }else if (data==-1) {
                    layer.msg("改分类已存在");
                }else if (data==0){
                    layer.msg("改分类修改失败");
                }else {
                    layer.alert("修改成功");
                    top.location.reload();
                }
            },"text")
        }

        function add() {
            let categoryName = $.trim($("#categoryName").val());
            if (categoryName==""){
                layer.msg("请填写分类名称");
            }else {
                $.get("CategoryServlet?method=add",{"categoryName":categoryName},function (data) {
                    if (data==-2){
                        layer.msg("请将信息填写完整");
                    }else if (data==-1) {
                        layer.msg("改分类已存在");
                    }else if (data==0){
                        layer.msg("改分类添加失败");
                    }else {
                        layer.alert("添加成功");
                        top.location.reload();
                    }
                },"text")
            }
        }
    </script>
</head>
<body onload="select()">
<div class="m-main">
    <div class="m-food">
        <div class="mf-top border-t">
            <div>
                分类管理
            </div>
        </div>
        <div id="container">
            <script type="text/x-handlebars-template" id="template">
                {{#each this}}
                <div class="mf-menu border-t" style="height: auto; line-height: normal; padding: 30px 0">
                    <div class="fl">
                        <span class="m-wt"></span>
                        <span id="uCategoryName{{categoryId}}">{{categoryName}}</span>
                    </div>
                    <div class="fr dingwei">
                        <button class="xiugai" onclick="change('category{{categoryId}}',1)">
                            修改
                        </button>
                        <button class="del" onclick="del({{categoryId}})">
                            删除
                        </button>
                    </div>

                    <div id="update_category{{categoryId}}" style="display: none;" class="change">
                        <div style="padding-top: 20px" class="clear">
                            <span class="m-wt" style="padding: 0 30px; width: 70px"></span>
                            <input type="text" class="t-ad" style="width: 150px" id="categoryName{{categoryId}}"
                                   placeholder="请输入分类名称" value="{{categoryName}}">
                        </div>

                        <div class="act-botton clear"
                             style="margin: 10px 0 10px 15px; padding: 10px 0">
                            <div class="save-button">
                                <a href="javascript:" class="radius" onclick="update({{categoryId}})">保存</a>
                            </div>
                            <div class="cancel-button">
                                <a href="javascript:" class="radius"
                                   onclick="change('category{{categoryId}}',2)">取消</a>
                            </div>
                        </div>
                    </div>
                </div>
                {{/each}}
            </script>
        </div>
        <div class="mf-top" style="margin-top: 30px">
            <div id="addcategory">
                <div style="line-height: 40px">
                    <span class="m-wt" style="padding: 0 20px"></span><a href="#"
                                                                         class=" rb-red" onclick="change('addcategory',3)">+添加新分类</a>
                </div>
            </div>
            <div id="insert_addcategory" style="display: none;" class="change">
                <div style="padding-top: 20px" class="clear">
                    <span class="m-wt" style="padding: 0 30px; width: 70px"></span>
                    <input type="text" class="t-ad" style="width: 150px" id="categoryName"
                           placeholder="请输入分类名称" />
                </div>

                <div class="act-botton clear"
                     style="margin: 10px 0 10px 15px; padding: 10px 0">
                    <div class="save-button">
                        <a href="javascript:" class="radius" onclick="add()">保存</a>
                    </div>
                    <div class="cancel-button">
                        <a href="javascript:" class="radius"
                           onclick="change('addcategory',4)">取消</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<SCRIPT Language=VBScript><!--

//--></SCRIPT>
