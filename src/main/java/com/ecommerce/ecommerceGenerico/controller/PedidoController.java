package com.ecommerce.ecommerceGenerico.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerceGenerico.entityes.PedidoEntity;
import com.ecommerce.ecommerceGenerico.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/usuario/pedido")
public class PedidoController {

	PedidoService pedidoService;
	
	public PedidoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}
	
	@Operation(summary = "aqui cria um pedido, apaga os itens do carrinho selecioanado e ainda diminui o estoque dos produtos")
	@PostMapping("/{usuarioId}")
	public PedidoEntity criarPedido(@PathVariable Long usuarioId) {
		return pedidoService.criaPedido(usuarioId);
	}
	
	@Operation(summary = "lista todos pedidos já feitos de usuários que já passaram pela empresa")
	@GetMapping()
	public List<PedidoEntity> listarPedidos() {
		return pedidoService.listarPedidos();
	}
}
