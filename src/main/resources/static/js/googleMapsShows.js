/**
 * 
 */ $(document).ready(function(){
	 var server = "http://localhost:8080";
	 $.mapsGoogle = function(ltd,lon){

		 var origin = new google.maps.LatLng(ltd, lon);
		 
		 
		 var adress;
		 $.get(server + "/getUrunler").done(function(data){

			 var dest = data[0].bulunduguAdres;
			 
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
		 alert(adress);


		 //alert(ltd);alert(lon);
	 }
	 
 });