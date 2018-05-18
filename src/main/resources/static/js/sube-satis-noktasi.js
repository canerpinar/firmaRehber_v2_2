/**
 * 
 */
$(document).ready(function(){
	var server = "http://localhost:8080";
	$.updateSube = function(id){
		//alert(id);
		
		$("#modalSubeForUpdate").modal();
		$.get(server+"/firma/admin/updateSube/"+id).done(function(data){
			
			$("#subeAd").val(data.ad);
			$("#subeMail").val(data.email);
			
		});

	}
	
	
	
});