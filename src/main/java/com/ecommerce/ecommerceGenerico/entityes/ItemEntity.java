package com.ecommerce.ecommerceGenerico.entityes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class ItemEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@ManyToOne
	@JoinColumn(name = "produto_id", referencedColumnName = "id")
	@NotNull
	private ProdutoEntity produto;
	
	@ManyToOne
	@JoinColumn(name = "carrinho_id", referencedColumnName = "id")
	@NotNull
	@JsonIgnore
	private CarrinhoEntity carrinho;
	
	@Positive(message = "quantidade deve ser positiva !")
	private Integer quantidade;
	
	public ItemEntity() {}

	public ItemEntity(ProdutoEntity produto, CarrinhoEntity carrinho, Integer quantidade) {
		super();
		this.produto = produto;
		this.carrinho = carrinho;
		this.quantidade = quantidade;
	}

	public ProdutoEntity getProduto() {
		return produto;
	}

	public void setProduto(ProdutoEntity produto) {
		this.produto = produto;
	}

	public CarrinhoEntity getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(CarrinhoEntity carrinho) {
		this.carrinho = carrinho;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		
		this.quantidade = quantidade;
	}

	public Long getId() {
		return id;
	}
	
}
