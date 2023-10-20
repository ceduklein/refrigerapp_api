package br.com.refrigerappapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.refrigerappapi.dto.OrderDTO;
import br.com.refrigerappapi.exception.RulesException;
import br.com.refrigerappapi.model.entity.Customer;
import br.com.refrigerappapi.model.entity.Order;
import br.com.refrigerappapi.model.entity.User;
import br.com.refrigerappapi.model.repository.OrderRepository;

@Service
public class OrderService {
  
  @Autowired
  private OrderRepository repository;

  @Autowired
  private UserService userServiceervice;

  @Autowired
  private CustomerService customerService;

  public Order save(OrderDTO dto) throws RulesException {
    User user = userServiceervice.findById(dto.getIdUser());
    Customer customer = customerService.findById(dto.getIdCustomer());

    Order order = new Order();
    order.setTotal(0.0);
    order.setUser(user);
    order.setCustomer(customer);

    return repository.save(order);
  }

  public List<Order> list() throws RulesException {
    return repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
  }

  public Order findById(Long id) throws RulesException {
    return validateOrder(id);
  }

  public List<Order> findByUserId(Long idUser) throws RulesException {
    User user = userServiceervice.findById(idUser);
    
    List<Order> orders = repository.findByUser(user);
    if (orders.isEmpty())
      throw new RulesException("Nenhum pedido encontrado para este usuário");
    
    return orders;
  }

  public List<Order> findByCustomerId(Long idCustomer) throws RulesException {
    Customer customer = customerService.findById(idCustomer);

    List<Order> orders = repository.findByCustomer(customer);
    if (orders.isEmpty())
      throw new RulesException("Nenhum pedido encontrado para este usuário");
    
    return orders;
  }

  public void increaseTotal(Long id, Double totalItem) throws RulesException {
    Order order = validateOrder(id);
    order.setTotal(order.getTotal() + totalItem);

    repository.save(order);
  }

  public void decreaseTotal(Long id, Double totalItem) throws RulesException {
    Order order = validateOrder(id);
    order.setTotal(order.getTotal() - totalItem);

    repository.save(order);
  }

  public void delete(Long id) throws RulesException {
    Order order = validateOrder(id);
    repository.delete(order);
  }

  private Order validateOrder(Long id) throws RulesException {
    Optional<Order> order = repository.findById(id);
    if (!order.isPresent())
      throw new RulesException("Pedido não encontrado");
    
    return order.get();
  }
}
