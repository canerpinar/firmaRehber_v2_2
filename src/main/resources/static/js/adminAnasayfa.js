/**
 * http://usejsdoc.org/
 */
$(document).ready(function(){
var server="http://localhost:8080/";

	$.kategoriEkle = function(){
		var kategoriAd = $("#kategoriAd").val();
		/*
		var jsonKategori = '{ "kategoriAd" : "'+kategoriAd+'" ,"altKategori" : [{"kategoriAd" : "İran","subAltKategori" :[{"kategoriAd" : "Som"}'+
		',{"kategoriAd" : "Kom"}]},{"kategoriAd" : "Turkish","subAltKategori" :[{"kategoriAd" : "Göreme"},{"kategoriAd" : "Demirci"}]}]}';
		*/
		//alert("kategori adi" + kategoriText);
		var _jsonKategori = {
				altKategori :[]
		};

		
		
		
		//_jsonKategori["kategoriAd"] = kategoriAd;
		
		
		for(k=0;k<altKategoriCount;k++){
			
			var sayac=0;
			subAltKategori = [];
			var altKategoriAd;
				$("#altKategori_"+k).find("input").each(function(){
					item ={};
					
					if(sayac === 0){
						altKategoriAd = $(this).val();
						sayac=1;
						return;
					}
					item["subAltKategoriAd"] = $(this).val();
					subAltKategori.push(item);				
				});
				
				_jsonKategori.altKategori.push({					
					altKategoriAd : altKategoriAd , subAltKat : subAltKategori
				});
				
		}
		_jsonKategori["kategoriAd"] = kategoriAd;
		console.log(JSON.stringify(_jsonKategori));
		
		var jsonKategori = [];
		jsonKategori.push(_jsonKategori);
		
		$.ajax({			
			contentType: "application/json",
			type : 'POST',
			url : '/admin/siteGenel/addKategori',
			data : JSON.stringify(_jsonKategori)
			,success : function() {
				$("#modalKategori").modal('hide');
				_jsonKategori = _jsonKategori;
				var addUyeForKategori ="<span><b>"+_jsonKategori.kategoriAd+"</b></span><br/>";
				var altKat = "";
				for(alt in _jsonKategori.altKategori){
									
					altKat = altKat+"<span>Alt Kategori :"+_jsonKategori.altKategori[alt].kategoriAd+"</span><br/>";
					for(sub in _jsonKategori.altKategori[alt].subAltKategori){
						var subAlt = '<span>İkinci Alt :'+_jsonKategori.altKategori[alt].subAltKategori[sub].kategoriAd+'<a href=#>Uye Ekle</a><br/></span>';
						altKat=altKat + subAlt;
					}
				}
				addUyeForKategori = addUyeForKategori + altKat;
				$("#addUyeForKategori").append(addUyeForKategori);
				
				//alert("kategoriAdı eklendi");
			}
		});	
		
		
	}
	
	$.modalKategoriUpdate = function(){
		
		$.get(server+"admin/siteGenelEdit/allKategori").done(function(data){
			var kategoriler ="";
			for(kategori in data){
				var kat = data[kategori].kategoriAd;
				kategoriler =kategoriler + kat+'<a href="/admin/siteGenel/kategoriEdit/'+data[kategori].id+'">Düzenle</a><br/>';				
			}
			$("#modalKategoriUpdateForm").html(kategoriler);			
		});
	}
	
	var altKategoriCount = 0;


	var endClick = 0;
	$.altKategoriEkle = function(){
		
		$("#altKategori").append("<div id='altKategori_"+altKategoriCount+"'><input name='altKategoriName_"+altKategoriCount+"' type='text' placeholder='Alt Kategori Adı'/>"+
				"</div><a onclick='$.subAltKategoriEkle("+altKategoriCount+");' href='#'>İkinci Alt Kategori Ekle</a><span style='padding-left:30px;'>");
		altKategoriCount++;
	}
	$.subAltKategoriEkle = function(_altKategoriCount){
		console.log(_altKategoriCount);
		$("#altKategori_"+_altKategoriCount).append('<input name="subAltKategoriName_'+_altKategoriCount+'" type="text"'+
				'placeholder="İkinci Alt Kategori Adı"/>');
		console.log('#altKategori_'+_altKategoriCount);
		console.log($("[id^=altKategori_]").length);
	}
	
	
	
	
	var kategoriIdForUpdate;
	$.altkategoriekle = function(kategoriAdi,id){
		$("#modalAltKategoriEkle").modal();
		$("#kategoriAdi").text(kategoriAdi);
		kategoriIdForUpdate=id;				
	}	
	var altkategoriJson = [];

	subKategoriArray = {};
	
	var subkategoriCount = 0;
	$.subkategoriekle = function(){
		
		$("#subkategoridiv").append('<input id="subkategoriad_'+subkategoriCount+'" type="text" placeHolder="İkinci Alt Kategori Ad" /><br/>');
		
		var subalt = $("#subkategoriad_"+subkategoriCount).val();

		subkategoriCount++;
	}
	var kategoriIdForSub;
	var altKategoriIdForSub;
	$.subaltkategoriekle = function(kategoriId,altKategoriAd,altKategoriId){
		$("#modalSubKategoriEkle").modal();
		alert(kategoriId+altKategoriAd + altKategoriId);
		$("#subkategoriAdi").text(altKategoriAd);
		kategoriIdForSub = kategoriId;
		altKategoriIdForSub = altKategoriId;
		
		
	}
	
	$.subkategoriforinputekle = function(){
		$("#subkategoriforinput").append('<input type="text" name="subkategoriad" placeHolder="İkinci Alt Kategori Ad"/><br/>');
	}
	
	$.submitSubKategori = function(){
		var altKategoriForSubJson = [];
		items = [];
		$("#subkategoriforinput").find("input").each(function(){
			item = {};
			item["subAltKategoriAd"] = $(this).val();
			console.log($(this).val());
			items.push(item);
		});
		altItems = {};
		altItems["kategoriId"] = kategoriIdForSub;
		altItems["altKategoriId"] = altKategoriIdForSub;
		altItems["subAlt"] = items;
		altKategoriForSubJson.push(altItems);
		altKategoriForSubJson=JSON.stringify(altKategoriForSubJson);
		console.log(altKategoriForSubJson);
		$.ajax({			
			contentType: "application/json",
			type : 'POST',
			url : '/admin/siteGenel/addSubAltKategori',
			data : altKategoriForSubJson	
			,success : function() {
				altKategoriForSubJson = [];
				kategoriIdForSub="";
				altKategoriIdForSub="";
				items=[];
				$("#modalSubKategoriEkle").modal('hide');
			}
		});	
		
		
	}
	
	$.submitAltKategori = function (){
		altkategoriJson=[];
		subaltkategoriJson=[];
		var altkategoriAd = $("#altKategoriAd").val();
		itemAlt = {};
		itemAlt["altKategoriAd"] = altkategoriAd;
		itemAlt["kategoriId"] = kategoriIdForUpdate;
		
		
		var length = $("#subkategoridiv").find("input").length;
		
		for(sayac=0;sayac<length;sayac++){
			var sub = $("#subkategoriad_"+sayac).val();
			item = {};
			item["subkategoriad"] = sub;
			subaltkategoriJson.push(item);
						
		}
		itemAlt["subAlt"] = subaltkategoriJson;
		altkategoriJson.push(itemAlt);

		
		
		
		altkategoriJson=JSON.stringify(altkategoriJson);
		console.log(altkategoriJson);
		$.ajax({			
			contentType: "application/json",
			type : 'POST',
			url : '/admin/siteGenel/addAltKategori',
			data : altkategoriJson		
			,success : function() {
				kategoriIdForUpdate="";
				altKategoriJson = [];
				subaltkategoriJson=[];
			}
		});	
	}
	

	$.kategoriEndLink = function(_altKategoriCount){
		/*
			for(k=0 ; k<altKategoriCount ; k++){				
				var sayac = 0;
				var subSayac = 0;
				$("#altKategori_"+k).find("input").each(function(){
					if(subSayac === 0){
						subSayac = 1;
						return;
					}				
						if(sayac===0){							
							
							if($("#altKategori_"+k).find("input").length > 2){
								_jsonSubAltKategori = '"subAltKategori" : [{"kategoriAd" : "'+$(this).val()+'"},';	
							}else if($("#altKategori_"+k).find("input").length < 2){
								_jsonSubAltKategori = '"subAltKategori" : [{"kategoriAd" : "'+$(this).val()+'"}';	
							}
															
						}
						if(sayac !==0 && sayac < $("#altKategori_"+k).length){
							_jsonSubAltKategori = _jsonSubAltKategori+ ',{"kategoriAd" : "'+$(this).val()+'"';
							
						}
						if(sayac === $("#altKategori_"+k).length){
							_jsonSubAltKategori = _jsonSubAltKategori + '{"kategoriAd" : "'+$(this).val()+'"}]}';
							
						}						
					_jsonAltKategori = 	_jsonAltKategori + _jsonSubAltKategori;								
					sayac++;
										
				});
				
				if(k===0){
					_jsonAltKategori = '"altKategori" : [{"kategoriAd" : "'+$("input[name=altKategoriName_"+k+"]").val()+'",';
				}else if(k !== 0 && k < altKategoriCount){
					_jsonAltKategori = ',{"kategoriAd" : "'+$("input[name=altKategoriName_"+k+"]").val()+'",';
				}else if(k === altKategoriCount){
					_jsonAltKategori = ',{"kategoriAd" : "'+$("input[name=altKategoriName_"+k+"]}").val()+'"}]';
				}
				_jsonAltKategori = _jsonAltKategori + _jsonSubAltKategori;
				_jsonKategori = _jsonKategori + _jsonAltKategori;
				sayac = 0;
				
			
			}
			var kategoriAd = $("#kategoriAd").val();
			_jsonKategori = '{"kategoriAd" : "'+kategoriAd+'",' + _jsonKategori+"]}";
			console.log(_jsonKategori);
				/*
			var sayac=0;
				if(k===0){					
	
					$("#altKategori_"+k).find("input").each(function(){
						if(sayac===0){
							_jsonSubAltKategori ='"subAltKategori" :[{"kategoriAd":'+'{"'+$(this).val()+'"}';
						}else if (sayac !== 0 && sayac < $("#altKategori_"+k).find("input").length){
							_jsonSubAltKategori =',{"kategoriAd" : '+ $(this).val()+'}"';
						}else if(sayac === $("#altKategori_"+k).find("input").length){
							_jsonSubAltKategori ='{"kategoriAd" : "'+ $(this).val()+'}"]';
						}
						_jsonAltKategori = _jsonAltKategori + _jsonSubAltKategori;
						sayac++;
					});
				}
					
				}
					console.log(_jsonAltKategori);
					*/
			}
			 
		
	
	

	$.kategoriUpdateClick=function(url){
		$("#modal_updateKategori").modal();
		
		$("#altKategoriInput").html("");
		$.get(server+url).done(function(data){
			var inputHtml="<form method='POST' action=/siteUpdate/kategoriUpdate/"+data.id+">";
			var inputHtmlSubKat = "";
			var inputHtmlAltKat = "";
			
			console.log(data);
			$("#anaKategoriAdiUpdate").val(data.kategoriAd);
			for(i in data.altKategori){
				
				inputHtmlAltKat ="<input type='text' name='altKategori' value='"+data.altKategori[i].altKategoriAd+"'/><br/>";
				
				
				for(sub in data.altKategori[i].subaltKategori){
					//alert("deneme");
					
	
					inputHtmlSubKat=inputHtmlSubKat+"<input type='text'name='subaltkategori' value='"+data.altKategori[i].subaltKategori[sub].subAltKategoriAd+"'/><br/>";
					
					
				}
					inputHtmlAltKat = inputHtmlAltKat + inputHtmlSubKat;
					inputHtml = inputHtml + inputHtmlAltKat;
					inputHtmlAltKat="";
					inputHtmlSubKat="";
					
				console.log(data.altKategori[i].altKategoriAd);
			}
			inputHtml = inputHtml+ inputHtmlAltKat+"<input type='text' name='anaKategoriName' value='"+data.kategoriAd+"' /><br/><input type='submit' value='Update'/></form>";
			$("#altKategoriInput").append(inputHtml);
			console.log(inputHtml);
			
		});	
		inputHtml="";
	}
	
	$.clearInputReklam = function(){
		$("#reklamAd").val("");
		$("#reklamLink").val("")
		$("#reklamPosition").val("");
	}
	
	$.reklamUpdateClick = function(url){
		$("#modalReklam").modal();
		
		$.get(server+url).done(function(data){
			alert(data);
			$("#reklamAd").val(data.reklamAd);
			$("#reklamLink").val(data.reklamLink);
			$('#containerReklam').attr('action', '/siteUpdate/reklamEdit/'+data.id);
		});
		
		
		
	}
	
	$.messageSent = function(url){
		$("#messageModal").modal();
		$("#firmaMail").text("");
		$("#firmaAd").text("");
		$("#firmaSahip").text("");
		$("#firmaId").text("");
		$.get("http://localhost:8080/"+url).done(function(data){
			$("#firmaMail").text(data.email);
			$("#firmaAd").text(data.firmaName);
			$("#firmaSahip").text(data.firmaOwner);
			$("#firmaId").val(data.id);
		});
		
		
	}
	
	$.referansReview = function(url){
		$("#referansModal").modal();
		var htmlText =""; 
		$.get(server+url).done(function(data){
			htmlText = "Firma Adı : " + data.firmaName+"<br/>";
			alert(data.firmaName);
			for(referans in data.referansList){
				htmlText = htmlText+ "Referans adi : " + data.referansList[referans].referansAdi + "<br/>"+
				"Referans Link : " + data.referansList[referans].referansLink+"<br/>"+
				"Referans Resim : <img src='' /><br/>" ;
			}
			$("#referansModalBody").html(htmlText);
		});
		
	}
	var kategori_id_;
	var kategori_ad_;
	
	var altkategori_id_;
	var altkategori_ad_;
	
	var subaltkategori_ad_;
	var subaltkategori_id_;
	$.kategoriSeoEkle = function(kategori_id){
		kategori_id_=kategori_id;
		
		$("#modalSeoEkleForKategori").modal();
		$.get(server+"admin/siteGenelEdit/kategoriEdit/"+kategori_id).done(function(data){
			kategori_ad_=data.kategoriAd;
			$("#seoEkleKategoriName").html(data.kategoriAd +" KATEGORİ'si için SEO");
		});
		//lert(kategori_id);
	}
	
	$.kategoriSeoDuzenle = function(kategori_id){
		$("#modalSeoUpdateForKategori").modal();
		
		$.get(server+"admin/siteGenelEdit/kategoriEdit/"+kategori_id).done(function(data){
			//alert(data.kategoriAd);
			$("#seoUpdateKategoriName").html(data.kategoriAd +"KATEGORİ'si için SEO Düzenleme");
		});
		//alert(kategori_id);
	}
	
	$.altkategoriSeoEkle = function(kategori_id,altkategori_id){
		$("#modalSeoEkleForAltKategori").modal();
		$("#modalSeoUpdateForAltKategori").modal('hide');
		var kategoriAd;
		$.get(server+"admin/siteGenelEdit/kategoriEdit/"+kategori_id).done(function(data){
			kategori_id_=kategori_id;
			
			kategori_ad_= data.kategoriAd;
			
			kategoriAd = data.kategoriAd;
			
			$("#seoEkleAltKategoriName_Kat").text(kategoriAd);
		});
	
		$.get(server+"admin/siteGenelEdit/altKategoriEdit/"+altkategori_id).done(function(data){
			//alert(data.altKategoriAd);
			altkategori_id_ = altkategori_id;
			altkategori_ad_=data.altKategoriAd;
			$("#seoEkleAltKategoriName").html(data.altKategoriAd +" ALT KATEGORİ'si için SEO");
		});
		//alert(kategori_id+" sdsdf"+altkategori_id);
	}
	
	$.altkategoriSeoDuzenle = function(kategori_id,altkategori_id){
		$("#modalSeoUpdateForAltKategori").modal();
		var kategoriAd;
		$.get(server+"admin/siteGenelEdit/kategoriEdit/"+kategori_id).done(function(data){
			kategoriAd = data.kategoriAd;
			$("#seoUpdateAltKategoriName_Kat").text(kategoriAd);
		});
		var altKat_id;
		$.get(server+"admin/siteGenelEdit/altKategoriEdit/"+altkategori_id).done(function(data){
			//alert(data.altKategoriAd);
			$("#seoUpdateAltKategoriName").html(data.altKategoriAd +" ALT KATEGORİ'si için SEO Düzenleme");
			
		});
		
		$.get(server+"admin/siteGenelEdit/altKategoriSeoReview/"+altkategori_id).done(function(data){
			//alert(data.altKategoriAd);
			var altKategoriSeoView='<table class="table">'+
				'<tr><th>META NAME</th><th>META CONTENT</th></tr>';
			for(alt in data){
				altKategoriSeoView = altKategoriSeoView +
				'<tr><td>' + data[alt].metaName+'</td><td>'+
				data[alt].metaContent+'</td>'+
				'<td><input type="hidden" name="id" value="'+data[alt].id+'"/><a onclick="$.deleteAltKategoriSeo('+altkategori_id+','+data[alt].id+');" href="#">Sil</a></td>'+
				'</tr>';
			}
			altKategoriSeoView = altKategoriSeoView +'<tr>'+
			'<td><a href="#" onclick="$.altkategoriSeoEkle('+kategori_id+','+altkategori_id+')">Yeni Ekle</a></td></tr></table>';
			$("#altKategoriSeoView").html(altKategoriSeoView);
		});
		
		//alert(kategori_id +"sdfsf "+ altkategori_id);
	}
	
	$.deleteAltKategoriSeo = function(altkategori_id,altKategoriSeoId,){
		//alert(altKategoriSeoId);
		var url=server+"admin/siteGenelEdit/deleteAltKategoriSeo/"+altkategori_id+"/"+altKategoriSeoId;
		
		$.post(url, function(data) {
			  $("#modalSeoUpdateForAltKategori").modal('hide');
			});
		 location.reload();
	}
	$.subaltkategoriSeoEkle = function(kategori_id,altkategori_id,subaltkategori_id){
		$("#modalSeoUpdateForSubAltKategori").modal('hide');
		$("#modalSeoEkleForSubAltKategori").modal();
		var kategoriAd;
		$.get(server+"admin/siteGenelEdit/kategoriEdit/"+kategori_id).done(function(data){
			kategoriAd = data.kategoriAd;
			kategori_id_=data.id;
			kategori_ad_=data.kategoriAd;
			$("#seoEkleSubAltKategoriName_Kat").text(kategoriAd);

		});
		
		var altKategoriAd;
		$.get(server+"admin/siteGenelEdit/altKategoriEdit/"+altkategori_id).done(function(data){
			altKategoriAd = data.altKategoriAd;
			altkategori_ad_=data.altKategoriAd;
			altkategori_id_=data.id;
			//console.log(altkategori_id_);
			//console.log(altkategori_ad_);
			$("#seoEkleSubAltKategoriName_AltKat").text(altKategoriAd);
		});
		
		
		
		$.get(server+"admin/siteGenelEdit/subAltKategoriEdit/"+subaltkategori_id).done(function(data){
			//alert(data.subAltKategoriAd);
			subaltkategori_id_=data.id;
			subaltkategori_ad_=data.subAltKategoriAd;
			$("#seoEkleSubAltKategoriName").html(data.subAltKategoriAd +" İKİNCİ ALT KATEGORİ'si için SEO");
			//console.log(subaltkategori_id_);
			//console.log(subaltkategori_ad_);
		});
		//alert(kategori_id+" sdsdf"+altkategori_id+" dsdfds"+subaltkategori_id);
	}
	
	$.subaltkategoriSeoDuzenle = function(kategori_id,altkategori_id,subaltkategori_id){
		$("#modalSeoUpdateForSubAltKategori").modal();
		var kategoriAd;
		$.get(server+"admin/siteGenelEdit/kategoriEdit/"+kategori_id).done(function(data){
			kategoriAd = data.kategoriAd;
			
			$("#seoUpdateSubAltKategoriName_Kat").text(kategoriAd);
		});
		
		var altKategoriAd;
		$.get(server+"admin/siteGenelEdit/altKategoriEdit/"+altkategori_id).done(function(data){
			altKategoriAd = data.altKategoriAd;
			/*
			altkategori_ad_= data.altKategoriAd;
			altkategori_id_=altkategori_id;
			*/
			$("#seoUpdateSubAltKategoriName_AltKat").text(altKategoriAd);
		});
		$.get(server+"admin/siteGenelEdit/subAltKategoriEdit/"+subaltkategori_id).done(function(data){

			$("#seoUpdateSubAltKategoriName").html(data.subAltKategoriAd +" İKİNCİ ALT KATEGORİ'si için SEO Düzenleme");
		});
		
		$.get(server+"admin/siteGenelEdit/subAltKategoriSeoReview/"+subaltkategori_id).done(function(data){
			//alert(data.altKategoriAd);
			var subAltKategoriSeoView='<table class="table">'+
				'<tr><th>META NAME</th><th>META CONTENT</th></tr>';
			for(subalt in data){
				subAltKategoriSeoView = subAltKategoriSeoView +
				'<tr><td>' + data[subalt].metaName+'</td><td>'+
				data[subalt].metaContent+'</td>'+
				'<td><input type="hidden" name="id" value="'+data[subalt].id+'"/><a onclick="$.deleteSubAltKategoriSeo('+subaltkategori_id+','+data[subalt].id+');" href="#">Sil</a></td>'+
				'</tr>';
			}
			subAltKategoriSeoView = subAltKategoriSeoView +'<tr>'+
			'<td><a href="#" onclick="$.subaltkategoriSeoEkle('+kategori_id+','+altkategori_id+','+subaltkategori_id+')">Yeni Ekle</a></td></tr></table>';
			$("#subAltKategoriSeoView").html(subAltKategoriSeoView);
		});
		//alert(kategori_id +"sdfsf "+ altkategori_id+"sdfsd"+subaltkategori_id);
	}
	
	$.deleteSubAltKategoriSeo = function(subaltkategori_id,subAltSeoId){
		var url=server+"admin/siteGenelEdit/deleteSubAltKategoriSeo/"+subaltkategori_id+"/"+subAltSeoId;
		
		$.post(url, function(data) {
			  $("#modalSeoUpdateForSubAltKategori").modal('hide');
			});
		 location.reload();
	}
	
	$.submitKategoriSeo=function(){
		$("#kategori_id").val(kategori_id_);
		$("#kategori_ad").val(kategori_ad_);
		kategori_id_="";
		kategori_ad_="";
	}
	
	$.submitAltKategoriSeo=function(){
		$("#_altkategori_id").val(kategori_id_);
		$("#_altkategori_ad").val(kategori_ad_);
		
		$("#altkategori_id").val(altkategori_id_);
		$("#altkategori_ad").val(altkategori_ad_);	
		//console.log("kat id:"+kategori_id_ + "kat ad :" + kategori_ad_ + "altkategori id :" + altkategori_id_+" altkat ad :" +altkategori_ad_);
		alert("asda");
		kategori_id_="";
		kategori_ad_="";
		altkategori_id_="";
		altkategori_id_="";
	}
	
	$.submitSubAltKategoriSeo = function (){
		$("#subaltkategori_id").val(kategori_id_);
		$("#subaltkategori_ad").val(kategori_ad_);
		
		$("#subaltkategori_id_").val(altkategori_id_);
		$("#subaltkategori_ad_").val(altkategori_ad_);
		
		$("#_subaltkategori_id").val(subaltkategori_id_);
		$("#_subaltkategori_ad").val(subaltkategori_ad_);
		//alert("kat id " + kategori_id_+" alt kat id" + altkategori_id_ + "sub alt kat id" + subaltkategori_id_);
		kategori_id_="";
		kategori_ad_="";
		altkategori_id_="";
		altkategori_ad_="";
		subaltkategori_id_="";
		subaltkategori_ad_="";
		return false;
		
	}
	

	

});