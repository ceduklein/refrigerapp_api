package br.com.refrigerappapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.refrigerappapi.model.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
