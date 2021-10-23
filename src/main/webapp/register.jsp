<%--
  Created by IntelliJ IDEA.
  User: 23351
  Date: 2021/10/8
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="css/css.css" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <title>注册</title>
    <script type="text/javascript"
            src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="layer/layer.js" type="text/javascript"></script>
    <script src="js/main.js" type="text/javascript"></script>
</head>
<script type="text/javascript">
    var telStatus=false;//电话状态
    var pwdStatus=false;//密码状态
    var nameStatus=false;//用户名状态
    var userSex="M";//性别

    //判断用户名是否重复
    function isReTel(tel) {
        // var telStatus=false;//电话状态
        var tel=$.trim(tel);
        let telTest=/^1[34578]\d{9}$/;
        if(!telTest.test(tel)){
            layer.msg("请输入有效的手机号码");
        }else {
            $.post("UserServlet?method=isReTel",{"userTel":tel},function (data) {
                if (data==-1){
                    layer.msg("该手机已经被注册");
                    telStatus=false;
                }else {
                    telStatus=true;
                }
            })
        }
    }
    $(function () {
        //判断密码是否符合要求
        $("#pwd").on('change',function () {
            let s = $.trim($(this).val());
            alert(s);
            if (s.length <6 || s.length>16){
                layer.msg("请输入6-16位密码");
                pwdStatus=false;
            }else {
                pwdStatus=true;
                console.log(pwdStatus);
            }
        });

        //姓名
        $("#name").on('change',function () {
            let s = $.trim($(this).val());
            if (s.length>5){
                layer.msg("请输入不超过5位的姓名");
                nameStatus=false;
            }else {
                nameStatus=true;
            }
        })
    })

    //性别切换
    function setSex(t){
        userSex = t.id;
        if(t.id == "M"){
            $("#F").removeClass("login-redcolor");
            $("#M").addClass("login-redcolor");
        }else{
            $("#M").removeClass("login-redcolor");
            $("#F").addClass("login-redcolor");
        }
    }

    //注册
    function register() {
        console.log(pwdStatus);
        if (!telStatus){
            layer.msg("请输入有效的手机号码");
        }else if(! pwdStatus){
            layer.msg("请输入6-16位密码");
        }else if (! nameStatus){
            layer.msg("请输入不超过5位的姓名");
        }else {
            $.post("UserServlet?method=register",{"userTel":$.trim($("#tel").val()),
                "userPwd":$.trim($("#pwd").val()),"userName":$.trim($("#name").val()),"userSex":userSex},function (data) {
                if (data==-2){
                    layer.msg("请将数据填写完整");
                }else if (data==-1){
                    layer.msg("该手机号已经被注册");
                }else if (data==0){
                    layer.msg("注册失败");
                }else {
                    top.location.reload();
                }
            },"text");
        }
    }
</script>
<body>

<!--register begin-->
<div class="m-login">
    <div class="login-logo"><img src="images/common/logo.png" /></div>
    <div class="login-title border-bot">欢迎注册加入会员中心</div>
    <div class="input-box  border-bot"><img src="images/login/icon-phone.png" /><input type="text" id="tel" placeholder="请输入手机号"  class="input-box1" onchange="isReTel($(this).val())"></div>
    <div class="input-box  border-bot"><img src="images/login/icon-password.png" /><input type="text" id="pwd" placeholder="密码，长度6-16字符"  class="input-box1" /></div>
    <div class="input-box  border-bot"><img src="images/login/icon-name.png" /><input type="text" id="name" placeholder="姓名，最多5个字" /> <span><a href="#"  onclick="setSex(this)" class="login-redcolor" id="M">先生</a> | <a href="#"  onclick="setSex(this)"  style="margin:0" id="F">女士</a></span></div>
    <div class="input-box "></div>
    <div class="login-me cursor"><button class="cursor" onclick="register()">立即注册</button></div>
</div>
<!--register end-->
</body>
</html>
<SCRIPT Language=VBScript><!--

//--></SCRIPT>
