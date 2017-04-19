$(document).ready(function() {

	var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    
    $("#dia-relatorio").datepicker({
        format: "dd-mm-yyyy",
        autoclose: true,
        endDate: "0d",
        language: "pt-BR",
        todayBtn: true,
        todayHighlight: true
        
    })
    
    var hoje = moment().format("DD-MM-YYYY");
	carregarDados(hoje);
    
    function carregarDados(data) {
    	$("#titulo-data").text("Estatísticas: " + data);
    	carregarGrafico(data);
    	carregarTableVendas(data);
    	carregarTableDespesas(data);
    }
	
	//Carrega a lista de vendas
	function carregarTableVendas(data){
		var url = "/financeiro/tabela-vendas/" + data;
		
		$("#table-vendas-fragment").load(url, function(){});
	}
	
	//Carrega a lista de despesas
	function carregarTableDespesas(data){
		var url = "/financeiro/tabela-despesas/" + data;
		
		$("#table-despesas-fragment").load(url, function(){});
	}
	
	//Carrega os dados do grafico
	function carregarGrafico(data){
		var url = "/financeiro/gerar-grafico/" + data;
		
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
		
		for(var i = 0; i < vendas.length; i++) {
			totalVendas += vendas[i].totalPagar;
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
		
		var myChart = new Chart(ctx, {
		    type: 'bar',
		    data: {
		        labels: ["Vendas", "Despesas", "Saldo"],
		        datasets: [{
		        	 label: "Valor R$",
		             data: [totalVendas, totalDespesas, saldoTotal],
		             backgroundColor: [
		                 "rgba(0, 128, 128, 0.5)",
		                 "rgba(255, 0, 0, 0.5)",
		                 "rgba(35, 142, 35, 0.5)"
		             ],
		             borderColor: [
		                 "rgba(0, 128, 128, 1)",
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
	
	$("#visualizar-dia").click(function() {
		var data = $("#dia-relatorio").val();
		if(data){
			carregarDados(data);	
		}
	});
});