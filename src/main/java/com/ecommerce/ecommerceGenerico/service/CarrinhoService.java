package com.ecommerce.ecommerceGenerico.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecommerce.ecommerceGenerico.entityes.CarrinhoEntity;
import com.ecommerce.ecommerceGenerico.entityes.ItemEntity;
import com.ecommerce.ecommerceGenerico.entityes.ProdutoEntity;
import com.ecommerce.ecommerceGenerico.exception.excesoesPersonalizadas.ProdutoException;
import com.ecommerce.ecommerceGenerico.repository.CarrinhoRepository;
import com.ecommerce.ecommerceGenerico.repository.ItemRepository;
import com.ecommerce.ecommerceGenerico.repository.ProdutoRepository;

@Service
public class CarrinhoService {

	// o repositorio que vou acessar vai ser o de itens..
	ItemRepository itemRepository;
	CarrinhoRepository carrinhoRepository;
	ProdutoRepository produtoRepository;
	
	public CarrinhoService( 
			ItemRepository itemRepository,
			CarrinhoRepository carrinhoRepository,
			ProdutoRepository produtoRepository) {
		
		this.itemRepository = itemRepository;
		this.carrinhoRepository = carrinhoRepository;
		this.produtoRepository = produtoRepository;
	}
	
	public List<ItemEntity> listarItensCarrinho(Long carrinhoid) {
		// se id não existir lança erro
		CarrinhoEntity carrinho = carrinhoRepository.findById(carrinhoid).orElseThrow();
		
		return carrinho.getItens();
		
	}

	public ItemEntity adicionarItemCarrinho(Long carrinhoId, Long produtoId, Integer quantidade) {

		ProdutoEntity produto = produtoRepository.findById(produtoId).orElseThrow();
		CarrinhoEntity carrinho = carrinhoRepository.findById(carrinhoId).orElseThrow();
		
		
		boolean produtoJaInserido = carrinho.getItens()
		.stream()
		.map(obj -> obj.getProduto().getId())
		.anyMatch(idProdutoExistente -> idProdutoExistente == produtoId);
		
		if(produtoJaInserido) {
			return incrementaApenasQuantidadeItem(carrinho, produto, quantidade);
		}
		
		// usei o get porque já fiz a validação !!
		
		avaliaSeEstoqueSuficiente(produto, quantidade);
		
		ItemEntity item = new ItemEntity(produto, carrinho, quantidade);
		
		carrinho.getItens().add(item);
		
		itemRepository.save(item);
		
		return itemRepository.save(item);
	}

	private ItemEntity incrementaApenasQuantidadeItem(CarrinhoEntity carrinho, ProdutoEntity produto, Integer quantidade) {
		
		ItemEntity itemExistente = itemRepository.findByCarrinhoAndProduto(carrinho, produto);
		
		avaliaSeEstoqueSuficiente(itemExistente.getProduto(), itemExistente.getQuantidade() + quantidade);
		
		itemExistente.setQuantidade(itemExistente.getQuantidade() + quantidade);
		
		return itemRepository.save(itemExistente);
	}
	
	private void avaliaSeEstoqueSuficiente(ProdutoEntity produto, Integer quantidade) {
		if(produto.getEstoque() < quantidade) {
			// lança erro
			throw new ProdutoException("quantidade de itens ultrapaçou o estoque de " + 
					produto.getEstoque());
		}
	}
}
