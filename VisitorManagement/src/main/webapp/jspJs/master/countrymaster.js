$('#myTable').DataTable({
	'destroy' : true,
	'bProcessing' : true,
	'bServerSide' : true,
	'sort' : 'position',
	'bStateSave' : false,
	'iDisplayStart' : 0,
	'fnDrawCallback' : function() {
	},
	'sAjaxSource' : '/visitorManagement/restcountrymaster/searchCountryMasterByAjax',
	'aoColumns' : [ {
		'data' : 'countryname',
		'defaultContent' : ""
	}, {
		'data' : 'isdcode',
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
		'targets' : [ 1 ]
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

	$("#countryid").val('');
	$("#countryname").val('');
	$("#isdcode").val('');
	$("#add_details_section").show();
}

function editData(countryid, countryname, isdcode) {

	$("#countryid").val(countryid);
	$("#countryname").val(countryname);
	$("#isdcode").val(isdcode);
	$("#add_details_section").show();
	$("#view_details_section").hide();

}
function saveData() {

	if (validation()) {
		var countrymasterbean = {};
		$('#countrydata').find('input').each(function() {
			countrymasterbean[$(this).attr('name')] = $(this).val();
		});
	
			
	$.ajax({
				type : "post",
				data : JSON.stringify(countrymasterbean),
				url : "/visitorManagement/restcountrymaster/saveData",
				contentType : "application/json",
				success : function(resp) {
							
					if (resp['SAVE'] != null || resp['SAVE'] != undefined) {
	
						toastr.info("" + 'DATA SAVE ' + resp['SAVE'] + "","Message..!");
						$('#myTable').DataTable().ajax.reload();
						$('#add_details_section').hide();
						$('#view_details_section').show();
					}
	
					else if (resp['DUPLICATE'] != null	|| resp['DUPLICATE'] != undefined) {
							toastr.info("" + '' + resp['DUPLICATE'] + "","Message..!");
							
					}
	
					else if (resp['ERRORMASSAGE'] != null || resp['ERRORMASSAGE'] != undefined) {
								toastr.info("" + '' + resp['ERRORMASSAGE'] + "","Message..!");
					}
	
				}
		});
	}
}
function deleteData(countryid) {

	if (null != countryid && countryid.trim().length > 0) {
		swal({
			title : "Are you sure?",
			text : " Delete Country Master Details ",
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
					url : "/visitorManagement/restcountrymaster/deleteData",
					type : "post",
					dataType : "text",
					data : {
						countryid : countryid,

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
	var countryname = $("#countryname").val();
	if (countryname == "" || countryname == null || countryname == undefined) {
		toastr.info("Country Name", "Required Field");
		return false;
	}
	
	return true;
}