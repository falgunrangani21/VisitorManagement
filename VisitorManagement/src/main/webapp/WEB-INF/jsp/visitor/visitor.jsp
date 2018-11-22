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

    <link rel="stylesheet" type="text/css" href="assets/custom_plugins/Datedropper/datedropper.css">
    <link rel="stylesheet" type="text/css" href="assets/custom_plugins/Timedropper/timedropper.css">
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
<body class="vertical-layout vertical-menu 2-columns   menu-expanded fixed-navbar" data-open="click" data-menu="vertical-menu" data-col="2-columns">
<!-- fixed-top-->

<!-- <script src="assets/custom_plugins/js/header.js"></script> 
<script src="assets/custom_plugins/js/admin_menu.js"></script> -->
 

<jsp:include flush="true" page="/WEB-INF/jsp/common/header.jsp"/>	
<jsp:include flush="true" page="/WEB-INF/jsp/common/menu.jsp"/>
<script language="javascript" type="text/javascript">
    document.getElementById('visitorhome').className = "nav-item active";
</script>

<div class="app-content content">

    <div class="filter">
        <div class="filter-body">
            <h4 class="text-uppercase mb-0">Visitor Search
                <a class="filter-close" href="#"><i class="ft-x font-medium-3"></i></a>
            </h4>

            <div class="filter-area">
                <ul class="nav nav-tabs nav-underline no-hover-bg">
                    <li class="nav-item">
                        <a class="nav-link active" id="CurrentVisitorsArea" data-toggle="tab" aria-controls="CurrentVisitors" href="#CurrentVisitors" aria-expanded="true">Current Visitors</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="AllVisitorsArea" data-toggle="tab" aria-controls="AllVisitors" href="#AllVisitors" aria-expanded="false">All Visitors</a>
                    </li>
                </ul>
                <div class="tab-content pt-1">
                    <div role="tabpanel" class="tab-pane active" id="CurrentVisitors" aria-expanded="true" aria-labelledby="CurrentVisitorsArea">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>From Time</label>
                                    <input type="text"  name="cvsfromtime" class="form-control input-lg" id="autoswitch" placeholder="Date Dropper">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>To Time</label>
                                    <input type="text"  name="cvstotime" class="form-control input-lg" id="meridians" placeholder="Date Dropper">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group" id="visitorfrom">
                                    <label for="projectinput2">Visitor From </label>
                                    <select class="select2 form-control" data-placeholder="Select Company" id="cvscompanyid" name="cvscompanyid">
                                     <option value="">Select Company</option>
                                       <c:forEach var="i" items="${vcompanyList}" >
                                       		<option value="${i[0]}">${i[1]}</option>
										</c:forEach> 
	                                 </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group" id="meetingperson">
                                    <label for="projectinput2">Meeting With</label>
                                    <select class="select2 form-control" data-placeholder="Select Employee" id="cvsemployeeid" name="cvsemployeeid">
                                    <option value="">Select Employee</option>
                                         	<c:forEach var="i" items="${employeeList}">
                                     		   <option value="${i[0]}">${i[1]}</option>
                                        	</c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <a onclick="currentVisitorSearch()" class="btn btn-success btn-min-width box-shadow-2 mb-1 text-white float-right">Search</a>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane" id="AllVisitors" aria-labelledby="AllVisitorsArea">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group" id="visitorName">
                                    <label for="projectinput2">Visitor Name</label>
                                    <select class="select2 form-control" data-placeholder="Select Visitor" id="avvisitorid" name="avvisitorid">
                                     <option value="">Select Visitor</option>
                                       <c:forEach var="i" items="${vlocationList}">
											<option value="${i[0]}">${i[2]}</option>
										</c:forEach> 
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group" id="Location_Search">
                                    <label for="projectinput2">Location</label>
                                    <select class="select2 form-control" data-placeholder="Select Location" id="avlocationid" name="avlocationid">
                                    <option value="">Select Location</option>
                                       <c:forEach var="i" items="${vlocationList}">
											<option value="${i[0]}">${i[1]}</option>
										</c:forEach> 
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group" id="meetingperson_Searching">
                                    <label for="projectinput2">Meeting With</label>
                                    <select class="select2 form-control" data-placeholder="Select Employee" id="avemployeeid" name="avemployeeid">
                             		<option value="">Select Employee</option>
                                         	<c:forEach var="i" items="${employeeList}">
                                     		   <option value="${i[0]}">${i[1]}</option>
                                        	</c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                            		 <label for="projectinput2">Card Number</label>
                            		 <select class="select2 form-control" data-placeholder="Select Card" id="avcardid" name="avcardid">
                             			<option value="">Select Card</option>
                                         	<c:forEach var="i" items="${vcardList}">
                                     		   <option value="${i[0]}">${i[1]}</option>
                                        	</c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <a onclick="allVisitorSearch()" class="btn btn-success btn-min-width box-shadow-2 mb-1 text-white float-right">Search</a>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>
    </div>

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
                                    <h4 class="card-title">Current Visitors List</h4>
                                </a>
                                <div class="heading-elements">
                                    <ul class="list-inline mb-0">
                                        <li>
                                            <a class="show-filter btn btn-primary btn-min-width mr-0 mb-0btn-padding default_bgbtn"><i class="fa fa-search mr-1"></i> Search Visitor</a>
                                        </li>
                                        <li>
                                            <a onclick="openmodal()" class="show-add-detail-form btn btn-primary btn-min-width mr-0 mb-0btn-padding default_bgbtn"><i class="fa fa-plus mr-1"></i> New Visitor</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="card-content collapse show">
                                <div class="card-body card-dashboard">

                                    <div class="table-responsive">
                                        <table class="table table-hover table-xl mb-0 dashboard-table" id="currentvisitors">
                                            <tbody>
                                            <c:forEach var="i" items="${currentVisitorList}">
                                                <tr>
                                                    <td class="text-truncate">
                                                    <input type="hidden" id="vids" name="vids" value="${i[0]}">
                                                        <div class="row">
                                                            <div class="col-lg-5 col-md-5 col-sm-12 col-xs-12">
                                                                <div class="media">
                                                                    <div class="media-left pr-1">
                                                                        <span class="user-profile text-center color-custom-demo">
	                                                                         <c:if test="${not empty i[4]}">
	                                                                            <img src="${serverpath}${i[4]}" class="" alt="${i[2]}" title="${i[2]}">
	                                                                        </c:if>
	                                                                         <c:if test="${empty i[4]}" >
	                                                                            <img src="" class="letterpic" alt="${i[2]}" title="${i[2]}">
	                                                                        </c:if>  
                                                                         </span>
                                                                        <!--<span class="user-profile text-center color-custom-demo">
                                                                            <img src="" class="letterpic" alt="avatar" title="Savan Hanshaliya">
                                                                        </span>-->
                                                                    </div>
                                                                    <div class="media-body w-100">
                                                                        <h3 class="media-heading mb-0">${i[2]}
                                                                            <span id="visid" class="badge badge-info badge-square visitor-number">
                                                                             	${i[7]}
                                                                            </span>
                                                                        </h3>
                                                                        <p class="font-medium-01 mb-0 text-muted">
                                                                            <span class="mr-1"><i class="fa fa-envelope-o lighten-3"></i> ${i[5]}</span>
                                                                            <span><i class="fa fa-mobile-phone lighten-3"></i> ${i[6]}</span>
                                                                        </p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                                                                <dl class="row mb-0" id="startTimer">
                                                                    <dt class="col-sm-6">
                                                                        <a onclick="checkIn('${i[0]}')" id="checkinid" class="checkin-out-badge startButton${i[0]}">
                                                                            <span id="checkInTime${i[0]}">Check In</span>
                                                                        </a>
                                                                        <a onclick="checkOut('${i[0]}')"  class="checkin-out-badge stopButton${i[0]}">
                                                                            <span id="checkOutTime${i[0]}">Check Out</span>
                                                                        </a>
                                                                    </dt>
                                                                  <%--   <dd class="col-sm-6 values${i[0]} vid${i[0]}">00:00:00</dd>  --%>
                                                                </dl>
                                                            </div>
                                                            <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                                                <div class="visitor-compnay-details">
                                                                    <dl class="row">
                                                                        <dt class="col-sm-3">From</dt>
                                                                        <dd class="col-sm-9">${i[12]}</dd>
                                                                    </dl>
                                                                    <dl class="row">
                                                                        <dt class="col-sm-3">Location</dt>
                                                                        <dd class="col-sm-9">${i[1]}</dd>
                                                                    </dl>
                                                                    <dl class="row">
                                                                        <dt class="col-sm-3">To Meet</dt>
                                                                        <dd class="col-sm-9">${i[13]}</dd>
                                                                    </dl>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                                 </c:forEach>
                                       		</tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-header">
                                <a data-action="collapse">
                                    <h4 class="card-title">All Visitors List</h4>
                                </a>
                                <div class="heading-elements">
                                    <ul class="list-inline mb-0">
                                        <li>
                                            <a class="show-filter btn btn-primary btn-min-width mr-0 mb-0btn-padding default_bgbtn"><i class="fa fa-search mr-1"></i> Search Visitor</a>
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
                                                <th>Profile</th>
                                                <th>Visitor Id</th>
                                                <th>Name</th>
                                                <th>Email</th>
                                                <th>Contact No.</th>
                                                <th>From / Type</th>
                                                <th>To Meet</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                           <!--   <tr>
                                                <td class="text-center">
                                                    <a data-toggle="modal" data-target="#primary" class="user-profile text-center color-custom-demo">
                                                        <img src="" class="letterpic" alt="Shivprasad" title="Shivprasad Yadav">
                                                    </a>
                                                </td>
                                                <td>V00001</td>
                                                <td>Shivprasad Yadav</td>
                                                <td>shivprasad@gmail.com</td>
                                                <td>9898980000</td>
                                                <td>Aditya Birla Group Pvt Ltd</td>
                                                <td>Shivprasad Narayan</td>
                                           	 </tr>  -->
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
                                    <h4 class="card-title">Add New Visitor</h4>
                                </a>
                                <div class="heading-elements">
                                    <ul class="list-inline mb-0">
                                        <li>
                                            <a class="show-details-table btn btn-primary btn-min-width mr-0 mb-0 btn-padding default_bgbtn"><i class="fa fa-undo mr-1"></i>  Move Back</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="card-content collapse show" id="visitordata">
                                <div class="card-body">
                                    <form class="form" id="mainform" name="mainform">
                                    <input type="hidden" id="ischeckedin" name="ischeckedin" value=""/> 
                                    <input type="hidden" id="vid" name="vid">
                                        <div class="form-body">
                                            <div class="row">
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label>Visitor Name</label>
                                                        <input type="text" id="vname" class="form-control" placeholder="Enter Name" name="vname" oninput="inputchar(this)">
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                   <div class="form-group">
                                                        <label>Email</label>
                                                        <input type="text" id="vemail" class="form-control" placeholder="Enter Email" name="vemail">
                                                    </div>
                                                </div>

                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label>Contact Number</label>
                                                        <input type="text" id="vcontactnumber" class="form-control text-right" placeholder="Enter Contact Number" name="vcontactnumber"maxlength="10" oninput="this.value = this.value.replace(/[^0-9.]/g, ''); this.value = this.value.replace(/(.*)\./g,'$1');">
                                                    </div>
                                                </div>

                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label>Profile</label>
                                                        <input type="file" id="vphoto" class="form-control text-right" placeholder="Upload User Profile" name="vphoto">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-9">
                                                    <div class="row">
                                                    	<div class="col-md-4">
                                                            <div class="form-group">
                                                                <label>Visitor Belongs to any Company?</label>
                                                                <div class="input-group">
                                                                    <fieldset class="mr-2">
                                                                        <div class="custom-control custom-radio">
                                                                            <input type="radio" class="custom-control-input" checked name="visfromcompany" id="isFromCopmanyYes" value="y">
                                                                            <label class="custom-control-label" for="isFromCopmanyYes">Yes</label>
                                                                        </div>
                                                                    </fieldset>
                                                                    <fieldset>
                                                                        <div class="custom-control custom-radio">
                                                                            <input type="radio" class="custom-control-input" name="visfromcompany" id="isFromCopmanyNo" value="n">
                                                                            <label class="custom-control-label" for="isFromCopmanyNo">No</label>
                                                                        </div>
                                                                    </fieldset>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="col-md-4">
                                                            <div class="form-group" id="CopmanyYes">
                                                                <label for="projectinput2">Select Visitor Company</label>
                                                                <select class="select2 form-control" id="vcompanyid" name="vcompanyid" data-placeholder="Select Visitor Company">
                                                                   <option value="">Select Visitor Company</option>
                                                                	 <c:forEach var="i" items="${vcompanyList}">
										                            	<option value="${i[0]}">${i[1]}</option>
										                           	 </c:forEach> 
                                                                    <option value="O">Others</option>                            
                                                                </select>
                                                            </div>

                                                            <div class="form-group" id="CopmanyNo" style="display: none">
                                                                <label for="projectinput2">Visitor Type</label>
                                                                <input type="text" id="visitortype" class="form-control" placeholder="Enter Visitor Type" name="visitortype">
                                                            </div>
                                                        </div>

                                                          <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label>Location</label>
                                                                <textarea rows="1" id="vlocation" class="form-control" placeholder="Enter Company Address" name="vlocation"></textarea>
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-4">
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
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label for="projectinput2">Select Location</label>
                                                                <select class="select2 form-control" id="locationid" name="locationid" data-placeholder="Select Location">
                                                                <option value="">Select Location</option>
                                                                   <c:forEach var="i" items="${locationList}">
                                                                    	<option value="${i[0]}">${i[1]}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                         <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label for="projectinput2">Select Department</label>
                                                                <select class="select2 form-control" id="departmentid" name="departmentid" data-placeholder="Select Department">
                                                                <option value="">Select Department</option>
                                                                   <c:forEach var="i" items="${departmentList}">
                                                                    	<option value="${i[0]}">${i[1]}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label for="projectinput2">Meeting With</label>
                                                                <select class="select2 form-control" id="employeeid" name="employeeid" data-placeholder="Select Employee">
                                                                <option>Select Employee</option>
                                                                   <c:forEach var="i" items="${employeeList}">
                                                                    	<option value="${i[0]}">${i[1]}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-8">
                                                            <div class="form-group">
                                                                <label>Purpose</label>
                                                                <textarea rows="1" id="purpose" class="form-control" placeholder="Purpose to Visit" name="purpose"></textarea>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-2">
                                                            <div class="form-group">
                                                         		<label for="projectinput2">Select Card No.</label>
                                                                <select class="select2 form-control" id="vpassid" name="vpassid" data-placeholder="Select Card No.">
                                                                   <option value="">Select Card No.</option>
                                                                	 <c:forEach var="i" items="${vcardList}">
										                            	<option value="${i[0]}">${i[1]}</option>
										                           	 </c:forEach> 
                                                                    <option value="O">Others</option>                            
                                                                </select>
                                                           </div>
                                                        </div>     
                                                        <div class="col-md-2">
                                                            <div class="form-group">
                                                                <label>No of Person</label>
                                                                <input type="text" id="noofperson" class="form-control" placeholder="No of Person" name="noofperson" oninput="this.value = this.value.replace(/[^0-9.]/g, ''); this.value = this.value.replace(/(.*)\./g,'$1');">
                                                            </div>
                                                        </div>
                                                        <input type="hidden" id="isvehicle" name="isvehicle">
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label>Has Vehicle ?</label>
                                                                <fieldset>
                                                                    <div class="custom-control custom-checkbox">
                                                                        <input type="checkbox" class="custom-control-input" name="vehicle" id="vehicle">
                                                                        <label class="custom-control-label" for="vehicle">Yes, visitor has Vehicle</label>
                                                                    </div>
                                                                </fieldset>
                                                            </div>
                                                            <div class="form-group vehicle-number-dec">
                                                                <input type="text" id="vehiclenumber" class="form-control" placeholder="Vehicle Number" name="vehiclenumber" maxlength="10">
                                                            </div>
                                                        </div>
														<input type="hidden" id="ismaterial" name="ismaterial">
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label>Has Luggage ?</label>
                                                                <fieldset>
                                                                    <div class="custom-control custom-checkbox">
                                                                        <input type="checkbox" class="custom-control-input" name="ismaterialcarried" id="ismaterialcarried">
                                                                        <label class="custom-control-label" for="ismaterialcarried">Yes, visitor has Luggage</label>
                                                                    </div>
                                                                </fieldset>
                                                            </div>
                                                            <div class="form-group material-number-dec">
                                                                <input type="text" id="materialdeposit" class="form-control" placeholder="Enter Luggage Description" name="materialdeposit">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-md-3">
                                                    <div class="user-profile-preview text-center">
                                                        <div class="card-body p-0">
                                                            <label class="float-left">Uploaded Image</label>
                                                            <img src="assets/images/face.png" class="height-150 img-fluid" alt="Card image" id="Image">

                                                            <div class="image-action pt-1">
                                                                <a class="badge badge-info text-white" id="changeImg"><span>Change</span></a>
                                                                <a class="badge badge-danger text-white" id="removeImg"><span>Remove</span></a>
                                                            </div>
                                                        </div>
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
                            <h4 class="modal-title white" id="myModalLabel8">Visitor Detail</h4>
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
                                                <img src="" id="userimagetxt" class="letterpic" alt="Shalini" title="Shalini Mishra">
                                            </span>
                                            <!--<span class="user-profile text-center color-custom-demo">
                                                <img src="" class="letterpic" alt="avatar" title="Savan Hanshaliya">
                                            </span>-->
                                        </div>
                                        <div class="media-body w-100">
                                            <h3 class="media-heading mb-0" id="vnametxt">
                                                <span class="badge badge-info badge-square visitor-number float-right" id="vpassnumbertxt">
                                                    V 98
                                                </span>
                                            </h3>
                                            
                                            <p class="font-medium-01 mb-0 text-muted">
                                                <span class="mr-1"><i class="fa fa-envelope-o lighten-3" id="emailtxt"></i> </span>
                                                <span><i class="fa fa-mobile-phone lighten-3" id="contacttxt"></i> </span>
                                            </p>

                                            <div class="badge border-left-primary badge-striped user-access-details mb-03 mt-1" id="checkintxt">Check In Time: <span>25-07-2018 12:45:56 PM</span></div>
                                            <div class="badge border-left-danger badge-striped user-access-details" id="checkouttxt">Check Out Time: <span>25-07-2018 12:45:56 PM</span></div>

                                        </div>
                                    </div>

                                    <div class="card border-left-warning border-left-2 mt-2 user-details">
                                        <div class="card-body">
                                            <span class="badge badge-danger badge-square user-id">
                                                <span id="vidtxt"> </span>
                                            </span>
                                            <dl class="row mb-0">
                                                <dt class="col-sm-5">Is From Company?</dt>
                                                <dd class="col-sm-7" id="isfromcompanytxt"></dd>
                                            </dl>
                                            <dl class="row mb-0">
                                                <dt class="col-sm-5">Company Name</dt>
                                                <dd class="col-sm-7" id="vcnametxt"></dd>
                                            </dl>
                                            <dl class="row mb-0">
                                                <dt class="col-sm-5">Location</dt>
                                                <dd class="col-sm-7" id="locationtxt"></dd>
                                            </dl>
                                            <dl class="row mb-0">
                                                <dt class="col-sm-5">Meeting With</dt>
                                                <dd class="col-sm-7" id="employeetxt"></dd>
                                            </dl>
                                            <dl class="row mb-0">
                                                <dt class="col-sm-5">Purpose</dt>
                                                <dd class="col-sm-7" id="purposetxt"></dd>
                                            </dl>
                                            <dl class="row mb-0">
                                                <dt class="col-sm-5">No of Person</dt>
                                                <dd class="col-sm-7" id="noofpersontxt">6</dd>
                                            </dl>
                                            <dl class="row mb-0">
                                                <dt class="col-sm-5">Has Vehicle?</dt>
                                                <dd class="col-sm-7" id="isvehicletxt">Yes
                                                    <span class="other-details" id="vehicletxt"></span>
                                                </dd>
                                            </dl>
                                            <dl class="row mb-0">
                                                <dt class="col-sm-5">Has Luggage?</dt>
                                                <dd class="col-sm-7">Yes
                                                    <span class="other-details">2 Luggage, 1 Handbag</span></dd>
                                            </dl>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

			<div class="modal fade text-left" id="new_company" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="myModalLabel8" aria-hidden="true">
                <div class="modal-dialog modal-lg" role="document" id="visitorcompanydata">
                    <div class="modal-content">
                        <div class="modal-header bg-primary white">
                            <h4 class="modal-title white" id="myModalLabel8">Visitor Detail</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true" class="text-white">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
							<div class="row">
							<div class="col-md-3">
                                <div class="form-group">
									<label>Company Name</label>
                                    <input type="text" id="vcname" class="form-control" placeholder="Enter Company Name" maxlength="128" name="vcname">
                                </div>
                            </div>
							<div class="col-md-3">
                                <div class="form-group">
                                    <label>Email</label>
                                    <input type="text" id="vcemail" class="form-control" placeholder="Enter Email" maxlength="128" name="vcemail">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>Contact Number</label>
                                    <input type="text" id="vccontactno" class="form-control text-right" placeholder="Enter Contact Number"  maxlength="10"  name="vccontactno" oninput="this.value = this.value.replace(/[^0-9.]/g, ''); this.value = this.value.replace(/(.*)\./g,'$1');">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
									<label>Fax Number</label>
									<input type="text" id="vcfax" class="form-control text-right" placeholder="Enter Fax Number"  maxlength="10"  name="vcfax" oninput="this.value = this.value.replace(/[^0-9.]/g, ''); this.value = this.value.replace(/(.*)\./g,'$1');">
                                </div>
                            </div>
						</div>
						<div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="projectinput2">Select Country</label>
                                    <select class="select2 form-control" id="vccountryid" name="vccountryid" data-placeholder="Select Country">
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
                                    <select class="select2 form-control" id="vcstateid" name="vcstateid" data-placeholder="Select State">
                                                            
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="projectinput2">Select City</label>
                                    <select class="select2 form-control" id="vccityid" name="vccityid" data-placeholder="Select City">
                                                          
                                    </select>
                                </div>
                            </div>
                        </div>

                        </div>
                        <div class="modal-footer">
                            <button onclick="saveCompanyData()" type="button" class="btn btn-outline-primary">Save</button>
                            <button onclick="clearData()" type="button" class="btn grey btn-outline-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                 </div>
            </div>

			<div class="modal fade text-left" id="new_card" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="myModalLabel8" aria-hidden="true">
                <div class="modal-dialog modal-lg" role="document" id="visitorcarddata">
                    <div class="modal-content">
                        <div class="modal-header bg-primary white">
                            <h4 class="modal-title white" id="myModalLabel8">Visitor Card Detail</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true" class="text-white">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
						<div class="row">
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
                                    <label for="projectinput2">Select Location</label>
                                    <select class="select2 form-control" id="locationid" name="locationid" data-placeholder="Select Location">
                                    <option value="">Select Location</option>
										<c:forEach var="i" items="${locationList}">
			                            	<option value="${i[0]}">${i[1]}</option>
			                            </c:forEach> 
                                    </select>
                                </div>
                            </div>
                          <div class="col-md-3">
                                <div class="form-group">
									<label>Card Number</label>
									<input type="text" id="vpassnumber" name="vpassnumber" class="form-control text-right" placeholder="Enter Card Number"  maxlength="10">
                                </div>
                            </div>
                        </div>

                        </div>
                        <div class="modal-footer">
                            <button onclick="saveCardData()" type="button" class="btn btn-outline-primary">Save</button>
                            <button onclick="clearCardData()" type="button" class="btn grey btn-outline-secondary" data-dismiss="modal">Close</button>
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
<script src="assets/js/scripts/customizer.min.js" type="text/javascript"></script>

<!--Other Js to be added here as needed on particular page-->
<script src="assets/vendors/js/tables/datatable/datatables.min.js" type="text/javascript"></script>
<script src="assets/js/scripts/tables/datatables/datatable-basic.js" type="text/javascript"></script>

<script src="assets/vendors/js/forms/select/select2.full.min.js" type="text/javascript"></script>
<script src="assets/js/scripts/forms/select/form-select2.min.js" type="text/javascript"></script>

<!--Alert Custom JS-->
<script src="assets/js/scripts/extensions/sweet-alerts.min.js" type="text/javascript"></script>
<script src="assets/js/scripts/extensions/toastr.min.js" type="text/javascript"></script>


<script src="assets/custom_plugins/easytimer/easytimer.min.js" type="text/javascript"></script>
<!--<script src="assets/custom_plugins/letterpic/jquery.letterpic.js" type="text/javascript"></script>-->

<script src="assets/custom_plugins/Datedropper/datedropper.js" type="text/javascript"></script>
<script src="assets/custom_plugins/Timedropper/timedropper.js" type="text/javascript"></script>
<script src="assets/js/scripts/extensions/date-time-dropper.min.js" type="text/javascript"></script>
<script src="assets/custom_plugins/letterpic/jquery.letterpic.js" type="text/javascript"></script>
<script src="assets/custom_plugins/js/custom.js" type="text/javascript"></script>

<!-- my js -->
<script src="jsUtil/Validationevents.js" type="text/javascript"></script>
<script type="text/javascript" src="jsUtil/fileUploadUtil.js"></script>
<script src="jspJs/visitor/visitor.js"  type="text/javascript"></script>
</body>

</html>