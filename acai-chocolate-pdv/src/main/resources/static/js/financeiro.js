$(document).ready(function() {

	var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    
    $("#dia-inicio, #dia-fim").datepicker({
        format: "dd-mm-yyyy",
        autoclose: true,
        endDate: "0d",
        language: "pt-BR",
        todayBtn: true,
        todayHighlight: true
        
    })
    
    var hoje = moment().format("DD-MM-YYYY");
	carregarDados(hoje, hoje);
    
    function carregarDados(inicio, fim) {
    	$("#titulo-data").text("Estatísticas: " + inicio + " até " + fim);
    	carregarGrafico(inicio, fim);
    	carregarTableVendas(inicio, fim);
    	carregarTableDespesas(inicio, fim);
    }
	
	//Carrega a lista de vendas
	function carregarTableVendas(inicio, fim){
		var url = "/financeiro/tabela-vendas/" + inicio + "/" + fim;
		
		$("#table-vendas-fragment").load(url, function(){});
	}
	
	//Carrega a lista de despesas
	function carregarTableDespesas(inicio, fim){
		var url = "/financeiro/tabela-despesas/" + inicio + "/" + fim;
		
		$("#table-despesas-fragment").load(url, function(){});
	}
	
	//Carrega os dados do grafico
	function carregarGrafico(inicio, fim){
		var url = "/financeiro/gerar-grafico/" + inicio + "/" + fim;
		
		$.ajax({
			type:"get",
			url: url,
			beforeSend: function(request){request.setRequestHeader(header, token)},
			dataType: "json",
			success: function(data){
				 gerarGrafico(data.vendas, data.despesas);
			}
		});
	}
	
	//Gera o chart
	function gerarGrafico(vendas, despesas) {
		
		var totalVendas = 0;
		var totalDespesas = 0;
		var saldoTotal = 0;
		var custos = 0;
		
		for(var i = 0; i < vendas.length; i++) {
			totalVendas += vendas[i].totalPagar;
			for(var j = 0; j < vendas[i].produtos.length; j++) {
				custos += vendas[i].produtos[j].custo;
			}
		}
		
		for(var i = 0; i < despesas.length; i++) {
			totalDespesas += despesas[i].valor;
		}
		
		totalVendas = totalVendas.toFixed(2);
		totalDespesas = totalDespesas.toFixed(2);
		saldoTotal = totalVendas - totalDespesas;
		
		$("#grafico-vendas").remove();
		$("#div-grafico").append("<canvas id='grafico-vendas'></canvas>");
		
		var ctx = $("#grafico-vendas");
		
		console.log(totalVendas);
		console.log(custos);
		console.log(totalDespesas);
		console.log(saldoTotal);
		
		var myChart = new Chart(ctx, {
		    type: 'bar',
		    data: {
		        labels: ["Vendas", "Custos", "Despesas", "Saldo"],
		        datasets: [{
		        	 label: "Valor R$",
		             data: [totalVendas, custos, totalDespesas, saldoTotal],
		             backgroundColor: [
		                 "rgba(0, 128, 128, 0.5)",
		                 "rgba(255, 215, 0, 0.5)",
		                 "rgba(255, 0, 0, 0.5)",
		                 "rgba(35, 142, 35, 0.5)"
		             ],
		             borderColor: [
		                 "rgba(0, 128, 128, 1)",
		                 "rgba(255, 215, 0, 1)",
		                 "rgba(255, 0, 0, 1)",
		                 "rgba(35, 142, 35, 1)"
		             ],
		             borderWidth: 1
		        }]
		    },
		    options: {
		        scales: {
		            yAxes: [{
		                ticks: {
		                    beginAtZero:true
		                }
		            }]
		        }
		    }
		});
	}
	
	$("#carregar-dados").click(function() {
		var inicio = $("#dia-inicio").val();
		var fim = $("#dia-fim").val();
		if(inicio && fim){
			carregarDados(inicio, fim);	
		}
	});
});