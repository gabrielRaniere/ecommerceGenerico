package com.ecommerce.ecommerceGenerico.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecommerce.ecommerceGenerico.entityes.ProdutoEntity;
import com.ecommerce.ecommerceGenerico.exception.excesoesPersonalizadas.ProdutoException;
import com.ecommerce.ecommerceGenerico.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	ProdutoRepository produtoRepository;
	
	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	public ProdutoEntity adicionarNovoProduto(String nome, Double preco, Integer estoque) 
	throws ProdutoException {
		// verifica se o nome já existe na base
		if(listaTodosProdutos()
		.stream()
		.map(produto -> produto.getNome().toLowerCase())
		.collect(Collectors.toList()).contains(nome.toLowerCase())) {
			// LANÇA ERRO: PRODUTO JÁ EXISTE
			throw new ProdutoException("produto já existente na loja.");
		}
		
		ProdutoEntity produto = new ProdutoEntity(nome, preco, estoque);
		produtoRepository.save(produto);
		
		return produto;
	}
	
	public List<ProdutoEntity> listaTodosProdutos() {
		return produtoRepository.findAll();
	}
	
	public ProdutoEntity atualizaProduto(Long id, String nome, Double preco, Integer estoque) 
	throws ProdutoException{
		
		Optional<ProdutoEntity> produtoAtualizar = produtoRepository.findById(id);
		
		if(produtoAtualizar.isEmpty()) {
			// LANÇA ERRO DE PRODUTO NÃO ENCONTRADO !
			throw new ProdutoException("Produto não encontrado.");
			
		}
		
		produtoAtualizar.ifPresent(produto -> {
			produto.setNome(nome);
			produto.setPreco(preco);
			produto.setEstoque(estoque);
			
			produtoRepository.save(produto);
		});
		
		return produtoAtualizar.orElse(null);
	}
	
	public String deletaProduto(Long id) throws ProdutoException {
		
		Optional<ProdutoEntity> produto = produtoRepository.findById(id);
		
		if(produto.isEmpty()) {			
			throw new ProdutoException("produto não encontrado.");
		} else {
			produtoRepository.deleteById(id);
			return "produto excluído com sucesso !";
		}
		
	}

	public String deletarTodos() {
		
		produtoRepository.deleteAll();
		
		return "Todos produtos foram apagados !";
	}
	
	
	
}
