package com.ecommerce.ecommerceGenerico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerceGenerico.entityes.CarrinhoEntity;
import com.ecommerce.ecommerceGenerico.entityes.ItemEntity;
import com.ecommerce.ecommerceGenerico.entityes.ProdutoEntity;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long>{
	ItemEntity findByCarrinhoAndProduto(CarrinhoEntity carrinho, ProdutoEntity produto);
}
