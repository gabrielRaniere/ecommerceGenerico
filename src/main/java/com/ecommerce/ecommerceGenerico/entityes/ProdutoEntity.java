package com.ecommerce.ecommerceGenerico.entityes;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class ProdutoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.REMOVE)
	List<ItemEntity> item = new ArrayList<ItemEntity>();
			
	@NotNull(message = "você deve enviar o nome do produto..")
	private String nome;
	
	@Positive
	private Double preco = 0.0;
	
	@Positive
	private Integer estoque = 0;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}
	
	public Long getId() {
		return id;
	}

	public ProdutoEntity() {
	}
	
	public ProdutoEntity(String nome, Double preco, Integer estoque) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.estoque = estoque;
	}
	
	
}
