$(document).ready(function(){
	$.turkishLanguageChange = function() {

            window.location.replace('index.html?lang=' + 'tr_TR');       
	}
	
	$.englishLanguageChange = function() {

        window.location.replace('index.html?lang=' + 'en_EN');       
}
	
	$.onConfirmPassword = function(){
		  if ($('#password').val() == $('#confirm_password').val()) {
			    //$('#submitButton').css('visibility', 'visible');
			    return true;
			  } else {				  
				  //$('#submitButton').css('visibility', 'hidden');
				  return false;				  
			  }
	}
	$('#password, #confirm_password').on('keyup', function () {
		  if ($('#password').val() == $('#confirm_password').val()) {
			    $('#submitButton').css('visibility', 'visible');
			    return true;
			  } else {				  
				  $('#submitButton').css('visibility', 'hidden');
				  return false;				  
			  }
				  
	});
		
});



