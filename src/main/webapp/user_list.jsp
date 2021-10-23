<%--
  Created by IntelliJ IDEA.
  User: 23351
  Date: 2021/10/11
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="css/css.css" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="css/menu.css" />
    <title>用户管理</title>
    <script type="text/javascript"
            src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="layer/layer.js" type="text/javascript"></script>
    <script src="js/main.js" type="text/javascript"></script>
    <script type="text/javascript">
        function update(id,type){
            var msg;
            if(type == 'Y'){
                msg = "启用";
            }else{
                msg = "停用";
            }
            layer.confirm('确定要'+msg+'吗？', {
                btn: ['确定','取消'] //按钮
            }, function(){
                let userRole=$.trim($("#userRole"+id).val());
                if(userRole=='A'){
                    layer.msg("管理员不能被禁用");
                    return;
                }
                $.get("UserServlet?method=updateStatus",{"userId":id,"userStatus":type,"userRole":userRole},function (data) {
                    if (data==-2){
                        layer.msg("请将信息填写完整");
                    }else if (data==-1){
                        layer.msg("该用户不存在");
                    }else if (data==1){
                        layer.msg(msg+'成功');
                        location.reload();
                    }else if (data==-3){
                        layer.msg("管理员不能被禁用");
                    }else {
                        layer.msg(msg+"失败");
                    }
                },"text")
            });
        }

        function fuzzyQuery() {
            let beginTime = $.trim($("#beginTime").val());
            let endTime = $.trim($("#endTime").val());
            let userTel = $.trim($("#userTel").val());
            if (endTime !=null && endTime !=""){
                if (beginTime>endTime) {
                    layer.msg("开始时间不能大于初始时间");
                    return;
                }
            }
            location.href = "UserServlet?method=fuzzyQuery&LineSize=1&beginTime="+beginTime+"&endTime="+endTime+"&userTel="+userTel;
        }
    </script>
</head>
<body>
<div class="m-main">
    <div class="m-food">
        <div class="mf-top border-t">
            <div class="fl">用户管理</div>
            <div class="fr">
                <input type="date" id="beginTime" value="${req.beginTime}" placeholder="注册时间-始" style="width:100px;" />
                -<input type="date" id="endTime" value="${req.endTime}" placeholder="注册时间-终" style="width:100px;" />
                <input type="text" id="userTel" value="${req.userTel}" placeholder="手机号码" />
                <button onclick="fuzzyQuery()">查询</button></div>
        </div>
        <c:forEach items="${list}" var="c" varStatus="status">
        <div class="mf-menu border-t">
            <input type="hidden" id="userRole${c.userId}" value="${c.userRole}">
            <div class="fl">
                <span class="m-wt1"></span>
                <span>${c.userTel}</span>
                <span>${c.userName}</span>
                <span>${c.userSex=="F"?"女":"男"}</span>
                <span>${c.addTime}</span>
            </div>
            <div class="fr">
                <c:if test="${c.userStatus=='Y'}">
                    <button class="xiugai" onclick="update(${c.userId},'N')">停用</button>
                </c:if>
                <c:if test="${c.userStatus=='N'}">
                    <button class="xiajia" onclick="update(${c.userId},'Y')">启用</button>
                </c:if>
            </div>
        </div>
        </c:forEach>
        <div class="mf-top" style="margin-top:30px">
            <ul class="pagination" style="margin-left:250px">
            <c:forEach var="s" begin="1" end="${count}" >
                <c:if test="${LineSize}==${s}">
                    <li><a class="active" href="UserServlet?method=fuzzyQuery&LineSize=${s}&beginTime=${req.beginTime}&endTime=${req.endTime}&userTel=${userTel}">${s}</a></li>
                </c:if>
                <li><a href="UserServlet?method=fuzzyQuery&LineSize=${s}&beginTime=${req.beginTime}&endTime=${req.endTime}&userTel=${userTel}">${s}</a></li>
            </c:forEach>
            </ul>
        </div>

    </div>
</div>
</body>
</html>
<SCRIPT Language=VBScript><!--

//--></SCRIPT>
