<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript"
        src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>

<script src="js/main.js" type="text/javascript"></script><script
        src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
<script type="text/javascript" src="js/jquery.fly.min.js"></script>
<script type="text/javascript">


	//获取需要放数据的容器
	var containerCart;
	//获取我们定义的模板的dom对象。主要是想获取里面的内容
	var templateCart;

	$(document).ready(function() {
		containerCart = $('#containerCart');
		templateCart = $('#templateCart');
		if (change2(3))
			getCart();


		$("#cart_show1").click(function () {
			var h = $("#cart").height();
			if (h > 200) {
				$("#cart").css("height", "8%");
			} else {
				$("#cart").css("height", "100%");
			}
		});

	});

	//获取购物车数据
	function getCart() {
		$.get("CartServlet?method=show", function(data) {
			var data = JSON.parse(data);

			var count = 0; //总金额
			var num = 0;  //餐品总数量
			
			for ( var e of data.list) {
				count += e.productPrice * e.productNum;
				num += e.productNum;
			}
			count = Math.round(count * 100) / 100;
			
			$("#cart_image").html(count);
			$("#num").html(num);
			//编译模板的里的内容
			var template = Handlebars.compile(templateCart.html());
			//把后台获取到的数据渲染到页面
			containerCart.html(template(data));
			
			
		})
	}
	
	//购物车餐品的增减
	function save(id,type){
		$.get("CartServlet?method=add",{"productId":id,"type":type},function (data) {
			if (data){
				getCart();
			}else {
				layer.msg("修改失败");
			}
		})
	}
	
	//清空购物车
	function clearCart() {
		layer.confirm('确定要清空吗？', {
			btn : [ '确定', '取消' ]
			//按钮
		}, function() {
			$.get("CartServlet?method=clear",function (data) {
					location.reload();
            });

		});
	}

</script>


<div class="m-shopping" id="cart">
		<div class="m-cart">
			<div id="close">
				<img src="images/common/close.png" class="m-img" />
			</div>
			<span> 我的购物盒</span> <a href="#" onclick="clearCart()">清空购物盒</a>
		</div>
		<div id="containerCart">
		<script type="text/x-handlebars-template" id="templateCart">
		{{#each list}}
		<div class="border-bot eat">
			<div class=" eat-left fl">
				<img src="upload/{{productPic}}"  style="width: 50px;height: 50px"/> <span>{{productName}}</span>
				<br /> <span class="login-redcolor">{{productPrice}}元</span>
			</div>
			<div class="fr  eat-right">
				<button class="cursor">
					<img src="images/common/minus-red.png" onclick="save({{productId}},2)" />
				</button>
				<input type="text" placeholder="{{productNum}}" />
				<button class="cursor">
					<img src="images/common/plus-green.png" onclick="save({{productId}},1)" />
				</button>
			</div>
		</div>
		{{/each}}
		</script>
		</div>
		<div class="login-bgrcolor eat-bot" id="cart_show1">
			<img src="images/menu/box.png" class="e-img" /> <span
				class="e-top login-redcolor" id="num">2</span><strong class="e-title1">总计<span
				class="e-bigfont" id="cart_image"></span><span></span>
			</strong>
			<button class="e-btn fr cu"
				onclick="location.href='OrderSubmitServlet?method=search'">选好了 &gt;</button>
		</div>
</div>
