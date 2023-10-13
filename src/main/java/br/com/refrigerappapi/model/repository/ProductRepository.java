package br.com.refrigerappapi.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.refrigerappapi.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("from Product p where p.isActive = true")
	List<Product> findActives();
}
