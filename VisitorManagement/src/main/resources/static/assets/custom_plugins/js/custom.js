$(document).ready(function(){
    $(".show-add-detail-form").click(function(){
        $("#view_details_section").hide();
        $("#add_details_section").show();
    });

    $(".show-details-table").click(function(){
        $("#view_details_section").show();
        $("#add_details_section").hide();
    });

    $(".save_form_details").click(function(){
        $("#view_details_section").show();
        $("#add_details_section").hide();
    });

    $(".show-filter").click(function() {
        $(".filter").toggleClass("open")
    });

    $(".filter-close").click(function() {
        $(".filter").removeClass("open")
    });

});

/*(".customizer-toggle").on("click", function () {
    s(".customizer").toggleClass("open")
}), s(".customizer-close").on("click", function () {
    s(".customizer").removeClass("open")
    */
/*Letterpic Js*/
$(function() {
    $(".letterpic").letterpic({
        onElementReplaced: function($el, isBrokenImage) {
            console.log($el, isBrokenImage);
        },
        onImageError: function($el, url) {
            console.log('Image loading error: ' + url);
        }
    });

    var letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    function getRandomInitials() {
        var name = "";
        name += letters.charAt(Math.floor(Math.random() * letters.length));
        name += " ";
        name += letters.charAt(Math.floor(Math.random() * letters.length));
        return name;
    }

    function addLetterpicEl($el) {
        $el.append("<div class='letterpic' title='" + getRandomInitials() + "'></div>");
    }

    /*// gradient demo
    var $gradientDemo = $(".gradient-demo");
    for(var i = 0; i < 19; i++) {
        addLetterpicEl($gradientDemo);
    }
    $gradientDemo.find(".letterpic").letterpic().click(function() { alert('chaining'); });

    // custom gradient demo
    var $gradientCustomDemo = $(".gradient-custom-demo");
    var customGradients = [
        ["#fff", "#333", "#000", "#777"],
        ["#aaafff", "#aaa777"],
        ["#dddfff", "#ddd333", "#ddd777"],
    ];
    for(var i = 0; i < customGradients.length; i++) {
        addLetterpicEl($gradientCustomDemo);
    }
    $gradientCustomDemo.find(".letterpic").letterpic({
        gradients: customGradients
    });*/

    /*// colors demo
    var $colorDemo = $(".color-demo");
    for(var i = 0; i < 0; i++) {
        addLetterpicEl($colorDemo);
    }
    $colorDemo.find(".letterpic").letterpic({ fill: 'color' });
*/
    // custom colors demo
    var $colorCustomDemo = $(".color-custom-demo");
    var customColors = [ "#df4617", "#49606a", "#3241a2", "#007c6e", "#b4bb2d" ];
    for(var i = 0; i < 0; i++) {
        addLetterpicEl($colorCustomDemo);
    }
    $colorCustomDemo.find(".letterpic").letterpic({
        fill: 'color',
        colors: customColors
    });

   // image demo
    /*var $imageDemo = $(".image-demo");
    for(var i = 0; i < 0; i++) {
        addLetterpicEl($imageDemo);
    }
    $imageDemo.find(".letterpic").letterpic({ fill: 'image' });*/

    /*// custom images demo
   var images = [
       "https://image.freepik.com/free-vector/floral-background-with-watercolor-leaves_1085-886.jpg",
       "https://image.freepik.com/free-vector/watercolor-feathers-background_23-2147607455.jpg",
       "https://image.freepik.com/free-vector/octopus-pattern-design_1235-151.jpg"
   ];
   var $imageCustomDemo = $(".image-custom-demo");
   for(var i = 0; i < images.length; i++) {
       addLetterpicEl($imageCustomDemo);
   }
   $imageCustomDemo.find(".letterpic").letterpic({
       fill: 'image',
       images: images,
       fontColor: "#CCC",
       imageOverlayColor: "rgba(0, 0, 0, .5)" });*/

});



/*Timer*/
var timer = new Timer();
$('#startTimer .startButton').click(function () {
	timer.start();
});

$('#startTimer .stopButton').click(function () {
	 timer.stop();
});

timer.addEventListener('secondsUpdated', function (e) {
    $('#startTimer .values').html(timer.getTimeValues().toString());
});
timer.addEventListener('started', function (e) {
    $('#startTimer .values').html(timer.getTimeValues().toString());
});
timer.addEventListener('reset', function (e) {
    $('#startTimer .values').html(timer.getTimeValues().toString());
});
