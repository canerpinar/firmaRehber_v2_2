/**
 * 
 */
$(document).ready(function() {

	
	$.modalContactUpdate = function() {
		$("#modalContactUpdate").modal();
	}
	
	//var res= 'url(../img/content/contact-us-heading-bg.jpg) top center no-repeat';
	var res= 'url(../../'+$("#firmaBgImage").val()+')top center no-repeat';
	//url(../img/content/company-heading-bg.jpg) top center no-repeat
	
	console.log("resim : "+res);
	
	$(".company-heading-view .company-slider-content .general-view").css({"background":res});
	
	var server="http://localhost:8080";
	$("#fileUpload").on("click",function(){
		//alert("ssdfsdf");
		$("#upload-file").click();		
	});
	

	$("#upload-file").change(function(){
		var file = $("#upload-file").val();
		var username=$("#firmaName").val();
		/*
		$.ajax({			
			type : 'POST',
			url : '/firma/admin/fileUploadBgChange',
			data : file,username	
			,success : function() {
				alert("gönderi başarılı");
			}
		});	
		*/
		/*
		$.post("/firma/admin/fileUploadBgChange?file="+file+"&username="+username).done(function(){
			alert(data);
			alert("gönderi başarılı");
			
		});
		*/
		var filename = file.replace(/C:\\fakepath\\/i, '');
		$("#fileUploadChangeForm").submit();
		var res= 'url(../../'+username+'/'+filename+')';

		
		console.log("file name:" + filename);console.log("resim : "+res);
		
		$(".company-heading-view").css("backgroundImage" , res);
		//alert("seçildi");
	});
});