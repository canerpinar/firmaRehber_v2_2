/**
 * 
 */ 

 var map, infoWindow;
 var urunler=[];
 var server = "http://localhost:8080";
     function initMap() {
       map = new google.maps.Map(document.getElementById('map'), {
         center: {lat: 41.015137, lng: 28.979530},
         zoom: 5
       });
       
       infoWindow = new google.maps.InfoWindow();
       
       // Try HTML5 geolocation.
        if (navigator.geolocation) {
         navigator.geolocation.getCurrentPosition(function(position) {
           var pos = {
             lat: position.coords.latitude,
             lng: position.coords.longitude
           };
			
           infoWindow.setPosition(pos);
           infoWindow.setContent('Location found.');
           infoWindow.open(map);

           
         }, function() {
           handleLocationError(true, infoWindow, map.getCenter());
          
         });

       } else {
         // Browser doesn't support Geolocation
         handleLocationError(false, infoWindow, map.getCenter());
       }
        mapsGoogle();

     }  
	
     
      
       function geocodeAddress(geocoder, resultsMap) {
    	            
          geocoder.geocode({'address': address}, function(results, status) {
            if (status === 'OK') {
              resultsMap.setCenter(results[0].geometry.location);
              var marker = new google.maps.Marker({
                map: resultsMap,
                position: results[0].geometry.location
              });
            } else {
              alert('Geocode was not successful for the following reason: ' + status);
            }
          });
        }
       

		

/*$(document).ready(function(){
	 var server = "http://localhost:8080";
	 var adress;
	 $.mapsGoogle = function(){

		 var origin = new google.maps.LatLng();
		 
		 $.get(server + "/getUrunler").done(function(data){

			 var dest = data[0].bulunduguAdres;
			 adress = data[0].bulunduguAdres;
			  var service = new google.maps.DistanceMatrixService;
			  service.getDistanceMatrix({
			    origins: [origin],
			    destinations: ["'"+dest+"'"],
			    travelMode: google.maps.TravelMode.DRIVING,
			    unitSystem: google.maps.UnitSystem.METRIC,
			    avoidHighways: false,
			    avoidTolls: false
			  }, function(response, status) {
			    if (status !== google.maps.DistanceMatrixStatus.OK) {
			      alert('Error was: ' + status);
			    } else {
			      alert(response.originAddresses[0] + ' --> ' + response.destinationAddresses + ' ==> ' + response.rows[0].elements[0].distance.text);
			    }
			  });
			 
			  
		 });
		 
		 return origin;

		 //alert(ltd);alert(lon);
	 }
	 
    
	 
	
});*/

	 
