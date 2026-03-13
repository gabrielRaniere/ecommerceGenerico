package com.ecommerce.ecommerceGenerico.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerceGenerico.entityes.PedidoEntity;
import com.ecommerce.ecommerceGenerico.service.PedidoService;

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
	
	@PostMapping("/{usuarioId}")
	public PedidoEntity criarPedido(@PathVariable Long usuarioId) {
		return pedidoService.criaPedido(usuarioId);
	}
	
	@GetMapping()
	public List<PedidoEntity> listarPedidos() {
		return pedidoService.listarPedidos();
	}
}
