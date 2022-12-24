<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/common/tablig.jsp"%>
<!DOCTYPE html>
<html>
<head>

<title>Danh sach bai viet</title>
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
			<td><select name="categoryId" id="categoryId">
					<option value="0">Chon loai giày</option>
					<c:forEach var="item" items="${list}">
						<option value="${item.id}">${item.name}</option>
					</c:forEach>
			</select></td>
			<button id="submitDeleteProductAll" data-toggle="tooltip"
				title="Xoa bai viet">
				<span><i class="fa fa-trash bigger-150" aria-hidden="true"></i></span>
			</button>
			<button id="submitEditProductAll" data-toggle="tooltip"
				title="Sửa bai viet">
				<span><i class="fa fa-pencil bigger-150" aria-hidden="true"></i></span>
			</button>
			<button id="submitAddProduct" data-toggle="tooltip"
				title="Them bai viet">
				<span><i class="fa fa-plus-square bigger-150"
					aria-hidden="true"></i></span>
			</button>

			<br /> <br />
			<table class="timetable_sub" id="table-sanpham">
				<thead>
					<tr>
						<th><div class="checkbox">
								<label><input id="checkAll" type="checkbox" value=""></label>
							</div></th>
						<th>ID</th>
						<th>NAME</th>
						<th>IMAGE</th>
						<th>PRICE</th>
						<th>DESCRIPTION</th>
						<th>SLHANGTON</th>

					</tr>
				</thead>
				<tbody id="tableProduct">
					<%-- <tr>
						<td><div class="checkbox">
								<label><input type="checkbox" name="check" value="41"></label>
							</div></td>
						<td>41</td>
						<td class="name">cdwcc</td>
						<td class="invert-image" data-value='<c:ur value="/uploads/${ }"/>' >
						<a>
						<img style="width: 52px" alt="" class="img-responsive" src='<c:url value="/uploads/${ }"/>'>
						</a>
						</td>
						<td class="price">3.0</td>
						<td data-value="1" class="description">ccdc</td>
						<td class="slht">4</td>
</tr> --%>
				</tbody>
			</table>
			<!-- modal -->
			<div class="modal fade" id="modalEdit">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">Thông tin sản phẩm</h4>
						</div>
						<div class="modal-body">
							<form method="post" enctype="multipart/form-data" id="form_modal">
								<input name="id" type="hidden" id="idsp" value="">
								<td><select name="categoryId" id="categoryIdModal">
										<c:forEach var="item" items="${list}">
											<option value="${item.id}">${item.name}</option>
										</c:forEach>
								</select></td> <br> <label for="name">Name</label> <input type="text"
									id="namesp" name="name" class="form-control" placeholder="Name">
								<label for="image">Image</label> <input name="image" type="file"
									id="imagesp" class="form-control" placeholder="Image"
									style="width: 50%" onchange="chooseFile(this)" accept="image/*">
								<img name="showImage" alt="" id="img" width="52px" height="52px"
									src=""> <br> <label for="price">Price</label> <input
									name="price" type="number" id="pricesp" min="1"
									class="form-control" placeholder="Price"> <label
									for="description">Description</label> <input name="description"
									type="text" id="descriptionsp" class="form-control"
									placeholder="Description"> <label for="slht">Soluong</label>
								<input name="SLHangTon" type="number" id="slsp" min="1"
									class="form-control" placeholder="So luong">
								<div class="modal-footer" id="modal-footer">
									<button type="button" class="btn btn-primary"
										data-dismiss="modal">Cancel</button>
									<button id="editSP" type="submit" class="btn btn-primary">Submit</button>
								</div>
							</form>
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
		src="https://cdnjs.cloudflare.com/ajax/libs/notify/0.4.2/notify.min.js"></script>
 -->
	<script>
	
	$("#submitAddProduct").click(function(){
		//reset form modal
		$("#img").attr('src',"");
		document.getElementById("form_modal").reset();
		//gán modal thành add
		var modalFooter = document.getElementById("modal-footer");
		modalFooter.innerHTML =  "<button type='button' class='btn btn-primary' data-dismiss='modal'>Cancel</button>"
            +" <button id='addSP' type='submit' class='btn btn-primary'>Submit</button>";
		 $("#modalEdit").modal('show');
		 $("#addSP").click(function(event){
			 event.preventDefault();
			 $('.notifyjs-corner').empty();
				var cateId=$("#categoryIdModal").val();
				var name=  $("#namesp").val();
			    var image=	$("#img").attr('src');
			    var price= $("#pricesp").val();
			    var description=$("#descriptionsp").val();
			    var SLHangTon=	$("#slsp").val();
			    var title=  $("#namesp").val();
			    //xac nhan do la edit
			    var code="add";
			    //khi thêm sản phẩm sẽ k có id nên cho =0;
				$("#idsp").val(0);
			    
			    var form = $('#form_modal')[0];
			    var data = new FormData(form);
			    data.append("code",code);
			    data.append("title",title);
			    
			   
			    
			    if(cateId==""||name==""||image==""||price==""||description==""||SLHangTon==""||title==""){
			    	$.notify('Vui long dien thong tin',"erro");
			    }else{
			    
			   
			 	$.ajax({	// đẩy dữ liệu lên => api-table-product
					url : '/do-an-cuoi-ki/api-table-product',
					type : 'POST',
					enctype: 'multipart/form-data',
					data:data,
					processData: false,
                    contentType: false,
                    cache: false,
                    timeout: 800000,
					success : function(re) {
						console.log(re);
						if(re==0){
							$.notify('Anh da ton tai',"erro");
						}else{
							
							$.notify('Them thanh cong',"success");
							$("#modalEdit").modal('hide');
							var table = document.getElementById("tableProduct");
							table.innerHTML = re;
						}
						

					}
				});  
			    }
			});
		
	});
	
		$("#categoryId").change(function() {
			var cateId = $(this).val();
			$.ajax({
				url : '/do-an-cuoi-ki/api-table-product',
				type : 'GET',
				data : {
					cateId : cateId
				},
				success : function(re) {
					var table = document.getElementById("tableProduct");
					table.innerHTML = re;
				}
			});
		});
		$("#checkAll").change(function(){
			if(this.checked){
				$("#table-sanpham input").each(function(){
					$(this).prop("checked",true);	
				})
			}else{
				$("#table-sanpham input").each(function(){
					$(this).prop("checked",false);
				})
			}
		});
		
		 $("#submitDeleteProductAll").click(function(){
			$('.notifyjs-corner').empty();
			var atLeastOneIsChecked = $('input[name="check"]:checked').length ;
			var thongbao = confirm("Are you sure want to delete!");
			if(atLeastOneIsChecked>0){			
				$("#table-sanpham > tbody input:checked").each(function(){
					var id=$(this).val();
					var self = $(this);
					if(thongbao==true){
						$.ajax({
							url : 'http://localhost:8080/do-an-cuoi-ki/api-table-product'+'?'+$.param({"id":	id}),
							type : 'DELETE',
							success : function(value) {
									$.notify('Xóa thành công',"success");
									self.closest("tr").remove();
							}
						});
					}else{
						
					}
					
				}) 
			}else{
				$.notify('Chưa chọn sản phẩm',"error");
				
			}
		 	
		}); 
		
		
		
		 function chooseFile(fileInput){
			 if(fileInput.files && fileInput.files[0]){
				 var reader=new FileReader();
				 reader.onload=function(e){
					 $("#img").attr('src',e.target.result);
				 }
				 reader.readAsDataURL(fileInput.files[0])
			 }
			 
		 }
			$("#submitEditProductAll").click(function(){
				 var modalFooter = document.getElementById("modal-footer");
				 modalFooter.innerHTML =  "<button type='button' class='btn btn-primary' data-dismiss='modal'>Cancel</button>"
		                +" <button id='editSP' type='button' class='btn btn-primary'>Submit</button>";
			$('.notifyjs-corner').empty();
			var atLeastOneIsChecked = $('input[name="check"]:checked').length ;
			if(atLeastOneIsChecked>0 && atLeastOneIsChecked<2){			
				$("#table-sanpham > tbody input:checked").each(function(){
					 $("#modalEdit").modal('show'); 
					var id=$(this).val();
					var name=$(this).closest("tr").find(".name").text();
					var image=$(this).closest("tr").find(".invert-image").attr("data-value");
					var price=$(this).closest("tr").find(".price").text();
					var description=$(this).closest("tr").find(".description").text();
					var slht=$(this).closest("tr").find(".slht").text();
					
					//lấy tên ảnh
					var getNameImage=image.split("/").pop();
					//gán du lieu
					$("#idsp").val(id);
				    $("#namesp").val(name);
					$("#img").attr('src',image);
					$("#pricesp").val(price);
					$("#descriptionsp").val(description);
					$("#slsp").val(slht);
					//thay đổi theo select cateid
					var categoryId=$("#categoryId").val();
					document.getElementById("categoryIdModal").value = <c:out value='categoryId'/>
					
					
						$('body').on('click','#editSP',function() {
							$('.notifyjs-corner').empty();
							var cateId=$("#categoryIdModal").val();
							var id=$("#idsp").val();
							var name=  $("#namesp").val();
						    var image=	$("#img").attr('src');
						    var price= $("#pricesp").val();
						    var description=$("#descriptionsp").val();
						    var SLHangTon=	$("#slsp").val();
						    var title=  $("#namesp").val();
						    //xac nhan do la edit
						    var code="edit";
						   			    
						    
						    var form = $('#form_modal')[0];
						    var data = new FormData(form);
						    data.append("code",code);
						    data.append("title",title);
						    // xét xem input=file có rống hay k .khi sửa mà k đổi ảnh thì bị rỗng và ngc lại
						    let vd=document.getElementById("imagesp").value;
						    
						    if(vd==""){
						    	 data.append("getImage",getNameImage);
						    }
						    
						   
						    if(cateId==""||id==""||name==""||image==""||price==""||description==""||SLHangTon==""||title==""){
						    	$.notify('Vui long dien thong tin',"erro");
						    }else{
						    	$.ajax({
							 		url : '/do-an-cuoi-ki/api-table-product',
									type : 'POST',
									enctype: 'multipart/form-data',
									data:data,
									processData: false,
				                    contentType: false,
				                    cache: false,
				                    timeout: 800000,
									success : function(re) {
										//console.log(re);
											$('.notifyjs-corner').empty();
										if(re==0){
											$.notify('Anh da ton tai',"erro");
										}else{
											$.notify('Sua thanh cong',"success");
											$("#modalEdit").modal('hide');
											var table = document.getElementById("tableProduct");
											table.innerHTML = re;
											// để select giống với giá trị
											var categoryId=$(".description").attr("data-value");
											document.getElementById("categoryId").value = <c:out value='categoryId'/>

										}
							
									}
								});  
							
						    }
							});
						
					
				}) ;
				
			}else if(atLeastOneIsChecked>1){
				$.notify('Chon 1 san pham de sua',"error");
			}
			else{
				$.notify('Chưa chọn sản phẩm',"error");
				
			}
			});
	</script>
</body>
</html>