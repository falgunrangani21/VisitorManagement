var filter = /^[-a-z0-9~!$%^&*_=+}{\'?]+(\.[-a-z0-9~!$%^&*_=+}{\'?]+)*@([a-z0-9_][-a-z0-9_]*(\.[-a-z0-9_]+)*\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,5})?$/i;
$('#myTable').DataTable({
	'destroy' : true,
	'bProcessing' : true,
	'bServerSide' : true,
	'sort' : 'position',
	'bStateSave' : false,
	'iDisplayStart' : 0,
	'fnDrawCallback' : function() {
		$(".letterpic").letterpic({
            gradients: [
                ["#fff", "#333", "#000", "#777"],
                ["#aaafff", "#aaa777"],
                ["#dddfff", "#ddd333", "#ddd777"],
            ]
        });
	},
	'sAjaxSource' : '/visitorManagement/restemployeemaster/searchEmployeemasterByAjax',
	'aoColumns' : [ {
		'data' : 'profileurl',
		'defaultContent' : ""
	}, {
		'data' : 'empname',
		'defaultContent' : ""
	}, {
		'data' : 'emp_email',
		'defaultContent' : ""
	}, {
		'data' : 'empcontactnumber',
		'defaultContent' : ""
	}, {
		'data' : 'empqualification',
		'defaultContent' : ""
	}, {
		'data' : 'designation',
		'defaultContent' : ""
	}, {
		'data' : 'cname',
		'defaultContent' : ""
	}, {
		'data' : 'branchname',
		'defaultContent' : ""
	},{
		'data': 'deptname',
		'defaultContent' : ""
	}, {
		'data' : 'action',
		'defaultContent' : ""
	} ],

	columnDefs : [ {
		'targets' : [ -1,0 ],
		'orderable' : false
	}, {
		'className' : 'text-right',
		'targets' : [ 3 ]
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
	
	$("#employeeid").val('').trigger('change');
	$("#companyid").val('').trigger('change');
	$("#locationid").val('').trigger('change');
	$("#empname").val('');
	
	$("#male").prop('checked',false);
	$("#female").prop('checked',false);
	
	$("#empEmail").val('');
	$("#empcontactnumber").val('');
	$("#empqualification").val('');
	$("#designation").val('');	
	$("#departmentid").val('');
	$('#Image').attr('src', "assets/images/face.png");
	
	$('#isactive').hide();	
	$("#add_details_section").show();
	$("#view_details_section").hide();
}

function saveData(){
	
	if(validation()){
		
		var employeemasterbean={};	
		$("#employeedata").find('input').each(function(){
			employeemasterbean[$(this).attr('name')]=$(this).val();
		});
		
		employeemasterbean['companyid']=$('#companyid').val();
		employeemasterbean['departmentid']=$('#departmentid').val();
		employeemasterbean['locationid']=$('#locationid').val();
		employeemasterbean['active']=$('#active').val();	
		var empgender = $("input[name='empgender']:checked").val();
		employeemasterbean['empgender']=empgender;
		
		var formData = new FormData();
		
		if(undefined!=$('#mainform #empphoto')[0].files[0])
			formData.append('empphoto',$('#mainform #empphoto')[0].files[0]);
			formData.append('employeemaster',new Blob([JSON.stringify(employeemasterbean)],{type: "application/json"})); 
			
		$.ajax({
			url : "/visitorManagement/restemployeemaster/saveData",
			type : 'post',
			dataType: 'json',
			processData: false,
			contentType:false,
			data :	formData,		
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

function validation() {

	var companyname = $('#companyid').val();
	var locationname = $('#locationid').val();
	var empname = $('#empname').val();
	var empEmail = $('#empEmail').val();
	var contactnumber = $('#empcontactnumber').val();
	var empphoto=$('#empphoto').val();
	var empqualification = $('#empqualification').val();
	var deptname = $('#departmentid').val();
	var designation = $('#designation').val();
	
	if (companyname == "" || companyname==null || companyname==undefined) {
		toastr.info("Select Company Name", "Required Field");
		return false;
	}
	
	if (locationname == "" || locationname==null || locationname==undefined) {
		toastr.info("Select Location Name", "Required Field");
		return false;
	}
	
	if (empname == "" || empname==null || empname==undefined) {
		toastr.info("Employee Name", "Required Field");
		return false;
	}
	
	if($('input[name=empgender]:checked').length<=0)
	{
		toastr.info("Select Gender", "Required Field");
		return false;
	}

	if(empEmail=="" || empEmail==null || empEmail==undefined){
		toastr.info("Enter Email","Required Field");
		return false;
	}
	
	if(!(null==empEmail || !empEmail.trim()) && !filter.test(empEmail)){
		toastr.info("Email is Not Valid!!","Required Field");
	    $("#empEmail").focus();
	    return false;
	}
	
	if (contactnumber == "" || contactnumber==null || contactnumber==undefined || contactnumber<10) {
		toastr.info("Enter Contact Number", "Required Field");
		return false;
	}
	
	if(contactnumber.length<10){
		toastr.info("Enter 10 Digits Number","Required Field!");
		return false;	
	}

	if (empphoto == "" || empphoto==null || empphoto==undefined) {
		toastr.info("Image", "Required Field");
		return false;
	}
	if (empqualification == "" || empqualification==null || empqualification==undefined) {
		toastr.info("Enter Qualification", "Required Field");
		return false;
	}
	
	if (deptname == "" || deptname==null || deptname==undefined) {
		toastr.info("Select Department", "Required Field");
		return false;
	}
	
	if (designation == "" || designation==null || designation==undefined) {
		toastr.info("Enter Designation", "Required Field");
		return false;
	}
	
		return true;

}

$("#removeImg").on('click',function(){
	
	$("#userprofile").val("");
	 $('#Image').attr('src', "assets/images/face.png");
});

$("#changeImg").on('click',function(){
	
	$("#userprofile").val("");
	 $('#Image').attr('src', "assets/images/face.png");
});



filevalidateFlag=true;
$(document).on('change', '#empphoto', function () {
	 
	var errorMsg=checkFileUpload("mainform","empphoto","10",['jpg','jpeg','png']);
	if(null!=errorMsg && errorMsg!=undefined && errorMsg!=""){
		toastr.info(errorMsg,"Invalid Image");
    	filevalidateFlag=false;
    	$(this).val("");
    	return false;
    }else{
    	filevalidateFlag=true;
    	readURL(this);
    }
}); 

function readURL(input) {

	  if (input.files && input.files[0]) {
	    var reader = new FileReader();

	    reader.onload = function(e) {
	      $('#Image').attr('src', e.target.result);
	    };

	    reader.readAsDataURL(input.files[0]);
	  }
}
 


var locationidtemp=null;
var departmentidtemp=null;
function editData(employeeid,companyid,locationid,empname,gender,empEmail,empcontactnumber,Image,empqualification,departmentid,designation){

	locationidtemp=locationid;
	departmentidtemp=departmentid;
	$("#employeeid").val(employeeid).trigger('change');
	$("#companyid").val(companyid).trigger('change');
	$("#locationid").val(locationid).trigger('change');
	
	$("#empname").val(empname);
	
	if(gender=="Male"){
		$("#male").prop('checked',true);
	}
	else if(gender=="Female"){
		$("#female").prop('checked',true);
	} 
	 
	if(Image==null || Image=="" || Image==undefined)
	{
		$("#Image").attr("src", 'assets/images/face.png');
	}
	else 
	{
		$("#Image").attr("src", Image);
	}	 
	
	$("#empEmail").val(empEmail);
	$("#empcontactnumber").val(empcontactnumber);
	$("#empqualification").val(empqualification);
	$("#designation").val(designation);	
	$("#departmentid").val(departmentid);
	$('#isactive').hide();
	$("#add_details_section").show();
	$("#view_details_section").hide();
}

function deleteData(employeeid) {

	if (null != employeeid && employeeid.trim().length > 0) {
		swal({
			title : "Are you sure?",
			text : " Delete Employee Master Details ",
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
					url : "/visitorManagement/restemployeemaster/deleteData",
					type : "post",
					dataType : "text",
					data : {
						employeeid : employeeid,

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

function showUserData(userimage,employeeid,empname,emp_email,empcontactnumber,gender,cname,branchname,empqualification,deptname,designation){
		
	if(userimage!=null && userimage!="")
	{
		$("#userimagetxt").attr("src",userimage);
	}
	else 
	{
		str="<span class=\"user-profile text-center color-custom-demo\">" +
				"<img src='assets/images/face.png' id=\"userimagetxt\" alt='"+empname+"' title='"+empname+"'></span>";
		$("#userimagetxts").empty().append(str);
	}
	
	$("#employeeidtxt").text("Employee Id: "+employeeid);
	$("#employeenametxt").text(empname);
	$('#emailtxt').text(emp_email);
	$('#contacttxt').text(empcontactnumber);
	$('#gendertxt').text(gender);
	$('#cnametxt').text(cname);
	$('#branchnametxt').text(branchname);
	$('#qualificationtxt').text(empqualification);
	$('#deptnametxt').text(deptname);
	$('#designationtxt').text(designation);
	
	$("#primary").modal('show');

}

$("#companyid").on('change',function(){
	
	var companyid = $("#companyid").val();
	
	if (companyid != "" || companyid != null || companyid != undefined) {
	
		$.ajax({
		url : "/visitorManagement/restemployeemaster/getLocationData",
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
$("#locationid").on('change',function(){
	
	var companyid = $("#companyid").val();
	var locationid=$("#locationid").val();
	
	if ( locationid!= "" || locationid != null || locationid != undefined) {
	
		$.ajax({
		url : "/visitorManagement/restemployeemaster/getDepartmentData",
		method : "post",
		dataType : 'json',
		data : {
			companyid : companyid,
			locationid : locationid,
		},
		success : function(response) {
		
			var str = "<option value=''>Select Department</option>";
			if (null != response.departmentList){
			for (var i = 0; i < response.departmentList.length; i++) {
			
					str += "<option value="+ response.departmentList[i][0] + ">"+ response.departmentList[i][1]+ "</option>";
			 	}
			}
			$("#departmentid").empty().append(str).val(departmentidtemp).trigger('change');
		},
		error : function(msg) {
			console.log("Error: " + msg);
		}
			
		});
	}
});