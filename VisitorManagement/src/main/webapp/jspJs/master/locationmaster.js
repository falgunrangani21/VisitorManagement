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
	'sAjaxSource' : '/visitorManagement/restlocationmaster/searchBranchMasterByAjax',
	'aoColumns' : [ {
		'data' : 'companyname',
		'defaultContent' : ""
	}, {
		'data' : 'branchname',
		'defaultContent' : ""
	}, {
		'data' : 'email',
		'defaultContent' : ""
	}
	, {
		'data' : 'contactnumber',
		'defaultContent' : ""
	}
	, {
		'data' : 'Address',
		'defaultContent' : ""
	}
	, {
		'data' : 'fax',
		'defaultContent' : ""
	}
	, {
		'data' : 'contactperson',
		'defaultContent' : ""
	}
	, {
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

	$('#locationid').val('');
	$('#branchname').val('');
	$('#companyid').val('').trigger('change');
	$('#email').val('');
	$('#contactno').val('');
	$('#fax').val('');
	$('#stateid').val('').trigger('change');
	$('#cityid').val('').trigger('change');
	$('#countryid').val('').trigger('change');
	$('#address').val('');
	$("#personTableid tbody").empty();
	$('#add_details_section').show();
}

function saveData() {
	
	if(validation()){
		
		var locationbean = {};	
		$('#locationdata').find('input').each(function() {
			locationbean[$(this).attr('name')] = $(this).val();
		});
		locationbean['companyid'] = $("#companyid").val();
		locationbean['countryid'] = $("#countryid").val();
		locationbean['stateid'] = $("#stateid").val();
		locationbean['cityid'] = $("#cityid").val();
		locationbean['address'] = $("#address").val();
	
		// personbean=tableToJson("#personTableid tr");
		// convert table data into json format
	
		var head = [], i = 0, personData = [],rowObj;
		$.each($("#personTableid thead th"), function() {
			head[0] = "personname";
			head[1] = "designation";
			head[2] = "contactnumber";
			head[3] = "personid";
	
		});
	
		$.each($("#personTableid tbody tr"), function() {
			var $row = $(this);
	
			/*i = 0;
			$.each($("td", $row), function() {
				var $col = $(this);
				rowObj[head[i]] = $col.text();
				rowObj[head[3]] = $("#personidtxt").val();
				//i++;
			})*/
			rowObj={};
			$.each($("td input",$row), function() {
				var inputtype = $(this).attr('type');
				var ids = 'none';
				var values = 'none';
				ids = $(this).attr('name');
				values = $(this).val();				
				rowObj[ids] = values;
			})
			
			personData.push(rowObj);			
			console.log(personData)
		});
		
		$.ajax({
			type : "post",
			data : JSON.stringify({personmaster: personData ,locationmaster:locationbean}),
			url : "/visitorManagement/restlocationmaster/saveData",
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
					toastr.info("" + '' + resp['DUPLICATE'] + "", "Message..!");
				}
	
				else if (resp['ERRORMASSAGE'] != null
						|| resp['ERRORMASSAGE'] != undefined) {
					toastr.info("" + '' + resp['ERRORMASSAGE'] + "", "Message..!");
				}
				$('#add_details_section').hide();
			}
		});
	}
}

function validation() {

	var branchname = $('#branchname').val();
	var companyid = $('#companyid').val();
	var email = $('#email').val();
	var contactno = $('#contactno').val();
	var fax = $('#fax').val(); 
	var countryid = $('#countryid').val();
	var stateid = $('#stateid').val();
	var cityid = $('#cityid').val();
	var address = $('#address').val();
	
	if (branchname == "" || branchname==null || branchname==undefined) {
		toastr.info("Enter Branch Name", "Required Field");
		return false;
	}
	
	if (companyid == "" || companyid==null || companyid==undefined) {
		toastr.info("Select Company", "Required Field");
		return false;
	}
	
	if (email == "" || email==null || email==undefined) {
		toastr.info("Enter Email", "Required Field");
		return false;
	}
	
	if(!(null==email || !email.trim()) && !filter.test(email)){
		toastr.info("Email is Not Valid!!","Required Field");
	    $("#email").focus();
	    return false;
	}
	
	if(contactno=="" || contactno==null || contactno==undefined){
		toastr.info("Enter Contact Number","Required Field");
		return false;
	}
	
	if(contactno.length<10){
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

	
	if (address == "" || address==null || address==undefined) {
		toastr.info("Enter Address", "Required Field");
		return false;
	}
		return true;

}

function createPerson() {
	var personname = $("#personname").val();
	var designation = $("#designation").val();
	var contactnumber = $("#contactnumber").val();
	var personid = $("#personid").val(); //will get personid -1
	
	var rowhtml = addPerson(personid, personname, designation, contactnumber);
	/******************************add new person***********************************/
	if(editflag!=true && row_index==""){
		
		if (null == personid || personid == "-1" || personid == -1 || undefined == personid) {	
			$("#personTableid").append(rowhtml);
		}  else {
			document.getElementById("personTableid").deleteRow(parseInt(personid) + 1);
			$('#personTableid > tbody > tr').eq(personid - 1).after(rowhtml);
		}
	}
	/******************************edit person***********************************/
	else{
		
		var str ="<a class=\"mr-1 text-success\" onclick=\"editPerson(this)\"><i class=\"fa fa-pencil\" aria-hidden=\"true\"></i></a>"
			+ "<a href=\"#\" class=\"text-danger\" onclick=\"deletePerson(this)\"><i class=\"fa fa-trash\" aria-hidden=\"true\"></i></a>";
		
		
		$('#personTableid tr:eq('+row_index+') td:eq(0)').html("<input type='hidden' id='personnametxt' name='personname' value="+personname+">"+personname);
		$('#personTableid tr:eq('+row_index+') td:eq(1)').html("<input type='hidden' id='designationtxt' name='designation' value="+designation+">"+designation);
		$('#personTableid tr:eq('+row_index+') td:eq(2)').html("<input type='hidden' id='contactnumbertxt' name='contactnumber' value="+contactnumber+">"+contactnumber);
		$('#personTableid tr:eq('+row_index+') td:eq(3)').html(str);
		
	}
	//delete data["undefined"];
	editflag=false;
	row_index="";
	clearPersonModal();
	$("#primary").modal('hide');
}

function addPerson(personid, personname, designation, contactnumber) {
	var str = "";
	str += "<tr>"
			+ "<td>"
			+ personname
			+ " "
			+ createHiddenFiled("personid", personid)
			+ createHiddenFiled("personname", personname)
			+ "</td>"
			+ "<td>"
			+ designation
			+ " "
			+ createHiddenFiled("designation", designation)
			+ " </td>"
			+ "<td>"
			+ contactnumber
			+ "  "
			+ createHiddenFiled("contactnumber", contactnumber)
			+ "</td>"
			+ "<td class=\"text-center\">"
			+ "<a class=\"mr-1 text-success\" onclick=\"editPerson(this)\"><i class=\"fa fa-pencil\" aria-hidden=\"true\"></i></a>"
			+ "<a href=\"#\" class=\"text-danger\" onclick=\"deletePerson(this)\"><i class=\"fa fa-trash\" aria-hidden=\"true\"></i></a>"
			+ "</td>" + "</tr>";
	return str;
}

var editflag;
var row_index;
function editPerson(thiss) {
	
	var $this = $(thiss);
	row_index =  $($this.closest('tr')).index()+1;
	
    var personnametxt = $($this.parents('tr')).find('input[id=personnametxt]').val();
	alert("personnametxt"+personnametxt)
    var designationtxt = $($this.parents('tr')).find('input[id=designationtxt]').val();
    var contactnumbertxt = $($this.parents('tr')).find('input[id=contactnumbertxt]').val();
	
	$("#personname").val(personnametxt);
	$("#designation").val(designationtxt);
	$("#contactnumber").val(contactnumbertxt);
	editflag=true;
	$("#primary").modal('show');
}

function deletePerson(obj) { //delete person on add & edit both
	
	var $this = $(obj);
	$this.closest('tr').remove();
}

function createHiddenFiled(idName, value) {
	var str = "<input type ='hidden' id = '" +idName+"txt' name ='" + idName
			+ "' value ='" + value + "'>";
	return str;
}

function clearPersonModal() {
	$("#personid").val("-1");
	$("#personname").val('');
	$("#designation").val('');
	$("#contactnumber").val('');
}

function openPersonModel() {
	editflag=false;
	row_index="";
	var personname = $("#personname").val('');
	var designation = $("#designation").val('');
	var contactnumber = $("#contactnumber").val('');
	$("#primary").modal('show');
}
//for getting person Details #contact_person_detail
function getPersonDetails(locationid)
{
	if (locationid != "" || locationid != null || locationid != undefined) {

		$.ajax({
		url : "/visitorManagement/restlocationmaster/getPersonDetails",
		method : "post",
		dataType : 'json',
		data : {
			locationid : locationid
		},
		success : function(response) {
				str="";
				if(response.personList!=null)
				{
					for(var i=0;i<response.personList.length;i++)
					{	
						str+="<tr>"
	                          +"<td>"+response.personList[i][1]+"</td>"
	                          +"<td>"+response.personList[i][2]+"</td>"
	                          +"<td>"+response.personList[i][3]+"</td>"
	                        "</tr>";
					}
					
					$("#personGridTable tbody").empty().append(str);
				}	

			$("#contact_person_detail").modal('show');
		},
		error : function(msg) {
		console.log("Error: " + msg);
		}

		});
		}
	
	}

var stateidtemp=null;
var cityidtemp=null;
function editData(locationid,branchname,companyid,email,contactno,fax,countryid,stateid,cityid,address)
{
	stateidtemp=stateid;
	cityidtemp=cityid;
	$("#locationid").val(locationid);
	$('#branchname').val(branchname);
	$('#companyid').val(companyid).trigger('change');
	$('#email').val(email);
	$('#contactno').val(contactno);
	$('#fax').val(fax);
	$("#countryid").val(countryid).trigger('change')
	$("#stateid").val(stateid).trigger('change');
	$("#cityid").val(cityid).trigger('change');
	$('#address').val(address);
	
	$.ajax({
		url : "/visitorManagement/restlocationmaster/getPersonDetails",
		method : "post",
		dataType : 'json',
		data : {
			locationid : locationid
		},
		success : function(response) {
				if(response.personList!=null)
				{
					str="";
					for(var i=0;i<response.personList.length;i++)
					{	
					str+="<tr>"
                          +"<td>" +
                          		"<input type='hidden' id='personidtxt' name='personid' value="+response.personList[i][0]+">" +
                          				"<input type='hidden' id='personnametxt' name='personname' value="+response.personList[i][1]+">"+response.personList[i][1]+"</td>"
                          +"<td><input type='hidden' id='designationtxt' name='designation' value="+response.personList[i][2]+">"+response.personList[i][2]+"</td>"
                          +"<td><input type='hidden' id='contactnumbertxt' name='contactnumber' value="+response.personList[i][3]+">"+response.personList[i][3]+"</td>"
                          + "</td>"
              			+ "<td class=\"text-center\">"
              			+ "<a class=\"mr-1 text-success\" onclick=\"editPerson(this)\"><i class=\"fa fa-pencil\" aria-hidden=\"true\"></i></a>"
              			+ "<a href=\"#\" class=\"text-danger\" onclick=\"deletePerson(this)\"><i class=\"fa fa-trash\" aria-hidden=\"true\"></i></a>"
              			+ "</td>" +
                        "</tr>";
					}
					
					$("#personTableid tbody").empty().append(str);
				}	

		},
		error : function(msg) {
		console.log("Error: " + msg);
		}

		});
	
	$('#add_details_section').show();
	$('#view_details_section').hide();
}

function deleteData(locationid)
{
	if (null != locationid && locationid.trim().length > 0) {
			swal({
				title : "Are you sure?",
				text : " Delete Location Master Details ",
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
						url : "/visitorManagement/restlocationmaster/deleteData",
						type : "post",
						dataType : "text",
						data : {
							locationid : locationid,
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

$("#countryid").on('change',function(){

	var countryid = $("#countryid").val();
	if (countryid != "" || countryid != null || countryid != undefined) {

	$.ajax({
	url : "/visitorManagement/restlocationmaster/getStateData",
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

$(document).ajaxComplete(function() {
	  $('[data-toggle="popover"]').popover()
});