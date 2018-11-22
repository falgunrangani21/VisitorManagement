$('#myTable').DataTable({
	'destroy' : true,
	'bProcessing' : true,
	'bServerSide' : true,
	'sort' : 'position',
	'bStateSave' : false,
	'iDisplayStart' : 0,
	'fnDrawCallback' : function() {
	},
	'sAjaxSource' : '/visitorManagement/reststatemaster/searchStateMasterByAjax',
	'aoColumns' : [ {
		'data' : 'statename',
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

function openmodal() {
	
	$("#stateid").val('')
	$("#countryid").val('').trigger('change');
	$("#statename").val('').trigger('change');
	$("#add_details_section").show();
}

function editData(stateid,statename,countryid) {

	$("#stateid").val(stateid);
	$("#countryid").val(countryid).trigger('change');
	$("#statename").val(statename).trigger('change');
	$("#add_details_section").show();
	$("#view_details_section").hide();

}

function saveData() {
	
	if (validation()) {
		var statemasterbean = {};
		$('#statedata').find('input').each(function() {
			statemasterbean[$(this).attr('name')] = $(this).val();
		});
		
		statemasterbean['countryid'] = $("#countryid").val();

		$.ajax({
			type : "post",
			data : JSON.stringify(statemasterbean),
			url : "/visitorManagement/reststatemaster/saveData",
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

function deleteData(stateid) {

	if (null != stateid && stateid.trim().length > 0) {
		swal({
			title : "Are you sure?",
			text : " Delete State Master Details ",
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
					url : "/visitorManagement/reststatemaster/deleteData",
					type : "post",
					dataType : "text",
					data : {
						stateid : stateid,

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
	
	var countryid = $('#countryid').val();
	var statename = $('#statename').val();
	
	if (countryid == "" || countryid==null || countryid==undefined) {
		toastr.info("Country Name", "Required Field");
		return false;
	}
	
	if (statename == "" || statename==null || statename==undefined) {
		toastr.info("State Name", "Required Field");
		return false;
	}
		return true;
}