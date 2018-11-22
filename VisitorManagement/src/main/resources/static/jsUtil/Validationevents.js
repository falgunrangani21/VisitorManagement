//oninput alphanumeric and somesymbols
function inputtext(obj){
obj.value=obj.value.replace(/[^a-z A-Z0-9\n,@!:&\\?.()-]/,'');
}

//oninput integer
function inputint(obj){
	obj.value=obj.value.replace(/[^0-9]/g,'');
}

//oninput integer
function inputchar(obj){
	obj.value=obj.value.replace(/[^a-z A-Z]/,'');
	
}

//oninput float
function oninputnumericfloat(e,obj){
	var objVal = obj.value;
	var finalval="";
	var dotcount=0;
	for (var i = 0; i < objVal.length; i++) {
	    if (objVal.charAt(i) == ".") {
	      dotcount += 1;
	      if(dotcount>1){
	    		e.preventDefault();
	      }else{
	    	  finalval+=objVal.charAt(i);
	      }
	      }
	    if(isNaN(objVal.charAt(i))){
	    	e.preventDefault();
	    	
	    }else{
	    	finalval+=objVal.charAt(i);
	    }
	  }
	obj.value=finalval;
	
}


//oninput float negative nos

function oninputnumericfloatneg(e,obj){

	var objVal = obj.value;
	var finalval="";
	var dotcount=0,minuscount=0;
	for (var i = 0; i < objVal.length; i++) {
	    if (objVal.charAt(i) == ".") {
	      dotcount += 1;
	      if(dotcount>1){
	    		e.preventDefault();
	      }else{
	    	  finalval+=objVal.charAt(i);
	      }
	      }
	    if(i==0){
	    	 if (objVal.charAt(i) == "-") {
	    		 minuscount += 1;
	   	      if(minuscount>1){
	   	    		e.preventDefault();
	   	      }else{
	   	    	  finalval+=objVal.charAt(i);
	   	      }
	   	      }	
	    }
	    if(isNaN(objVal.charAt(i))){
	    	e.preventDefault();
	    	
	    }else{
	    	finalval+=objVal.charAt(i);
	    }
	  }
	obj.value=finalval;
	

}
//onblur float
function blurfloat(obj){
	
	if(obj.value==""){
		$("#"+obj.id).val("0");
	}else{
		var y=parseFloat(obj.value).toFixed(2);
		$("#"+obj.id).val(y);
	}
	
}
//onblur integer
function blurint(obj){
	
	if(obj.value==""){
		$("#"+obj.id).val("0");
	}else{
		var y=parseInt(obj.value);
		$("#"+obj.id).val(y);
	}
	
}



