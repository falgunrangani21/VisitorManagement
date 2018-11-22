var filter = /^[-a-z0-9~!$%^&*_=+}{\'?]+(\.[-a-z0-9~!$%^&*_=+}{\'?]+)*@([a-z0-9_][-a-z0-9_]*(\.[-a-z0-9_]+)*\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,5})?$/i;
$('#myTable').DataTable({
	'destroy' : true,
	'bProcessing' : true,
	'bServerSide' : false,
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
	'sAjaxSource' : '/visitorManagement/restusermaster/searchUsermasterByAjax',
	'aoColumns' : [ {
		'data' : 'profileurl',
		'defaultContent' : ""
	}, {
		'data' : 'username',
		'defaultContent' : ""
	}, {
		'data' : 'email',
		'defaultContent' : ""
	}, {
		'data' : 'contactnumber',
		'defaultContent' : ""
	}, {
		'data' : 'gender',
		'defaultContent' : ""
	}, {
		'data' : 'countryname',
		'defaultContent' : ""
	}, {
		'data' : 'statename',
		'defaultContent' : ""
	}, {
		'data' : 'cityname',
		'defaultContent' : ""
	}, {
		'data' : 'action',
		'defaultContent' : ""
	} ],

	columnDefs : [ {
		'targets' : [-1,0],
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
	
	$("#userid").val('');
	$("#username").val('');
	$("#usertype").val('').trigger('change');
	$("#roleaccesstype").val('');
	
	$("#male").prop('checked',false);
	$("#female").prop('checked',false);
	
	$("#email").val('');
	$("#contactnumber").val('');
	
	$('#Image').attr('src', "assets/images/face.png");
	$("#loginname").val('');
	$("#password").val('');
	$("#countryid").val('').trigger('change');
	$("#stateid").val('').trigger('change');
	$("#cityid").val('').trigger('change');
	$('#isactive').hide();
	$("#add_details_section").show();
	$("#view_details_section").hide();
}

function saveData(){
	
	if(validation()){
		
		var usermasterbean={};	
		$("#userdata").find('input').each(function(){
			usermasterbean[$(this).attr('name')]=$(this).val();
		});
		
		usermasterbean['usertype']=$('#usertype').val();
		usermasterbean['countryid']=$('#countryid').val();
		usermasterbean['stateid']=$('#stateid').val();
		usermasterbean['cityid']=$('#cityid').val();
		usermasterbean['active']=$('#active').val();	
		var gender = $("input[name='gender']:checked").val();
		usermasterbean['gender']=gender;
	
		var formData = new FormData();
	
		if(undefined!=$('#mainform #userprofile')[0].files[0])
			formData.append('userprofile',$('#mainform #userprofile')[0].files[0]);
			formData.append('usermaster',new Blob([JSON.stringify(usermasterbean)],{type: "application/json"})); 
		
		$.ajax({
			url : "/visitorManagement/restusermaster/saveData",
			type : 'post',
			dataType: 'json',
			processData: false, //prevent jQuery from automatically transforming the data into a query string
			contentType:false,
			data :	formData,		
			success : function(resp) {
					if (resp['SAVE'] != null || resp['SAVE'] != undefined) {
					
						toastr.info("" + 'DATA SAVE ' + resp['SAVE'] + "",
								"Message..!");
						//$('#myTable').DataTable().ajax.reload();
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
					setTimeout(() => {
						window.location.href="/visitorManagement/usermaster";
					}, 1000);
				}	
		});
	}
}

function validation() {

	var username = $('#username').val();
	var usertype = $('#usertype').val();
	var roleaccesstype = $('#roleaccesstype').val();
	var email = $('#email').val();
	var contactnumber = $('#contactnumber').val(); 
	var countryid = $('#countryid').val();
	var stateid = $('#stateid').val();
	var cityid = $('#cityid').val();
	var loginname = $('#loginname').val();
	var password = $('#password').val();
	
	if (username == "" || username==null || username==undefined) {
		toastr.info("Enter User Name", "Required Field");
		return false;
	}
	
	if (usertype == "" || usertype==null || usertype==undefined) {
		toastr.info("Select User Type", "Required Field");
		return false;
	}
	
	if (roleaccesstype == "" || roleaccesstype==null || roleaccesstype==undefined) {
		toastr.info("Enter Access Type", "Required Field");
		return false;
	}
	
	if($('input[name=gender]:checked').length<=0)
	{
		toastr.info("Select Gender", "Required Field");
		return false;
	}

	if(email=="" || email==null || email==undefined){
		toastr.info("Enter Email","Required Field");
		return false;
	}
	
	if(!(null==email || !email.trim()) && !filter.test(email)){
		toastr.info("Email is Not Valid!!","Required Field");
	    $("#email").focus();
	    return false;
	}
	
	if (contactnumber == "" || contactnumber==null || contactnumber==undefined) {
		toastr.info("Enter Contact Number", "Required Field");
		return false;
	}
	
	if(contactnumber.length<10){
		toastr.info("Enter 10 Digits Number","Required Field!");
		return false;	
	}
	
	if (countryid == "" || countryid==null || countryid==undefined) {
		toastr.info("Country Name", "Required Field");
		return false;
	}
	
	if (stateid == "" || stateid==null || stateid==undefined) {
		toastr.info("State Name", "Required Field");
		return false;
	}
	
	if (cityid == "" || cityid==null || cityid==undefined) {
		toastr.info("City Name", "Required Field");
		return false;
	}

	
	if (loginname == "" || loginname==null || loginname==undefined) {
		toastr.info("Enter Login Name", "Required Field");
		return false;
	}
	
	if (password == "" || password==null || password==undefined) {
		toastr.info("Enter Password", "Required Field");
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
$(document).on('change', '#userprofile', function () {
	 
	var errorMsg=checkFileUpload("mainform","userprofile","10",['jpg','jpeg','png']);
	if(null!=errorMsg && errorMsg!=undefined && errorMsg!=""){
		toastr.info(errorMsg,"Invalid Image");
		$(this).val("");
    	filevalidateFlag=false;
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

var stateidtemp=null;
var cityidtemp=null;
function editData(userid,Image,username,usertype,roleaccesstype,gender,email,contactnumber,countryid,stateid,cityid,loginname,password){

	stateidtemp=stateid;
	cityidtemp=cityid;
	$("#userid").val(userid);
	$("#username").val(username);
	$("#usertype").val(usertype).trigger('change');
	$("#roleaccesstype").val(roleaccesstype);
	$("#gender").val(gender);
	$("#email").val(email);
	$("#contactnumber").val(contactnumber);
	
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
	
	$("#loginname").val(loginname);
	$("#password").val(password);
	$("#countryid").val(countryid).trigger('change')
	$("#stateid").val(stateid).trigger('change');
	$("#cityid").val(cityid).trigger('change');
	$('#isactive').hide();
	$("#add_details_section").show();
	$("#view_details_section").hide();

}

function deleteData(userid) {

	if (null != userid && userid.trim().length > 0) {
		swal({
			title : "Are you sure?",
			text : " Delete User Master Details ",
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
					url : "/visitorManagement/restusermaster/deleteData",
					type : "post",
					dataType : "text",
					data : {
						userid : userid,

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


function showUserData(userimage,username,email,contact,gender,loginname,usertype,roleaccesstype,userid)
{
	
	if(userimage!=null && userimage!="")
	{
		$("#userimagetxt").attr("src",userimage);
	}
	else 
	{
		str="<span class=\"user-profile text-center color-custom-demo\">" +
				"<img src='assets/images/face.png' id=\"userimagetxt\" alt='"+username+"' title='"+username+"'></span>";
		$("#userimagetxts").empty().append(str);
	}	
	
	 $('#useridtxt').text("User Id: "+userid); 
	 $("#usernametxt").text(username);
	 $("#emailtxt").text(email);
	 $("#contacttxt").text(contact);
	 $("#gendertxt").text(gender);
	 $("#loginnametxt").text(loginname);
	 $("#usertypetxt").text(usertype);
	 $("#roleaccesstypetxt").text(roleaccesstype);
	 
	 $("#primary").modal('show')

}


$("#countryid").on('change',function(){
	
	var countryid = $("#countryid").val();
	
	if (countryid != "" || countryid != null || countryid != undefined) {
	
		$.ajax({
		url : "/visitorManagement/restusermaster/getStateData",
		method : "post",
		dataType : 'json',
		data : {
		countryid : countryid
		},
		success : function(response) {
		
			var str = "<option value=''>Select State</option>";
			if (null != response.stateList){
			for (var i = 0; i < response.stateList.length; i++) {
			
					str += "<option value="+ response.stateList[i][0] + ">"+ response.stateList[i][1]+ "</option>";
			 	}
			}
			$("#stateid").empty().append(str).val(stateidtemp).trigger('change');
		},
		error : function(msg) {
			console.log("Error: " + msg);
		}
			
		});
	}
});


$("#stateid").on('change',function(){

	var countryid = $("#countryid").val();
	var stateid  =$("#stateid").val();
	
	if (countryid != "" || countryid != null || countryid != undefined && stateid != "" || stateid != null || stateid != undefined) {

	$.ajax({
		url : "/visitorManagement/restlocationmaster/getCityData",
		method : "post",
		dataType : 'json',
		data : {
		countryid : countryid ,
		stateid   : stateid,
		},
		success : function(response) {
	
		var str = "<option value=''>Select State</option>";
			if (null != response.cityList){
				for (var i = 0; i < response.cityList.length; i++) {
			
						str += "<option value="+ response.cityList[i][0] + ">"+ response.cityList[i][1]+ "</option>";
				 	}
				}
						$("#cityid").empty().append(str).val(cityidtemp).trigger('change');
		},
		
		error : function(msg) {
		console.log("Error: " + msg);
		}

	});
	}
});
/*
$('#change').click(function(){
	$('#Image').remove();
});
*/