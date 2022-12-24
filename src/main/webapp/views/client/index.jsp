<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/common/tablig.jsp"%>
<!DOCTYPE html>
<html>
<head>

<title>Insert title here</title>
</head>
<body>
	<!-- banner -->
	<div class="banner_top" id="home">
		<div class="wrapper_top_w3layouts">

			<div class="header_agileits">
				<div class="logo" style="top: 1.5%;">
					<h1>
						<a class="navbar-brand" href='<c:url value="/trangchu"/>'><span>Downy</span>
							<i>Shoes</i></a>
					</h1>
				</div>
				<div class="overlay overlay-contentpush">
					<button type="button" class="overlay-close">
						<i class="fa fa-times" aria-hidden="true"></i>
					</button>

					<nav>
						<ul>
							<li><a href='<c:url value="/trangchu"/>' class="active">Home</a></li>
						
							<li><a href='<c:url value="/shop?getAllPro=getAll"/>'>Shop
									Now</a></li>
							
						</ul>
					</nav>
				</div>
				<div class="mobile-nav-button">
					<button id="trigger-overlay" type="button">
						<i class="fa fa-bars" aria-hidden="true"></i>
					</button>
				</div>
				<!-- cart details -->
				<div class="top_nav_right">
					<div class="shoecart shoecart2 cart cart box_1">
						<form action='<c:url value="/checkout"/>' method="post"
							class="last">
							<input type="hidden" name="cmd" value="_cart"> <input
								type="hidden" name="display" value="1">
							<button class="top_shoe_cart" type="submit" name="submit"
								value="">
								<i class="fa fa-cart-arrow-down" aria-hidden="true"></i>
								<c:if test="${not empty giohangs}">
									<span
										style="border-radius: 40px; font-size: 11px; position: absolute; text-align: center; width: 20px; height: 20px; background: red; margin-top: -13px; margin-left: -33px;">${listSize}
									</span>
								</c:if>
								<c:if test="${ empty giohangs}">
									<span id="listsize"
										style="border-radius: 40px; font-size: 11px; position: absolute; text-align: center; width: 20px; height: 20px; background: red; margin-top: -13px; margin-left: -33px;">
										0 </span>
								</c:if>
							</button>
						</form>
					</div>

				</div>
				<!-- //cart details -->
				<!-- search -->
				<div class="search_w3ls_agileinfo">
					<div class="cd-main-header">
						<ul class="cd-header-buttons">

							<c:if test="${not empty USERMODEL }">
								<li
									style="padding-right: 25px; font: bold; color: white; font-weight: 800">Xin
									chào,${USERMODEL.username}</li>
								<li
									style="padding-right: 40px; font: bold; color: white; font-weight: 800"><a
									href='<c:url value="/j_security_check?code=thoat"/>'>Thoát</a></li>
							</c:if>
							<c:if test="${ empty USERMODEL }">
								<li
									style="padding-right: 40px; font: bold; color: white; font-weight: 800"><a
									href='<c:url value="/j_security_check?code=login"/>'>Sign in/up</a></li>
							</c:if>




							<li><a class="cd-search-trigger" href="#cd-search"> <span></span></a></li>

						</ul>
					</div>
					<div id="cd-search" class="cd-search">
						<form action='<c:url value="/shop"/>' method="get">
							<input name="Search" type="search"
								placeholder="Click enter after typing...">
						</form>
					</div>
				</div>
				<!-- //search -->

				<div class="clearfix"></div>
			</div>
			<!-- /slider -->
			<div class="slider">
				<div class="callbacks_container">
					<ul class="rslides callbacks callbacks1" id="slider4">

						<li>
							<div class="banner-top2">
								<div class="banner-info-wthree">
									<h3>Nike</h3>
									<p>See how good they feel.</p>

								</div>

							</div>
						</li>
						<li>
							<div class="banner-top3">
								<div class="banner-info-wthree">
									<h3>Biti's</h3>
									<p>For All Walks of Life.</p>

								</div>

							</div>
						</li>
						<li>
							<div class="banner-top">
								<div class="banner-info-wthree">
									<h3>Converse</h3>
									<p>See how good they feel.</p>

								</div>

							</div>
						</li>
						<li>
							<div class="banner-top1">
								<div class="banner-info-wthree">
									<h3>Adidas</h3>
									<p>For All Walks of Life.</p>

								</div>

							</div>
						</li>
					</ul>
				</div>
				<div class="clearfix"></div>
			</div>
			<!-- //slider -->
			<ul class="top_icons">
				<li><a href="#"><span class="fa fa-facebook"
						aria-hidden="true"></span></a></li>
				<li><a href="#"><span class="fa fa-twitter"
						aria-hidden="true"></span></a></li>
				<li><a href="#"><span class="fa fa-linkedin"
						aria-hidden="true"></span></a></li>
				<li><a href="#"><span class="fa fa-google-plus"
						aria-hidden="true"></span></a></li>

			</ul>
		</div>
	</div>
	
	<div style="display: flex;" class="grids_sec_2">
		<c:forEach items="${list}" var="item">
			<div class="style-grids_main">
				<div class="grid_sec_info">
					<div style="" class="style-grid-2-text_info">
						<h3>${item.name }</h3>
						<div style="margin-bottom: 50px;" class="shop-button">
							<a href='<c:url value="/shop?code=${item.id}"/>'>Shop</a>
						</div>
					</div>

				</div>
				<div class="clearfix"></div>
			</div>

		</c:forEach>
	</div>

</body>
</html>