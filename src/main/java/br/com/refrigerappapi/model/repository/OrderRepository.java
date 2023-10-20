package br.com.refrigerappapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.refrigerappapi.model.entity.Customer;
import br.com.refrigerappapi.model.entity.Order;
import br.com.refrigerappapi.model.entity.User;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {
  List<Order> findByUser(User user);

  List<Order> findByCustomer(Customer customer);
}
