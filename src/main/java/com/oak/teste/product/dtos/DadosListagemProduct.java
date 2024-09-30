package com.oak.teste.product.dtos;

import com.oak.teste.product.Product;

public record DadosListagemProduct(
		
		String nome,
		
		Double valor) {
	
	public DadosListagemProduct(Product product) {
		this(product.getNome(), product.getValor());
	}

}
