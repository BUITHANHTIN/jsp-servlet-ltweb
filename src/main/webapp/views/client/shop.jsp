<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/common/tablig.jsp"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<div class="services-breadcrumb_w3ls_agileinfo">
		<div class="inner_breadcrumb_agileits_w3">

			<ul class="short">
				<li><a href="index.html">Home</a><i>|</i></li>
				<li>Shop</li>
			</ul>
		</div>
	</div>
	<!-- //banner_inner -->
	</div>
	<div class="ads-grid_shop">
		<div class="shop_inner_inf">
			  <!-- tittle heading -->

			<!-- //tittle heading -->
			<!-- product left -->
			<div class="side-bar col-md-3">
				<div class="search-hotel">
					<h3 class="agileits-sear-head">Search Here..</h3>
					<form action='<c:url value="/shop"/>' method="get">
						<input type="search" placeholder="Product name..." name="Search"
							required=""> <input type="submit" value=" ">
					</form>
				</div>
				<!-- price range -->
				<div class="range">
					<h3 class="agileits-sear-head">Price range</h3>
					<ul class="dropdown-menu6">
						<li>
							<div id="slider-range"></div> <input type="text" id="amount"
							style="border: 0; color: #ffffff; font-weight: normal;" />
						</li>
					</ul>
				</div>
				<!-- //price range -->
				<!--preference -->
				<div class="left-side">
					<h3 class="agileits-sear-head">Occasion</h3>
					<ul>
						<li><input type="checkbox" class="checked"> <span
							class="span">Casuals</span></li>
						<li><input type="checkbox" class="checked"> <span
							class="span">Party</span></li>
						<li><input type="checkbox" class="checked"> <span
							class="span">Wedding</span></li>
						<li><input type="checkbox" class="checked"> <span
							class="span">Ethnic</span></li>
					</ul>
				</div>
				<!-- // preference -->
				<!-- discounts -->
				<div class="left-side">
					<h3 class="agileits-sear-head">Discount</h3>
					<ul>
						<li><input type="checkbox" class="checked"> <span
							class="span">5% or More</span></li>
						<li><input type="checkbox" class="checked"> <span
							class="span">10% or More</span></li>
						<li><input type="checkbox" class="checked"> <span
							class="span">20% or More</span></li>
						<li><input type="checkbox" class="checked"> <span
							class="span">30% or More</span></li>
						<li><input type="checkbox" class="checked"> <span
							class="span">50% or More</span></li>
						<li><input type="checkbox" class="checked"> <span
							class="span">60% or More</span></li>
					</ul>
				</div>
				<!-- //discounts -->
				<!-- reviews -->
				<div class="customer-rev left-side">
					<h3 class="agileits-sear-head">Customer Review</h3>
					<ul>
						<li><a href="#"> <i class="fa fa-star" aria-hidden="true"></i>
								<i class="fa fa-star" aria-hidden="true"></i> <i
								class="fa fa-star" aria-hidden="true"></i> <i class="fa fa-star"
								aria-hidden="true"></i> <i class="fa fa-star" aria-hidden="true"></i>
								<span>5.0</span>
						</a></li>
						<li><a href="#"> <i class="fa fa-star" aria-hidden="true"></i>
								<i class="fa fa-star" aria-hidden="true"></i> <i
								class="fa fa-star" aria-hidden="true"></i> <i class="fa fa-star"
								aria-hidden="true"></i> <i class="fa fa-star-o"
								aria-hidden="true"></i> <span>4.0</span>
						</a></li>
						<li><a href="#"> <i class="fa fa-star" aria-hidden="true"></i>
								<i class="fa fa-star" aria-hidden="true"></i> <i
								class="fa fa-star" aria-hidden="true"></i> <i
								class="fa fa-star-half-o" aria-hidden="true"></i> <i
								class="fa fa-star-o" aria-hidden="true"></i> <span>3.5</span>
						</a></li>
						<li><a href="#"> <i class="fa fa-star" aria-hidden="true"></i>
								<i class="fa fa-star" aria-hidden="true"></i> <i
								class="fa fa-star" aria-hidden="true"></i> <i
								class="fa fa-star-o" aria-hidden="true"></i> <i
								class="fa fa-star-o" aria-hidden="true"></i> <span>3.0</span>
						</a></li>
						<li><a href="#"> <i class="fa fa-star" aria-hidden="true"></i>
								<i class="fa fa-star" aria-hidden="true"></i> <i
								class="fa fa-star-half-o" aria-hidden="true"></i> <i
								class="fa fa-star-o" aria-hidden="true"></i> <i
								class="fa fa-star-o" aria-hidden="true"></i> <span>2.5</span>
						</a></li>
					</ul>
				</div>
				<!-- //reviews -->
				<!-- deals -->
				<div class="deal-leftmk left-side">
					<h3 class="agileits-sear-head">Special Deals</h3>
					<c:forEach items="${listTwo}" var="item">
						<div class="special-sec1">
							<div class="col-xs-4 img-deals">
								<img src="uploads/${item.image}" alt="">
							</div>
							<div class="col-xs-8 img-deal1">
								<h3>
									<a
										href='<c:url value="/single?code=${item.id}&cateID=${item.cateID}" />'>${item.name}</a>
								</h3>
								<h3>$${item.price}</h3>
							</div>
							<div class="clearfix"></div>
						</div>
					</c:forEach>
				</div>
				<!-- //deals -->

			</div>
			<!-- //product left -->
			<!-- product right -->
			<div class="left-ads-display col-md-9">
				<div class="wrapper_top_shop">
					<div class="col-md-6 shop_left">
						<img src='<c:url value="/template/web/images/banner3.jpg"/>'
							alt="">
						<h6>40% off</h6>
					</div>
					<div class="col-md-6 shop_right">
						<img src='<c:url value="/template/web/images/banner2.jpg"/>'
							alt="">
						<h6>50% off</h6>
					</div>
					<div class="clearfix"></div>
					<!-- product-sec1 -->
					<div class="product-sec1">
						<!--/mens-->
						<c:forEach items="${list}" var="item">
							<div class="col-md-4 product-men">
								<div class="product-shoe-info shoe">
									<div class="men-pro-item">
										<div class="men-thumb-item">
											<img src="uploads/${item.image}" alt="">
											<div class="men-cart-pro">
												<div class="inner-men-cart-pro">
													<a
														href='<c:url value="/single?code=${item.id}&cateID=${item.cateID}" />'
														class="link-product-add-cart">Quick View</a>
												</div>
											</div>
											<span class="product-new-top">New</span>
										</div>
										<div class="item-info-product">
											<h4>
												<a
													href='<c:url value="/single?code=${item.id}&cateID=${item.cateID}" />'>${item.name}
												</a>
											</h4>
											<div class="info-product-price">
												<div class="grid_meta">
													<div class="product_price">
														<div class="grid-price ">
															<span class="money ">$${item.price}</span>
														</div>
													</div>

												</div>

											</div>
											<div class="clearfix"></div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
						<!-- //mens -->
						<div class="clearfix"></div>

					</div>
					<c:if test="${checkPaging==2}">
						<nav aria-label="Page navigation example">
							<ul class="pagination" style="padding-left: 280px;">
								<c:if test="${pagingID>1 }">
									<li class="page-item"><a class="page-link"
										href='<c:url value="/shop?paging=${pagingID-1}&Search=${search }&code=2"/>'>Previous</a></li>
								</c:if>

								<c:forEach var="item" begin="1" end="${endPaging}">
									<li class="${pagingID==item?" active":""}" class="page-item"><a
										class="page-link"
										href='<c:url value="/shop?paging=${item}&Search=${search }&code=2"/>'>${item}</a></li>
								</c:forEach>
								<c:if test="${pagingID< endPaging}">
									<li class="page-item"><a class="page-link"
										href='<c:url value="/shop?paging=${pagingID+1}&Search=${search}&code=2"/>'>Next</a></li>
								</c:if>
							</ul>
						</nav>
					</c:if>
					<c:if test="${checkPaging==1}">
						<nav aria-label="Page navigation example">
							<ul class="pagination" style="padding-left: 280px;">
								<c:if test="${pagingID>1 }">
									<li class="page-item"><a class="page-link"
										href='<c:url value="/shop?paging=${pagingID-1}&getAllPro=getAll&code=1"/>'>Previous</a></li>
								</c:if>

								<c:forEach var="item" begin="1" end="${endPaging}">
									<li class="${pagingID==item?" active":""}" class="page-item"><a
										class="page-link"
										href='<c:url value="/shop?paging=${item}&getAllPro=getAll&code=1"/>'>${item}</a></li>
								</c:forEach>
								<c:if test="${pagingID< endPaging}">
									<li class="page-item"><a class="page-link"
										href='<c:url value="/shop?paging=${pagingID+1}&getAllPro=getAll&code=1"/>'>Next</a></li>
								</c:if>
							</ul>
						</nav>
					</c:if>
					<c:if test="${checkPaging==0}">
						<nav aria-label="Page navigation example">
							<ul class="pagination" style="padding-left: 280px;">
								<c:if test="${pagingID>1 }">
									<li class="page-item"><a class="page-link"
										href='<c:url value="/shop?paging=${pagingID-1}&code=${cateID}"/>'>Previous</a></li>
								</c:if>
								<c:forEach var="item" begin="1" end="${endPaging}">
									<li class="${pagingID==item?" active":""}" class="page-item"><a
										class="page-link"
										href='<c:url value="/shop?paging=${item}&code=${cateID}"/>'>${item}</a></li>
								</c:forEach>
								<c:if test="${pagingID< endPaging}">
									<li class="page-item"><a class="page-link"
										href='<c:url value="/shop?paging=${pagingID+1}&code=${cateID}"/>'>Next</a></li>
								</c:if>
							</ul>
						</nav>
					</c:if>
					<!-- //product-sec1 -->
					<div class="col-md-6 shop_left shp">
						<img src='<c:url value="/template/web/images/banner4.jpg"/>'
							alt="">
						<h6>21% off</h6>
					</div>
					<div class="col-md-6 shop_right shp">
						<img src='<c:url value="/template/web/images/banner1.jpg"/>'
							alt="">
						<h6>31% off</h6>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
</body>
</html>