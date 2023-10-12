package br.com.refrigerappapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.refrigerappapi.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
