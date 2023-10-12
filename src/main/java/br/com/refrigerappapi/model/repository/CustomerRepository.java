package br.com.refrigerappapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.refrigerappapi.model.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
