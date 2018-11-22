
// Compare Date Function with Current Date
	function compareDateWithCurrentDate(ddmmyyyy, sepratorChar){			// here Date Foremate : dd-mm-yyyy    &  sepratorChar :  '-'
			//console.log("ddmmyyyy == "+ddmmyyyy);
		  	var currentdate = new  Date();
			var dt  = new Date((currentdate.getMonth() + 1) + '/' + currentdate.getDate() + '/' +  currentdate.getFullYear());
			var datepart = ddmmyyyy.split(sepratorChar);
			var mmddyyyy = new Date(datepart[1]+"/"+datepart[0]+"/"+datepart[2]);
		 if(mmddyyyy <  dt)   {
			    return false;
		  }
		 return true;
	}
	
	
//Compare Two Date
	function compareTwoDates(ddmmyyyyMin,ddmmyyyyMax, sepratorChar){			// here Date Foremate : dd-mm-yyyy    &  sepratorChar :  '-'
		//console.log("ddmmyyyyMin == "+ddmmyyyyMin);
		//console.log("ddmmyyyyMax == "+ddmmyyyyMax);
		var datepart = ddmmyyyyMin.split(sepratorChar);
		var mmddyyyyMin = new Date(datepart[1]+"/"+datepart[0]+"/"+datepart[2]);
		datepart = ddmmyyyyMax.split(sepratorChar);
		var ddmmyyyyMax = new Date(datepart[1]+"/"+datepart[0]+"/"+datepart[2]);
		 if(ddmmyyyyMax <= mmddyyyyMin)   {
			    return false;
		  }
		 return true;
	}
	

	
	
	
	
	
	
	