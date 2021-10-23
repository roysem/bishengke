<%--
  Created by IntelliJ IDEA.
  User: 23351
  Date: 2021/10/9
  Time: 9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="css/css.css" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="css/address.css" />
    <title>个人信息</title>
    <script type="text/javascript"
            src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="layer/layer.js" type="text/javascript"></script>
    <script src="js/main.js" type="text/javascript"></script>
</head>
<script>

    function verify1() {
        $("#codeVerify").attr("src","UserServlet?method=codeVerify&time="+Math.random()*1000);
    }
</script>
<body>
<div class="m-address">
    <div class="wrapper">
        <div class="area" style="bottom:110px;">
            <div class="type border-bottom">
                <span class="left">个人信息</span>
            </div>
            <div class="title1 a-user">
                <input type="hidden" id="userId" value="${list.userId}">

                <div class="fl">
							<span><img src="images/member/desc-icon-name.png" />
								姓名/性别</span>
                    <span class="pad" id="uName">${list.userName} ${list.userSex=="M"?"先生":"女士"}</span>
                </div>
                <div class="fr">
                    <a href="#" class="login-redcolor" onclick="change('name_sex',1)">修改</a>
                </div>
            </div>
            <div style="display: none;" class="title1 a-user a-setuser change"
                 id="update_name_sex">
                <div class="fl userleft">
							<span><img src="images/member/desc-icon-name.png" />
								姓名/性别</span>
                </div>
                <div class="text-input07" style="width: 155px">
                    <input id="j-new-city"  class="name" onchange="isReName(this)" type="text" value="${list.userName} " />
                </div>
                <select id="userSex" name="userSex" class="text-input07">
                    <option value="M" ${list.userSex == "M"?"selected":""}>先生</option>
                    <option value="F" ${list.userSex == "F"?"selected":""}>女士</option>
                </select>
                <div class="act-botton clear">
                    <div class="save-button">
                        <a href="javascript:" onclick="updateName()" class="radius">保存</a>
                    </div>
                    <div class="cancel-button">
                        <a href="javascript:" class="radius"
                           onclick="change('name_sex',2)">取消</a>
                    </div>
                </div>
            </div>
            <div class="border-bottom"></div>
            <div class="title1 a-user">
                <div class="fl">
							<span><img src="images/member/desc-icon-phone.png" />
								手机号码</span>
                    <span class="pad">${list.userTel}</span>
                </div>
            </div>
            <div class="border-bottom"></div>
            <div class="title1 a-user">
                <div class="fl">
                    <span><img src="images/login/icon-name.png" /> 登陆密码</span>
                    <input type="password" value="11111" style="border: none"
                           class="pad" />
                    <span class="add-icon cursor"></span>
                </div>
                <div class="fr">
                    <a href="#" class="login-redcolor" onclick="change('pwd',1)">修改</a>
                </div>
            </div>
            <div style="display: none;" class="title1 a-user change" id="update_pwd">
                <div class="fl">
                    <span><img src="images/login/icon-name.png" /> 登陆密码</span>
                </div>
                <div class="fl">
                    <div class="text-input07">
                        <input type="text" id="oldPwd" value="请输入旧密码" />
                    </div>
                    <div class="fr">
                        <p style="line-height: 20px; margin-left: 10px">
                            请输入6-16位密码，可使用阿拉伯数字
                            <br />
                            英文字母或两者结合
                        </p>
                    </div>
                    <br />
                    <div class="text-input07">
                        <input type="text" id="newPwd"  value="请输入新密码" />
                    </div>
                    <br />
                    <div class="text-input07">
                        <input type="text" id="code" value="请验证码" />
                    </div>
                    <div class="fl">
                        <p style="line-height: 30px; margin-left: 10px">
                            看不清？
                            <a href="javascript:" onclick="verify1()" class="login-redcolor" >换一张</a>
                            <img src="UserServlet?method=codeVerify" id="codeVerify" alt="验证码" onclick="verify1()">
                        </p>
                    </div>
                </div>
                <div class="act-botton clear"
                     style="margin: 20px 0; padding: 20px 0">
                    <div class="save-button">
                        <a href="javascript:" class="radius" onclick="updatePwd()">保存</a>
                    </div>
                    <div class="cancel-button">
                        <a href="javascript:" class="radius" onclick="change('pwd',2)">取消</a>
                    </div>
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
<script type="text/javascript">

//    /**
//     * 刷新验证码
//     */
//    function verify() {
//        alert(1);
//    }

    //姓名是否一致
    function isReName(data) {
        let s = $.trim($(data).val());
        let s1 = $.trim($("#uName").text());
        if (s.length>5){
            layer.msg("姓名请在5个字以内");
        }else if(s==s1.split(" ")[0]){
            layer.msg("姓名不能和原性别一样")
        }
    }

    function updateName() {
        let s = $.trim($(".name").val());
        let s1 = $.trim($("#uName").text());
        let s2 = $.trim($("#userSex").val());
        if (s.length>5){
            layer.msg("姓名请在5个字以内");
            return;
        }
        if (s==s1.split(" ")[0] && s2==s1.split(" ")[1] ){
            layer.msg("请做出修改");
        }else {
            $.post("UserServlet?method=updateName",{"userId":$("#userId").val(),"userName":s,"userSex":s2},function (data) {
                if (data==-1){
                    layer.msg("请将信息填写完整");
                }else if (data==0){
                    layer.msg("修改失败")
                }else {
                    top.location.reload();
                }
            },"text")
        }
    }

    function updatePwd() {
        let oldPwd = $.trim($("#oldPwd").val());
        let newPwd = $.trim($("#newPwd").val());
        let code = $.trim($("#code").val());

        if (oldPwd==null || newPwd==null || code==null){
            layer.msg("请将信息填写完整");
            return;
        }
        if (newPwd.length<6 || newPwd.length>16){
            layer.msg("请输入6-16位密码");
        }else if (oldPwd ==newPwd){
            layer.msg("不能用原密码一致");
        }else {
            $.post("UserServlet?method=updatePwd",{"userId":$("#userId").val(),"oldPwd":oldPwd,"newPwd":newPwd,"code":code},function (data) {
                if (data==-2){
                    layer.msg("请将信息输入完整");
                }else if (data==-1){
                    layer.msg("验证码输入错误");
                }else if (data==0){
                    layer.msg("密码错误");
                }else if (data==2){
                    layer.msg("修改失败");
                }else {
                    top.location.reload();
                }
            },"text")
        }
    }

    function verify() {

    }
</script>