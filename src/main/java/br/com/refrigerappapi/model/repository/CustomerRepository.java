package br.com.refrigerappapi.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.refrigerappapi.model.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	boolean existsByDoc(String doc);
	
	boolean existsByEmail(String email);
	
	@Query("from Customer c where c.id != :id and c.email = :email")
	Optional<Customer> findByEmailWhere(@Param("id") Long id, @Param("email") String email);
}
