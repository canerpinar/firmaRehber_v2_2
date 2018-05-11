/**
 * 
 * 
 */
$(document).ready(function(){
	var server = "http://localhost:8080";
	$.get(server+"/firma/admin/allKategori").done(function(data){
		for(i in data){
			$('#kategoriList').append("<option name='"+data[i].id+"' value='" + data[i].kategoriAd+ "'>");
		console.log("denemeler");
		}
	});
	
	$("#kategori").on("input",function(){
		//alert($(this).val());

		$.get(server+"/firma/admin/getKategori/"+ $(this).val()).done(function(data){
			for(i in data){
				
				for(s in data[i].altKategori){
					$('#altkategoriList').append("<option value='" + data[i].altKategori[s].altKategoriAd + "'>");
				}
								
			console.log("denemeler");
			}
			$("#kategori_id").val(data[0].id);
		});
		
	});
	
	$("#altKategori").on("input",function(){
		
		$.get(server+"/firma/admin/getAltKategori/"+ $(this).val()).done(function(data){
			for(i in data){
				
				for(s in data[i].subaltKategori){
					$('#subkategoriList').append("<option value='" + data[i].subaltKategori[s].subAltKategoriAd + "'>");
				}
								
			console.log("denemeler");
			}
			$("#alt_kategori_id").val(data[0].id);
		});
		
	});
	
	
	$("#subKategori").on("input",function(){
		
		$.get(server+"/firma/admin/getSubAltKategori/"+ $(this).val()).done(function(data){
			for(i in data){
				
				for(s in data[i].subaltKategori){
					$('#subkategoriList').append("<option value='" + data[i].subaltKategori[s].subAltKategoriAd + "'>");
				}
								
			console.log("denemeler");
			}
			$("#subalt_kategori_id").val(data[0].id);
		});
		
	});
	
	
	
	
	
	
	
});
