<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/common/tablig.jsp"%>
<!DOCTYPE html>
<html>
<head>

<title>Danh sach hoa don</title>
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
			<td><select name="statusId" id="statusId">
					<option  value="0">Don dat hang</option>
					<c:forEach var="item" items="${list}">
						<option value="${item.id}">${item.name}</option>
					</c:forEach>
			</select></td>
			<button id="submitDeleteBill" data-toggle="tooltip"
				title="Xoa bill">
				<span><i class="fa fa-trash bigger-150" aria-hidden="true"></i></span>
			</button>
			<button  id="submitEditBill" data-toggle="tooltip"
				title="Chinh sua trang thai">
				<span><i class="fa fa-pencil bigger-150" aria-hidden="true"></i></span>
			</button>
			
			
			<br /> <br />
			<table class="timetable_sub" id="table-bill">
				<thead>
					<tr>
						<th><div class="checkbox">
								<label><input id="checkAll" type="checkbox" value=""></label>
							</div></th>
						<th>ID</th>
						<th>NAME</th>
						<th>PHONE</th>
						<th>PRICE</th>
						<th>SUMCOUNT</th>
						<th>ADDRESS</th>
						<th>NOTE</th>
						<th>DATE</th>
						<th>MORONG</th>
					</tr>
				</thead>
				<tbody id="tableBill" >
				
				</tbody>
			</table>
			<!-- modal chinh sua trang thai -->
			<div class="modal fade" id="modalEdit">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			      <h4 class="modal-title">Thông tin sản phẩm</h4>
			      </div>
			      <div class="modal-body">
			      <form  enctype="multipart/form-data" id="form_modal" >
			             <input type="hidden" id="idsp" value="">
			             <td><select name="statusIdModal" id="statusIdModal">
					         <c:forEach var="item" items="${list}">
						     <option value="${item.id}">${item.name}</option>
					         </c:forEach>
			                 </select></td>
			                
			            </form>
			      </div>
			      <div class="modal-footer" id="modal-footer">
			             <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
                         <button id="editStatus" type="submit" class="btn btn-primary">Submit</button>
                  </div>
			    </div>
			  </div>
			</div>
			<!--end modal -->
			
			
			<!-- modal mo rong -->
			<div class="modal fade" id="modalMorong">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			      <h4 class="modal-title">Thông tin sản phẩm</h4>
			      </div>
			      <div class="modal-body" >
			      <form  enctype="multipart/form-data" >  
			      
			      <table class="timetable_sub" >
							<thead>
								<tr>								
									<th>Name</th>
									<th>Image</th>
									<th> Price</th>
									<th>SL</th>
								
								</tr>
							</thead>
							<tbody id="form_morong">
									<%--  <tr>
									<td class="invert-name" >${item.name}</td>
										<td style="width: 100px" class="invert-image"><img
												src="uploads/${item.image }" alt=" " class="img-responsive"></td>
										<td class="invert-price" >${item.price
										}</td>
										<td class="invert-sl" >${item.name}</td>
										
									</tr> --%>
								 
							</tbody>
						</table>
			        
			            </form>
			      </div>
			      <div class="modal-footer">
			             <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
                  </div>
			    </div>
			  </div>
			</div>
			<!--end modal -->
			

		</div>
	</div>
	<!-- /.main-content -->
	<!-- <script
		src="https://cdnjs.cloudflare.com/ajax/libs/notify/0.4.2/notify.min.js"></script>
	 -->
	<script>
	

	
		$("#statusId").change(function() {
			var statusId = $(this).val();
			$.ajax({
				url : '/do-an-cuoi-ki/api-table-bill',
				type : 'GET',
				data : {
					statusId : statusId
				},
				success : function(re) {
					var table = document.getElementById("tableBill");
					table.innerHTML = re;
				}
			});
		});
		$("#checkAll").change(function(){
			if(this.checked){
				$("#table-bill input").each(function(){
					$(this).prop("checked",true);	
				})
			}else{
				$("#table-bill input").each(function(){
					$(this).prop("checked",false);
				})
			}
		});
		
		 $("#submitDeleteBill").click(function(){
			$('.notifyjs-corner').empty();
			var atLeastOneIsChecked = $('input[name="check"]:checked').length ;
			if(atLeastOneIsChecked>0){			
				$("#table-bill > tbody input:checked").each(function(){
					var id=$(this).val();
					var thongbao = confirm("Are you sure want to delete!");
					var self = $(this);
					if(thongbao==true){
						$.ajax({
							url : 'http://localhost:8080/do-an-cuoi-ki/api-table-bill'+'?'+$.param({"id":	id}),
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
				$.notify('Chưa chọn bill',"error");
				
			}
		 	
		}); 
		
		
		 $("#submitEditBill").click(function(){
		$('.notifyjs-corner').empty();
		var atLeastOneIsChecked = $('input[name="check"]:checked').length ;
		if(atLeastOneIsChecked>0 && atLeastOneIsChecked<2){			
			$("#table-bill > tbody input:checked").each(function(){
				 $("#modalEdit").modal('show'); 
				var id=$(this).val();
				
				//gán du lieu
				$("#idsp").val(id);
			   
				//thay đổi theo select cateid
				var statusId=$("#statusId").val();
				document.getElementById("statusIdModal").value = <c:out value='statusId'/>
				
				
			}) ;
			$("#editStatus").click(function(){
				$('.notifyjs-corner').empty();
				var statusId=$("#statusIdModal").val();
				var id=$("#idsp").val();
				//de pk cai nay laf formmodal morong
				var code="edit";
			    if(statusId==""||id==""){
			    	$.notify('Vui long dien thong tin',"erro");
			    }else{
			 	$.ajax({
					url : '/do-an-cuoi-ki/api-table-bill',
					type : 'POST',
					data :{
						code:code,
						id : id,
						statusId : statusId
					},
					success : function(re) {
						$("#modalEdit").modal('hide');
						$.notify('Cap nhat thanh cong',"success");

						var table = document.getElementById("tableBill");
						table.innerHTML = re;
						
						var statusId=$(".note").attr("data-value");
						document.getElementById("statusId").value = <c:out value='statusId'/>

					}
				});  
			    }
			
			});
		}else if(atLeastOneIsChecked>1){
			$.notify('Chon 1 san pham de sua',"error");
		}
		else{
			$.notify('Chưa chọn sản phẩm',"error");
			
		}
	 	
	}); 
		 $( "body" ).on( "click",".submitMorong", function() {
			var id= $(this).closest("tr").find(".id").attr("data-value");
			
			//de pk cai nay laf formmodal morong
			var code="morong";
			
			$.ajax({
				url : '/do-an-cuoi-ki/api-table-bill',
				type : 'POST',
				data :{
					id : id,
					code : code
				},
				success : function(re) {
					var table = document.getElementById("form_morong");
					table.innerHTML = re;
					$("#modalMorong").modal('show');

				}
			});
			
 
         });
		
	</script>
</body>
</html>