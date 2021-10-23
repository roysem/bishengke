<%--
  Created by IntelliJ IDEA.
  User: 23351
  Date: 2021/10/9
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/css.css" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <script type="text/javascript"
            src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="layer/layer.js" type="text/javascript"></script>
    <script src="js/main.js" type="text/javascript"></script>
</head>
<script>
    function exit() {
        layer.confirm('是否退出',function () {
            location.href="UserServlet?method=exit";
        });
    }

    function change2(data) {
        console.log($("#userId").val())
        if($("#userId").val()==""){
            show('',550,550,'login.jsp');
        }else {
            if(data==1){
                location.href="member.jsp";
            }else if(data==2) {
                location.href="menu.jsp";
            }else {
                return true;
            }
        }
    }

</script>
<body>
<div class="m-top">
    <input type="hidden" id="userId" value="${sessionScope.get("user").userId}">
    <div class="box" id="j-top-nav">
        <a href="main.jsp"><div class="logo fl"></div> </a>
        <a href="menu.jsp"><div class="menu fl" style="width:100px">
            菜单
        </div> </a>
        <div class="separator fl"></div>
        <a href="#" onclick="change2(1)" ><div class="menu fl"  style="width:100px">
            会员中心
        </div> </a>
        <input type="hidden" id="j-is-login" value="false" />
        <div class="clien fl" onclick="exit()">
            <span>${sessionScope.get("user").userName}<img src="images/common/arrow-down.png" align="center" /></span>
        </div>
        <div class="start fr" id="j-start-order"
             onclick="change2(2)">
            立即点餐
        </div>
        <input type="hidden" id="isMemberLogin" value="" />
        <input type="hidden" id="isNewLogin" value="" />
        <input type="hidden" id="j-is-index" value="true" />
        <input type="hidden" id="j-has-order" value="false" />
        <input type="hidden" id="j-order-type" name="orderType" value="null" />
        <input type="hidden" id="j-defaultClassHtmlName" value="Special.htm" />
        <input type="hidden" id="j-username-afterlogin" value="" />

    </div>
</div>
</body>
</html>
