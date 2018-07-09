/**
 * http://usejsdoc.org/
 */
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
	$('#password_ , #confirm_password').on('keyup', function () {
		var passwordVal = $('#password_').val();
		var passwordConfirm = $('#confirm_password').val();
		  if ( passwordVal === passwordConfirm) {
			    $('#submitButton').css('visibility', 'visible');
			    return true;
			  } else {				  
				  $('#submitButton').css('visibility', 'hidden');
				  return false;				  
			  }
				  
	});
	
	$("#register-button").on("click",function(){
		window.location.href = "company-sign.html";
	});
	
	$("#password").on("keyup",function(){
		if($("#username").val()!=="" && $("#password").val()!==""){
			$("#loginButton").css('visibility','visible');
		}
	});
	
	$("#username").on("click",function(){
		if($("#username").val()!=="" && $("#password").val()!==""){
			$("#loginButton").css('visibility','visible');
		}
	});
	
	$("#password").on("change",function(){
		if($("#username").val()==="" || $("#password").val()===""){
			$("#loginButton").css('visibility','hidden');
		}
	});
	$.pageRefreshForCategories = function(kategoriAd){//hangi alt kategori tıklandıysa ana sayfa refresh yapılmalı
		$("#urunListForIndexPage").text("");
		alert(kategoriAd);
	}
	
	$.urlParam = function(name){
	    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
	    if (results==null){
	       return null;
	    }
	    else{
	       return decodeURI(results[1]) || 0;
	    }
	}
	
	if($.urlParam('loginFail')=='false'){
	$("#kullaniciLoginFail").css('visibility','visible');
		$("#loginButtonClick").click();

		//alert("yalnış giriş");
	}
		
});