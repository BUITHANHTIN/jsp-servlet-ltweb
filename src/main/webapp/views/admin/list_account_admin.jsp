<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/common/tablig.jsp"%>
<!DOCTYPE html>
<html>
<head>

<title>Danh sach admin</title>
</head>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try {
						ace.settings.check('breadcrumbs', 'fixed')
					} catch (e) {
					}
				</script>

				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a>
					</li>
					<li class="active">Dashboard</li>
				</ul>
				<!-- /.breadcrumb -->

				<div class="nav-search" id="nav-search">
					<form class="form-search">
						<span class="input-icon"> <input type="text"
							placeholder="Search ..." class="nav-search-input"
							id="nav-search-input" autocomplete="off" /> <i
							class="ace-icon fa fa-search nav-search-icon"></i>
						</span>
					</form>
				</div>
				<!-- /.nav-search -->
			</div>

			<button id="submitAdd" data-toggle="tooltip" title="Them nhan vien">
				<span><i class="fa fa-plus-square bigger-150"
					aria-hidden="true"></i></span>
			</button>

			<br /> <br />
			<table class="timetable_sub" id="table">
				<thead>
					<tr>
						<!-- <th><div class="checkbox">
									<label><input id="checkAll" type="checkbox" value=""></label>
								</div></th> -->
						<th>ID</th>
						<th>NAME</th>
						<th>USER</th>
						<th>PASS</th>
						<th>FUNCTIONS</th>

					</tr>
				</thead>
				<tbody id="tableProduct">
					<c:forEach items="${list}" var="item">
						<tr id="${item.id}">
							<td class="invert-id" data-value="${item.id}">${item.id}</td>
							<td class="invert-username">${item.username}</td>
							<td class="invert-user">${item.user}</td>

							<td><input value="${item.pass}" aria-label="Password"
								class="invert-pass" readonly="" focus-row-control=""
								focus-type="passwordField" disabled="" type="password"></td>


							<td><button class="submitDeleteAccount"
									data-toggle="tooltip" title="Xoa">
									<span><i class="fa fa-trash bigger-150"
										aria-hidden="true"></i></span>
								</button>
								<button class="submitEditAccount" data-toggle="tooltip"
									title="Sửa">
									<span><i class="fa fa-pencil bigger-150"
										aria-hidden="true"></i></span>
								</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<!-- modal -->
			<div class="modal fade" id="modalEdit">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">Thông tin ca nhan</h4>
						</div>
						<div class="modal-body">
							<form enctype="multipart/form-data" id="form_modal">
								<input type="hidden" id="id" value=""> <label for="name">Name</label>
								<input type="text" id="name" class="form-control"
									placeholder="Name"> <label for="user">User</label> <input
									type="text" id="user" class="form-control" placeholder="User">
								<label for="pass">Pass</label> <input type="password" id="pass"
									class="form-control" placeholder="Pass"> <i
									data-value="1" id="eyePass" class="fa fa-eye-slash"
									aria-hidden="true"></i>

							</form>
						</div>
						<div class="modal-footer" id="modal-footer">
							<button type="button" class="btn btn-primary"
								data-dismiss="modal">Cancel</button>
							<button id="editAccount" type="submit" class="btn btn-primary">Submit</button>
						</div>
					</div>
				</div>
			</div>
			<!--end modal -->
			<ul class="pagination" id="pagination" style="padding-left: 25%"></ul>

		</div>
	</div>
	<!-- /.main-content -->
	<!-- <script
		src="https://cdnjs.cloudflare.com/ajax/libs/notify/0.4.2/notify.min.js"></script> -->

	<script> 
		$("#submitAdd")
				.click(
						function() {
							//reset form modal

							document.getElementById("form_modal").reset();
							//gán modal thành add
							var modalFooter = document
									.getElementById("modal-footer");
							modalFooter.innerHTML = "<button type='button' class='btn btn-primary' data-dismiss='modal'>Cancel</button>"
									+ " <button id='addAccount' type='submit' class='btn btn-primary'>Submit</button>";
							$("#modalEdit").modal('show');

							//button add
							$("#addAccount")
									.click(
											function() {
												$('.notifyjs-corner').empty();

												var nameAC = $("#name").val();
												var userAC = $("#user").val();
												var passAC = $("#pass").val();
												var code = "add";

												if (nameAC == ""
														|| userAC == ""
														|| passAC == "") {
													$
															.notify(
																	'Vui long dien thong tin',
																	"erro");
												} else {
													$
															.ajax({
																url : '/do-an-cuoi-ki/api-table-accountAdmin',
																type : 'POST',

																data : {
																	code : code,
																	name : nameAC,
																	user : userAC,
																	pass : passAC
																},
																success : function(
																		re) {
																	$
																			.notify(
																					'Them thanh cong',
																					"success");
																	$(
																			"#modalEdit")
																			.modal(
																					'hide');
																	$('tbody')
																			.append(
																					'<tr id='+re+'>\
									<td class="invert-id" data-value='+re+' >'
																							+ re
																							+ '</td>\
									<td class="invert-username">'
																							+ nameAC
																							+ '</td>\
									<td class="invert-user">'
																							+ userAC
																							+ '</td>\
									<td><input value="'+passAC+'" aria-label="Password" class="invert-pass" readonly="" focus-row-control="" focus-type="passwordField" disabled="" type="password"></td> \
									<td><button class="submitDeleteAccount"data-toggle="tooltip" title="Xoa"><span><i class="fa fa-trash bigger-150"aria-hidden="true"></i></span>\
								</button>\
								<button class="submitEditAccount" data-toggle="tooltip"title="Sửa"><span><i class="fa fa-pencil bigger-150"aria-hidden="true"></i></span></button></td>\
									</tr>');

																}
															});
												}

											});

						});

		$('body')
				.on(
						'click',
						'.submitDeleteAccount',
						function() {

							$('.notifyjs-corner').empty();
							var thongbao = confirm("Are you sure want to delete!");
							var id = $(this).closest("tr").find(".invert-id")
									.attr("data-value");

							var self = $(this);

							if (thongbao == true) {

								$
										.ajax({
											url : 'http://localhost:8080/do-an-cuoi-ki/api-table-accountAdmin'
													+ '?' + $.param({
														"id" : id

													}),
											type : 'DELETE',
											success : function(value) {
												$.notify('Xóa thành công',
														"success");
												self.closest("tr").remove();
											}
										});
							} else {

							}

						});

		$('body')
				.on(
						'click',
						'.submitEditAccount',
						function() {

							$("#modalEdit").modal('show');
							//gán modal thành add
							var modalFooter = document
									.getElementById("modal-footer");
							modalFooter.innerHTML = "<button type='button' class='btn btn-primary' data-dismiss='modal'>Cancel</button>"
									+ " <button id='editAccount' type='submit' class='btn btn-primary'>Submit</button>";

							var id = $(this).closest("tr").find(".invert-id")
									.attr("data-value");
							var username = $(this).closest("tr").find(
									".invert-username").text();
							var user = $(this).closest("tr").find(
									".invert-user").text();
							var pass = $(this).closest("tr").find(
									".invert-pass").val();

							$("#id").val(id);
							$("#name").val(username);
							$("#user").val(user);
							$("#pass").val(pass);

							$("#editAccount")
									.click(
											function() {

												$('.notifyjs-corner').empty();
												var idAC = $("#id").val();
												var nameAC = $("#name").val();
												var userAC = $("#user").val();
												var passAC = $("#pass").val();
												//phan biet edit/add
												var code = "edit";

												if (idAC == "" || nameAC == ""
														|| userAC == ""
														|| passAC == "") {
													$
															.notify(
																	'Vui long dien thong tin',
																	"erro");
												} else {
													$
															.ajax({
																url : '/do-an-cuoi-ki/api-table-accountAdmin',
																type : 'POST',

																data : {
																	code : code,
																	id : idAC,
																	name : nameAC,
																	user : userAC,
																	pass : passAC
																},
																success : function(
																		re) {
																	$
																			.notify(
																					'Sua thanh cong',
																					"success");
																	$(
																			"#modalEdit")
																			.modal(
																					'hide');

																	$(
																			'#'
																					+ idAC)
																			.children(
																					'td[class=invert-username]')
																			.text(
																					nameAC);
																	$(
																			'#'
																					+ idAC)
																			.children(
																					'td[class=invert-user]')
																			.text(
																					userAC);
																	// nào rảnh thì làm thêm modal để xác nhận thêm
																	$(
																			'#'
																					+ idAC)
																			.children(
																					'input[class=invert-pass]')
																			.val(
																					passAC);

																}
															});
												}

											});

						});
		$("#eyePass").click(function() {
			//neu la 1 thi type=pass/0 thi type=text
			var value = $("#eyePass").attr("data-value");
			if (value == 1) {
				$("#pass").attr("type", "text");
				$("#eyePass").attr("data-value", "0");
			} else {
				$("#pass").attr("type", "password");
				$("#eyePass").attr("data-value", 
					"1");
			}

		});
	</script>
</body>
</html>