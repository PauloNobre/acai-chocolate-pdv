$(document).ready(function(){
	
	var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
	
    $("#btn-abrir-caixa").click(function() {
    	var abertura = $("#abertura-caixa").val();
    	
    	$.ajax({
    		type:"post",
    		data:{abertura : abertura},
    		url: "/caixa/abrir",
    		beforeSend: function(request){request.setRequestHeader(header, token)},
    		dataType: "json",
    		success: function(data){
    			window.location.href = data.url;
    		}
    	});
    });
})