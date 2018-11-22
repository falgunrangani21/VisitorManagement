


	function notificationError(titleText, contentText){
		$.confirm({
	        title: ''+titleText,
	        content: ''+contentText,
	        icon: 'fas fa-exclamation-triangle',
	        type: 'red',
	        theme: 'material',
	        buttons: {
	            omg: {
	                text: 'close',
	                btnClass: 'btn-red',
	            },
	           /* close: function () {
	            }*/
	        }
	    });
	}
	
	
	function notificationRequiredFiled(contentText){
		$.confirm({
					        title: 'Requied Field.!',
					        content: ''+contentText+'!',
					        icon: 'fas fa-info-circle',
					        type: 'blue',
					        theme: 'material',
					        buttons: {
					           /* omg: {
					                text: 'close',
					                btnClass: 'btn-blue',
					            },*/
					            close: function () {
					            }
					        }
					 });
		}
	
	function notificationSuccess(titleText,contentText,buttonText){
		if(undefined!=buttonText && null!=buttonText && buttonText!=""){
			$.confirm({
		        title: ''+titleText,
		        content: ''+contentText,
		        icon: 'fas fa-check-circle',
		        type: 'green',
		        theme: 'material',
		        buttons: {
				   omg: {
					  text: ''+buttonText,
				      btnClass: 'btn-green',
				   },
				   close: function () {
				  }
		        }
		    });
		}else{
			$.confirm({
		        title: ''+titleText,
		        content: ''+contentText,
		        icon: 'fas fa-check-circle',
		        type: 'green',
		        theme: 'material',
		        buttons: {
				   close: function () {
				  }
		        }
		    });
		}
	}
	
	function deleteConfirm(deleteOf,formId,actionUrl){
		$.confirm({
			   title: 'Delete Record ?',
			    content: 'Are you sure to delete '+deleteOf+'?',
			    icon : 'far fa-trash-alt',
			    type: 'green',
			    theme: 'material',
			    buttons: {
			        trueBtn: {
			            text: 'Yes, Delete it!',
			            btnClass: 'btn-green',
			            action: function () {
			            	document.getElementById(""+formId).method="post";
			            	document.getElementById(""+formId).action=actionUrl;
			            	document.getElementById(""+formId).submit();
			            }
			        },
			        cancel: {
			        	btnClass: 'btn-red',
			        	action :function () {
			        		
				        }
			        }
			    }
			});
		
	}
	
	
	function notificationInfo(titleText, contentText){
		$.confirm({
	        title: ''+titleText,
	        content: ''+contentText,
	        icon: 'fas fa-info-circle',
	        type: 'blue',
	        theme: 'material',
	        buttons: {
	            /*omg: {
	                text: 'close',
	                btnClass: 'btn-blue',
	            },*/
	        	
	           close: function () {
	           }
	        }
	    });
	}
	
	
	
//Auto LogOut 
	function notificationAutoLogout(){
	$.confirm({
		   title: 'Login Session Expire?',
		    content: 'Your login session expired. Please login again..',
		    icon: 'fas fa-power-off',
	        type: 'blue',
	        theme: 'material',
		    autoClose: 'logoutUser|5000',
		    buttons: {
		        logoutUser: {
		            text: 'Logout in..',
		            action: function () {
		            	window.location.href = "login_logout";
		            }
		        }
		        /*cancel: function () {
		            
		        }*/
		    }
		});
	}
	