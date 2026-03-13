package com.ecommerce.ecommerceGenerico.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ecommerce.ecommerceGenerico.exception.excesoesPersonalizadas.ProdutoException;
import com.ecommerce.ecommerceGenerico.exception.excesoesPersonalizadas.UsuarioException;

@ControllerAdvice
public class ReceptorExcessoesGlobais {
	
	@ExceptionHandler(exception = ProdutoException.class)
	public ResponseEntity<ExceptionBody> retornarProdutoExcessao(ProdutoException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionBody(
				e.getMessage(),
				"404"
				));
	}
	
	@ExceptionHandler(exception = UsuarioException.class)
	public ResponseEntity<ExceptionBody> retornaUsuarioExcessao(UsuarioException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionBody(
				e.getMessage(),
				"400"));
	}
	
	@ExceptionHandler(exception = NoSuchElementException.class)
	public ResponseEntity<ExceptionBody> retornaUsuarioExcessao(NoSuchElementException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionBody(
				e.getMessage(),
				"404"));
	}
}
