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

import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	ProdutoService produtoService;
	
	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}
	
	@PostMapping
	public ProdutoEntity adicionarNovoProduto(
			@RequestParam String nome,
			@RequestParam Double preco,
			@RequestParam Integer estoque
			) {
		return produtoService.adicionarNovoProduto(nome, preco , estoque);
	}
	
	@GetMapping
	public Iterable<ProdutoEntity> listarTodosProdutos() {
		return produtoService.listaTodosProdutos();
	}
	
	@PutMapping
	public ProdutoEntity atualizarProduto(
			@RequestParam Long id, 
			@RequestParam String nome,
			@RequestParam Double preco,
			@RequestParam Integer estoque) {
		
		return produtoService.atualizaProduto(id, nome, preco, estoque);
	}
	
	@DeleteMapping("/{idProduto}")
	public String deletarProduto(@PathVariable Long idProduto) {
		
		return produtoService.deletaProduto(idProduto);
	}
	
	@DeleteMapping
	public String deletarTodos() {
		return produtoService.deletarTodos();
	}
}
