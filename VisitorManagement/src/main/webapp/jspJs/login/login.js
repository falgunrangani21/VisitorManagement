$(document).ready(function(){ 
	$("#loginname").focus();
  $("#password").keydown(function(event) {
     if (event.keyCode == 32) {
         event.preventDefault();
     }
  });
});

function checklogin() {

	if (validation()) {
		var loginbean = {};
		$('#logindata').find('input').each(function() {
		 	loginbean[$(this).attr('name')] = $(this).val();
		});

		$.ajax({
					type : "Post",
					data : JSON.stringify(loginbean),
					url : "/visitorManagement/restlogin/checklogin",
					contentType : "application/json",
					success : function(resp) {
						if (resp['success'] != null || resp['success'] != undefined) {
							if(resp['GetToken']!=null || resp['GetToken']!=undefined){
								sessionStorage.setItem('Token', resp['GetToken']);
								window.location.href = "/visitorManagement/visitor";								
							}
							/*sessionStorage.removeItem('Token');
							sessionStorage.clear();*/
							
						} else if (resp['failed'] != null
								|| resp['failed'] != undefined) {
							toastr.info(
									"Login Name And Password does not Match ",
									"ERROR MESSAGE");
						}
					}
				});

	}
}

function validation() {
	var loginname = $("#loginname").val();
	var password = $("#password").val();

	if (loginname == "" || loginname == null || loginname == undefined) {
		toastr.info("Login Name ", "Required Field");
		return false;
	}

	if (password == "" || password == null || password == undefined) {
		toastr.info("Password  ", "Required Field");
		return false;
	}

	return true;
}