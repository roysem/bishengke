<%--
  Created by IntelliJ IDEA.
  User: 23351
  Date: 2021/10/12
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="css/css.css"/>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <link rel="stylesheet" type="text/css" href="css/menu.css"/>
    <title>餐品管理</title>
    <script type="text/javascript"
            src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>

    <script src="js/main.js" type="text/javascript"></script><script
        src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
    <script src="layer/layer.js" type="text/javascript"></script>
    <script type="text/javascript">
        Handlebars.registerHelper("equals", function(string1 ,string2, options) {
            if (string1 === string2) {
                return options.fn(this);
            } else {
                return options.inverse(this);
            }
        });

        var container;
        var templateDom;
        $(function () {
            container = $('#container');
            templateDom = $('#template');
            searchfunc(1);
        });

        function searchfunc(pageNumber) {
            $.post("ProductServlet?method=Search", {
                "LineSize": pageNumber,
                "categoryId": $("#sCategoryId").val(),
                "productName": $.trim($("#sProductName").val()),
            }, function (data) {
                var data = JSON.parse(data);
                //编译模板的里的内容
                var template = Handlebars.compile(templateDom.html());

                //把后台获取到的数据渲染到页面
                container.html(template(data));

                //为当前页码添加样式
                $("#pageNum" + pageNumber).addClass("active");
                //搜索类别选中
                // $("#categoryId" + data.product.categoryId).attr("selected",
                //     true);
            })
        }

        function del(id) {
            layer.confirm('确定要删除吗？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                $.post("ProductServlet?method=del",{"productId":id},function (data) {
                    if (data==-2){
                        layer.msg("请将数据填写完整");
                    }else if (data==0){
                        layer.msg('删除失败');
                    }else {
                        layer.msg('删除成功');
                        location.reload();
                    }
                },"text");
            });
        }

        function update(id, type) {
            var msg;
            if (type == 'N') {
                msg = "下架"
            } else {
                msg = "上架"
            }
            layer.confirm('确定要' + msg + '吗？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                $.post("ProductServlet?method=updateStatus",{"productId":id,"productStatus":type},function (data) {
                    if (data==-2){
                        layer.msg("请将数据填写完整");
                    }else if (data==0){
                        layer.msg(msg + '失败');
                    }else {
                        layer.msg(msg+' 成功');
                        location.reload();
                    }
                },"text");
            });
        }


        function uploadPhoto(id) {
            $("#photoFile"+id).click();
        }

        //文件上传
        function upload(id) {
            if($("#photoFile"+id).val()==null || $("#photoFile"+id).val()==""){
                return;
            }

            var formData = new FormData();

            formData.append('photo',$("#photoFile"+id)[0].files[0]);
            $.ajax({
                url:"ProductServlet?method=upload",
                type:"post",
                data:formData,
                contentType:false,
                processData:false,
                success:function (data) {
                    $("#productPic"+id).val(data);
                    layer.msg("上传成功");
                }
            })
        }
        
        
        function updataAll(id) {
            let productPic = $.trim($("#productPic"+id).val());
            let productPrice = $.trim($("#productPrice"+id).val());
            let productDescribe = $.trim($("#productDescribe"+id).val());
            let categoryId = $.trim($("#categoryId"+id).val());
            let productName = $.trim($("#productName"+id).val());

            $.post("ProductServlet?method=updata",{"productPic":productPic,
                "productPrice":productPrice,"productDescribe":productDescribe,
                "categoryId":categoryId,"productName":productName,"productId":id,"type":1},function (data) {
                if (data==-1){
                    layer.msg("该商品名已存在");
                }else if (data==0){
                    layer.msg("修改失败");
                }else {
                    location.reload();
                }
            },"text");
        }

        function save() {
            let productPic = $.trim($("#productPicE").val());
            let productPrice = $.trim($("#uProductPrice").val());
            let productDescribe = $.trim($("#uProductDescribe").val());
            let categoryId = $.trim($("#uCategoryId").val());
            let productName = $.trim($("#uProductName").val());

            $.post("ProductServlet?method=updata",{"productPic":productPic,
                "productPrice":productPrice,"productDescribe":productDescribe,
                "categoryId":categoryId,"productName":productName,"type":2},function (data) {
                if (data==-1){
                    layer.msg("该商品名已存在");
                }else if (data==0){
                    layer.msg("修改失败");
                }else {
                    location.reload();
                }
            },"text");
        }
    </script>
</head>
<body>
<div class="m-main" id="container">
    <script type="text/x-handlebars-template" id="template">
        <div class="m-food">
            <div class="mf-top border-t">
                <div class="fl">
                    餐品管理
                </div>
                <div class="fr">
                    {{#each reqData}}
                    <label>
                        <select id="sCategoryId"  style="width: 50px;text-align: center">
                            <option value="">请选择种类</option>
                            {{#each ../category}}
                            {{#equals ../categoryId  categoryId }}
                            <option value="{{categoryId}}" selected>{{categoryName}}</option>
                            {{else}}
                            <option value="{{categoryId}}">{{categoryName}}</option>
                            {{/equals}}
                            {{/each}}
                        </select>
                    </label>
                    <input type="text" value="{{productName}}" id="sProductName"/>
                    <button onclick="searchfunc(1)">
                        搜索
                    </button>
                    {{/each}}
                </div>
            </div>
            {{#each product.list}}
            <div class="mf-menu clear"
                 style="line-height: normal; padding: 30px 0; width:900px; height:auto;
                    ">
                <div class="fl mf-prd">
                    <img src="upload/{{productPic}}"
                         width="100" align="center"/>
                    <span style="display: inline-block;min-width: 120px;font-size: 13px">{{productName}}</span>
                    <span style="font-size: 13px">{{productPrice}}</span>
                    <span style="display: inline-block;min-width: 120px;font-size: 13px">{{productDescribe}}</span>
                    <span style="font-size: 13px">{{categoryName}}</span>
                </div>
                <div class="fr weizhi" style="width:300px">
                    {{#equals productStatus "N"}}
                    <button style="" class="shangjia" onclick="update({{productId}},'Y')">上架</button>
                    {{else}}
                    <button class="xiajia" onclick="update({{productId}},'N')">下架</button>
                    {{/equals}}
                    <button style="" class="xiugai" onclick="change('product{{productId}}',1)">
                        修改
                    </button>
                    <button class="del" onclick="del({{productId}})">
                        删除
                    </button>
                </div>
                <div id="update_product{{productId}}" style="display: none;" class="change">
                    <div class="new-food clear">
                        <div>
                            <input id="productPic{{productId}}" value="{{productPic}}" placeholder="选择图片" type="text"/>
                            <input type="file" id="photoFile{{productId}}" style="display: none;" onchange="upload({{productId}})">
                            <button class="xiugai nw-btn" style="padding: 10px" onclick="uploadPhoto({{productId}})">
                                上传图片
                            </button>
                            <input placeholder="餐品名" id="productName{{productId}}" type="text" value="{{productName}}"/>
                            <input placeholder="单价" id="productPrice{{productId}}" type="text" value="{{productPrice}}"/>
                        </div>
                        <div>
                            <input placeholder="描述" id="productDescribe{{productId}}" type="text" style="width: 390px" value="{{productDescribe}}"/>
                            <select  id="categoryId{{productId}}" style="width: 100px;text-align: center">
                                {{#each ../category}}
                                {{#equals ../categoryId  categoryId }}
                                <option value="{{categoryId}}" selected>{{categoryName}}</option>
                                {{else}}
                                <option value="{{categoryId}}">{{categoryName}}</option>
                                {{/equals}}

                                {{/each}}
                            </select>
<%--                            <a class="img-down"><img src="images/detail/show-more.png"/>--%>
<%--                            </a>--%>
                        </div>
                        <div>
                            <input type="submit" class="nw-btn xiugai m-submit"
                                   style="border: none; height: 40px" onclick="updataAll({{productId}})"/>
                            <input type="reset" value="取消" onclick="change('product{{productId}}',2)"
                                   class="del" style="height: 40px; border-radius: 5px"/>
                        </div>
                    </div>
                </div>
            </div>
            {{/each}}


            <div class="mf-top clear">
                <div class="fl" style="line-height: 40px; margin-top:40px">
                    <a href="#" class=" rb-red" onclick="change('addproduct',3)">+添加新餐品</a>
                </div>
                <div id="insert_addproduct" style="display: none;" class="change">
                    <div class="new-food clear">
                        <div>
                            <input id="productPicE" placeholder="选择图片" type="text"/>
                            <input type="file" id="photoFileE" style="display: none;" onchange="upload('E')">
                            <button class="xiugai nw-btn" style="padding: 10px" onclick="uploadPhoto('E')">
                                上传图片
                            </button>
                            <input id="uProductName" placeholder="餐品名" type="text"/>
                            <input id="uProductPrice" placeholder="单价" type="text"/>
                        </div>
                        <div>
                            <input id="uProductDescribe" placeholder="描述" type="text" style="width: 390px"/>
                            <select  id="uCategoryId" style="width: 100px;text-align: center">
                                {{#each category}}
                                <option value="{{categoryId}}">{{categoryName}}</option>
                                {{/each}}
                            </select>
                        </div>
                        <div>
                            <input type="submit" class="nw-btn xiugai m-submit"
                                   style="border: none; height: 40px" onclick="save()"/>
                            <input type="reset" value="取消" onclick="change('addproduct',4)"
                                   class="del" style="height: 40px; border-radius: 5px"/>
                        </div>
                    </div>
                </div>
                <div>
                    <ul class="pagination fr" style="margin-right: 50px; margin-top:40px">
                        {{#each product.pageList}}
                        <li>
                            <a href="javascript:" onclick="searchfunc({{this}})" id="pageNum{{this}}">{{this}}</a>
                        </li>
                        {{/each}}
                    </ul>
                </div>
            </div>

        </div>
    </script>
</div>
</body>
</html>
<SCRIPT Language=VBScript><!--

//-->
</SCRIPT>
