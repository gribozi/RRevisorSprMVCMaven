jQuery(document).ready(function(){
	
	//jQuery("body").css("background-color", "#111");
	
	jQuery("a.fancy").fancybox();
	
	jQuery(".restaurant").click(function() {
	    if (jQuery(".restaurant").is(":checked")) {
	    	jQuery("#submitbtn").attr('disabled', false); 
	    }
	    else {
	    	jQuery("#submitbtn").attr('disabled', true);
	    }
	});

	initializeMap();	
});

  //-------------------------------------------------
  //  Google map
  //-------------------------------------------------
function initializeMap() {
  var mapOptions = {
    zoom: 14,
    center: new google.maps.LatLng(46.505622, 30.631019),
    scrollwheel: false,
    // mapTypeId: google.maps.MapTypeId.HYBRID
  };
  var map = new google.maps.Map(document.getElementsByClassName('map-container')[0], mapOptions);

  new google.maps.Marker({
    position: new google.maps.LatLng(46.504387, 30.619852),
    map: map,
    title: 'Компот',
    icon: 'img/map-pin.png'
  });
}