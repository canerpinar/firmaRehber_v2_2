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
	
	$.mapsLoad = function(){
		$("#mapsModal").modal();
		
	}
	
	$('#navs a[data-toggle="tab"]').bind('click', function (e) {
		
		$('#subeForm_satis input[type="text"]').each(function(){
			$(this).val("");
			
		});
		
		$('#subeForm_sube input[type="text"]').each(function(){
			$(this).val("");
			
		});
		
		});
	
});