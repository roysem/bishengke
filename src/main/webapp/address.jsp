<%--
  Created by IntelliJ IDEA.
  User: 23351
  Date: 2021/10/9
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>地址管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="css/css.css" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="css/menu.css" />
    <link rel="stylesheet" type="text/css" href="css/address.css" />
    <script type="text/javascript"
            src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/distpicker/2.0.3/distpicker.js"></script>

    <script src="layer/layer.js" type="text/javascript"></script>
    <script src="js/main.js" type="text/javascript"></script>
    <script type="text/javascript">

    </script>

</head>
<body>
<div class="m-main">
    <input type="hidden" id="userId" value="${sessionScope.get("user").userId}"/>
    <div class="m-food">
        <div class="mf-top border-t">
            <div>
                地址管理
            </div>
        </div>
        <c:forEach items="${list}" var="c" varStatus="status">
        <div class="mf-menu border-t"
             style="height: auto; line-height:7px; padding: 30px 0">

            <div class="fl">
                <span class="m-wt"></span>
                <input type="hidden" id="addressId${status.count}" value="${c.addressId}">
                <span>${c.addressProvince}${c.addressCity}${c.addressDistrict}${c.addressDescribe}</span>
            </div>
            <div class="fr">
                <button class="xiugai dingwei" onclick="change('address${status.count}',1)">
                    修改
                </button>
                <button class="del dingwei" onclick="del(${c.addressId})">
                    删除
                </button>
            </div>

            <div style="display: none;" class="change" id="update_address${status.count}">
                <div style="padding-top: 20px" class="clear">
                    <span class="m-wt" style="padding: 0 30px; width: 70px"></span>
                    <div data-toggle="distpicker">
                        <select id="addressProvince${status.count}" data-province="${c.addressProvince}"  ></select>
                        —
                        <select id="addressCity${status.count}" data-city="${c.addressCity}"></select>
                        —
                        <select id="addressDistrict${status.count}" data-district="${c.addressDistrict}"></select>
                        —
                        <input id="addressDescribe${status.count}" type="text" class="t-ad" style="width: 150px" value="${c.addressDescribe}" />
                    </div>
                </div>

                <div class="act-botton clear"
                     style="margin: 10px 0 10px 15px; padding: 10px 0">
                    <div class="save-button">
                        <a href="javascript:" class="radius" onclick="update(${status.count})">保存</a>
                    </div>
                    <div class="cancel-button">
                        <a href="javascript:" class="radius"
                           onclick="change('address${status.count}',2)">取消</a>
                    </div>
                </div>
            </div>
        </div>
        </c:forEach>
    </div>
    <div class="mf-top" style="margin-top: 30px">
        <div id="addaddress">
            <div style="line-height: 40px">
                <span class="m-wt" style="padding: 0 30px"></span><a href="#"
                                                                     class=" rb-red" onclick="change('addaddress',3)">+使用新地址</a>
            </div>
        </div>
        <div style="display: none;" id="insert_addaddress" class="change">
            <div style="margin-top: 20px">
                <div data-toggle="distpicker">
                    <select id="Province" data-province="湖北省"  ></select>
                    —
                    <select id="City" data-city="武汉市"></select>
                    —
                    <select id="District" data-district="江汉区"></select>
                    —
                    <input id="Describe" type="text" class="t-ad" style="width: 150px" />
                </div>
            </div>

            <div class="act-botton clear"
                 style="margin: 20px 40px; padding: 20px 0">
                <div class="save-button">
                    <a href="javascript:" class="radius" onclick="add()">保存</a>
                </div>
                <div class="cancel-button">
                    <a href="javascript:" class="radius"
                         onclick="change('addaddress',4)">取消</a>
                </div>
            </div>
        </div>
        <div class="area clear"
             style="margin-top: 60px; font-size: 14px; color: #999">
            <span class="m-wt" style="padding: 0 30px"></span> 友情提示：
            <br />
            <span class="m-wt" style="padding: 0 30px"></span>如果您选择不设置密码，您送餐信息的主要内容会以*号遮蔽，如：虹桥路2号，会显示为“虹﹡……﹡2号”。
            <br />
            <span class="m-wt" style="padding: 0 30px"></span>该显示信息可能不受保护，建议您设置密码。
        </div>
    </div>
</div>
</body>
</html>
<SCRIPT Language=VBScript><!--

//--></SCRIPT>

<script>
    function update(e) {
        let addressProvince = $.trim($('#addressProvince'+e).val());
        let addressCity = $.trim($('#addressCity'+e).val());
        let addressDistrict = $.trim($('#addressDistrict'+e).val());
        let addressDescribe = $.trim($('#addressDescribe'+e).val());
        let addressId = $.trim($('#addressId'+e).val());
        let userId = $.trim($("#userId").val());

        if (addressId==null || addressProvince==null || addressCity==null || addressDistrict==null || addressDescribe==null){
            layer.msg("请将信息填写完整");
        }else {
            $.post("TAddressServlet?method=update",{"userId":userId,"addressId":addressId,"addressProvince":addressProvince ,"addressCity":addressCity,"addressDistrict":addressDistrict,"addressDescribe":addressDescribe},function (data) {
                if (data==-1){
                    layer.msg("请将信息填写完整");
                }else if (data==0){
                    layer.msg("修改失败");
                }else if (data==-2){
                    layer.msg("改地址以有");
                }
                top.location.reload();
            },"text")
        }
    }



    function add() {
        let addressProvince = $.trim($('#Province').val());
        let addressCity = $.trim($('#City').val());
        let addressDistrict = $.trim($("#District").val());
        let addressDescribe = $.trim($("#Describe").val());
        let userId = $.trim($("#userId").val());

        if (addressProvince==null || addressCity==null || addressDistrict==null || addressDescribe==null){
            layer.msg("请将信息填写完整");
        }else {
            $.post("TAddressServlet?method=add",{"userId":userId,"addressProvince":addressProvince ,"addressCity":addressCity,"addressDistrict":addressDistrict,"addressDescribe":addressDescribe},function (data) {
                if (data==-1){
                    layer.msg("请将信息填写完整");
                }else if (data==0){
                    layer.msg("修改失败");
                }else if (data==-2){
                    layer.msg("改地址以有");
                }
                top.location.reload();
            },"text")
        }
    }

    function del(addressId){
        layer.confirm('确定要删除吗？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            $.get("TAddressServlet?method=del",{"addressId":addressId});
            layer.msg('删除成功');
            top.location.reload();
        });
    }
</script>