package com.ecommerce.ecommerceGenerico.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.ecommerceGenerico.client.Email;
import com.ecommerce.ecommerceGenerico.entityes.CarrinhoEntity;
import com.ecommerce.ecommerceGenerico.entityes.ItemEntity;
import com.ecommerce.ecommerceGenerico.entityes.PedidoEntity;
import com.ecommerce.ecommerceGenerico.entityes.UsuarioEntity;
import com.ecommerce.ecommerceGenerico.exception.excesoesPersonalizadas.ProdutoException;
import com.ecommerce.ecommerceGenerico.exception.excesoesPersonalizadas.UsuarioException;
import com.ecommerce.ecommerceGenerico.repository.CarrinhoRepository;
import com.ecommerce.ecommerceGenerico.repository.PedidoRepository;
import com.ecommerce.ecommerceGenerico.repository.ProdutoRepository;
import com.ecommerce.ecommerceGenerico.repository.UserRepository;

@Service
public class PedidoService {
	
	PedidoRepository pedidoRepository;
	UserRepository userRepository;
	CarrinhoRepository carrinhoRepository;
	ProdutoRepository produtoRepository;
	
	Email emailService;
	
	public PedidoService(
			PedidoRepository pedidoRepository,
			UserRepository userRepository,
			CarrinhoRepository carrinhoRepository,
			ProdutoRepository produtoRepository,
			Email emailService) {
		this.pedidoRepository = pedidoRepository;
		this.userRepository = userRepository;
		this.carrinhoRepository = carrinhoRepository;
		this.produtoRepository = produtoRepository;
		this.emailService = emailService;
	}
	
	public List<PedidoEntity> listarPedidos() {
		return pedidoRepository.findAll();
	}

	
	public PedidoEntity criaPedido(Long usuarioId) {
		UsuarioEntity usuarioAtual = userRepository.findById(usuarioId)
				.orElseThrow(() -> new UsuarioException("usuario não encontrado."));
		
		List<ItemEntity> itens = usuarioAtual.getCarrinhoEntity().getItens();
		
		Double totalPagar = itens.stream()
				.map(item -> item.getProduto().getPreco() * item.getQuantidade())
				.reduce((ac, n) -> ac + n)
				.orElseThrow();
		
		// diminuir estoque produtos
		diminuiEstoqueProduto(itens);
		
		// apagar itens
		apagaItensCarrinho(usuarioAtual.getCarrinhoEntity());
		
		PedidoEntity pedido = new PedidoEntity(usuarioAtual, OffsetDateTime.now(), totalPagar);
		
		emailService.enviaEmail(usuarioAtual.getEmail(), usuarioAtual.getNome(), totalPagar);
		return pedidoRepository.save(pedido);
	}

	private void diminuiEstoqueProduto(List<ItemEntity> itens) {
		itens.stream().forEach((item) -> {
			int estoqueAntigo = item.getProduto().getEstoque();
			int novoEstoque = estoqueAntigo - item.getQuantidade();
			
			if(novoEstoque < 0 ) {
				throw new ProdutoException("estoque inferior ao número de produtos do carrinho.");
			}
			
			item.getProduto().setEstoque(novoEstoque);
			
			produtoRepository.save(item.getProduto());
		});
	}
	
	private void apagaItensCarrinho(CarrinhoEntity carrinho) {
		carrinho.getItens().clear();
	}

}
