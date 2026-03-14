package com.ecommerce.ecommerceGenerico.controller;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerceGenerico.entityes.ProdutoEntity;
import com.ecommerce.ecommerceGenerico.service.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	ProdutoService produtoService;
	
	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}
	
	@Operation(summary = "adiciona um novo produto com : nome, preço e estoque")
	@PostMapping
	public ProdutoEntity adicionarNovoProduto(
			@RequestParam String nome,
			@RequestParam Double preco,
			@RequestParam Integer estoque
			) {
		return produtoService.adicionarNovoProduto(nome, preco , estoque);
	}
	
	@Operation(summary = "lista todos os produtos da empresa")
	@GetMapping
	public Iterable<ProdutoEntity> listarTodosProdutos() {
		return produtoService.listaTodosProdutos();
	}
	
	@Operation(summary = "recebe o id do produto a atualizar e altera : nome ou preco ou estoque")
	@PutMapping
	public ProdutoEntity atualizarProduto(
			@RequestParam Long id, 
			@RequestParam String nome,
			@RequestParam Double preco,
			@RequestParam Integer estoque) {
		
		return produtoService.atualizaProduto(id, nome, preco, estoque);
	}
	
	@Operation(summary = "deleta um produto com base no seu id")
	@DeleteMapping("/{idProduto}")
	public String deletarProduto(@PathVariable Long idProduto) {
		
		return produtoService.deletaProduto(idProduto);
	}
	
	@Operation(summary = "deleta todos os produtos da base de dados")
	@DeleteMapping
	public String deletarTodos() {
		return produtoService.deletarTodos();
	}
}
