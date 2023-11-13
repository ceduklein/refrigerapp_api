package br.com.refrigerappapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.refrigerappapi.model.entity.Order;
import br.com.refrigerappapi.model.entity.OrderItem;
import br.com.refrigerappapi.model.entity.Product;

import java.util.List;


public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

  List<OrderItem> findByOrder(Order order);

  List<OrderItem> findByProduct(Product Product);
}
