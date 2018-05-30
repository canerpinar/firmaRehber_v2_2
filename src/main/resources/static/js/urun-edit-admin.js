/**
 * 
 */
$(document).ready(function(){
	var server ="http://localhost:8080/";
	$("#kategoriSelect").on("change",function(){
		$("#altKategoriSelect").text('');		
		$("#ikinciAltKategoriSelect").text('');
		var kategori_id = $("#kategoriSelect option:selected").val()
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
		$.get(server+"admin/siteGenelEdit/altKategoriEdit/"+altKategori_id).done(function(data){
			for(i in data.subaltKategori){
				var data_ = data.subaltKategori[i].subAltKategoriAd;
				var id = data.subaltKategori[i].id;
				console.log(data_);
				$("#ikinciAltKategoriSelect").append('<option value="'+id+'">'+data_+'</option>');
			}
			
		});
	});
	
});