/**
 * http://usejsdoc.org/
 */
$(document).ready(function(){
	
	var server = "http://localhost:8080/";
	
	 
	function getCookie(name)
	  {
		$("#whereUrun").val("");
	    var re = new RegExp(name + "=([^;]+)");
	    var value = re.exec(document.cookie);
	    return (value != null) ? unescape(value[1]) : null;
	    
	  }

	//alert(getCookie("firma_id"));
	var id = getCookie("firma_id");

	$.get(server+"firma/admin/getAllUrunForFirma/"+id).done(function(data){
		$("#urunlerForKampanya").append('<option value="0">Ürün Seçiniz.</option>');
		for(i in data){
			$("#urunlerForKampanya").append('<option id="'+data[i].urunFiyat+'" value="'+data[i].id+'">'+data[i].urunAd+'</option>');
		}
		
	});
	
	$("#urunlerForKampanya").on("change",function(){
		var fiyat = $("#urunlerForKampanya option:selected").attr("id");
		$("#urunCurrentFiyat").text(fiyat);
	});
	
});