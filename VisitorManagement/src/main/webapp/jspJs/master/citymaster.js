var jwttoken = sessionStorage.getItem("Token");
$('#myTable').DataTable({
	'destroy' : true,
	'bProcessing' : true,
	'bServerSide' : true,
	'sort' : 'position',
	'bStateSave' : false,
	'iDisplayStart' : 0,
	'fnDrawCallback' : function() {
	},
	'sAjaxSource' : '/visitorManagement/visitors/restcitymaster/searchCitymasterByAjax',
	"fnServerData" : function(sSource, aoData, fnCallback,
			oSettings) {
		oSettings.jqXHR = $.ajax({
			"dataType" : 'json',

			"type" : "POST",
			"url" : sSource,
			"data" : aoData,
			headers : {
				'Authorization' : "Authorization",
				'Token' : "token " + jwttoken + "",
			},
			"success" : function(json) {
				fnCallback(json);
			}
		});

	},
	'aoColumns' : [ {
		'data' : 'cityname',
		'defaultContent' : ""
	}, {
		'data' : 'stdcode',
		'defaultContent' : ""
	}, {
		'data' : 'pincode',
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
		'targets' : [ 1, 2 ]
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
	
	$('#cityid').val('');
	$("#countryid").val('').trigger('change');
	$("#stateid").val('').trigger('change');
	$("#cityname").val('');
	$("#stdcode").val('');
	$("#pincode").val('');
	$("#add_details_section").show();
	
}
var stateidtemp=null;
function editData(cityid, cityname, stdcode, pincode, countryid, stateid) {

	stateidtemp=stateid;
	$("#cityid").val(cityid);
	$("#cityname").val(cityname);
	$("#stdcode").val(stdcode);
	$("#pincode").val(pincode);
	$("#countryid").val(countryid).trigger('change')
	$("#stateid").val(stateidtemp).trigger('change');
	$("#add_details_section").show();
	$("#view_details_section").hide();

}

$("#countryid").on('change',function(){
	
	var countryid = $("#countryid").val();
	
	if (countryid != "" || countryid != null || countryid != undefined) {
	
		$.ajax({
		url : "/visitorManagement/visitors/restcitymaster/getStateData",
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
function saveData() {
	
	if (validation()) {
		var citymasterbean = {};
		$('#citydata').find('input').each(function() {
			citymasterbean[$(this).attr('name')] = $(this).val();
		});
		
		
		citymasterbean['countryid'] = $("#countryid").val();
		citymasterbean['stateid'] = $("#stateid").val();
		
		$.ajax({
			type : "post",
			data : JSON.stringify(citymasterbean),
			url : "/visitorManagement/visitors/restcitymaster/saveData",
			contentType : "application/json",
			headers : {
				'Authorization' : "Authorization",
				'Token' : "token " + jwttoken + "",
			},
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
	console.log("token "+jwttoken);
}

function deleteData(cityid) {

	if (null != cityid && cityid.trim().length > 0) {
		swal({
			title : "Are you sure?",
			text : " Delete City Master Details ",
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
					url : "/visitorManagement/restcitymaster/visitors/deleteData",
					headers : {
						'Authorization' : "Authorization",
						'Token' : "token " + jwttoken + "",
					},
					type : "post",
					dataType : "text",
					data : {
						cityid : cityid,

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
	var stateid = $('#stateid').val();
	var cityname = $('#cityname').val();
	
	if (countryid == "" || countryid==null || countryid==undefined) {
		toastr.info("Country Name", "Required Field");
		return false;
	}
	
	if (stateid == "" || stateid==null || stateid==undefined) {
		toastr.info("State Name", "Required Field");
		return false;
	}
	
	if (cityname == "" || cityname==null || cityname==undefined) {
		toastr.info("City Name", "Required Field");
		return false;
	} 
	return true; 
}