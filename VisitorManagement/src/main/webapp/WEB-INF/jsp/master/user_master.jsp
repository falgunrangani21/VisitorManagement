<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html class="loading" lang="en" data-textdirection="ltr">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">

    <title>E5 Visitor Management System</title>

    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Quicksand:300,400,500,700" rel="stylesheet">
    <link href="assets/custom_plugins/fontawesome/css/font-awesome.min.css" rel="stylesheet">

    <!-- BEGIN VENDOR CSS-->
    <link rel="stylesheet" type="text/css" href="assets/css/vendors.min.css">
    <!--Alert Required CSS-->
    <link rel="stylesheet" type="text/css" href="assets/vendors/css/extensions/toastr.css">
    <link rel="stylesheet" type="text/css" href="assets/vendors/css/extensions/sweetalert.css">

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
    <link rel="stylesheet" type="text/css" href="assets/css/style.css">
    <link rel="stylesheet" type="text/css" href="assets/custom_plugins/css/custom.css">


</head>
<body class="vertical-layout vertical-menu 2-columns   menu-expanded fixed-navbar"
      data-open="click" data-menu="vertical-menu" data-col="2-columns">
<!-- fixed-top-->

<jsp:include flush="true" page="/WEB-INF/jsp/common/header.jsp"/>	
<jsp:include flush="true" page="/WEB-INF/jsp/common/menu.jsp"/>

<script language="javascript" type="text/javascript">
    document.getElementById('masters').className="nav-item active";
    document.getElementById('usermaster').className="active";
</script>

<div class="app-content content">
    <div class="content-wrapper">
        <div class="content-header row"></div>
        <div class="content-body">
            <section id="view_details_section">
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-header">
                                <a data-action="collapse">
                                    <h4 class="card-title">User Master</h4>
                                </a>
                                <div class="heading-elements">
                                    <ul class="list-inline mb-0">
                                        <li>
                                            <a onclick="openmodal()" class="show-add-detail-form btn btn-primary btn-min-width mr-0 mb-0btn-padding default_bgbtn"><i class="fa fa-plus mr-1"></i> Add User</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="card-content collapse show">
                                <div class="card-body card-dashboard">
                                    <div class="table-responsive">
                                        <table class="table table-striped table-bordered zero-configuration" id="myTable">
                                            <thead>
                                            <tr class="default_bgcolor">
                                               <th>Profile Image</th>
                                                <th>User Name</th>
                                                <th>Email</th>
                                                <th>Contact</th>
                                                <th>Gender</th>
                                                <th>Country</th>
                                                <th>State</th>
                                                <th>City</th>
                                                <th class="text-center">Action</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                          	</tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <section id="add_details_section" class="input-validation" style="display: none">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                <a data-action="collapse">
                                    <h4 class="card-title">Add User</h4>
                                </a>
                                <div class="heading-elements">
                                    <ul class="list-inline mb-0">
                                        <li>
                                            <a class="show-details-table btn btn-primary btn-min-width mr-0 mb-0 btn-padding default_bgbtn"><i class="fa fa-undo mr-1"></i>  Move Back</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="card-content collapse show" id="userdata">
                                <div class="card-body">
                                    <form class="form" id="mainform" name="mainform"> 
										<input type="hidden" id="userid" name="userid"> 
                                     <div class="form-body">
                                            <div class="row">
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label>User Name</label>
                                                        <input type="text" id="username" class="form-control" placeholder="Enter User Name" name="username" maxlength="128">
                                                    </div>
                                                </div>
      
                                                 <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label for="projectinput2">Select User Type</label>
                                                                <select class="select2 form-control" id="usertype" name="usertype" data-placeholder="Select UserType">
                                                                	<option value="">Select UserType</option>
                                                                   	<option value="U">User</option>
                                                                   	<option value="A">Admin</option>
                                                                   	<option value="Z">System Admin</option>
                                                                   	<option value="S">Super Admin</option>                                                                   	
                                                                </select>
                                                            </div>
                                                </div>


                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label>Role Access Type</label>
                                                        <input type="text" id="roleaccesstype" class="form-control" placeholder="Enter Role Access" name="roleaccesstype" maxlength="1" oninput="inputchar(this)">
                                                    </div>
                                                </div>
                 
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label>Gender</label>
                                                        <div class="input-group">
                                                            <fieldset class="mr-2">
                                                                <div class="custom-control custom-radio">
                                                           
                                                                    <input type="radio" class="custom-control-input" name="gender" id="male" value="m" maxlength="1">
                                                                    <label class="custom-control-label" for="male">Male</label>
                                                                </div>
                                                            </fieldset>
                                                            <fieldset>
                                                                <div class="custom-control custom-radio">
                                                                    <input type="radio" class="custom-control-input" name="gender" id="female" value="f" maxlength="1">
                                                                    <label class="custom-control-label" for="female">Female</label>
                                                                </div>
                                                            </fieldset>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-9">
                                                    <div class="row">
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label>Email</label>
                                                                <input type="text" id="email" class="form-control" placeholder="Enter Email" name="email" maxlength="128">
                                                            </div>
                                                        </div>

                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label>Contact Number</label>
                                                                <input type="text" id="contactnumber" class="form-control text-right" placeholder="Enter Contact Number" name="contactnumber" maxlength="10" oninput="this.value = this.value.replace(/[^0-9.]/g, ''); this.value = this.value.replace(/(.*)\./g,'$1');">
                                                            </div>
                                                        </div>

                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label>Profile</label>
                                                                <input type="file" id="userprofile" class="form-control text-right" placeholder="Upload User Profile" name="userprofile">
                                                                <input type="hidden" id="profileurl" name="profileurl">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label for="projectinput2">Select Country</label>
                                                                <select class="select2 form-control" id="countryid" name="countryid" data-placeholder="Select Country">
                                                                	<option>Select Country</option>
                                                                    <c:forEach var="i" items="${countryList}">
			                                                      	 	<option value="${i[0]}">${i[1]}</option>
			                                                      	 </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label for="projectinput2">Select State</label>
                                                                <select class="select2 form-control" id="stateid" name="stateid" data-placeholder="Select State">
                                                                    
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label for="projectinput2">Select City</label>
                                                                <select class="select2 form-control" id="cityid" name="cityid" data-placeholder="Select City">
                                                                   
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label>Login Name</label>
                                                                <input type="text" id="loginname" class="form-control" placeholder="Enter Login Name" name="loginname" maxlength="128">
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label>Password</label>
                                                                <input type="text" id="password" class="form-control" placeholder="Enter Password" name="password" maxlength="16">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
											<!-- <a data-toggle="modal" data-target="#primary" class="user-profile text-center color-custom-demo">
											                                                            <img src="" class="letterpic" alt="Savan" title="Savan Hanshaliya">
											                                                        </a>
											
											<a data-toggle="modal" data-target="#primary" class="user-profile text-center color-custom-demo">
											                                                            <img src="assets/images/portrait/medium/avatar-m-4.png" class="" alt="Shalini" title="Shalini Mishra">
											                                                        </a> -->
                                                <div class="col-md-3">
                                                    <div class="user-profile-preview text-center">
                                                        <div class="card-body p-0">
                                                            <label class="float-left">Uploaded Image</label>
                                                            <img src="assets/images/face.png" class="height-150 img-fluid" id="Image">

                                                           <div class="image-action pt-1">
                                                               <a class="badge badge-info text-white" id="changeImg"><span>Change</span></a>
                                                               <a class="badge badge-danger text-white" id="removeImg"><span>Remove</span></a>
                                                           </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row" id="isactive">
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <fieldset>
                                                            <div class="custom-control custom-checkbox">
                                                                <input type="checkbox" class="custom-control-input" name="active" id="active" value="1" checked >
                                                                <label class="custom-control-label" for="active">Is Active</label>
                                                            </div>
                                                        </fieldset>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                        <div class="form-actions">
                                            <div class="float-right">
                                                <a onclick="saveData()" class="btn btn-success btn-min-width mr-1 mb-1 text-white btn-padding">Save</a>
                                                <a class="show-details-table btn btn-danger btn-min-width mb-1 text-white btn-padding">Cancel</a>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <div class="modal fade text-left" id="primary" tabindex="-1" role="dialog" aria-labelledby="myModalLabel8" aria-hidden="true" data-backdrop="static" data-keyboard="false">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header bg-primary white">
                            <h4 class="modal-title white" id="myModalLabel8">User Detail</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true" class="text-white">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">

                            <div class="row">
                                <div class="col-md-12">
                                    <div class="media">
                                        <div class="media-left pr-1" id="userimagetxts">
                                            <span class="user-profile text-center color-custom-demo">
                                                <img src="" id="userimagetxt" alt="Shalini" title="Shalini Mishra">
                                            </span>
                                            <!--<span class="user-profile text-center color-custom-demo">
                                                <img src="" class="letterpic" alt="avatar" title="Savan Hanshaliya">
                                            </span>-->
                                        </div>
                                        <div class="media-body w-100">
                                            <h3 class="media-heading mb-0" id="usernametxt"><!-- Shalini Mishra -->
                                            </h3>
                                            <p class="font-medium-01 mb-0 text-muted">
                                                <span class="mr-1"><i class="fa fa-envelope-o lighten-3" id="emailtxt" ></i> </span>
                                                <span><i class="fa fa-mobile-phone lighten-3" id="contacttxt" ></i> </span>
                                            </p>
                                        </div>
                                    </div>

                                    <div class="card border-left-warning border-left-2 mt-2 user-details">
                                        <div class="card-body">
                                            <span class="badge badge-danger badge-square user-id">
                                                <span id="useridtxt">User Id: U6785</span>
                                            </span>
                                            <dl class="row mb-0">
                                                <dt class="col-sm-3" >Gender</dt>
                                                <dd class="col-sm-9" id="gendertxt">Female</dd>
                                            </dl>
                                            <dl class="row mb-0">
                                                <dt class="col-sm-3">User Type</dt>
                                                <dd class="col-sm-9" id="usertypetxt">Admin</dd>
                                            </dl>
                                            <dl class="row mb-0">
                                                <dt class="col-sm-3">Role Access</dt>
                                                <dd class="col-sm-9" id="roleaccesstypetxt">A description list is perfect for defining terms.</dd>
                                            </dl>
                                          <!--   <dl class="row mb-0">
                                                <dt class="col-sm-3">Country</dt>
                                                <dd class="col-sm-9">India</dd>
                                            </dl>
                                            <dl class="row mb-0">
                                                <dt class="col-sm-3">State</dt>
                                                <dd class="col-sm-9">Gujarat</dd>
                                            </dl>
                                            <dl class="row mb-0">
                                                <dt class="col-sm-3">City</dt>
                                                <dd class="col-sm-9">Surat</dd>
                                            </dl> -->
                                            <dl class="row mb-0">
                                                <dt class="col-sm-3" >Login Name</dt>
                                                <dd class="col-sm-9" id="loginnametxt"></dd>
                                            </dl>

                                            <dl class="row mb-0">
                                                <dt class="col-sm-3">&nbsp;</dt>
                                                <dd class="col-sm-9">
                                                    <div class="badge border-left-primary badge-striped user-access-details mb-1">Last Login: <span>25-07-2018 12:45:56 PM</span></div>

                                                    <div class="badge border-left-danger badge-striped user-access-details">Password Updated on: <span>25-07-2018 12:45:56 PM</span></div>
                                                </dd>
                                            </dl>

                                        </div>


                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<script src="assets/custom_plugins/js/footer.js"></script>

<!--Jquery.js Required-->
<script src="assets/vendors/js/vendors.min.js" type="text/javascript"></script>

<!--Alert Reuired JS-->
<script src="assets/sweetalert/sweetalert.min.js" type="text/javascript"></script>
<script src="assets/vendors/js/extensions/toastr.min.js" type="text/javascript"></script>

<!--Js Required for Menu-->
<script src="assets/js/core/app-menu.min.js" type="text/javascript"></script>
<script src="assets/js/core/app.min.js" type="text/javascript"></script>

<!--Other Js to be added here as needed on particular page-->
<script src="assets/vendors/js/tables/datatable/datatables.min.js" type="text/javascript"></script>
<script src="assets/js/scripts/tables/datatables/datatable-basic.js" type="text/javascript"></script>

<script src="assets/vendors/js/forms/select/select2.full.min.js" type="text/javascript"></script>
<script src="assets/js/scripts/forms/select/form-select2.min.js" type="text/javascript"></script>

<!--Alert Custom JS-->
<script src="assets/js/scripts/extensions/sweet-alerts.min.js" type="text/javascript"></script>
<script src="assets/js/scripts/extensions/toastr.min.js" type="text/javascript"></script>

<script src="assets/custom_plugins/letterpic/jquery.letterpic.js" type="text/javascript"></script>

<script src="assets/custom_plugins/js/custom.js" type="text/javascript"></script>
<script src="assets/js/scripts/popover/popover.min.js" type="text/javascript"></script>
<!-- my js -->
<script src="jsUtil/Validationevents.js" type="text/javascript"></script>
<script type="text/javascript" src="jsUtil/fileUploadUtil.js"></script>
<script src="jspJs/master/usermaster.js"  type="text/javascript"></script>
</body>

</html>