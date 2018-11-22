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
    document.getElementById('locationmaster').className="active";
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
                                    <h4 class="card-title">Location Master</h4>
                                </a>
                                <div class="heading-elements">
                                    <ul class="list-inline mb-0">
                                        <li>
                                            <a class="show-add-detail-form btn btn-primary btn-min-width mr-0 mb-0btn-padding default_bgbtn"><i class="fa fa-plus mr-1"></i> Add Location</a>
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
                                            <th>Compnay Name</th>
                                            <th>Branch Name</th>
                                            <th>Email</th>
                                            <th>Contact Number</th>
                                            <th>Address</th>
                                            <th>Fax</th>
                                            <th> Contact Person</th>
                                            <th class="text-center">Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                         <!--    <td>Efive</td>
                                            <td>techEfive</td>
                                            <td>efive@gmail.com</td>
                                            <td>978554782</td>
                                            <td>
                                               <a class="text-info" data-toggle="popover" data-content="Ahmedabad-368388, GUJARAT, India" data-trigger="hover" data-original-title="Address"><i class="fa fa-eye" aria-hidden="true"></i></a>
                                            </td> 
                                            <td>S00001</td>                                           
                                           <td>
											    <a class="badge badge-success badge-square text-white pl-1 pr-1" data-toggle="modal" data-target="#contact_person_detail">4</a>
											</td>
                                            <td class="text-center">
                                              <a class="mr-1 text-success"><i class="fa fa-pencil" aria-hidden="true"></i></a>
                                              <a href="#" class="text-danger"><i class="fa fa-trash" aria-hidden="true"></i></a>
                                          </td> -->
                                        </tr>                                        
                                        </tbody>
                                    </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <form class="form" id="locationmaster" name="locationmaster">
            <section id="add_details_section" class="input-validation" style="display: none">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                <a data-action="collapse">
                                    <h4 class="card-title">Add Location / Branch Master</h4>
                                </a>
                                <div class="heading-elements">
                                    <ul class="list-inline mb-0">
                                        <li>
                                            <a class="show-details-table btn btn-primary btn-min-width mr-0 mb-0 btn-padding default_bgbtn" onclick="openmodal()"><i class="fa fa-undo mr-1"></i>  Move Back</a>
                                        </li>


                                    </ul>
                                </div>
                            </div>
                            <div class="card-content collapse show">
                                <div class="card-body" id="locationdata">
                                   <input type="hidden" name="locationid" id="locationid">
                                        <div class="form-body">
                                            <div class="row">
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label>Branch Name</label>
                                                        <input type="text" id="branchname" class="form-control" placeholder="Enter Branch Name" name="branchname" maxlength="128">
                                                    </div>
                                                </div>

                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label for="projectinput2">Select Company</label>
                                                        <select class="select2 form-control" id="companyid" name="companyid" data-placeholder="Select Company">
                                                        <option value="">Select Company</option>
                                                              <c:forEach var="i" items="${companyList}">
                                                      	 		<option value="${i[0]}">${i[1]}</option>
                                                      	 	 </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label>Email</label>
                                                        <input type="text" id="email" class="form-control" placeholder="Enter Email" name="email" maxlength="128">
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label>Contact Number</label>
                                                        <input type="text" id="contactno" class="form-control text-right" placeholder="Enter Contact Number" name="contactno" maxlength="10" oninput="this.value = this.value.replace(/[^0-9.]/g, ''); this.value = this.value.replace(/(.*)\./g,'$1');">
                                                    </div>
                                                </div>

                                            </div>

                                            <div class="row">
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label>Fax Number</label>
                                                        <input type="text" id="fax" class="form-control text-right" placeholder="Enter Fax Number" name="fax" maxlength="10"  oninput="this.value = this.value.replace(/[^0-9.]/g, ''); this.value = this.value.replace(/(.*)\./g,'$1');">
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label for="projectinput2">Select Country</label>
                                                        <select class="select2 form-control" id="countryid" name="countryid" data-placeholder="Select Country">
                                                          <option value="">Select Country</option>
                                                          <c:forEach var="i" items="${countryList}">
                                                      	 	<option value="${i[0]}">${i[1]}</option>
                                                      	 </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label for="projectinput2">Select State</label>
                                                        <select class="select2 form-control" id="stateid" name="stateid" data-placeholder="Select State">
                                                         <option value="">Select State</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label for="projectinput2">Select City</label>
                                                        <select class="select2 form-control" id="cityid" name="cityid" data-placeholder="Select City">
                                                            <option value="">Select City</option>
                                                        </select>
                                                    </div>
                                                </div>

                                            </div>
                                            
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label>Address</label>
                                                        <textarea id="address" name="address" class="form-control" style="height: 45px; resize: none;" maxlength="128"></textarea>
                                                    </div>
                                                </div>                                                
                                                <div class="col-md-3">
                                                   
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-12">
                                                    <a class="float-right btn btn-primary btn-min-width mr-0 mb-1 btn-padding default_bgbtn" onclick="openPersonModel()" ><i class="fa fa-plus mr-1"></i>  Add Contact Person</a>
                                                </div>
                                                <div class="col-md-12">
                                                    <table class="table table-striped" id="personTableid">
                                                        <thead>
                                                        <tr>
                                                        	<th>Person Name</th>
                                                            <th>Designation</th>
                                                            <th>Contact Number</th>
                                                            <th>Action</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                       
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-actions">
                                            <div class="float-right">
                                                <a class="btn btn-success btn-min-width mr-1 mb-1 text-white btn-padding" onclick="saveData()">Save</a>
                                                <a class="show-details-table btn btn-danger btn-min-width mb-1 text-white btn-padding">Cancel</a>
                                            </div>
                                        </div>
                                   
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>


            <div class="modal fade text-left" id="primary" tabindex="-1" role="dialog" aria-labelledby="myModalLabel8" aria-hidden="true" data-keyboard="false" data-backdrop="static">
                <div class="modal-dialog modal-sm" role="document">
                    <div class="modal-content">
                        <div class="modal-header bg-primary white">
                            <h4 class="modal-title white" id="myModalLabel8">Add Contact Person</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true" class="text-white">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                         <input type="hidden" name="personid" id="personid" value="-1">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Person Name</label>
                                        <input type="text" id="personname" class="form-control" placeholder="Enter Person Name" name="personname" maxlength="128">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Designation</label>
                                        <input type="text" id="designation" class="form-control" placeholder="Enter Person Designation" name="designation" maxlength="64">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Contact Number</label>
                                        <input type="text" id="contactnumber" class="form-control" placeholder="Enter Contact Number" name="contactnumber" maxlength="10"  oninput="this.value = this.value.replace(/[^0-9.]/g, ''); this.value = this.value.replace(/(.*)\./g,'$1');">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-outline-primary" onclick="createPerson()">Save</button>
                            <button type="button" class="btn grey btn-outline-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="modal fade text-left" id="contact_person_detail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel8" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header bg-primary white">
                            <h4 class="modal-title white" id="myModalLabel8">Contact Person Detail</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true" class="text-white">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-12">
                                   <table class="table table-striped" id="personGridTable">
                                        <thead>
                                            <tr>
                                                <th>Person Name</th>
                                                <th>Designation</th>
                                                <th>Contact Number</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            
										</tbody>
									</table>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn grey btn-outline-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
	   </form>
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
<!--  my js -->
<script src="jspJs/master/locationmaster.js"></script>
</body>

</html>