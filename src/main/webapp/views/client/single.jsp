<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/common/tablig.jsp"%>
<!DOCTYPE html>
<html lang="zxx">
<head>
<title>Downy Shoes an Ecommerce Category Bootstrap Responsive
	Website Template | Single :: w3layouts</title>
</head>
<c:url var="checkout" value="/checkout" />
<body>
	<div class="services-breadcrumb_w3ls_agileinfo">
		<div class="inner_breadcrumb_agileits_w3">

			<ul class="short">
				<li><a href="index.html">Home</a><i>|</i></li>
				<li>Single</li>
			</ul>
		</div>
	</div>
	<!-- //banner_inner -->
	</div>
	<div class="ads-grid_shop">
		<div class="shop_inner_inf">
			<div class="col-md-4 single-right-left ">
				<div class="grid images_3_of_2">
					<div class="flexslider">

						<ul class="slides">
							<li data-thumb="uploads/${oneprod.image}">
								<div class="thumb-image">
									<img src="uploads/${oneprod.image}" data-imagezoom="true"
										class="img-responsive">
								</div>
							</li>
							<li data-thumb="uploads/${oneprod.image}">
								<div class="thumb-image">
									<img src="uploads/${oneprod.image}" data-imagezoom="true"
										class="img-responsive">
								</div>
							</li>
							<li data-thumb="uploads/${oneprod.image}">
								<div class="thumb-image">
									<img src="uploads/${oneprod.image}" data-imagezoom="true"
										class="img-responsive">
								</div>
							</li>
						</ul>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
			<div class="col-md-8 single-right-left simpleCart_shelfItem">
				<h3>${oneprod.title }</h3>
				<p>
					<span class="item_price">$${oneprod.price }</span>
					<del>$1,199</del>
				</p>
				<div class="rating1">
					<ul class="stars">
						<li><a href="#"><i class="fa fa-star" aria-hidden="true"></i></a></li>
						<li><a href="#"><i class="fa fa-star" aria-hidden="true"></i></a></li>
						<li><a href="#"><i class="fa fa-star" aria-hidden="true"></i></a></li>
						<li><a href="#"><i class="fa fa-star-half-o"
								aria-hidden="true"></i></a></li>
						<li><a href="#"><i class="fa fa-star-o"
								aria-hidden="true"></i></a></li>
					</ul>
				</div>
				<div class="description">
					<h5>Check delivery, payment options and charges at your
						location</h5>
					<form action="#" method="post">
						<input type="text" value="Enter pincode"
							onfocus="this.value = '';"
							onblur="if (this.value == '') {this.value = 'Enter pincode';}"
							required=""> <input type="submit" value="Check">
					</form>
				</div>
				<div class="color-quality">
					<div class="color-quality-right">
						<h5>Quality :</h5>
						<select id="country1" onchange="change_country(this.value)"
							class="frm-field required sect">
							<option value="null">5 Qty</option>
							<option value="null">6 Qty</option>
							<option value="null">7 Qty</option>
							<option value="null">10 Qty</option>
						</select>
					</div>
				</div>
				<div class="occasional">
					<h5>Types :</h5>
					<div class="colr ert">
						<label class="radio"><input type="radio" name="radio"
							checked=""><i></i>Casual Shoes</label>
					</div>
					<div class="colr">
						<label class="radio"><input type="radio" name="radio"><i></i>Sneakers
						</label>
					</div>
					<div class="colr">
						<label class="radio"><input type="radio" name="radio"><i></i>Formal
							Shoes</label>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="occasion-cart">
					<div class="shoe single-item single_page_b">
						<form id="formsubmit">
							<input type="hidden" name="id" id="id" value="${oneprod.id} ">
							<input type="hidden" name="name" id="name"
								value="${oneprod.name} "> <input type="hidden"
								name="image" id="image" value="${oneprod.image} "><input
								type="hidden" name="count" id="count" value="1"> <input
								type="hidden" name="price" id="price" value="${oneprod.price } ">
							<input type="button" name="submit" id="submitSingle"
								value="Add to cart" class="button add"> <!-- <a href="#"
								data-toggle="modal" data-target="#myModal1"></a> -->
						</form>

					</div>

				</div>
				<ul class="social-nav model-3d-0 footer-social social single_page">
					<li class="share">Share On :</li>
					<li><a href="#" class="facebook">
							<div class="front">
								<i class="fa fa-facebook" aria-hidden="true"></i>
							</div>
							<div class="back">
								<i class="fa fa-facebook" aria-hidden="true"></i>
							</div>
					</a></li>
					<li><a href="#" class="twitter">
							<div class="front">
								<i class="fa fa-twitter" aria-hidden="true"></i>
							</div>
							<div class="back">
								<i class="fa fa-twitter" aria-hidden="true"></i>
							</div>
					</a></li>
					<li><a href="#" class="instagram">
							<div class="front">
								<i class="fa fa-instagram" aria-hidden="true"></i>
							</div>
							<div class="back">
								<i class="fa fa-instagram" aria-hidden="true"></i>
							</div>
					</a></li>
					<li><a href="#" class="pinterest">
							<div class="front">
								<i class="fa fa-linkedin" aria-hidden="true"></i>
							</div>
							<div class="back">
							
								<i class="fa fa-linkedin" aria-hidden="true"></i>
							</div>
					</a></li>
				</ul>

			</div>
			<div class="clearfix"></div>
			<!--/tabs-->
			<div class="responsive_tabs">
				<div id="horizontalTab">
					<ul class="resp-tabs-list">
						<li>Description</li>
						<li>Reviews</li>
						<li>Information</li>
					</ul>
					<div class="resp-tabs-container">
						<!--/tab_one-->
						<div class="tab1">

							<div class="single_page">
								<h6></h6>
								<p>${oneprod.description }</p>
							</div>
						</div>
						<!--//tab_one-->
						<div class="tab2">
							<div class="single_page">
								<div class="bootstrap-tab-text-grids">
									<div class="bootstrap-tab-text-grid">
										<div class="bootstrap-tab-text-grid-left">
											<img src='<c:url value="/template/web/images/d2.jpg"/>'
												alt=" " class="img-responsive">
										</div>
										<div class="bootstrap-tab-text-grid-right">
											<ul>
												<li><a href="#">Admin</a></li>
												<li><a href="#"><i class="fa fa-reply-all"
														aria-hidden="true"></i> Reply</a></li>
											</ul>
											<p>Lorem ipsum dolor sit amet, consectetur adipisicing
												elPellentesque vehicula augue eget.Ut enim ad minima veniam,
												quis nostrum exercitationem ullam corporis suscipit
												laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis
												autem vel eum iure reprehenderit.</p>
										</div>
										<div class="clearfix"></div>
									</div>
									<div class="add-review">
										<h4>add a review</h4>
										<form action="#" method="post">
											<input type="text" name="Name" required="Name"> <input
												type="email" name="Email" required="Email">
											<textarea name="Message" required=""></textarea>
											<input type="submit" value="SEND">
										</form>
									</div>
								</div>

							</div>
						</div>
						<div class="tab3">

							<div class="single_page">
								<h6>${oneprod.name }</h6>
								<p>${oneprod.description }</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--//tabs-->
			<!-- /new_arrivals -->
			<div class="new_arrivals">
				<h3>Featured Products</h3>
				<!-- /womens -->
				<c:forEach items="${listFour}" var="item">

					<div class="col-md-3 product-men women_two inforShoes">
						<div class="product-shoe-info shoe">
							<div class="men-pro-item">
								<div class="men-thumb-item">
									<img src="uploads/${item.image}" alt="">
									<div class="men-cart-pro">
										<div class="inner-men-cart-pro">
											<a
												href='<c:url value="/single?code=${item.id }&cateID=${item.cateID}"/>'
												class="link-product-add-cart">Quick View</a>
										</div>
									</div>
									<span class="product-new-top">New</span>
								</div>
								<div class="item-info-product">
									<h4>
										<a
											href='<c:url value="/single?code=${item.id }&cateID=${item.cateID}"/>'>${item.name }</a>
									</h4>
									<div class="info-product-price">
										<div class="grid_meta">
											<div class="product_price">
												<div class="grid-price ">
													<span class="money ">$${item.price }</span>
												</div>
											</div>
											<ul class="stars">
												<li><a href="#"><i class="fa fa-star"
														aria-hidden="true"></i></a></li>
												<li><a href="#"><i class="fa fa-star"
														aria-hidden="true"></i></a></li>
												<li><a href="#"><i class="fa fa-star"
														aria-hidden="true"></i></a></li>
												<li><a href="#"><i class="fa fa-star"
														aria-hidden="true"></i></a></li>
												<li><a href="#"><i class="fa fa-star-half-o"
														aria-hidden="true"></i></a></li>
											</ul>
										</div>
										<div class="shoe single-item hvr-outline-out" id="EachProduct">
											<form id="formsubmit1">
												<input type="hidden" name="id" class="id"
													value="${item.id} "> <input type="hidden"
													name="name" class="name" value="${item.name} "> <input
													type="hidden" name="image" class="image"
													value="${item.image} "><input type="hidden"
													name="count" class="count" value="1"> <input
													type="hidden" name="price" class="price"
													value="${item.price } "> <input type="button"
													name="submit" value="Add to cart"
													class="button add buttonaddnew"> <a href="#"
													data-toggle="modal" data-target="#myModal1"></a>
											</form>
										</div>
									</div>
									<div class="clearfix"></div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
				<!-- //womens -->
				<div class="clearfix"></div>
			</div>
			<!--//new_arrivals-->
		</div>
	</div>
	
	<!-- <script
		src="https://cdnjs.cloudflare.com/ajax/libs/notify/0.4.2/notify.min.js"></script> -->
	<script>
		$('#submitSingle').click(function(e) {
			e.preventDefault();
			var data = {};
			var formData = $('#formsubmit').serializeArray();
			$.each(formData, function(i, v) {
				data["" + v.name + ""] = v.value;
			});
			addNewCart(data);
		});

		$('.buttonaddnew').click(function(e) {
			$('.notifyjs-corner').empty();
			e.preventDefault();
			var price = $(this).closest(".inforShoes").find(".price").val();
			var name = $(this).closest(".inforShoes").find(".name").val();
			var image = $(this).closest(".inforShoes").find(".image").val();
			var count = $(this).closest(".inforShoes").find(".count").val();
			var id = $(this).closest(".inforShoes").find(".id").val();

			$.ajax({
				url : '/do-an-cuoi-ki/api-checkout',
				type : 'POST',
				contentType : 'application/json',
				data : JSON.stringify({
					price : price,
					id : id,
					name : name,
					count : count,
					image : image
				}),
				dataType : 'json',
				success : function(re) {
					$.notify('Đã thêm vào giỏ hàng', "success");
					$("#listsize").html(re);

				}
			});
		});
		function addNewCart(data) {
			$('.notifyjs-corner').empty();
			$.ajax({
				url : '/do-an-cuoi-ki/api-checkout',
				type : 'POST',
				contentType : 'application/json',
				data : JSON.stringify(data),
				dataType : 'json',
				success : function(re) {
					$.notify('Đã thêm vào giỏ hàng', "success");
					$("#listsize").html(re);

				}
			});
		}
	</script>

</body>
</html>