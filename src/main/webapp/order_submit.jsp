<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 23351
  Date: 2021/10/14
  Time: 18:34
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
    <link rel="stylesheet" type="text/css" href="css/address.css"/>
    <script type="text/javascript"
            src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/distpicker/2.0.3/distpicker.js"></script>
    <script src="layer/layer.js" type="text/javascript"></script>
    <script src="js/main.js" type="text/javascript"></script>
    <title>地址</title>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#address").text($("#selectaddress1").find("option:selected").text());
        })

        //控制增加地址
        var count = 0;

        function changeA() {
            count++;
            if (count % 2 == 1) {
                $('#addaddress').show();
            } else {
                $('#addaddress').hide();
            }
        }

        //控制选择地址
        var countb = 0;

        function changeB() {
            countb++;
            if (countb % 2 == 1) {
                $('#selectaddress').show();
            } else {
                $('#selectaddress').hide();
            }
        }

        function changeAddress() {
            $("#address").text($("#selectaddress1").find("option:selected").text());
        }



        //添加
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
                    location.reload();
                },"text")
            }
        }
    </script>
</head>

<body style="background:#F1F5F4">
<%@include file="head.jsp" %>

<div class="m-address clear" id="container">
        <div class="wrapper">
            <div class="area clear" style="padding-left:20px">
                <!-- 送餐方式 -->
                配餐地址
                <div class="type clear">
                    <input type="hidden" id="userId" value="${sessionScope.get("usee").userId}"/>
                    <span id="address" style="width: 200px;display: inline-block"></span>
                    <a href="#" class=" rb-red" style="margin-right:10px" onclick="changeB()">选择 v</a>
                    <a href="#" class=" rb-red" onclick="changeA()">+使用新地址</a>
                </div>
                <div style="display: none; margin-left:0px;margin-top: 0px" id="selectaddress"  class="change">
                    <select id="selectaddress1" onchange="changeAddress()" style="margin-left: 0px">
                        <c:forEach items="${address}" var="address">

                            <c:if test="${address.isDefault==1}">
                                <option value="${address.addressId}" selected>
                                        ${address.addressProvince}${address.addressCity}${address.addressDistrict}${address.addressDescribe}
                                </option>
                            </c:if>
                            <c:if test="${address.isDefault==2}">
                                <option value="${address.addressId}">
                                        ${address.addressProvince}${address.addressCity}${address.addressDistrict}${address.addressDescribe}
                                </option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <div style="display: none; margin-left:0px" id="addaddress" class="change">
                    <div style="margin-top: 0px">
                        <div data-toggle="distpicker">
                            <select id="Province" data-province="湖北省"></select>
                            —
                            <select id="City" data-city="武汉市"></select>
                            —
                            <select id="District" data-district="江汉区"></select>
                            —
                            <input id="Describe" type="text" class="t-ad" style="width: 150px"/>
                        </div>
                    </div>

                    <div class="act-botton clear"
                         style="margin: 20px 40px; padding: 20px 0">
                        <div class="save-button">
                            <a href="javascript:" class="radius" onclick="add()">保存</a>
                        </div>
                        <div class="cancel-button">
                            <a href="javascript:" class="radius"
                               onclick="changeA()">取消</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="area">
                <div class="addr-box-line">
                    <span class="text-w02 fl">顾客姓名</span>
                    <span class="text-w02 fl">${sessionScope.get("user").userName}</span>
                </div>
                <div class="addr-box-line">
                    <span class="text-w02 fl">联系电话</span>
                    <span class="text-w02 fl">>${sessionScope.get("user").userTel}</span>
                </div>
                <div class="addr-box-line">
                    <span class="text-w02 fr">总共金额</span>
                    <span class="text-w02 fr"><span class="login-redcolor" style="font-size:24px">${sum}</span>元</span>
                </div>
                <div class="addr-box-line" id="j-address-reminder">
                </div>
                <div class="act-botton" id="j-act-botton" style="padding:10px 50px; ">
                    <div style="padding:10px 50px; background:#DF544E; width:120px; text-align:center;
        border-radius:5px
       "><a href="javascript:" style="color:#FFF; font-size:22px;">提交订单 &gt;</a></div>
                </div>
            </div>


            <div class="area clear" style="margin-top:60px; font-size:14px; color:#999; padding-left:30px">
                友情提示：网络订餐不提供订单修改和取消功能，请提交前仔细核实订单内容
            </div>
        </div>


        <%@include file="foot.jsp" %>
</div>
</body>
</html>
<SCRIPT Language=VBScript><!--

//-->
</SCRIPT>