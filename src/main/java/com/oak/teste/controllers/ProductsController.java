package com.oak.teste.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.oak.teste.product.Product;
import com.oak.teste.product.ProductRepository;
import com.oak.teste.product.dtos.DadosCadastroProduct;
import com.oak.teste.product.dtos.DadosListagemProduct;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductsController {
	
	@Autowired
	ProductRepository productRepository;	
	

	@PostMapping
	@Transactional
	public ResponseEntity<DadosListagemProduct> create(@RequestBody @Valid DadosCadastroProduct dados, UriComponentsBuilder uriBuilder) {
		
		var product = new Product(dados);
		
		productRepository.save(product);
		
		var uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
				
		return ResponseEntity.created(uri).body(new DadosListagemProduct(product));
	}
	
	@GetMapping
	public ResponseEntity<List<DadosListagemProduct>> listarProdutos() {
		
		var lista = productRepository.findAll(Sort.by(Sort.Direction.ASC, "valor"));
		
		var listaDados = lista.stream().map(product -> new DadosListagemProduct(product)).toList();
		
		return ResponseEntity.ok(listaDados);
	}
}
