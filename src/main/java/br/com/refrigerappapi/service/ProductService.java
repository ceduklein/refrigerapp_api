package br.com.refrigerappapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.refrigerappapi.exception.RulesException;
import br.com.refrigerappapi.model.entity.Product;
import br.com.refrigerappapi.model.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	@Autowired
	private UserService userService;
	
	public Product save(Product prod) throws RulesException {
		
		Product product = new Product();
		product.setName(prod.getName());
		product.setBrand(prod.getBrand());
		product.setCategory(prod.getCategory());
		product.setModel(prod.getModel());
		product.setEan(prod.getEan());
		product.setVoltage(prod.getVoltage());
		product.setValue(prod.getValue());
		product.setQuantity(prod.getQuantity());
		product.setImage(prod.getImage());
		product.setActive(true);
		
		return repository.save(product);
	}
	
	public List<Product> list() throws RulesException {
		return repository.findAll();
	}
	
	public List<Product> listActives() throws RulesException {
		List<Product> products = repository.findActives();
		if (products.isEmpty()) 
			throw new RulesException("Nenhum produto ativo.");
		
		return products;
	}
	
	public Product findById(Long id) throws RulesException {
		return validateProduct(id);
	}
	
	public Product update(Long id, Product prod) throws RulesException {
		Product product = validateProduct(id);
		product.setName(prod.getName());
		product.setBrand(prod.getBrand());
		product.setCategory(prod.getCategory());
		product.setModel(prod.getModel());
		product.setEan(prod.getEan());
		product.setVoltage(prod.getVoltage());
		product.setValue(prod.getValue());
		product.setImage(prod.getImage());
		
		return repository.save(product);
	}
	
	public void updateQuantity(Long id, Long admin_id, Integer quantity) throws RulesException {
		userService.validateCredentials(admin_id);
		
		Product product = validateProduct(id);
		product.setQuantity(quantity);
		
		repository.save(product);
	}
	
	public void changeStatus(Long id, Long admin_id) throws RulesException {
		userService.validateCredentials(admin_id);
		
		Product product = validateProduct(id);
		product.setActive(!product.isActive());
		
		repository.save(product);
	}
	
	public void delete(Long id) throws RulesException {
		Product product = validateProduct(id);
		repository.delete(product);
	}
	
	private Product validateProduct(Long id) throws RulesException {
		Optional<Product> product = repository.findById(id);
		if (!product.isPresent())
			throw new RulesException("Produto não encontrado.");
		
		return product.get();
	}
}
