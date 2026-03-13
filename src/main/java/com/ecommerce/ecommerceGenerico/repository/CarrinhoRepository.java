package com.ecommerce.ecommerceGenerico.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerceGenerico.entityes.CarrinhoEntity;

@Repository
public interface CarrinhoRepository extends CrudRepository<CarrinhoEntity, Long>{
	
}
