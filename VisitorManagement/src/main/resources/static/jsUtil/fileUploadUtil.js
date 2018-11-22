
// Check File Upload 
function checkFileUpload(formId, htmlObjId, maxFileSize,extArr){    	//   
		if (window.File 	&& window.FileReader && window.FileList && window.Blob) {
								var fsize=document.forms[formId][htmlObjId].files[0].size;
								var ftype =document.forms[formId][htmlObjId].files[0].type;  	
			 					var fname= document.forms[formId][htmlObjId].files[0].name; 	 
								
			 					/*var fsize = document.getElementById(''+htmlObjId+'').files[0].size;
			 					var ftype = document.getElementById(''+htmlObjId+'').files[0].type;
			 					var fname= document.getElementById(''+htmlObjId+'').files[0].name;*/
			 				
								/*	Or Jquery
								 	* var 		fsize = $("#"+formId+" #"+htmlObjId+"")[0].files[0].size;
										var 		ftype = $("#"+formId+" #"+htmlObjId+"")[0].files[0].type;
										var 		fname = $("#"+formId+" #"+htmlObjId+"")[0].files[0].name;
								*/

								//document.forms[formId]["filename"].value =fname;
								
								if (fsize > (maxFileSize*1000000)) {
									return "File should be less then "+maxFileSize+" MB";
								} else {
										var sFileName = fname;
										var sExt = sFileName
												.split('.')[sFileName
												.split('.').length - 1]
												.toLowerCase();
										
												if(extArr.indexOf(sExt)<0){
													return "Please attached file should be "+extArr+"  !";
												}
								}
					}
	return "";
}


