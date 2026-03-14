package com.ecommerce.ecommerceGenerico.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerceGenerico.entityes.ItemEntity;
import com.ecommerce.ecommerceGenerico.service.CarrinhoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/usuario/carrinho/{carrinhoId}")
public class CarrinhoController {

	CarrinhoService carrinhoService;
	
	public CarrinhoController(CarrinhoService carrinhoService) {
		this.carrinhoService = carrinhoService;
	}
	
	@Operation(summary = "lista todos os itens do carrinho do usuário atual")
	@GetMapping()
	public List<ItemEntity> listarItensCarrinho(@PathVariable Long carrinhoId) { 
		return carrinhoService.listarItensCarrinho(carrinhoId);
	}
	
	@Operation(summary = "adiciona ao carrinho do usuário atual um novo item(produto + quantidade)")
	@PostMapping()
	public ItemEntity
	adicionarItemCarrinho(
			@PathVariable Long carrinhoId, 
			@RequestParam Long produtoId,
			@RequestParam Integer quantidade
			) {
		return carrinhoService.adicionarItemCarrinho(carrinhoId, produtoId, quantidade);
	}
}
