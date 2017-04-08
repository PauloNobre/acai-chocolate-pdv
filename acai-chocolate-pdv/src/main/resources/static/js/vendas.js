$(document).ready(function() {
	
	var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    
    var idVenda = $("#id-venda").val();
    
    $("#codigo").focus();
    
    carregarTabelaItens();
    
    //Realiza uma busca por um produto após o código ser informado
	$("#codigo").focusout(function() {
		var codigo = $("#codigo").val();
		
		if(codigo != '') {
			$.ajax({
				type:"post",
				data:{codigo: codigo},
				url: "/produto/buscar",
				beforeSend: function(request){request.setRequestHeader(header, token)},
				dataType: "json",
				success: function(data){
					if(data.produto) {
						preencherCampos(data.produto);
					} else {
						console.log("Deu erro");
						//falta tratar
					}
				}
			});
		}
	});
	
	//preenche campos após buscar um produto
	function preencherCampos(produto) {
		$("#produto").val(produto.nome);
		var valor = produto.valor;
		$("#valor-produto").val(valor.toFixed(2));
		ativarBotao();
	}
	
	//funnção para ativar botão de adicionar produto
	function ativarBotao() {
		$("#btn-adicionar").removeAttr("disabled");
	}
	
	//Funcao para desativar o botao de adicionar produto
	function desativarBotao() {
		$("#btn-adicionar").attr("disabled","disabled");
	}
	
	//Ao clicar em adicionar um produto
	$("#btn-adicionar").click(function() {
		var codigo = $("#codigo").val();
		var quantidade = $("#quantidade").val();
		
		$.ajax({
			type:"post",
			data:{	idVenda: idVenda,
					codigo: codigo,
					quantidade: quantidade
					},
			url: "/venda/novo-item-venda",
			beforeSend: function(request){request.setRequestHeader(header, token)},
			dataType: "json",
			success: function(data){
				window.location.href = data.url;
			}
		});
		
		limparCampos();
	});
	
	//Reseta os campos do form após adicionar um item
	function limparCampos() {
		$("#form-item-venda")[0].reset();
		desativarBotao();
		$("#codigo").focus();
	}
	
	//Carrega uma tabela com a lista de itens adicionados a venda
	function carregarTabelaItens(){
		var url = "/venda/carregar-tabela/" + idVenda;
		
		$("#div-table-itens").load(url, function(){})
	}
	
	//Concluir compra
	$("#btn-concluir").click(function(){
		var url = "/venda/concluir/" + idVenda;
		var comanda = $("#comanda").val();
		
		$.ajax({
			type:"post",
			data:{comanda: comanda},
			url: url,
			beforeSend: function(request){request.setRequestHeader(header, token)},
			dataType: "json",
			success: function(data){
				 window.location.href = data.url;
			}
		});
	})
	
	//ir para pagamento
	$("#btn-finalizar").click(function() {
		var valor = $("#valor-venda").val();
		valor =  Number(valor.replace(/[^0-9\.]+/g,""));
		$("#total-modal").val(valor.toFixed(2));
		
		$("#desconto-modal").val(0);
		$("#total-pagar-modal").val(valor.toFixed(2));
	});
	
	//Altera valor total a pagar
	$("#desconto-modal").change(function() {
		var total = $("#total-modal").val();
		var desconto = $("#desconto-modal").val();
		total = Number(total.replace(/[^0-9\.]+/g,""));
		desconto = Number(desconto.replace(/[^0-9\.]+/g,""));
		
		totalPagar = total - desconto;
		$("#total-pagar-modal").val(totalPagar.toFixed(2));
	});
	
	//Finalizar compra
	$("#btn-confirm-modal").click(function() {
		var url = "/venda/finalizar/" + idVenda;
		var desconto = $("#desconto-modal").val();
		
		$.ajax({
			type:"post",
			data:{desconto: desconto},
			url: url,
			beforeSend: function(request){request.setRequestHeader(header, token)},
			dataType: "json",
			success: function(data){
				 window.location.href = data.url;
			}
		});
	});
	
	//Comando Ctrl + space para pesagem
//	var pressedCtrl = false;
//	 $(document).keyup(function(e) {
//	 	if(e.which == 17){
//	 		pressedCtrl=false;
//	 	};
//	 });
//	$(document).keydown(function(e) { 
//		if(e.which == 17) {
//			pressedCtrl = true;
//		};
//		if((e.which == 32|| e.keyCode == 32) && pressedCtrl == true) {
//			alert('O comando Crtl+Enter foi acionado')
//		 } 
//	});
	
	//Comando Ctrl + E para excluir item
	var pressedCtrl = false;
	 $(document).keyup(function(e) {
	 	if(e.which == 17){
	 		pressedCtrl = false;
	 	};
	 });
	$(document).keydown(function(e) { 
		if(e.which == 17) {
			pressedCtrl = true;
		};
		if((e.which == 49|| e.keyCode == 49) && pressedCtrl) {
			$("#modal-deletar-item").modal("toggle");
		 } 
	});
	
	$("#btn-delete-item").click(function() {
		$("#modal-deletar-item").modal("toggle");
		var indice = $("#numero-item").val();
		var url = "/venda/deletar-item/" + idVenda;
		
		if(indice) {
			$.ajax({
				type:"post",
				data:{indice: indice},
				url: url,
				beforeSend: function(request){request.setRequestHeader(header, token)},
				dataType: "json",
				success: function(data){
					 window.location.href = data.url;
				}
			});
		}
	});
	
	$("#btn-confirmar-cancelar").click(function() {
		$("#modal-cancelar").modal("toggle");
		var url = "/venda/cancelar/" + idVenda;
		
		$.ajax({
			type:"post",
			url: url,
			beforeSend: function(request){request.setRequestHeader(header, token)},
			dataType: "json",
			success: function(data){
				 window.location.href = data.url;
			}
		});
	});
});