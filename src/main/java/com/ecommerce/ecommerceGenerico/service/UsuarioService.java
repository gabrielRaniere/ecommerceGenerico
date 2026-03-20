package com.ecommerce.ecommerceGenerico.service;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecommerce.ecommerceGenerico.entityes.CarrinhoEntity;
import com.ecommerce.ecommerceGenerico.entityes.UsuarioEntity;
import com.ecommerce.ecommerceGenerico.exception.excesoesPersonalizadas.UsuarioException;
import com.ecommerce.ecommerceGenerico.repository.UserRepository;


@Service
public class UsuarioService {
	
	UserRepository userRepository;
	
	
	public UsuarioService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	private boolean validaSenha(String senha) {
		if(senha.length() < 8) {
			return false;
		}
		
		return true;
	}
	
	public void adicionaUsuario(String nome, String email, String senha) {
		
		if(!validaSenha(senha)) {
			// lança erro
			throw new UsuarioException("senha deve ter pelo menos 8 caracteres entre números e letras.");
		}
		// as demais validações já são feitas com o java.jakarta.validation
		
		UsuarioEntity usuario = new UsuarioEntity(nome, email, senha, OffsetDateTime.now());

		CarrinhoEntity carrinho = new CarrinhoEntity();

		usuario.setCarrinhoEntity(carrinho);

		userRepository.save(usuario);
		
	}


	public Iterable<UsuarioEntity> listaUsuarios() {
		
		return userRepository.findAll();
	}

	public UsuarioEntity atualizaUsuario(Long userId,
			Optional<String> nome, Optional<String> email, Optional<String> senha) {
		
		UsuarioEntity usuario = userRepository.findById(userId)
				.orElseThrow(() -> new UsuarioException("usuário não encontrado."));
		
		nome.ifPresent(nomeReal -> usuario.setNome(nomeReal));
		email.ifPresent(emailReal -> usuario.setEmail(emailReal));
		senha.ifPresent(senhaReal -> usuario.setSenha(senhaReal));
		
		userRepository.save(usuario);
		
		return usuario;
	}

	public String removeUsuario(Long userId) {
		
		if(userId == null) {
			throw new IllegalArgumentException("id não pode ser nulo");
		}
		
		userRepository.deleteById(userId);
		
		return "usuário excluído com sucesso.";
	}
}
