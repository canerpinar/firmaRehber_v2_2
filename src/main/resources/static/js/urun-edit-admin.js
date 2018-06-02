/**
 * 
 */
$(document).ready(function(){
	var server ="http://localhost:8080/";
	$("#kategoriSelect").on("change",function(){
		$("#altKategoriSelect").text('');		
		$("#ikinciAltKategoriSelect").text('');
		var kategori_id = $("#kategoriSelect option:selected").val();
		var kategori_ad = $("#kategoriSelect option:selected").text();
		$("#kategoriName").val(kategori_ad);
	//	alert(kategori_ad);
		$("#altKategoriSelect").append('<option value="0">Seçiniz</option>');
		$.get(server+"admin/siteGenelEdit/kategoriEdit/"+kategori_id).done(function(data){
			for(i in data.altKategori){
				console.log(data.altKategori[i].altKategoriAd);
				$("#altKategoriSelect").append('<option value="'+data.altKategori[i].id+'">'+data.altKategori[i].altKategoriAd+'</option>')
			
			}
		});
		/*
		$("#altKategoriSelect").append('');
		
		$("#ikinciAltKategoriSelect").append('');
		*/
	});
	
	$("#altKategoriSelect").on("change",function(){
		$("#ikinciAltKategoriSelect").text('');
		var altKategori_id = $("#altKategoriSelect option:selected").val();
		var altKategori_ad = $("#altKategoriSelect option:selected").text();
		$("#altKategoriId").val(altKategoriId);
		$("#altKategoriName").val(altKategori_ad);
		$("#ikinciAltKategoriSelect").append('<option value="0">Seçiniz.</option>');
		$.get(server+"admin/siteGenelEdit/altKategoriEdit/"+altKategori_id).done(function(data){
			for(i in data.subaltKategori){
				var data_ = data.subaltKategori[i].subAltKategoriAd;
				var id = data.subaltKategori[i].id;
				console.log(data_);
				$("#ikinciAltKategoriSelect").append('<option value="'+id+'">'+data_+'</option>');
			}
			
		});
	});
	
	$("#ikinciAltKategoriSelect").on("change",function(){
		var subKategoriName = ("#ikinciAltKategoriSelect option:selected").text();
		var subKategoriId = ("#ikinciAltKategoriSelect option:selected").val();
		$("#subKategoriName").val(subKategoriName);
		$("#subKategoriId").val(subKategoriId);
	});
	
	$("#seoAdd").on("click",function(){
		$("#urunSeoModal").modal();
	});
	
	$("#addSeoForUrun").on("click",function(){
		$("#seoForUrunAdd").append('<input type="text" name="seoName" placeHolder="SEO NAME" /><input type="text" name="seoContent" placeHolder="SEO CONTENT" /><br/>');
	});
	

	$.seoSaveForUrunSubmit = function(){
		$("#urunPageNameForSeo").val($("#urunPageNameForUrun").val());
		$("#urunIdForSeo").val($("#urunIdForUrun").val());
	}
	
	$.deleteSeoContent = function(id){
		$.post(server + "/admin/seo/deleteSeoContent/"+id).done(function(data){
			
			alert("Seo Content silme başarılı");
		});
	}
	
});