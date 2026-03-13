package com.ecommerce.ecommerceGenerico.entityes;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class UsuarioEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	private String nome;
	
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	private String senha;
	
	@NotNull
	private OffsetDateTime dataCadastro;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<PedidoEntity> pedidos = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "carrinho_id", referencedColumnName = "id")
	CarrinhoEntity carrinhoEntity;
	
	
	public UsuarioEntity() {
	}
	

	public UsuarioEntity(String nome, String email, String senha, OffsetDateTime dataCadastro) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.dataCadastro = dataCadastro;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public OffsetDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(OffsetDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}


	public CarrinhoEntity getCarrinhoEntity() {
		return carrinhoEntity;
	}


	public void setCarrinhoEntity(CarrinhoEntity carrinhoEntity) {
		this.carrinhoEntity = carrinhoEntity;
	}


	public List<PedidoEntity> getPedidos() {
		return pedidos;
	}


	public void setPedidos(List<PedidoEntity> pedidos) {
		this.pedidos = pedidos;
	}
	
	
}
