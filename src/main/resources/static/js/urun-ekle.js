/**
 * http://usejsdoc.org/
 */
$(document).ready(function(){
	var server = "http://localhost:8080";
	$("textarea").jqte();
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
	
	
	  function getCookie(name)
	  {
		  $("#whereUrun").val("");
	    var re = new RegExp(name + "=([^;]+)");
	    var value = re.exec(document.cookie);
	    return (value != null) ? unescape(value[1]) : null;
	    
	  }

	//alert(getCookie("firma_id"));
	var id = getCookie("firma_id");

	$.get(server+"/firma/admin/getAllSatisNoktasi/"+id).done(function(data){
		$("#whereUrun").append("<option>Şube Seçiniz.</option>");
		for(i in data){
			$("#whereUrun").append("<option value='" + data[i].id + "'>"+data[i].ad+"</option>");

		}
		

	});
	
	$("#whereUrun").on("change",function(){
		$.get(server + "/firma/admin/updateSube/"+$(this).val()).done(function(data){
			$("#whereUrun").val(data.id);
			$("#urunAdres").val(data.subeAdres);
		});
		
		
		
	});
	
	$.urunDetailsForUpdate = function(id){
		$("#urunKategori_").text("");
		$("#urunAltKategori_").text("");
		$("#urunSubKategori_").text("");
		
		
		
		$.get(server+"/firma/admin/getUrun/"+id).done(function(data){
			$("#urunAd_").val(data.urunAd);
			//alert(data.katagoriId);
			
			$.get(server+"/firma/admin/allKategori").done(function(data_){
				for(i in data_){
					if(data_[i].kategoriAd === data.kategoriAd){
						$('#urunKategori_').append("<option selected='selected' name='"+data_[i].id+"' value='" + data_[i].kategoriAd+ "'>"+data_[i].kategoriAd+"</option>");
						
					}else{
						$('#urunKategori_').append("<option name='"+data_[i].id+"' value='" + data_[i].kategoriAd+ "'>"+data_[i].kategoriAd+"</option>");
						
					}
					
					
					
					
				//console.log("denemeler");
						
						for(s in data_[i].altKategori){
							
							if(data_[i].altKategori[s].altKategoriAd === data.altKategoriAd){
								$('#urunAltKategori_').append("<option  selected='selected' value='" + data_[i].altKategori[s].altKategoriAd + "'>"+data_[i].altKategori[s].altKategoriAd +"</option>");
							
							}
							else{
								if(data_[i].kategoriAd === data.kategoriAd){
									$('#urunAltKategori_').append("<option value='" + data_[i].altKategori[s].altKategoriAd + "'>"+data_[i].altKategori[s].altKategoriAd +"</option>");									
								}

							}
							
							
							for(k in data_[i].altKategori[s].subaltKategori){
								
								if(data_[i].altKategori[s].subaltKategori[k].subAltKategoriAd === data.subKategoriAd){
									$('#urunSubKategori_').append("<option selected='selected' value='" + data_[i].altKategori[s].subaltKategori[k].subAltKategoriAd + "'>"+data_[i].altKategori[s].subaltKategori[k].subAltKategoriAd+"</option>");
									
								}else{
									if(data_[i].altKategori[s].altKategoriAd === data.altKategoriAd){
										$('#urunSubKategori_').append("<option value='" + data_[i].altKategori[s].subaltKategori[k].subAltKategoriAd + "'>"+data_[i].altKategori[s].subaltKategori[k].subAltKategoriAd+"</option>");
										
									}

								}
							}
							
							
						}
					

						
						
				}
			});

			
			$("#urunUpdateKategoriId").val(data.katagoriId);
			$("#urunUpdateAltKategoriId").val(data.altKatagoriId);
			$("#urunUpdateSubKategoriId").val(data.subKategoriId);
			
			$("#urunUpdateId").val(data.id);
			$("#urunFiyat_").val(data.urunFiyat);
			$("#urunMarka_").val(data.marka);
			$("#selectedKategori").text(data.kategoriAd);
			$("#selectedAltKategori").text(data.altKategoriAd);
			$("#selectedİkinciKategori").text(data.subKategoriAd);
			$("#urunHakkinda_").text(data.urunHakkinda);
			$("#urunImage_1_").attr('src',"../../"+data.imagePath+"/"+data.image);
			$("#urunImage_2_").attr('src',"../../"+data.imagePath+"/"+data.imageOne);
			$("#urunImage_3_").attr('src',"../../"+data.imagePath+"/"+data.imageTwo);
			$("#urunImage_4_").attr('src',"../../"+data.imagePath+"/"+data.imageThree);
			//alert(data.urunAd);
			$("#modalUrunUpdate").modal();
		});
	}
	
	$("#urunKategori_").on("change",function(){
		$("#urunAltKategori_").text("");
		$("#urunSubKategori_").text("");
		//alert("sadsad");
		$.get(server+"/firma/admin/getKategori/"+ $(this).val()).done(function(data){
			for(i in data){
				
				for(s in data[i].altKategori){
					$('#urunAltKategori_').append("<option value='" + data[i].altKategori[s].altKategoriAd + "'>"+data[i].altKategori[s].altKategoriAd +"</option>");
				}
								
			//console.log("denemeler");
			}
			//$("#kategori_id").val(data[0].id);
		});
	});
	
	
	$("#urunAltKategori_").on("change",function(){
		$("#urunSubKategori_").text("");
		$.get(server+"/firma/admin/getAltKategori/"+ $(this).val()).done(function(data){
			for(i in data){
				
				for(s in data[i].subaltKategori){
					$('#urunSubKategori_').append("<option value='" + data[i].subaltKategori[s].subAltKategoriAd + "'>"+data[i].subaltKategori[s].subAltKategoriAd+"</option>");
				
				}
								
			//console.log("denemeler");
			}
			//$("#alt_kategori_id").val(data[0].id);
		});
		
	});
	
	$.inputFileTrue = function(image){
		//alert(image);
		var component = '#'+image;
		$(component).click();		
	}
	
	$("#urunUpdateForm input[type=file]").on("change",function(){		
		alert("Yükleme başarılı");
	});
	
	

});