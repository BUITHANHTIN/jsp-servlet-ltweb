<script type="text/javascript"
	src='<c:url value="/template/web/js/jquery-2.1.4.min.js" />'></script>
<!-- //js -->
<!-- cart-js -->

<script>
	shoe.render();

	shoe.cart.on('shoe_checkout', function(evt) {
		var items, len, i;

		if (this.subtotal() > 0) {
			items = this.items();

			for (i = 0, len = items.length; i < len; i++) {
			}
		}
	});
</script>
<!-- //cart-js -->
<!-- /nav -->
<script src='<c:url value="/template/web/js/modernizr-2.6.2.min.js" />'></script>
<script src='<c:url value="/template/web/js/classie.js" />'></script>
<script src='<c:url value="/template/web/js/demo1.js" />'></script>
<!-- //nav -->
<!-- cart-js -->
<script src='<c:url value="/template/web/js/minicart.js" />'></script>
<script>
	shoe.render();

	shoe.cart.on('shoe_checkout', function(evt) {
		var items, len, i;

		if (this.subtotal() > 0) {
			items = this.items();

			for (i = 0, len = items.length; i < len; i++) {
			}
		}
	});
</script>
<!-- //cart-js -->
<!-- script for responsive tabs -->
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
<!--search-bar-->
<script src='<c:url value="/template/web/js/search.js" />'></script>
<!--//search-bar-->
<!-- start-smoth-scrolling -->
<script type="text/javascript"
	src='<c:url value="/template/web/js/move-top.js" />'></script>
<script type="text/javascript"
	src='<c:url value="/template/web/js/easing.js" />'></script>
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
<!-- //end-smoth-scrolling -->
<script type="text/javascript"
	src='<c:url value="/template/web/js/bootstrap-3.1.1.min.js" />'></script>
