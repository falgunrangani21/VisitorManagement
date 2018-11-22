var filter = /^[-a-z0-9~!$%^&*_=+}{\'?]+(\.[-a-z0-9~!$%^&*_=+}{\'?]+)*@([a-z0-9_][-a-z0-9_]*(\.[-a-z0-9_]+)*\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,5})?$/i;
var webfilter = /^(http:\/\/www\.|https:\/\/www\.|http:\/\/|https:\/\/)?[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,5}(:[0-9]{1,5})?(\/.*)?$/i;
$('#myTable').DataTable({
	'destroy' : true,
	'bProcessing' : true,
	'bServerSide' : true,
	'sort' : 'position',
	'bStateSave' : false,
	'iDisplayStart' : 0,
	'fnDrawCallback' : function() {
	},
	'sAjaxSource' : '/visitorManagement/restcompanymaster/searchCompanyMasterByAjax',
	'aoColumns' : [ {
		'data' : 'companyname',
		'defaultContent' : ""
	}, {
		'data' : 'email',
		'defaultContent' : ""
	}, {
		'data' : 'contactnumber',
		'defaultContent' : ""
	}, {
		'data' : 'workDetails',
		'defaultContent' : ""
	}, {
		'data' : 'branches',
		'defaultContent' : ""
	}, {
		'data' : 'website',
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
		'targets' : [ 2, 4 ]
	} ]
});
$('input[type=search]').attr('onkeydown', 'disabledkey(event)').attr(
		'maxlength', '64');

function disabledkey(event) {
	var key = event.keyCode;

	if (key == 222)
		event.preventDefault();
}

function openmodal() {

	$('#cname').val('');
	$('#cemail').val('');
	$('#ctelephonenumber').val('');
	$('#cfax').val('');
	$('#workDetails').val('');
	$('#cnoOfemployee').val('');
	$('#cnoOfbranches').val('');
	$('#cwebsite').val('');
	$('#add_details_section').show();
}

function editData(companyid, companyname, cemail, cfax,ctelephonenumber, workDetails, cnoOfemployee, cnoOfbranches, cwebsite) {

	$('#companyid').val(companyid);
	$('#cname').val(companyname);
	$('#cemail').val(cemail);
	$('#ctelephonenumber').val(ctelephonenumber);
	$('#cfax').val(cfax);
	$('#workDetails').val(workDetails);
	$('#cnoOfemployee').val(cnoOfemployee);
	$('#cnoOfbranches').val(cnoOfbranches);
	$('#cwebsite').val(cwebsite);
	$('#add_details_section').show();
	$('#view_details_section').hide();

}

function saveData() {

	if (validation()) {
		var companymasterbean = {};
		$('#companydata').find('input').each(function() {
		
			companymasterbean[$(this).attr('name')] = $(this).val();
		});

		companymasterbean['workDetails'] = $("#workDetails").val();

		$.ajax({
			type : "post",
			data : JSON.stringify(companymasterbean),
			url : "/visitorManagement/restcompanymaster/saveData",
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

function deleteData(companyid) {

	if (null != companyid && companyid.trim().length > 0) {
		swal({
			title : "Are you sure?",
			text : " Delete Company Master Details ",
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
					url : "/visitorManagement/restcompanymaster/deleteData",
					type : "post",
					dataType : "text",
					data : {
						companyid : companyid,

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

function validation() {

	var companyname = $('#cname').val();
	var cemail = $('#cemail').val();
	var ctelephonenumber = $('#ctelephonenumber').val();
	var workDetails = $('#workDetails').val();
	var cwebsite = $('#cwebsite').val();
	
	if (companyname == "" || companyname==null || companyname==undefined) {
		toastr.info("Company Name", "Required Field");
		return false;
	}
	
	if (cemail == "" || cemail==null ||cemail==undefined) {
		toastr.info("Company Email", "Required Field");
		return false;
	}
	
	if(!(null==cemail || !cemail.trim()) && !filter.test(cemail)){
		toastr.info("Email is Not Valid!!","Required Field");
	    $("#cemail").focus();
	    return false;
	}
	
	if (ctelephonenumber == "" || ctelephonenumber==null || ctelephonenumber==undefined) {
		toastr.info("Company Contact Number", "Required Field");
		return false;
	}
	
	if(ctelephonenumber.length<10){
		toastr.info("Enter 10 Digits Number","Required Field!");
		return false;	
	}
	
	if (workDetails == "" || workDetails==null ||workDetails==undefined) {
		toastr.info("Company Work Details", "Required Field");
		return false;
	}
	
	if (cwebsite == "" || cwebsite==null ||cwebsite==undefined) {
		toastr.info("Company Website", "Required Field");
		return false;
	}
	
	if(!webfilter.test(cwebsite)){
	
		toastr.info("Company Website is not Valid", "Error Field");
		return false;
	}
		return true;

}