package com.ecommerce.ecommerceGenerico.services;

import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecommerce.ecommerceGenerico.entityes.UsuarioEntity;
import com.ecommerce.ecommerceGenerico.exception.excesoesPersonalizadas.UsuarioException;
import com.ecommerce.ecommerceGenerico.repository.UserRepository;
import com.ecommerce.ecommerceGenerico.service.UsuarioService;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	UsuarioService userService;
	
	@Test
	@DisplayName("testando criar um usuário e caso de sucesso")
	public void testaradicionaUsuariocase1() {
		
		String nome = "Gabriel Raniere";
		String email = "abc@gmail.com";
		String senha = "abcdfghijk1";
		
		userService.adicionaUsuario(nome, email, senha);
		
		UsuarioEntity usuario = new UsuarioEntity(nome, email, senha, OffsetDateTime.now());
		
		// se esse método é chamado, então foi salvo no banco o usuário
		verify(userRepository, times(1)).save(usuario);
	}
	
	@Test
	@DisplayName("testando criar um usuário com senha errada")
	public void testaradicionaUsuariocase2() {
		String nome = "Gabriel Raniere";
		String email = "abc@gmail.com";
		String senha = "abcdfg";
		
		assertThrows(UsuarioException.class, () -> userService.adicionaUsuario(nome, email, senha));
		
		verify(userRepository, times(0)).save(any(UsuarioEntity.class));
	}
	// a verificação de email fica no teste do controller, pois estou usando o jakarta.validation
	
	
	@Test
	@DisplayName("ler todos usuários e retornar os usuários no número correto.")
	public void testaLerTodosUsuarioscase1() {
		
		List<UsuarioEntity> usuarios = List.of(
				new UsuarioEntity("g1", "gabriel@gmail.com", "12345678", OffsetDateTime.now()),
				new UsuarioEntity("g2", "ddd@hotmail.com", "abcdfedd", OffsetDateTime.now())
				);
		
		when(userRepository.findAll()).thenReturn(usuarios);
		
		assertEquals(usuarios, userService.listaUsuarios());
		
		verify(userRepository, times(1)).findAll();
	}
	
	@Test
	@DisplayName("testa atualizar nome com sucesso")
	public void testaAtualizarUsuariocase1() {
		Optional<UsuarioEntity> usuarioRetorno = 
				Optional.ofNullable(new UsuarioEntity("nomeVelho", "gabriel@gmail.com", "12345678", OffsetDateTime.now()));
		
		when(userRepository.findById(1L)).thenReturn(
				usuarioRetorno
				);
		
		UsuarioEntity usuarioAtualizado = 
				userService.atualizaUsuario(1L, Optional.ofNullable("nomeNovo"), Optional.empty(), Optional.empty());
		
		// significa que o nome foi alterado
		assertEquals("nomeNovo", usuarioAtualizado.getNome());
		
		verify(userRepository, times(1)).save(any(UsuarioEntity.class));
	}
	
	@Test
	@DisplayName("testa atualizar TODOS com sucesso")
	public void testaAtualizarUsuariocase2() {
		Optional<UsuarioEntity> usuarioRetorno = 
				Optional.ofNullable(new UsuarioEntity("nomeVelho", "emailVelho@gmail.com", "12345678", OffsetDateTime.now()));
		
		when(userRepository.findById(1L)).thenReturn(
				usuarioRetorno
				);
		
		UsuarioEntity usuarioAtualizado = 
				userService.atualizaUsuario(1L, 
						Optional.ofNullable("nomeNovo"),
						Optional.ofNullable("emailNovo@gmail.com"),
						Optional.ofNullable("abcdefghi"));
		
		// significa que o nome, email e senha foram alterados
		assertArrayEquals(new String[]{"nomeNovo", "emailNovo@gmail.com", "abcdefghi"}, 
				new String[]{usuarioAtualizado.getNome(), usuarioAtualizado.getEmail(), usuarioAtualizado.getSenha()});
		
		verify(userRepository, times(1)).save(any(UsuarioEntity.class));
	}
	
	@Test
	@DisplayName("testa se retorna erro quando id não encontrado para atualizar")
	public void testaAtualizarUsuariocase3() {
		
		assertThrows(UsuarioException.class, () -> {
					userService.atualizaUsuario(1L, 
							Optional.ofNullable("nomeNovo"),
							Optional.ofNullable("emailNovo@gmail.com"),
							Optional.ofNullable("abcdefghi"));
		});
		
		
		verify(userRepository, times(0)).save(any(UsuarioEntity.class));
	}
	
	@Test
	@DisplayName("testa se dá  erro caso id seja null no parametro")
	public void testaRemoverUsuariocase1() { 
		assertThrows(IllegalArgumentException.class, () -> {
			userService.removeUsuario(null);
		});
	}
}
