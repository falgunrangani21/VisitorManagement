var filter = /^[-a-z0-9~!$%^&*_=+}{\'?]+(\.[-a-z0-9~!$%^&*_=+}{\'?]+)*@([a-z0-9_][-a-z0-9_]*(\.[-a-z0-9_]+)*\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,5})?$/i;
$('#myTable').DataTable({
	'destroy' : true,
	'bProcessing' : true,
	'bServerSide' : true,
	'sort' : 'position',
	'bStateSave' : false,
	'iDisplayStart' : 0,
	'fnDrawCallback' : function() {
	},
	'sAjaxSource' : '/visitorManagement/restvisitorcompanymaster/searchVisitorCompanyByAjax',
	'aoColumns' : [ {
		'data' : 'username',
		'defaultContent' : ""
	}, {
		'data' : 'email',
		'defaultContent' : ""
	},  {
		'data' : 'contactnumber',
		'defaultContent' : ""
	},{
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
		'targets' : [ -1 ],
		'orderable' : false
	}, {
		'className' : 'text-right',
		'targets' : [ 2 ]
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
	
	$("#vcompanyid").val('');
	$("#vcname").val('');
	$("#vcemail").val('');
	$("#vccontactno").val('');
	$("#vcfax").val('');
	$("#vccountryid").val('').trigger('change');
	$("#vcstateid").val('').trigger('change');
	$("#vccityid").val('').trigger('change');
	$('#isactive').hide();
	$("#add_details_section").show();
	$("#view_details_section").hide();
}

function saveData(){
	
	if(validation()){
		
	var visitorcompanybean={};
	$("#visitorcompanydata").find('input').each(function(){
		visitorcompanybean[$(this).attr('name')]=$(this).val();
	});
	visitorcompanybean['vcompanyid']=$('#vcompanyid').val();
	visitorcompanybean['employeeid']=$('#employeeid').val();
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

var vcstateidtemp=null;
var vccityidtemp=null;
function editData(vcompanyid,vcname,vcemail,vccontactno,vcfax,vccountryid,vcstateid,vccityid){
	
	vcstateidtemp=vcstateid;
	vccityidtemp=vccityid;
	$("#vcompanyid").val(vcompanyid);
	$("#vcname").val(vcname);
	$("#vcemail").val(vcemail);
	$("#vccontactno").val(vccontactno);
	$("#vcfax").val(vcfax);
	$("#vccountryid").val(vccountryid).trigger('change');
	$("#vcstateid").val(vcstateid).trigger('change');
	$("#vccityid").val(vccityid).trigger('change');
	$('#isactive').hide();
	$("#add_details_section").show();
	$("#view_details_section").hide();
}

function validation() {

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
	
	if(vccontactno.length<10){
		toastr.info("Enter 10 Digits Number","Required Field!");
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