<%--
  Created by IntelliJ IDEA.
  User: 23351
  Date: 2021/10/8
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="css/css.css" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <script type="text/javascript"
            src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="layer/layer.js" type="text/javascript"></script>
    <script src="js/main.js" type="text/javascript"></script>
    <title>登录</title>
</head>

<body>

<!--login begin-->
<div class="m-login">
    <div class="login-logo"><img src="images/common/logo.png" /></div>
    <div class="login-title border-bot login-redcolor" style="font-weight:normal">账号密码登陆</div>
    <div class="input-box  border-bot"><img src="images/login/icon-phone.png" /><input type="text" id="tel" placeholder="请输入手机号" class="input-box1"/></div>
    <div class="input-box  border-bot"><img src="images/login/icon-password.png" /><input type="password" id="psd" placeholder="密码，长度6-16字符" class="input-box1" /></div>
    <div class="input-box" style="text-align:left"><a href="register.jsp" class="login-redcolor" style="text-decoration:underline; font-size:18px">快速注册</a></div>
    <div class="input-box "></div>
    <div class="login-me cursor"><button class="cursor" onclick="login()">登陆</button></div>
</div>
<!--login end-->
</body>
</html>
<script type="text/javascript">
    function login() {
        let tel = $.trim($('#tel').val());
        let psd = $.trim($("#psd").val());
        let telTest=/^1[34578]\d{9}$/;
        if(!telTest.test(tel)){
            layer.msg("请输入有效的手机号码");
        }else if (psd.length > 16 || psd.length<6) {
            layer.msg("请输入6-16位密码");
        }else {
            $.post("UserServlet?method=login",{"userTel":tel,"userPwd":psd},function (data) {
                if(data==-2){
                    layer.msg("请将信息填写完整");
                }else if(data==-1){
                    layer.msg("该用户不存在");
                }else if(data==0){
                    layer.msg("密码不正确");
                }else if (data==2){
                    layer.msg("该用户已经被停封")
                }else {
                    top.location.reload();
                }
            },"text")
        }
    }
</script>
