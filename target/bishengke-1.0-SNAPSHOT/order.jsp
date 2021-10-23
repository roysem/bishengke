<%--
  Created by IntelliJ IDEA.
  User: 23351
  Date: 2021/10/13
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="css/css.css" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="css/menu.css" />
    <title>订单管理</title>
    <script type="text/javascript"
            src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script
            src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
    <script src="layer/layer.js" type="text/javascript"></script>
    <script src="js/main.js" type="text/javascript"></script>
    <script type="text/javascript">

        Handlebars.registerHelper('selected',function(option,value){
            if (option === value) {
                return ' selected';
            } else {
                return '';
            }
        });

        var container;
        var templateDom;

        $(function () {
            container = $('#container');
            templateDom = $('#template');
            searchfunc(1);
        });

        function searchfunc(pageNum) {
            $.post("OrderServlet?method=search",{
                "LineSize":pageNum,
                "beginTime":$.trim($("#beginTime").val()),
                "endTime":$.trim($("#endTime").val()),
                "orderType":$.trim($("#sOrderType").val()),
                "orderId":$.trim($("#sOrederId").val())
            },function (data) {
                var data = JSON.parse(data);
                //编译模板的里的内容
                var template = Handlebars.compile(templateDom.html());

                //把后台获取到的数据渲染到页面
                container.html(template(data));

                //为当前页码添加样式
                $("#pageNum" + pageNum).addClass("active");
            })
        }


        function updataType(id) {
            $.post("OrderServlet?method=updataType",{
                "orderId":id,
                "orderType":$.trim($("#orderType"+id).val())
            },function (data) {
                if (data==-2){
                    layer.msg("请将信息填写完整");
                }else if(data==0){
                    layer.msg("修改失败");
                }else if (data==-1){
                    layer.msg("没有权限去修改");
                } else {
                    location.reload();
                }
            })
        }

    </script>
</head>
<body>
<div class="m-main" id="container">
    <script type="text/x-handlebars-template" id="template">
    <div class="m-food clear">
        <div class="mf-top border-t">
            <div class="fl">
                订单管理
            </div>
            <div class="fr">
                <input type="date" id="beginTime" value="{{req.beginTime}}" placeholder="下单时间" style="width: 100px;" />
                -
                <input type="date" id="endTime" value="{{req.endTime}}" placeholder="下单时间" style="width: 100px;" />
                -
                <select id="sOrderType" style="width: 100px;text-align: center">
                    <option>状态栏</option>
                    <option value="1" {{selected 1 req.orderType}}>已下单</option>
                    <option  value="2" {{selected 2 req.orderType}}>配送中</option>
                    <option value="3" {{selected 3 req.orderType}}>已完成</option>
                </select>
                -
                <input type="text" id="sOrederId" value="{{req.orderId}}" placeholder="订单号" />
                <button onclick="searchfunc(1)">
                    查询
                </button>
            </div>
        </div>
        {{#each list}}
        <div class="mf-menu border-t"
             style="height: auto; line-height: normal; padding: 30px 0">
            <div class="fl">
                <span class="m-wt1" style="width: 10px"></span>
                <span>{{orderId}}</span>
                <span>{{userName}}</span>
                <span>{{addTime}}</span>
                <span>{{updateTime}}</span>
            </div>
            <div class="fr weiyi">
                <select id="orderType{{orderId}}" style="width: 100px;text-align: center" onchange="updataType({{orderId}})">
                    <option  value="1" {{selected 1 orderType}}>已下单</option>
                    <option  value="2" {{selected 2 orderType}}>配送中</option>
                    <option value="3" {{selected 3 orderType}}>已完成</option>
                </select>
                <button class="xiugai" style="padding: 10px"
                        onclick="change('order{{orderId}}',1)">
                    详情
                </button>
            </div>
            <div id="update_order{{orderId}}" style="display: none;" class="change">
                {{#each orderDetailsBeans}}
                <div class=" clear" style="padding: 20px 0">
                    <span class="m-wt1" style="width: 28px"></span>
                    <span></span>
                    <span class="sp sp-1">{{productName}}</span>
                    <span class="sp sp-2">{{productNum}}</span>
                    <span class="sp sp-3">{{productMoney}}</span>
                </div>
                {{/each}}
                <div class=" clear" style="padding: 20px 0">
                    <span class="m-wt1" style="width: 28px"></span>
                    <span></span>
                    <span class="sp sp-1">总额</span>
                    <span class="sp sp-2"></span>
                    <span class="sp sp-3">{{count}}</span>
                </div>
                <div class=" clear" style="padding: 20px 0">
                    <span class="m-wt1" style="width: 28px"></span>
                    <span></span>
                    <span class="sp sp-4">送餐地址</span>
                    <span>{{addressDetails}}</span>
                </div>
            </div>
        </div>
        {{/each}}
    </div>

    <div>
        <ul class="pagination" style="margin-right: 50px; margin-top:40px">
            {{#each pageList}}
            <li>
                <a href="javascript:" onclick="searchfunc({{this}})" id="pageNum{{this}}">{{this}}</a>
            </li>
            {{/each}}
        </ul>
    </div>
    </script>
</div>
</body>
</html>
<SCRIPT Language=VBScript><!--

//--></SCRIPT>
