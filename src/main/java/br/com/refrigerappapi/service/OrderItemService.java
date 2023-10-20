package br.com.refrigerappapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.refrigerappapi.dto.ItemDTO;
import br.com.refrigerappapi.exception.RulesException;
import br.com.refrigerappapi.model.entity.Order;
import br.com.refrigerappapi.model.entity.OrderItem;
import br.com.refrigerappapi.model.entity.Product;
import br.com.refrigerappapi.model.repository.OrderItemRepository;

@Service
public class OrderItemService {
  
  @Autowired
  private OrderItemRepository repository;

  @Autowired
  private OrderService orderService;

  @Autowired ProductService productService;

  public OrderItem save(ItemDTO dto) throws RulesException {
    Order order = orderService.findById(dto.getIdOrder());
    Product product = productService.findById(dto.getIdProduct());

    OrderItem item = new OrderItem();
    item.setOrder(order);
    item.setProduct(product);
    item.setQuantity(dto.getQuantity());
    item.setTotalItem(product.getValue() * dto.getQuantity());

    OrderItem savedItem = repository.save(item);
    orderService.increaseTotal(savedItem.getOrder().getId(), savedItem.getTotalItem());
    productService.decreaseStock(savedItem.getProduct().getId(), savedItem.getQuantity());

    return savedItem;
  }

  public OrderItem findById(Long id) throws RulesException {
		  return validateItem(id);
	}

  public List<OrderItem> findByOrderId(Long idOrder) throws RulesException {
		Order order = orderService.findById(idOrder);
		return repository.findByOrder(order);
	}

  public void delete(Long id) throws RulesException {
		OrderItem item = validateItem(id);
		Order order = orderService.findById(item.getOrder().getId());
    Product product = productService.findById(item.getProduct().getId());

		repository.delete(item);

    orderService.decreaseTotal(order.getId(), item.getTotalItem());
    productService.increaseStock(product.getId(), item.getQuantity());
	}

  public void deleteByOrderId(Long idOrder) throws RulesException {
		List<OrderItem> itens =  findByOrderId(idOrder);
		
		itens.forEach(item -> {
			try {
				delete(item.getId());
			} catch (RulesException e) {
				e.printStackTrace();
			}
		});
		
		orderService.delete(idOrder);
	}

  private OrderItem validateItem(Long id) throws RulesException {
    Optional<OrderItem> item = repository.findById(id);
    if (!item.isPresent())
      throw new RulesException("item não encontrado.");
    
    return item.get();
  }
}
