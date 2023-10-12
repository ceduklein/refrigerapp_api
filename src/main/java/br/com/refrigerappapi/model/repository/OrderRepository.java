package br.com.refrigerappapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.refrigerappapi.model.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
