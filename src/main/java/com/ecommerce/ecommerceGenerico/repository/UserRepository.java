package com.ecommerce.ecommerceGenerico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerceGenerico.entityes.UsuarioEntity;

@Repository
public interface UserRepository extends JpaRepository<UsuarioEntity, Long>{
	
}
