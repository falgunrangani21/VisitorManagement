// From MDL Project

var idArr = [];
var menuArr = "";
var nameArr = [];
var contentLengthArr = [];
//var widthArr = [];


idArr[0]=0;			menuArr="Event";				nameArr[0]="Smallcontent";					contentLengthArr[0]=300;
idArr[1]=1;			menuArr="Event";				nameArr[1]="Bigcontent";						contentLengthArr[1]=300;		
idArr[2]=2;			menuArr="News";				nameArr[2]="Smallcontent";					contentLengthArr[2]=300;			
idArr[3]=3;			menuArr="News";     			nameArr[3]="Bigcontent";						contentLengthArr[3]=300;			
idArr[4]=4;			menuArr="bod";					nameArr[4]="Smallcontent";					contentLengthArr[4]=300;				
idArr[5]=5;			menuArr="bod";					nameArr[5]="Bigcontent";						contentLengthArr[5]=610;				//Active

idArr[6]=6;			menuArr="leadership";		nameArr[6]="Smallcontent";					contentLengthArr[6]=300;			
idArr[7]=7;			menuArr="leadership";		nameArr[7]="Bigcontent";						contentLengthArr[7]=300;			
idArr[8]=8;			menuArr="Job Post";			nameArr[8]="Job Description";				contentLengthArr[8]=300;			
idArr[9]=9;			menuArr="Job Post";			nameArr[9]="Competion";						contentLengthArr[9]=300;			
idArr[10]=10;		menuArr="Partners";			nameArr[10]="Description";					contentLengthArr[10]=300;		
	




	function checkContentLength(content,idArrIndex){		// content  with html
		var contentLength = $(content).text().length;
		if(contentLength >contentLengthArr[idArrIndex]){
			return false;
		}else{
			return true;
		}
	}

	function getCententLength(idArrIndex) { 	// will be  return no of charactor
		return contentLengthArr[idArrIndex];
	}
