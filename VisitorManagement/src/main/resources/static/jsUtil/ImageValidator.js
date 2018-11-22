// From MDL Project

var idArr = [];
var menuArr = "";
var nameArr = [];
var heightArr = [];
var widthArr = [];


idArr[0]=0;			menuArr="home slider";			nameArr[0]="sliderbg";						heightArr[0]=845;			widthArr[0]=1630;
idArr[1]=1;			menuArr="home slider";			nameArr[1]="slideroverlay";				heightArr[1]=1345;		widthArr[1]=1350;
idArr[2]=2;			menuArr="banner content";	nameArr[2]="bannerbg";						heightArr[2]=845;			widthArr[2]=1630;
idArr[3]=3;			menuArr="shareholding";     	nameArr[3]="mainimage";					heightArr[3]=708;			widthArr[3]=340;
idArr[4]=4;			menuArr="shareholding";		nameArr[4]="logo";								heightArr[4]=86;				widthArr[4]=200;
idArr[5]=5;			menuArr="bod";							nameArr[5]="bod image";					heightArr[5]=500;			widthArr[5]=500;

idArr[6]=6;			menuArr="leadership";				nameArr[6]="leadership image";		heightArr[6]=500;			widthArr[6]=500;
idArr[7]=7;			menuArr="partners";					nameArr[7]="logo image";					heightArr[7]=142;			widthArr[7]=269;
idArr[8]=8;			menuArr="glossary";					nameArr[8]="main image";					heightArr[8]=250;			widthArr[8]=206;
idArr[9]=9;			menuArr="event";						nameArr[9]="thumnail image";			heightArr[9]=300;			widthArr[9]=600;
idArr[10]=10;		menuArr="event";						nameArr[10]="content upload";		heightArr[10]=431;		widthArr[10]=800;

idArr[11]=11;		menuArr="news";						nameArr[11]="content upload";		heightArr[11]=323;		widthArr[11]=600;
idArr[12]=12;		menuArr="popup";						nameArr[12]="image upload";			heightArr[12]=462;		widthArr[12]=700;





//not any fn  successfully working
var _URL = window.URL;
function checkImageDimention1(formid,elementid) {
	
	alert("xaf");
	alert("Form Id: "+formid);
	alert("element id : "+elementid);
	 var file, img;
	 
	 if ((file = ("#"+formid+" #"+elementid).files[0])) {
		 img = new Image();
		 img.onload = function () {
			 alert("Width:" + this.width + "   Height: " + this.height);//this will give you image width and height and you can easily validate here....
	        };
	        img.src = _URL.createObjectURL(file);
	 }
	
}


function checkImageDimention2(formid,elementid,index) {			// index = idArr[index]	array index
	var retnvar = false;

	//if (window.File 	&& window.FileReader && window.FileList && window.Blob) {
				var _URL = window.URL;
				var width = 0,  height = 0;
					var fileInput = $("#"+formid).find("input[id="+elementid+"]")[0];
						  file = fileInput.files && fileInput.files[0];
		  
						  if( file ) {
						        var img = new Image();
						        img.src = window.URL.createObjectURL( file );
						        img.onload = function() {
						             width = img.naturalWidth;
						                height = img.naturalHeight;
											alert(" onload Width:" +width+ "   Height: " +height);
						      /*    //  window.URL.revokeObjectURL( img.src );
										alert("widthArr[index] ==>> "+widthArr[index]);
										alert("heightArr[index] ==>> "+heightArr[index]);*/
											
						        };
						        
						        alert("Width:" +width+ "   Height: " +height);
						        if( width ==  widthArr[index] && height == heightArr[index] ) {
					            	alert("Pass");
					            	//return true;
					            	retnvar= true;
					            }else {
					            	alert("Fail");
					            	//return false;
					            	retnvar = false;
					            }
						    }
	//}
	
	return retnvar;
}



function checkImageDimention3(formid,elementid,index) {	
	
	  var fileInput = $("#"+formid).find("#"+elementid)[0],
      file = fileInput.files && fileInput.files[0];
				//console.log(file);
	  var width ,height;
	  var error=1;
			  if( file ) {
				  var width ,height;
				      var img = new Image();
				      img.src = window.URL.createObjectURL( file );
				      img.onload = function() {
				    	  		width = img.naturalWidth,
				    	  		height = img.naturalHeight;
				          window.URL.revokeObjectURL( img.src );
				      };
			  }
			  
			  if( width != 269 && height != 142) {
	        	  alert(" Pass  == Width:" +width+ "   Height: " +height);
	           return false;
	          }else{
	        	  alert("Fail  ==  Width:" +width+ "   Height: " +height);
	          return true;
	          }

}

function checkImageDimention(formid,elementid,index) {	
		var dimension=getDimensionOnTag(formid,elementid,index);
		var wnh =dimension.split("##");
			if( wnh[0] !=  widthArr[index]&& wnh[1] !=  heightArr[index] ) {
				return false;
	        }else{
	        	return true;
	        }
}


function getDimensionOnTag(formid,elementid,index) {		
	
	var img = document.getElementById('dimensionImage'); 
	//or however you get a handle to the IMG
	var width = img.clientWidth;
	var height = img.clientHeight;
	alert("width = "+width+" &  height = "+height);
	
	$("#"+formid+" #dimensionImage").remove();
	return width+"##"+height;

	//   img.src = e.target.result;
	
	
	
	 /*var fileInput = $("#"+formid).find("#"+elementid)[0],
     file = fileInput.files && fileInput.files[0];
	var h=0,w=0;
	 var img = new Image();
	 img.onload = function() {
	 //     $('#preview').append(img);
	      alert("Width: " + img.width + "\nHeight: " + img.height);
	      h=img.height;
	      w=img.width;
	   }
	   img.src = _URL.createObjectURL(file);
	   alert("W= "+w+ "  &  H = "+h);
	      return img.width+"##"+img.height;*/
}



function displayPreview(files,index) {

    var reader = new FileReader();
    var img = new Image();
    var width = 0,  height = 0;

    reader.onload = function (e) {
        img.src = e.target.result;
        fileSize = Math.round(files.size / 1024);
        //alert("File size is " + fileSize + " kb");

        img.onload = function () {
            alert("width=" + this.width + " height=" + this.height);
            
            width = this.width ;
            height = this.height;
         // $("#mdladminform #imgeurl ").append('<img src="' + e.target.result + '"/>');
           //$("#mdladminform #imgeurl ").attr('src', e.target.result);
        };
    };
    reader.readAsDataURL(files);
    
    if( width ==  widthArr[index] && height == heightArr[index] ) {
    	alert("Pass");
    	//return true;
    	retnvar= true;
    }else {
    	alert("Fail");
    	//return false;
    	retnvar = false;
    }
    
    
    
}


function getImageDimention(index){			// index = idArr[index]	array index
	
	return ""+widthArr[index]+" * "+heightArr[index]
}