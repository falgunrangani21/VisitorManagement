<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login V2</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Quicksand:300,400,500,700" rel="stylesheet">
    <link href="assets/custom_plugins/fontawesome/css/fontawesome-all.min.css" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="assets/css/vendors.min.css">

	 <!--Alert Reuired CSS-->
    <link rel="stylesheet" type="text/css" href="assets/vendors/css/extensions/toastr.css">
    <link rel="stylesheet" type="text/css" href="assets/sweetalert/sweetalert.css">
    
    <!--Datatable CSS-->
    <link rel="stylesheet" type="text/css" href="assets/vendors/css/tables/datatable/datatables.min.css">
    <link rel="stylesheet" type="text/css" href="assets/vendors/css/forms/selects/select2.min.css">
    <!-- BEGIN MODERN CSS-->
    <link rel="stylesheet" type="text/css" href="assets/css/app.min.css">
    <!-- END MODERN CSS-->
    <!-- BEGIN Page Level CSS-->
    <link rel="stylesheet" type="text/css" href="assets/css/core/menu/menu-types/vertical-menu.min.css">
    <link rel="stylesheet" type="text/css" href="assets/css/core/colors/palette-gradient.min.css">
    <link rel="stylesheet" type="text/css" href="assets/vendors/css/cryptocoins/cryptocoins.css">
    <!-- END Page Level CSS-->
    <!-- BEGIN Custom CSS-->
    <link rel="stylesheet" type="text/css" href="assets/login/main.css">
    <link rel="stylesheet" type="text/css" href="assets/css/style.css">
    <link rel="stylesheet" type="text/css" href="assets/custom_plugins/css/custom.css">


</head>
<body>

<div class="limiter" id="logindata">
    <div class="container-login100">
        <div class="wrap-login100">
            <form class="login100-form validate-form" id="login" name="login" method="post">
					<span class="login100-form-title pb-1">
						Login To
					</span>
                    <span class="login100-form-title pb-4">
						<i class="fas fa-font"></i>
					</span>

                    <div class="wrap-input100 validate-input" data-validate ="Valid email is: a@b.c">
                        <input class="input100" type="text" name="loginname" id="loginname">
                        <span class="focus-input100" data-placeholder="Email"></span>
                    </div>

                    <div class="wrap-input100 validate-input" data-validate="Enter password">
						<span class="btn-show-pass">
							<i class="fas fa-eye"></i>
						</span>
                        <input class="input100" type="password" name="password" id="password" onpaste="return false;">
                        <span class="focus-input100" data-placeholder="Password"></span>
                    </div>

                    <div class="container-login100-form-btn">
                        <div class="wrap-login100-form-btn">
                            <div class="login100-form-bgbtn"></div>
                            <button class="login100-form-btn" onclick="checklogin();">
                                Login
                            </button>
                        </div>
                    </div>
                    <div class="text-center pt-3">
                        <span class="txt1"><a class="txt2" href="#" id="forgotPassword">Forgot password</a></span>
                    </div>
                    <div class="text-center pt-3">
                        <span class="txt1">Don't have an account?</span>
                        <a class="txt2" href="#">Sign Up</a>
                    </div>
            </form>
        </div>
    </div>
</div>


<div id="dropDownSelect1"></div>

<script src="assets/vendors/js/vendors.min.js" type="text/javascript"></script>
<!--Alert Reuired JS-->
<script src="assets/sweetalert/sweetalert.min.js" type="text/javascript"></script>
<script src="assets/vendors/js/extensions/toastr.min.js" type="text/javascript"></script>

<script src="assets/login/main.js"></script>

<!-- <!--Alert Custom JS-->
<script src="assets/js/scripts/extensions/sweet-alerts.min.js" type="text/javascript"></script> -->
<script src="assets/js/scripts/extensions/toastr.min.js" type="text/javascript"></script>
<script src="jspJs/login/login.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).keypress(function(e){	
	  	if(e.which == 13) {
	  		checklogin(); 
		}
});

$(function($) {
	$("#forgotPassword").click(function() {
		alert("forgot clicked");
	});
});
</script>
</body>
</html>