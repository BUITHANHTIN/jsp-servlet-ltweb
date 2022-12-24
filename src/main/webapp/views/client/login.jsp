<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/common/tablig.jsp"%>
<!DOCTYPE html>
<html>
<head>

<title>Login</title>
</head>
<body>
	<h2>Sign in/up Form</h2>
	<div class="container" id="container">
		<div class="form-container sign-up-container">
			<form action='<c:url value="/j_security_check"/>' method="post">
				<h1>Create Account</h1>
				<div class="social-container"></div>
				<span>or use your email for registration</span>
				<c:if test="${not empty messageRe}">
					<div class="alert alert-${danger}"
						style="color: red; background-color: #FFCCCC; text-align: center;">
						<strong>${messageRe}</strong>
					</div>
				</c:if>
				<input type="text" placeholder="Name" name="name" /> <input
					type="email" placeholder="Email" name="j_username" /> <input
					type="password" placeholder="Password" name="j_password" />
				<button>Sign Up</button>
				<input type="hidden" value="registration" name="action" />

			</form>
		</div>
		<div class="form-container sign-in-container">
			<form action='<c:url value="/j_security_check"/>' method="post">
				<h1>Sign in</h1>
				<div class="social-container"></div>
				<span>or use your account</span>
				<c:if test="${not empty message}">
					<div class="alert alert-${danger}"
						style="color: red; background-color: #FFCCCC; text-align: center;">
						<strong>${message}</strong>
					</div>
				</c:if>
				<input type="email" placeholder="Email" name="j_username"
					id="userSignUp" value="${username}" /> <input type="password"
					placeholder="Password" name="j_password" value="${password}" /> <a
					id="forgetPass">Forgot your password?</a>
				<button>Sign In</button>
				<input type="hidden" value="login" name="action" />
			</form>
		</div>
		<div class="overlay-container">
			<div class="overlay">
				<div class="overlay-panel overlay-left">
					<h1>Welcome Back!</h1>
					<p>To keep connected with us please login with your personal
						info</p>
					<button class="ghost" id="signIn">Sign In</button>
				</div>
				<div class="overlay-panel overlay-right">
					<h1>Hello, Friend!</h1>
					<p>Enter your personal details and start journey with us</p>
					<button class="ghost" id="signUp">Sign Up</button>
				</div>
			</div>
		</div>
	</div>
	<!-- modal -->
	<div class="modal fade" id="modalEdit">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Nhập Thông tin</h4>
				</div>
				<div class="modal-body">
					<form enctype="multipart/form-data" id="form_modal">

						<label for="code">Code OTP</label> <input type="text" id="codeOtp"
							class="form-control" placeholder="OTP"> <label>Enter
							your new password </label> <input type="text" id="yourNewPassword"
							class="form-control" placeholder="Enter your new password">
						<label>Enter a new password </label> <input type="text"
							id="aNewPassword" class="form-control"
							placeholder="Enter a new password">
					</form>
				</div>
				<div class="modal-footer" id="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
					<button id="editPass" type="submit" class="btn btn-primary">Submit</button>
				</div>
			</div>
		</div>
	</div>
	<!--end modal -->
	<script>
        const signUpButton = document.getElementById('signUp');
        const signInButton = document.getElementById('signIn');
        const container = document.getElementById('container');

        signUpButton.addEventListener('click',() => {
            container.classList.add('right-panel-active');
        });

        signInButton.addEventListener('click',() => {
            container.classList.remove('right-panel-active');
        });
    </script>
	<!-- <script
		src="https://cdnjs.cloudflare.com/ajax/libs/notify/0.4.2/notify.min.js"></script> -->

	<script> 
	$("#editPass").click(function(){
		$('.notifyjs-corner').empty();
		
		var user=document.getElementById('userSignUp').value;
		var code=document.getElementById('codeOtp').value;
		var yourNewPassword=document.getElementById('yourNewPassword').value;
		var aNewPassword=document.getElementById('aNewPassword').value;
		var direc="editPass";
		
		console.log(user);
		console.log(code);
		console.log(yourNewPassword);
		console.log(aNewPassword);
		
		if(code==""||yourNewPassword==""||aNewPassword==""){
			$.notify('Vui long dien thong tin',"erro");
		}else{
			$.ajax({
                url : '/do-an-cuoi-ki/api-login',
				type : 'POST',
				data :{
					direc:direc,
					user:user,
					code:code,
					yourNewPassword : yourNewPassword,
					aNewPassword : aNewPassword
				},
				success : function(value) {
					if(value==0){
						$.notify('OTP khong hop le hoac mat khau khong trung nhau',"erro");
					}else if(value==1){
						$("#modalEdit").modal('hide');	
					}
					
					
				}
			});
		}
		
	});
	$("#forgetPass").click(function(){
		$('.notifyjs-corner').empty();
		//lấy tên user
		var user=document.getElementById('userSignUp').value;
		var direc="forgetPass";
		
		//kiểm tra nhập user ch
		if(user==""){
			$.notify('Vui lòng nhập email',"erro");
		}else{
			$.ajax({
                url : 'http://localhost:8080/do-an-cuoi-ki/api-login'+'?'+$.param({"user":user,"direc":direc}),
				type : 'POST',
				success : function(value) {
					var check=value;
					//1 laf email co ton tai vaf ngc lai
					if(check==1){
						$.notify('Kiểm tra thông tin trên email của bạn',"success");
						$("#modalEdit").modal('show');
					}else if(check==0)  {
						$.notify('Email của bạn chưa đăng kí',"erro");
					}
						
				}
			});
			
		}

	});
	
	</script>
</body>
</html>