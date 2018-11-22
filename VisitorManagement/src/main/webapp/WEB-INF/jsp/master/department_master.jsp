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
    document.getElementById('departmaster').className="active";
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
                                    <h4 class="card-title">Department Master</h4>
                                </a>
                                <div class="heading-elements">
                                    <ul class="list-inline mb-0">
                                        <li>
                                            <a onclick="openmodal()" class="show-add-detail-form btn btn-primary btn-min-width mr-0 mb-0btn-padding default_bgbtn"><i class="fa fa-plus mr-1"></i> Add Department</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="card-content collapse show">
                                <div class="card-body card-dashboard">
                                    <table class="table table-striped table-bordered zero-configuration" id="myTable">
                                        <thead>
                                        <tr class="default_bgcolor">
                                            <th>Department Name</th>
                                            <th>Company Name</th>
                                            <th>Location Name</th>
                                            <th class="text-center">Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                       <!--  <tr>
                                            <td>D00001</td>
                                            <td>C00001</td>
                                            <td>L00001</td>
                                            <td>Account</td>
                                            <td>Yes</td>
                                            <td class="text-center">
                                              <a class="mr-1 text-success"><i class="fa fa-pencil" aria-hidden="true"></i></a>
                                              <a href="#" class="text-danger"><i class="fa fa-trash" aria-hidden="true"></i></a>
                                          </td>
                                        </tr>
                                        <tr>
                                            <td>D00002</td>
                                            <td>C00001</td>
                                            <td>L00001</td>
                                            <td>Sales</td>
                                            <td>Yes</td>
                                            <td class="text-center">
                                              <a class="mr-1 text-success"><i class="fa fa-pencil" aria-hidden="true"></i></a>
                                              <a href="#" class="text-danger"><i class="fa fa-trash" aria-hidden="true"></i></a>
                                          </td>
                                        </tr>
                                        <tr>
                                            <td>D00003</td>
                                            <td>C00001</td>
                                            <td>L00001</td>
                                            <td>Purchase</td>
                                            <td>Yes</td>
                                            <td class="text-center">
                                              <a class="mr-1 text-success"><i class="fa fa-pencil" aria-hidden="true"></i></a>
                                              <a href="#" class="text-danger"><i class="fa fa-trash" aria-hidden="true"></i></a>
                                          </td>
                                        </tr>
             -->                            </tbody>
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
                                    <h4 class="card-title">Add Department Master</h4>
                                </a>
                                <div class="heading-elements">
                                    <ul class="list-inline mb-0">
                                        <li>
                                            <a class="show-details-table btn btn-primary btn-min-width mr-0 mb-0 btn-padding default_bgbtn"><i class="fa fa-undo mr-1"></i>  Move Back</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="card-content collapse show" id="departmentdata">
                                <div class="card-body">
                                    <form class="form">
                                        <div class="form-body">
                                        <input type="hidden" id="departmentid" name="departmentid">
                                            <div class="row">
                                                <div class="col-md-4">
                                                    <div class="form-group">
                                                        <label>Department Name</label>
                                                        <input type="text" id="deptname" class="form-control" placeholder="Enter Department Name" name="deptname" maxlength="128">
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label for="projectinput2">Select Company</label>
                                                       	 <select name="companyid" id="companyid" class="select2 form-control" data-placeholder="Select Company">
                                                      		<option value="">Select Company</option>
                                                      		<c:forEach var="i" items="${companyList}">
                                                      		 	<option value="${i[0]}">${i[1]}</option>
                                                      		</c:forEach>
                                                        </select>
                                                     </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label for="projectinput2">Select Location</label>
                                                        <select class="select2 form-control" id="locationid" name="locationid" data-placeholder="Select Location">
                                                     	 	<option>Select Location</option>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="col-md-2" id="isactive">
                                                    <div class="form-group">
                                                        <fieldset>
                                                          <div class="custom-control custom-checkbox mt-02">
                                                            <input type="checkbox" class="custom-control-input" checked name="active" id="active" value="1">
                                                            <label class="custom-control-label" for="isactive">Is Active</label>
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

<script src="assets/custom_plugins/js/custom.js" type="text/javascript"></script>
<script src="jspJs/master/departmentmaster.js"  type="text/javascript"></script>
</body>

</html>