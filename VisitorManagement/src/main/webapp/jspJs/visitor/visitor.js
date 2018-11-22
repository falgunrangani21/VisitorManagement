var filter = /^[-a-z0-9~!$%^&*_=+}{\'?]+(\.[-a-z0-9~!$%^&*_=+}{\'?]+)*@([a-z0-9_][-a-z0-9_]*(\.[-a-z0-9_]+)*\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,5})?$/i;

var cvsfromtime="";
var cvstotime="";
var cvscompany="";
var cvsemployee="";

var avvisitorid="";	
var avcompanyid=""	
var visfromcompanys = "";
var avvisitortypeid="";
var avlocationid="";
var avemployeeid="";	
var avcardid="";
var avNoOfPerson="";	

$(document).ready(function(){
	searchdata();
	var ischeckedin=$("#ischeckedin").val();
 var vids=$("#vids").val();
	console.log(vids);
	//localStorage.setItem("icheckedin","Y");

/*	if(localStorage.getItem("icheckedin") == "Y")
	{*/
		$("#checkinid").addClass('disabled');
		 var date = new Date();
		 var currentdate=date.toLocaleString().split(',');
		 var d=currentdate[0];
		 
		 var hours = date.getHours() > 12 ? date.getHours() - 12 : date.getHours();
	     var am_pm = date.getHours() >= 12 ? "PM" : "AM";
	     hours = hours < 10 ? "0" + hours : hours;
	     var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
	     var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
	     checkintime = hours + ":" + minutes + ":" + seconds + " " + am_pm;
	     $("#checkInTime"+vids).text(checkintime);
		// document.getElementById('checkInTime'+vid).innerHTML=checkintime;
		 $("#startTimer .col-sm-6.values.vid"+vid+"").html("00:00:00");
//	}	
	
});

var searchflag;
function currentVisitorSearch(){
	cvsfromtime=$('#autoswitch').val();
	cvstotime=$('#meridians').val();
	cvscompany=$('#cvscompanyid').val();
	cvsemployee=$('#cvsemployeeid').val();
	searchflag="c";
	searchdata();
}

function allVisitorSearch(){
	avvisitorid=$('#avvisitorid').val();	
	avlocationid=$('#avlocationid').val();
	avemployeeid=$('#avemployeeid').val();	
	avcardid=$('#avcardid').val();
	searchflag="a";
	searchdata();
}

function searchdata(){
	
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
	"fnServerParams":function(aoData){	
		
		aoData.push({"name":"cvsfromtime","value":cvsfromtime});
	    aoData.push({"name":"cvstotime","value":cvstotime});
	    aoData.push({"name":"cvscompany","value":cvscompany});
		aoData.push({"name":"cvsemployee","value":cvsemployee});
		
		aoData.push({"name":"avvisitorid","value":avvisitorid});
	    aoData.push({"name":"avlocationid","value":avlocationid});
	    aoData.push({"name":"avemployeeid","value":avemployeeid});
	    aoData.push({"name":"avcardid","value":avcardid});
	    aoData.push({"name": "searchflag","value":searchflag})
	},	
	'sAjaxSource' : '/visitorManagement/restvisitor/searchVisitorByAjax',
	'aoColumns' : [ {
		'data' : 'vphoto',
		'defaultContent' : ""
	}, {
		'data' : 'vid',
		'defaultContent' : ""
	},  {
		'data' : 'vname',
		'defaultContent' : ""
	}, {
		'data' : 'vemail',
		'defaultContent' : ""
	},{
		'data' : 'vcontactnumber',
		'defaultContent' : ""
	}, {
		'data' : 'visitortype',
		'defaultContent' : ""
	}, {
		'data' : 'empname',
		'defaultContent' : ""
	} ],
	columnDefs : [ {
		'targets' : [ 0 ],
		'orderable' : false
	}, {
		'className' : 'text-right',
		'targets' : [ 1,4 ]
	} ]
});
}
$('input[type=search]').attr('onkeydown', 'disabledkey(event)').attr(
		'maxlength', '64');

function disabledkey(event) {
	var key = event.keyCode;

	if (key == 222)
		event.preventDefault();
}

function openmodal(){
	
	$('#vid').val('');
	$('#vphoto').val('')
	$("#vname").val('');
	$("#vemail").val('');
	$("#vcontactnumber").val('');
	$("#visfromcompany").val('');
	$("#vcompanyid").val('').trigger('change');
	$('#visitortype').val('');
	$('#companyid').val('').trigger('change');
	$('#locationid').val('').trigger('change');
	$('#departmentid').val('').trigger('change');
	$('#vpassid').val('').trigger('change');	
	
	$("#vlocation").val('');
	$("#employeeid").val('').trigger('change');
	$("#purpose").val('');
	$('#vpassnumber').val('');
	$("#noofperson").val('');
	$("#vehiclenumber").val('');	  
	$("#materialdeposit").val('');
	$('input:checkbox').prop('checked', false);
	$('#isactive').hide();
	$("#add_details_section").show();
	$("#view_details_section").hide();
}

function saveData(){
	
	if(validation()){
		
		var visitormasterbean={};	
		$("#visitordata").find('input').each(function(){
			visitormasterbean[$(this).attr('name')]=$(this).val();
		});
		
		var visfromcompany = $("input[name='visfromcompany']:checked").val();
		visitormasterbean['visfromcompany']=visfromcompany;
		if(visfromcompany=="y" )
		{	
			visitormasterbean['vcompanyid']=$('#vcompanyid').val();	
		}
		visitormasterbean['vlocation']=$('#vlocation').val();
		visitormasterbean['locationid']=$('#locationid').val();
		visitormasterbean['companyid']=$('#companyid').val();
	
		visitormasterbean['departmentid']=$('#departmentid').val();
		visitormasterbean['employeeid']=$('#employeeid').val();
		visitormasterbean['purpose']=$('#purpose').val();
		visitormasterbean['vpassid']=$('#vpassid').val();
		visitormasterbean['ismaterialcarried']=$("#ismaterial").val();
		visitormasterbean['isvehicle']=$("#isvehicle").val();
		
		var formData = new FormData();	
		if(undefined!=$('#mainform #vphoto')[0].files[0])
			formData.append('vphoto',$('#mainform #vphoto')[0].files[0]);
			formData.append('visitor',new Blob([JSON.stringify(visitormasterbean)],{type: "application/json"})); 
	 
		$.ajax({
			url : "/visitorManagement/restvisitor/saveData",
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

function validation(){
	

	var vname = $('#vname').val();
	var vemail = $('#vemail').val();
	var vcontactnumber = $('#vcontactnumber').val();
	var vphoto = $('#vphoto').val();
	var visfromcompany = $("input[name='visfromcompany']:checked").val();
	var vcompanyid=$('#vcompanyid').val();
	var visitortype=$('#visitortype').val();
	var vlocation=$('#vlocation').val();
	var companyid=$('#companyid').val();
	var locationid=$('#locationid').val();	
	var departmentid=$('#departmentid').val();
	var employeeid=$('#employeeid').val();	
	var purpose=$('#purpose').val();
	var vpassid=$('#vpassid').val();
	var noofperson=$('#noofperson').val();
	var ismaterial=$("#ismaterial").val();
	var isvehicle=$("#isvehicle").val();	
	var vehiclenumber=$("#vehiclenumber").val();
	var materialdeposit=$("#materialdeposit").val();
	
	if (vname == "" || vname==null || vname==undefined) {
		toastr.info("Visitor Name", "Required Field");
		return false;
	}
	
	if (vemail == "" || vemail==null || vemail==undefined) {
		toastr.info("Email", "Required Field");
		return false;
	}
	
	if(!(null==vemail || !vemail.trim()) && !filter.test(vemail)){
		toastr.info("Email is Not Valid!!","Required Field");
	    $("#vemail").focus();
	    return false;
	}
	
	if (vcontactnumber == "" || vcontactnumber==null || vcontactnumber==undefined) {
		toastr.info("Contact Number", "Required Field");
		return false;
	}
	
	if(vcontactnumber.length<10){
		toastr.info("Enter 10 Digits Number","Required Field!");
		return false;	
	}
	
	if(visfromcompany=="y" )
	{	
		if (vcompanyid == "" || vcompanyid==null || vcompanyid==undefined) {
			toastr.info("Company Name", "Required Field");
			return false;
		}
	}
	else{
		
		if (visitortype == "" || visitortype==null || visitortype==undefined) {
			toastr.info("Visitor Name", "Required Field");
			return false;
		}		
	}
	
	if (vlocation == "" || vlocation==null || vlocation==undefined) {
		toastr.info("Visitor Location Name", "Required Field");
		return false;
	}
	
	if (companyid == "" || companyid==null || companyid==undefined) {
		toastr.info("Company Name", "Required Field");
		return false;
	}
	
	if (locationid == "" || locationid==null || locationid==undefined) {
		toastr.info("Location Name", "Required Field");
		return false;
	}
	
	if (departmentid == "" || departmentid==null || departmentid==undefined) {
		toastr.info("Department Name", "Required Field");
		return false;
	}
	
	if (employeeid == "" || employeeid==null || employeeid==undefined) {
		toastr.info("Employee Name", "Required Field");
		return false;
	}
	
	if (purpose == "" || purpose==null || purpose==undefined) {
		toastr.info("Purpose to Visit", "Required Field");
		return false;
	}
	
	if (vpassid == "" || vpassid==null || vpassid==undefined) {
		toastr.info("Visitor Card Number", "Required Field");
		return false;
	}
	
	if (noofperson == "" || noofperson==null || noofperson==undefined) {
		toastr.info("Number of Persons", "Required Field");
		return false;
	}
	
		if((vehiclenumber == "" || vehiclenumber==null || vehiclenumber==undefined) && isvehicle =="y"){
		toastr.info("Vehicle Number", "Required Field");
		return false;
	}
	 
		if((materialdeposit == "" || materialdeposit==null || materialdeposit==undefined) && ismaterial=="y"){
		toastr.info("Material Information", "Required Field");
		return false;
	}	
	
		return true;
}

$("#removeImg").on('click',function(){
	
	$("#vphoto").val("");
	 $('#Image').attr('src', "assets/images/face.png");
});

$("#changeImg").on('click',function(){
	
	$("#vphoto").val("");
	 $('#Image').attr('src', "assets/images/face.png");
});


filevalidateFlag=true;
$(document).on('change', '#vphoto', function () {
	 
	var errorMsg=checkFileUpload("mainform","vphoto","10",['jpg','jpeg','png']);
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


var timer;
var  ischeckedin=false;
var checkintime="";
var checkinflag=true;
function checkIn(vid){	
if(checkinflag)
	{
	$("#checkinid").addClass('disabled');
	timer=new Timer();

	//$("#ischeckedin").val("Y");
	localStorage.setItem("icheckedin","Y");
		timer.start();
	
	timer.addEventListener('secondsUpdated', function (e) {
	    $('#startTimer .values'+vid).html(timer.getTimeValues().toString());
	  
	});
	timer.addEventListener('started', function (e) {
	    $('#startTimer .values'+vid).html(timer.getTimeValues().toString());
	});
	timer.addEventListener('reset', function (e) {
	    $('#startTimer .values'+vid).html(timer.getTimeValues().toString());
	});
	
	 var checkType="checkin";
	 var date = new Date();
	 var currentdate=date.toLocaleString().split(',');
	 var d=currentdate[0];
	 
	 var hours = date.getHours() > 12 ? date.getHours() - 12 : date.getHours();
     var am_pm = date.getHours() >= 12 ? "PM" : "AM";
     hours = hours < 10 ? "0" + hours : hours;
     var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
     var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
     time = hours + ":" + minutes + ":" + seconds + " " + am_pm;
     var datetime=(d+" "+time).toString();
     checkintime=time
     $("#startTimer .col-sm-6.values.vid"+vid+"").html("00:00:00");
     
	 document.getElementById('checkInTime'+vid).innerHTML=time;
	 $.ajax({
			url : "/visitorManagement/restvisitor/CheckInAndCheckOut",
			method : "post",
			dataType : 'json',
			data : {
				vid:vid,
				datetime:datetime,
				checkType:checkType
			},
			success : function(response) {
				checkinflag=false;
			}
			 
		});  
	}
}

function checkOut(vid){
	
	timer.stop();
	
	
	
	 var checkType="checkout";
	 var date = new Date();
	 var currentdate=date.toLocaleString().split(',');
	 var d=currentdate[0];
	 
	 var hours = date.getHours() > 12 ? date.getHours() - 12 : date.getHours();
     var am_pm = date.getHours() >= 12 ? "PM" : "AM";
     hours = hours < 10 ? "0" + hours : hours;
     var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
     var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
     time = hours + ":" + minutes + ":" + seconds + " " + am_pm;
     var datetime=d+" "+time;
	 document.getElementById('checkOutTime'+vid).innerHTML=time;
	 $.ajax({
			url : "/visitorManagement/restvisitor/CheckInAndCheckOut",
			method : "post",
			dataType : 'json',
			data : {
				vid:vid,
				datetime:datetime,
				checkType:checkType
			},
			success : function(response) {
				
			}
			 
		});   
}
/*
function checkIn(vid){	 
	 var checkType="checkin";
	 var date = new Date();
	 var currentdate=date.toLocaleString().split(',');
	 var d=currentdate[0];
	 
	 var hours = date.getHours() > 12 ? date.getHours() - 12 : date.getHours();
     var am_pm = date.getHours() >= 12 ? "PM" : "AM";
     hours = hours < 10 ? "0" + hours : hours;
     var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
     var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
     time = hours + ":" + minutes + ":" + seconds + " " + am_pm;
     var datetime=(d+" "+time).toString();
     
     $("#startTimer .col-sm-6.values.vid"+vid+"").html("00:00:00");
     
	 document.getElementById('checkInTime'+vid).innerHTML=time;
	 $.ajax({
			url : "/visitorManagement/restvisitor/CheckInAndCheckOut",
			method : "post",
			dataType : 'json',
			data : {
				vid:vid,
				datetime:datetime,
				checkType:checkType
			},
			success : function(response) {
				 
			}
			 
		});  
	 
}

function checkOut(vid){
	 
	 var checkType="checkout";
	 var date = new Date();
	 var currentdate=date.toLocaleString().split(',');
	 var d=currentdate[0];
	 
	 var hours = date.getHours() > 12 ? date.getHours() - 12 : date.getHours();
     var am_pm = date.getHours() >= 12 ? "PM" : "AM";
     hours = hours < 10 ? "0" + hours : hours;
     var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
     var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
     time = hours + ":" + minutes + ":" + seconds + " " + am_pm;
     var datetime=d+" "+time;
	 document.getElementById('checkOutTime'+vid).innerHTML=time;
	 $.ajax({
			url : "/visitorManagement/restvisitor/CheckInAndCheckOut",
			method : "post",
			dataType : 'json',
			data : {
				vid:vid,
				datetime:datetime,
				checkType:checkType
			},
			success : function(response) {
				
			}
			 
		});   
}
*/
var vcstateidtemp;
var vccityidtemp;
$("#vccountryid").on('change',function(){
	
	var vccountryid = $("#vccountryid").val();
	
	if (vccountryid != "" || vccountryid != null || vccountryid != undefined) {
	
		$.ajax({
		url : "/visitorManagement/restvisitorcompanymaster/getStateData",
		method : "post",
		dataType : 'json',
		data : {
			countryid : vccountryid
		},
		success : function(response) {
		
			var str = "<option value=''>Select State</option>";
			if (null != response.stateList){
			for (var i = 0; i < response.stateList.length; i++) {
			
					str += "<option value="+ response.stateList[i][0] + ">"+ response.stateList[i][1]+ "</option>";
			 	}
			}
			$("#vcstateid").empty().append(str).val(vcstateidtemp).trigger('change');
		},
		error : function(msg) {
			console.log("Error: " + msg);
		}
			
		});
	}
});

$("#vcstateid").on('change',function(){

	var vccountryid = $("#vccountryid").val();
	var vcstateid  =$("#vcstateid").val();
	
	if (vccountryid != "" || vccountryid != null || vccountryid != undefined && vcstateid != "" || vcstateid != null || vcstateid != undefined) {

	$.ajax({
		url : "/visitorManagement/restvisitorcompanymaster/getCityData",
		method : "post",
		dataType : 'json',
		data : {
			countryid : vccountryid ,
			stateid   : vcstateid,
		},
		success : function(response) {
	
		var str = "<option value=''>Select State</option>";
			if (null != response.cityList){
				for (var i = 0; i < response.cityList.length; i++) {
			
						str += "<option value="+ response.cityList[i][0] + ">"+ response.cityList[i][1]+ "</option>";
				 	}
				}
						$("#vccityid").empty().append(str).val(vccityidtemp).trigger('change');
		},
		
		error : function(msg) {
			console.log("Error: " + msg);
		}

	});
	}
});

var locationidtemp;
var departmentidtemp;
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
			var str1 = "<option value=''>Select Visitor Card</option>";
			if (null != response.locationList){
			for (var i = 0; i < response.locationList.length; i++) {
			
					str += "<option value="+ response.locationList[i][0] + ">"+ response.locationList[i][1]+ "</option>";
			 	}
			}
			if (null != response.vcardList){
				for (var i = 0; i < response.vcardList.length; i++) {
				
						str1 += "<option value="+ response.vcardList[i][0] + ">"+ response.vcardList[i][1]+ "</option>";
				 	}
				}
			$("#locationid").empty().append(str).val(locationidtemp).trigger('change');
			
			$("#vpassid").empty().append(str1+"<option value='O'>Others</option>");
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
	
	if (locationid != "" || locationid != null || locationid != undefined) {
	
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

$("#departmentid").on('change',function(){
	
	var companyid = $("#companyid").val();
	var locationid=$("#locationid").val();
	var departmentid=$('#departmentid').val();
	
	if (departmentid != "" || departmentid != null || departmentid != undefined) {
	
		$.ajax({
		url : "/visitorManagement/restvisitor/getEmployeeData",
		method : "post",
		dataType : 'json',
		data : {
			companyid : companyid,
			locationid : locationid,
			departmentid : departmentid,
		},
		success : function(response) {
		
			var str = "<option value=''>Select Employee</option>";
			if (null != response.employeeList){
			for (var i = 0; i < response.employeeList.length; i++) {
			
					str += "<option value="+ response.employeeList[i][0] + ">"+ response.employeeList[i][1]+ "</option>";
			 	}
			}
			$("#employeeid").empty().append(str).trigger('change');
		},
		error : function(msg) {
			console.log("Error: " + msg);
		}
			
		});
	}
});

$("#avcompanyid").on('change',function(){
	
	var avcompanyid = $("#avcompanyid").val();

	if (avcompanyid != "" || avcompanyid != null || avcompanyid != undefined) {
	
		$.ajax({
		url : "/visitorManagement/restvisitor/getVisitorCompany",
		method : "post",
		dataType : 'json',
		data : {
			avcompanyid : avcompanyid, 
		},
		success : function(response) {
		
			var str = "<option value=''>Select Location</option>";
			if (null != response.vlocationList){
			for (var i = 0; i < response.vlocationList.length; i++) {
			
					str += "<option value="+ response.vlocationList[i][0] + ">"+ response.vlocationList[i][1]+ "</option>";
			 	}
			}
			$("#avlocationid").empty().append(str).trigger('change');
		},
		error : function(msg) {
			console.log("Error: " + msg);
		}
			
		});
	}
});

	function showUserData(vid,userimage,vname,email,contactnumber,location,employee,purpose,vpassnumber,noofperson,isvehicle,vehicle,ismaterial,material,checkin,checkout,isfromcompany,vcname){

	if(userimage!=null && userimage!="")
	{
		$("#userimagetxt").attr("src",userimage);
	}
	else 
	{
		str="<span class=\"user-profile text-center color-custom-demo\">" +
				"<img src='assets/images/face.png' id=\"userimagetxt\" alt='"+vname+"' title='"+vname+"'></span>";
		$("#userimagetxts").empty().append(str);
	}	
	
	$("#vidtxt").text("Visitor Id: "+vid);
	$("#vnametxt").text(vname);
	$('#emailtxt').text(email);
	$('#contacttxt').text(contactnumber);
	$('#locationtxt').text(location);
	$('#employeetxt').text(employee);	
	$('#purposetxt').text(purpose);
	$('#vpassnumbertxt').text(vpassnumber);
	$('#noofpersontxt').text(noofperson);
	$('#isvehicletxt').text(isvehicle);
	$("#vehicletxt").text(vehicle);
	$('#ismaterialtxt').text(ismaterial);
	$("#materialtxt").text(material);
	$('#checkintxt').text(checkin);
	$("#checkouttxt").text(checkout);
	$('#isfromcompanytxt').text(isfromcompany);
	$("#vcnametxt").text(vcname);	
	$("#primary").modal('show');

}
/***************************** Company Data Save ****************************/
$('#vcompanyid').change(function () {
	
	var vcompanyid=$('#vcompanyid').val();
	if(vcompanyid=="O"){
		  $('#new_company').modal('show');
	}
  
});

function clearData(){
	$('#vcompanyid').val('').trigger('change');
}

function saveCompanyData(){
	
	//if(validateCompany()){
		
		var visitorcompanybean={};
		$("#visitorcompanydata").find('input').each(function(){
			visitorcompanybean[$(this).attr('name')]=$(this).val();
		});
		
		visitorcompanybean['vccountryid']=$('#vccountryid').val();
		visitorcompanybean['vcstateid']=$('#vcstateid').val();
		visitorcompanybean['vccityid']=$('#vccityid').val();
	 
		$.ajax({
			url : "/visitorManagement/restvisitorcompanymaster/saveData",
			type : 'post',
			contentType : "application/json",
			data :	JSON.stringify(visitorcompanybean),		
			success : function(resp) {
					if (resp['SAVE'] != null || resp['SAVE'] != undefined) {
					
						toastr.info("" + 'DATA SAVE ' + resp['SAVE'] + "",
								"Message..!");
						$('#myTable').DataTable().ajax.reload();
						setTimeout(() => {
							window.location.href="/visitorManagement/visitor";
						}, 1000);
						  $('#new_company').modal('hide');
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
//	}
}

function validateCompany() {

	var vcname = $('#vcname').val();
	var vcemail = $('#vcemail').val();
	var vccontactno = $('#vccontactno').val();
	var vcfax = $('#vcfax').val();
	var vccountryid = $('#vccountryid').val();
	var vcstateid = $('#vcstateid').val();
	var vccityid = $('#vccityid').val();
	
	if (vcname == "" || vcname==null || vcname==undefined) {
		toastr.info("Company Name", "Required Field");
		return false;
	}
	
	if (vcemail == "" || vcemail==null || vcemail==undefined) {
		toastr.info("Email", "Required Field");
		return false;
	}
	
	if(!(null==vcemail || !vcemail.trim()) && !filter.test(vcemail)){
		toastr.info("Email is Not Valid!!","Required Field");
	    $("#vcemail").focus();
	    return false;
	}
	
	if (vccontactno == "" || vccontactno==null || vccontactno==undefined) {
		toastr.info("Contact Number", "Required Field");
		return false;
	}
	
	if (vccountryid == "" || vccountryid==null || vccountryid==undefined) {
		toastr.info("Country Name", "Required Field");
		return false;
	}
	
	if (vccountryid == "" || vccountryid==null || vccountryid==undefined) {
		toastr.info("Country Name", "Required Field");
		return false;
	}
	
	if (vcstateid == "" || vcstateid==null || vcstateid==undefined) {
		toastr.info("State Name", "Required Field");
		return false;
	}
	
	if (vccityid == "" || vccityid==null || vccityid==undefined) {
		toastr.info("City Name", "Required Field");
		return false;
	}
	
		return true;
}


/*******************************Visitor Card Data Save ***********************************/
$('#vpassid').change(function () {
	 
	var vpassid=$('#vpassid').val();	 
	if(vpassid=="O"){
		  $('#new_card').modal('show');
	}
  
});

function clearCardData(){
	$('#vpassid').val('').trigger('change');
}

function saveCardData(){
	
	if(validateCard()){
	
	var visitorcardbean={};
	$("#visitorcarddata").find('input').each(function(){
		visitorcardbean[$(this).attr('name')]=$(this).val();
	});
	visitorcardbean['companyid']=$('#companyid').val();
	visitorcardbean['locationid']=$('#locationid').val();
	
	$.ajax({
		url : "/visitorManagement/restvisitor/saveCardData",
		type : 'post',
		contentType : "application/json",
		data :	JSON.stringify(visitorcardbean),		
		success : function(resp) {
				if (resp['SAVE'] != null || resp['SAVE'] != undefined) {
				
					toastr.info("" + 'DATA SAVE ' + resp['SAVE'] + "",
							"Message..!");
					$('#myTable').DataTable().ajax.reload();
					  $('#new_company').modal('hide');
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

function validateCard() {

	var companyid = $('#companyid').val();
	var locationid = $('#locationid').val();
	var vpassnumber = $('#vpassnumber').val();
	
	if (companyid == "" || companyid==null || companyid==undefined) {
		toastr.info("Company Name", "Required Field");
		return false;
	}
	
	if (locationid == "" || locationid==null || locationid==undefined) {
		toastr.info("Location Name", "Required Field");
		return false;
	}
	 
	if (vpassnumber == "" || vpassnumber==null || vpassnumber==undefined) {
		toastr.info("Card Number", "Required Field");
		return false;
	} 
		return true;
}


/**************************** jsp js******************************************/
    $(document).ready(function () {
        $('#isFromCopmanyYes').click(function () {
            $('#CopmanyYes').show('fast');
            $('#CopmanyNo').hide('fast');
        });
        $('#isFromCopmanyNo').click(function () {
            $('#CopmanyYes').hide('fast');
            $('#CopmanyNo').show('fast');
        });
        $('#isFromCopmanyYes_Searching').click(function () {
            $('#CopmanyYes_Searching').show('fast');
            $('#CopmanyNo_Searching').hide('fast');
        });
        $('#isFromCopmanyNo_Searching').click(function () {
            $('#CopmanyYes_Searching').hide('fast');
            $('#CopmanyNo_Searching').show('fast');
        });

        $(".vehicle-number-dec").hide();
        $(".material-number-dec").hide();

        $('#vehicle').click(function() {
            if($(this).is(':checked')) {
                $(".vehicle-number-dec").show('fast');
                $("#isvehicle").val("y");
            } else {
                $(".vehicle-number-dec").hide('fast');
                $("#isvehicle").val("n");
            }
        });
        $('#ismaterialcarried').click(function() {
            if($(this).is(':checked')) {
                $(".material-number-dec").show('fast');
                $("#ismaterial").val("y");
            } else {
                $(".material-number-dec").hide('fast');
                $("#ismaterial").val("n");
            }
        });

        /*$('input').dateDropper();*/

});