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
		$("#urunlerForKampanya2").append('<option value="0">Ürün Seçiniz.</option>');
		$("#urunlerForKampanya2_").append('<option value="0">Ürün Seçiniz.</option>');
		for(i in data){
			$("#urunlerForKampanya").append('<option id="'+data[i].urunFiyat+'" value="'+data[i].id+'">'+data[i].urunAd+'</option>');
			$("#urunlerForKampanya2").append('<option id="'+data[i].urunFiyat+'" value="'+data[i].id+'">'+data[i].urunAd+'</option>');
			$("#urunlerForKampanya2_").append('<option id="'+data[i].urunFiyat+'" value="'+data[i].id+'">'+data[i].urunAd+'</option>');
		}
		
	});
	
	$.get(server+"/firma/admin/getAllSatisNoktasi/"+id).done(function(data){
		$("#subelerForKampanya").append('<table class="table">');
		$("#subelerForKampanya_").append('<table class="table">');
		for(i in data){
			$("#subelerForKampanya").append("<tr style='width:100%;'><td style='width:80%;'>"+data[i].ad+"</td><td style='width:20%;'><input type='checkbox' name='subeForKampanya' value='"+data[i].id+"'/></td></tr>");
			$("#subelerForKampanya_").append("<tr style='width:100%;'><td style='width:80%;'>"+data[i].ad+"</td><td style='width:20%;'><input type='checkbox' name='subeForKampanya' value='"+data[i].id+"'/></td></tr>");

		}
		$("#subelerForKampanya").append("</table>");
		$("#subelerForKampanya_").append("</table>");
	});
	
	$("#urunlerForKampanya").on("change",function(){
		var fiyat = $("#urunlerForKampanya option:selected").attr("id");
		$("#urunCurrentFiyat").val(fiyat);

	});
	
	$("#urunlerForKampanya2_").on("change",function(){
		var fiyat = $("#urunlerForKampanya2_ option:selected").attr("id");
		
		$("#urunCurrentFiyat2").val(fiyat);
		$('#kampanyaEkle_2 input[id="kampanyaTuru_2"]').val("2");
		

	});
	
	$("#allSubeForChecked").on("click",function(){
		if(this.checked===true){
			$('#subelerForKampanya input[type="checkbox"]').each(function(){
				this.checked = true;
			});
		}else if(this.checked===false){
			$('#subelerForKampanya input[type="checkbox"]').each(function(){
				this.checked = false;
			});
		}
		

	});
	
	$("#allSubeForChecked_").on("click",function(){
		if(this.checked===true){
			$('#subelerForKampanya_ input[type="checkbox"]').each(function(){
				this.checked = true;
			});
		}else if(this.checked===false){
			$('#subelerForKampanya_ input[type="checkbox"]').each(function(){
				this.checked = false;
			});
		}
		

	});
	
	$("#kampanyaOranInput").on("keyup",function(){
		var currentFiyat = $("#urunCurrentFiyat").val();
		var oran = $("#kampanyaOranInput").val();
		var indirim = currentFiyat * oran /100;
		currentFiyat = currentFiyat - indirim;
		$("#kampanyaFiyatSpan").text("indirimli fiyat :" + currentFiyat);
		$("#kampanyaFiyat").val(currentFiyat);
		$("#kampanyaEkle_1 input[name=kampanyaTuru]").val("1");
		alert(currentFiyat);
	});
	
	$.ziyaretKampanyaSubmit = function(){
		$("#ziyaretKampanyaTur").val(3);
		return true;
	}
	
	$.ozelGunKampanyasi = function(){
		$("#ozelGunKampanyaTur").val(4);
		return true;		
	}
	
});