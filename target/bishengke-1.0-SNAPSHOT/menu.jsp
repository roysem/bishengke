<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="css/css.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/menu.css" />
<title>点餐菜单</title>
<script type="text/javascript"
	src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
	<script
	src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
<script src="layer/layer.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.fly.min.js"></script>
<!-- 兼容IE10 -->
<script type="text/javascript" src="js/requestAnimationFrame.js"></script>
<script src="js/main.js" type="text/javascript"></script>
<script type="text/javascript">
	//添加购物车动态效果
	function fly(t){
		var cartLeft = $('#cart_image').offset().left - $(document).scrollTop(); // 获取a标签距离屏幕顶端的距离(因为fly插件的start开始位置是根据屏幕可视区域x，y来计算的，而不是根据整个文档的x，y来计算的)
		var cartTop = $('#cart_image').offset().top; // 获取a标签的y坐标


		var btnLeft = $(t).parent().find('img').offset().left - $(document).scrollTop()+20;
		var btnTop = $(t).parent().find('img').offset().top+20;

		var img = $(t).parent().find('img').attr('src');
		var flyer = $('<img class="u-flyer" src="'+img+'" style="width: 50px;height: 50px">');
		flyer.fly({
			start: {
				left: btnLeft,
				top: btnTop
			},
			end: {
				left: cartLeft, //结束位置（必填）
				top: cartTop, //结束位置（必填）
				width: 0, //结束时宽度
				height: 0 //结束时高度
			},
			onEnd: function(){ //结束回调
				console.log('加入成功！');
				this.destory(); //移除dom
			}
		});
		//每次添加餐品后重置购物车
		getCart();
	}

	
	//添加餐品到购物车
	function addCart(t,id){
		//校验用户是否登录
		if(change2(3)){
			$.get("CartServlet?method=add",{"productId":id,"type":1},function(data){
				if (data==0){
					layer.msg("添加失败");
				}else {
					layer.msg("添加成功");
					fly(t);
					getCart();
				}
			});
		}


		
	}
	
	//获取需要放数据的容器
	var container;
	//获取我们定义的模板的dom对象。主要是想获取里面的内容
	var templateDom;

	$(document).ready(function() {
		container = $('#container');
		templateDom = $('#template');
		select(-1);
	});
	
	//搜索餐品
	function select(id) {
		$.get("ProductServlet?method=Search",{
			"LineSize": 0,
			"categoryId": id,
			"productStatus": "Y"
		},function(data){
			var data = JSON.parse(data);
			

			//编译模板的里的内容
			var template = Handlebars.compile(templateDom.html());
			//把后台获取到的数据渲染到页面
			container.html(template(data));
			
			//样式渲染
			$(".m-main li").removeClass("on");
			$("#category"+id).addClass("on");
		});
	}
</script>

</head>
<body style="background: #efeee9;">
	<input type="hidden" id="pageName" value="customerCenter" />
	<input type="hidden" id="morePrivilege" value="false" />

		<%@ include file="head.jsp"%>
		<div class="m-main"  id="container">
			<script type="text/x-handlebars-template" id="template">
			<div class="m-menu fl">
				<ul>
					<!-- 菜单 -->
					
					<li id="category0" onclick="select(0)"><a href="#">热销</a></li>
					{{#each category}}
					<li id="category{{categoryId}}" onclick="select({{categoryId}})"><a href="#"> {{categoryName}}</a></li>
					{{/each}}
					<!-- /菜单 -->
				</ul>
			</div>
			<div class="m-menu-content fr" style="position: relative; top: 70px">
				<!-- 产品列表 -->
				<div style="height: 450px; display: none;"></div>
				<div class="m-product-list">
					{{#each  product.list}}
					<div class="product" condid="0" style="background: #FFF">
						<div class="img cursor">
							<img src="upload/{{productPic}}" />
						</div>
						<div class="title">{{productName}}</div>
						<div class="desc grey"></div>
						<div class="order j-menu-order" onclick="addCart(this,{{productId}})">
							<div class="start ui-bgbtn-green">+</div>
						</div>
					</div>
					{{/each}}
				</div>
				<!-- /产品列表 -->
			</div>
			</script>
		</div>

		<%@ include file="foot.jsp"%>
		<!-- 购物车-->
		<%
			if(session.getAttribute("user") != null){
		%>
		<%@ include file="cart.jsp"%>
		<%
			}
		%>

</body>
</html>
