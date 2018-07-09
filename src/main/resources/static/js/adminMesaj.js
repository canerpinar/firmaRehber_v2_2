/**
 * http://usejsdoc.org/
 */
$(document).ready(function(){
	var server="http://localhost:8080/";
	$.mesajRead = function(mesaj){


		$("#"+mesaj+"_count").text("");
		$("#"+mesaj).css("background-Color","white");

		
		$.post(server+"admin/firma/readMessage/"+mesaj).done(function(data){
		});
	}
	$.sendAnswerMessage = function(index){

		var answer=$("#answerContent"+index).val();
		var mesajKime = $("#mesajKime"+index).val();

		$.ajax({
			method :'post',
			url :'http://localhost:8080/admin/firma/sendMessageAnswer/'+mesajKime,
			data : {
				answer : $("#answerContent"+index).val(),
				mesajKime : $("#mesajKime"+index).val(),
				mesajKimden :$("#mesajKimden"+index).val(),
				mesajAlanId : $("#mesajAlanId"+index).val()
			},
			dataType : 'html',
			success:function(){
				alert("başarılı");
			}
		});

		return false;
/*		$.post(server+"firma/admin/sendMessageAnswer/"+mesaj).done(function(data){
		});*/
	}
	
	
});