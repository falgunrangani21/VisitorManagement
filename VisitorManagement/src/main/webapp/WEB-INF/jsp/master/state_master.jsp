<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <link rel="stylesheet" type="text/css" href="assets/css/style.css">
    <link rel="stylesheet" type="text/css" href="assets/custom_plugins/css/custom.css">


</head>
<body class="vertical-layout vertical-menu 2-columns   menu-expanded fixed-navbar"
   data-open="click" data-menu="vertical-menu" data-col="2-columns">

<jsp:include flush="true" page="/WEB-INF/jsp/common/header.jsp"/>	
<jsp:include flush="true" page="/WEB-INF/jsp/common/menu.jsp"/>

<script language="javascript" type="text/javascript">
    document.getElementById('masters').className="nav-item active";
    document.getElementById('citymaster').className="active";
</script> 

<div class="app-content content">
    <div class="content-wrapper">
        <div class="content-header row">
        </div>
        <div class="content-body">

            <section id="view_details_section">
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-header">
                                <a data-action="collapse">
                                    <h4 class="card-title">State List</h4>
                                </a>
                                <div class="heading-elements">
                                    <ul class="list-inline mb-0">
                                        <li>
                                            <a onclick="openmodal()" class="show-add-detail-form btn btn-primary btn-min-width box-shadow-1 mr-1 mb-1 text-white"><i class="fas fa-plus mr-1"></i> Add State</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="card-content collapse show">
                                <div class="card-body card-dashboard">
                                    <table class="table table-striped table-bordered zero-configuration" id="myTable">
                                        <thead>
                                        <tr>
                                            <th>State Name</th>
                                            <th>Action</th>
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
            </section>

            <section id="add_details_section" class="input-validation" style="display: none">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                <a data-action="collapse">
                                    <h4 class="card-title">Add State Details</h4>
                                </a>
                                <div class="heading-elements">
                                    <ul class="list-inline mb-0">
                                        <li>
                                            <a class="show-details-table btn btn-primary btn-min-width box-shadow-1 mr-1 mb-1 text-white"><i class="fas fa-undo mr-1"></i>  Move Back</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="card-content collapse show" id="statedata">
                                <div class="card-body">
                                    <form class="form" name="statemaster" id="statemaster">
                                     <input type="hidden" name="stateid" id="stateid">
                                        <div class="form-body">
                                            <div class="row">
                                                <div class="col-md-4">
                                                    <div class="form-group">
                                                        <label for="projectinput1">Select Country</label>
                                                        <select name="countryid" id="countryid" class="select2 form-control" data-placeholder="Select Country">
                                                      	<c:forEach var="i" items="${countryList}">
                                                      	 	<option value="${i[0]}">${i[1]}</option>
                                                      	 </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-md-4">
                                                    <div class="form-group">
                                                        <label for="projectinput3">Enter State Name</label>
                                                        <input type="text" name="statename" id="statename" class="form-control" placeholder="Enter State Name" maxlength="64" oninput="inputchar(this)">
                                                    </div>
                                                </div>
                                     	   </div>
                                     </div>
                                        <div class="form-actions">
                                            <div class="float-right">
                                                <a class="btn btn-success btn-min-width box-shadow-1 mr-1 mb-1 text-white" onclick="saveData()">Save</a>
                                                <a class="show-details-table btn btn-danger btn-min-width box-shadow-1 mb-1 text-white">Cancel</a>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

        </div>
    </div>
</div>

<footer class="footer footer-static footer-light navbar-border navbar-shadow">
    <p class="clearfix blue-grey lighten-2 text-sm-center mb-0 px-2">
        <span class="float-md-left d-block d-md-inline-block">Copyright &copy; 2018 Emerging Five , All rights reserved. </span>
    </p>
</footer>


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

<script src="assets/custom_plugins/js/custom.js" type="text/javascript"></script>

<!-- my js -->
<script src="jsUtil/Validationevents.js" type="text/javascript"></script>
<script src="jspJs/master/statemaster.js"  type="text/javascript"></script>
</body>

<!-- Mirrored from pixinvent.com/modern-admin-clean-bootstrap-4-dashboard-html-template/html/ltr/vertical-menu-template/index.html by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 21 Jul 2018 05:47:10 GMT -->
</html>