$('#myTable').DataTable({
	'destroy' : true,
	'bProcessing' : true,
	'bServerSide' : true,
	'sort' : 'position',
	'bStateSave' : false,
	'iDisplayStart' : 0,
	'fnDrawCallback' : function() {
	},
	'sAjaxSource' : '/visitorManagement/restdepartmentmaster/searchDepartmentmasterByAjax',
	'aoColumns' : [ {
		'data' : 'deptname',
		'defaultContent' : ""
	}, {
		'data' : 'companyname',
		'defaultContent' : ""
	}, {
		'data' : 'branchname',
		'defaultContent' : ""
	},{
		'data' : 'action',
		'defaultContent' : ""
	} ],

	columnDefs : [ {
		'targets' : [ -1 ],
		'orderable' : false
	}, {
		'className' : 'text-right',
		'targets' : [  ]
	} ]
});
$('input[type=search]').attr('onkeydown', 'disabledkey(event)').attr(
		'maxlength', '64');

function disabledkey(event) {
	var key = event.keyCode;

	if (key == 222)
		event.preventDefault();
}

function openmodal(){
	
	$("#departmentid").val('');
	$("#deptname").val('');
	$("#companyid").val('').trigger('change');
	$("#locationid").val('').trigger('change');
	$('#isactive').hide();
	$("#add_details_section").show();
	$("#view_details_section").hide();
}

function saveData(){
	
	if (validation()) {
		var departmentmasterbean={};
		$("#departmentdata").find('input').each(function(){
			departmentmasterbean[$(this).attr('name')]=$(this).val();
		});
		
		departmentmasterbean['companyid']=$('#companyid').val();
		departmentmasterbean['locationid']=$('#locationid').val();
		departmentmasterbean['active']=$('#active').val();	
		
		$.ajax({
			type : "post",
			data : JSON.stringify(departmentmasterbean),
			url : "/visitorManagement/restdepartmentmaster/saveData",
			contentType : "application/json",
			success : function(resp) {
					if (resp['SAVE'] != null || resp['SAVE'] != undefined) {
					
						toastr.info("" + 'DATA SAVE ' + resp['SAVE'] + "",
								"Message..!");
						$('#myTable').DataTable().ajax.reload();
						$('#add_details_section').hide();
						$('#view_details_section').show();
					} 
					
					else if (resp['DUPLICATE'] != null
							|| resp['DUPLICATE'] != undefined) {
						toastr.info("" + '' + resp['DUPLICATE'] + "",
										"Message..!");
					} 
					
					else if (resp['ERRORMASSAGE'] != null
							|| resp['ERRORMASSAGE'] != undefined) {
						toastr.info("" + '' + resp['ERRORMASSAGE'] + "",
								"Message..!");
					}
	
				}
		});
	}

}
var companyidtemp=null;
var locationidtemp=null;
function editData(departmentid,deptname,companyid,locationid) {

	companyidtemp=companyid;
	locationidtemp=locationid;
	$("#departmentid").val(departmentid).trigger('change');
	$("#deptname").val(deptname);
	$("#companyid").val(companyid).trigger('change');
	$("#locationid").val(locationid).trigger('change');
	$('#isactive').hide();
	$("#add_details_section").show();
	$("#view_details_section").hide();

}

function deleteData(departmentid) {

	if (null != departmentid && departmentid.trim().length > 0) {
		swal({
			title : "Are you sure?",
			text : " Delete Department Master Details ",
			type : "info",
			showCancelButton : true,
			confirmButtonColor : "#ff6060",
			confirmButtonText : "Yes, delete it",
			cancelButtonText : "Cancel",
			closeOnConfirm : !0,
			closeOnCancel : !0,
		}, function(isConfirm) {
			if (isConfirm) {
				$.ajax({
					url : "/visitorManagement/restdepartmentmaster/deleteData",
					type : "post",
					dataType : "text",
					data : {
						departmentid : departmentid,

					},
					async : false,
					success : function(response) {
						$('#myTable').DataTable().ajax.reload();
					}
				});
				swal({
					title : "",
					text : "",
					timer : 0000,
					showConfirmButton : false
				});
			} else {
				swal({
					title : "",
					text : ".",
					timer : 0,
					showConfirmButton : false
				});
			}
		});

	}

}

$("#companyid").on('change',function(){
	
	var companyid = $("#companyid").val();
	
	if (companyid != "" || companyid != null || companyid != undefined) {
	
		$.ajax({
		url : "/visitorManagement/restdepartmentmaster/getLocationData",
		method : "post",
		dataType : 'json',
		data : {
			companyid : companyid
		},
		success : function(response) {
			
			var str = "<option value=''>Select Location</option>";
			if (null != response.locationList){
			for (var i = 0; i < response.locationList.length; i++) {
			
					str += "<option value="+ response.locationList[i][0] + ">"+ response.locationList[i][1]+ "</option>";
			 	}
			}
			$("#locationid").empty().append(str).val(locationidtemp).trigger('change');
		},
		error : function(msg) {
			console.log("Error: " + msg);
		}
			
		});
	}
});

function validation(){

	var deptname= $("#deptname").val();
	var company= $("#companyid").val();
	var location= $("#locationid").val();
	
	if (deptname == "" || deptname==null || deptname==undefined) {
		toastr.info("Department Name", "Required Field");
		return false;
	}
	
	if (company == "" || company==null || company==undefined) {
		toastr.info("Company Name", "Required Field");
		return false;
	}
	
	if (location == "" || location==null || location==undefined) {
		toastr.info("Location Name", "Required Field");
		return false;
	}
		return true;
}