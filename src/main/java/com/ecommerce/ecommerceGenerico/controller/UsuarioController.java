package com.ecommerce.ecommerceGenerico.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.ecommerceGenerico.entityes.UsuarioEntity;
import com.ecommerce.ecommerceGenerico.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public Iterable<UsuarioEntity> listaTodosUsuarios() {
        return usuarioService.listaUsuarios();
    }

    @PostMapping
    public void adicionaUsuario(
            @RequestParam String nome,
            @RequestParam String email,
            @RequestParam String senha
    ) {
        usuarioService.adicionaUsuario(nome, email, senha);
    }
    
    @PutMapping("/{usuarioId}")
    public UsuarioEntity atualizaUsuario(
    		@PathVariable Long usuarioId,
    		@RequestParam Optional<String> nome,
    		@RequestParam Optional<String> email,
    		@RequestParam Optional<String> senha) {
    	return usuarioService.atualizaUsuario(usuarioId, nome, email, senha);
    }
    
    @DeleteMapping("/{usuarioId}")
    public String removeUsuario(@PathVariable Long usuarioId) {
    	return usuarioService.removeUsuario(usuarioId);
    }
}