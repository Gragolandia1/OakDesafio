package com.oak.teste.product.dtos;

import com.oak.teste.product.enums.Disponivel;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroProduct(
		
		@NotBlank
		String nome,
		
		@NotBlank
		String descricao,
		
		@NotNull
		Double valor,
		
		@Enumerated
		Disponivel disponivel) {

}
