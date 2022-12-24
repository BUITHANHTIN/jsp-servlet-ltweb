<%--  <script type="text/javascript"
	src='<c:url value="/template/web/js/jquery-2.1.4.min.js" />'></script>  --%>
	
<!-- //js -->
<!-- cart-js -->

<!-- <script>
	shoe.render();

	shoe.cart.on('shoe_checkout', function(evt) {
		var items, len, i;

		if (this.subtotal() > 0) {
			items = this.items();

			for (i = 0, len = items.length; i < len; i++) {
			}
		}
	});
</script> -->
<!-- //cart-js -->
<!-- /nav -->
<script src='<c:url value="/template/web/js/modernizr-2.6.2.min.js" />'></script>
<script src='<c:url value="/template/web/js/classie.js" />'></script>
<script src='<c:url value="/template/web/js/demo1.js" />'></script>
<script src='<c:url value="/template/web/js/imagezoom.js" />'></script>
<script src='<c:url value="/template/web/js/easy-responsive-tabs.js" />'></script>
<script>
	$(document).ready(function() {
		$('#horizontalTab').easyResponsiveTabs({
			type : 'default', //Types: default, vertical, accordion           
			width : 'auto', //auto or any width like 600px
			fit : true, // 100% fit in a container
			closed : 'accordion', // Start closed if in accordion view
			activate : function(event) { // Callback function if tab is switched
				var $tab = $(this);
				var $info = $('#tabInfo');
				var $name = $('span', $info);
				$name.text($tab.text());
				$info.show();
			}
		});
		$('#verticalTab').easyResponsiveTabs({
			type : 'vertical',
			width : 'auto',
			fit : true
		});
	});
</script>
<script src='<c:url value="/template/web/js/jquery.flexslider.js" />'></script>
<!-- //nav -->
<!--search-bar-->
<script src='<c:url value="/template/web/js/search.js" />'></script>
<!--//search-bar-->
<!-- price range (top products) -->
<script src='<c:url value="/template/web/js/jquery-ui.js" />'></script>

<!-- single -->
<!-- script for responsive tabs -->

<script>
	//<![CDATA[ 
	$(window).load(
			function() {
				$("#slider-range").slider(
						{
							range : true,
							min : 0,
							max : 9000,
							values : [ 50, 6000 ],
							slide : function(event, ui) {
								$("#amount").val(
										"$" + ui.values[0] + " - $"
												+ ui.values[1]);
							}
						});
				$("#amount").val(
						"$" + $("#slider-range").slider("values", 0) + " - $"
								+ $("#slider-range").slider("values", 1));

			}); //]]>
</script>

<!-- //price range (top products) -->

<!-- start-smoth-scrolling -->

<script type="text/javascript"
	src='<c:url value="/template/web/js/move-top.js" />'></script>
<script type="text/javascript"
	src='<c:url value="/template/web/js/easing.js" />'></script>
<script>
	// Can also be used with $(document).ready()
	$(window).load(function() {
		$('.flexslider').flexslider({
			animation : "slide",
			controlNav : "thumbnails"
		});
	});
</script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop : $(this.hash).offset().top
			}, 1000);
		});
	});
</script>
<script>
	$('.value-plus')
			.on(
					'click',
					function() {
						var divUpd = $(this).parent().find('.value'), newVal = parseInt(
								divUpd.text(), 10) + 1;
						divUpd.text(newVal);
					});

	$('.value-minus')
			.on(
					'click',
					function() {
						var divUpd = $(this).parent().find('.value'), newVal = parseInt(
								divUpd.text(), 10) - 1;
						if (newVal >= 1)
							divUpd.text(newVal);
					});
</script>
<!--quantity-->
<script>
	$(document).ready(function(c) {
		$('.close1').on('click', function(c) {
			$('.rem1').fadeOut('slow', function(c) {
				$('.rem1').remove();
			});
		});
	});
</script>
<script>
	$(document).ready(function(c) {
		$('.close2').on('click', function(c) {
			$('.rem2').fadeOut('slow', function(c) {
				$('.rem2').remove();
			});
		});
	});
</script>

<script>
	$(document).ready(function(c) {
		$('.close3').on('click', function(c) {
			$('.rem3').fadeOut('slow', function(c) {
				$('.rem3').remove();
			});
		});
	});
</script>



<!-- //end-smoth-scrolling -->
<script type="text/javascript"
	src='<c:url value="/template/web/js/bootstrap-3.1.1.min.js" />'></script>
