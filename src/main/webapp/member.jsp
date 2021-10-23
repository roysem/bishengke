<%--
  Created by IntelliJ IDEA.
  User: 23351
  Date: 2021/10/9
  Time: 9:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="css/css.css" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <title>会员管理</title>
    <script type="text/javascript"
            src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/distpicker/2.0.3/distpicker.js"></script>
    <script src="layer/layer.js" type="text/javascript"></script>
    <script src="js/main.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $(".center-bg li:first").addClass("on");
            $("#url").attr('src','RenderServlet?method=userInfo');
            $(".center-bg li").click(function() {
                $(".center-bg li").removeClass("on");
                $(this).addClass("on");
                $("#url").attr('src',$(this).children("input").val());
            });
        });
    </script>
</head>
<body style="background: #efeee9;">
<input type="hidden" id="pageName" value="customerCenter" />
<input type="hidden" id="morePrivilege" value="false" />
<form id="j-main-form" action="">
    <%@include file="head.jsp"%>
    <div id="j-popup-captcha"></div>
    <div id="j-popup-click"></div>

    <div class="m-customer-center">
        <div class="ui-chat" id="j-chat">
            <div class="online chat"></div>
            <div class="offline chat">
                <div class="tip"></div>
            </div>
        </div>

        <div id="j-center-top" class="top-bg">
            <div class="img"></div>
        </div>
        <div class="center-bg">
            <div class="fl center-left">
                <ul class="font14 cursor">
                    <li>
                        <input type="hidden" value="RenderServlet?method=userInfo"/>
                        <a num="7" class="tab07" href="javascript:">个人信息</a>
                    </li>
                    <li>
                        <input type="hidden" value="RenderServlet?method=address"/>
                        <a num="6" class="tab06" href="javascript:">地址管理</a>
                    </li>
                    <c:if test="${sessionScope.user.userRole=='A'}">
                    <li>
                        <input type="hidden" value="UserServlet?method=fuzzyQuery&LineSize=1"/>
                        <a num="1" class="tab01" href="javascript:">用户管理</a>
                    </li>
                    <li>
                        <input type="hidden" value="category.jsp"/>
                        <a num="6" class="tab08" href="javascript:">分类管理</a>
                    </li>
                    <li>
                        <input type="hidden" value="product.jsp"/>
                        <a num="7" class="tab09" href="javascript:">餐品管理</a>
                    </li>
                    </c:if>
                    <li>
                        <input type="hidden" value="order.jsp"/>
                        <a num="5" class="tab05" href="javascript:">订单管理</a>
                    </li>
                </ul>
            </div>
            <div id="j-customer-center-right" class="center-right">
                <div class="m-member-home">
                    <div class="center">
                        <div class="left center-left">
                            <iframe id="url" frameborder="0" height="600px", width="100%" src="" scrolling="no"></iframe>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>


<%@include file="foot.jsp"%>



</form>
<!-- 购物车-->
<%@include file="cart.jsp"%>


</body>
</html>
<SCRIPT Language=VBScript><!--

//--></SCRIPT>