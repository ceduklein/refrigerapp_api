package br.com.refrigerappapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.refrigerappapi.dto.ProductDTO;
import br.com.refrigerappapi.exception.RulesException;
import br.com.refrigerappapi.model.entity.Product;
import br.com.refrigerappapi.model.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	@Autowired
	private UserService userService;
	
	public Product save(ProductDTO dto) throws RulesException {	
		Product product = new Product();
		product.setName(dto.getName());
		product.setBrand(dto.getBrand());
		product.setCategory(dto.getCategory());
		product.setModel(dto.getModel());
		product.setEan(dto.getEan());
		product.setVoltage(dto.getVoltage());
		product.setValue(dto.getValue());
		product.setQuantity(dto.getQuantity());
		product.setImage(dto.getImage());
		product.setActive(true);
		
		return repository.save(product);
	}
	
	public List<Product> list() throws RulesException {
		return repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
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
	
	public Product update(Long id, ProductDTO dto) throws RulesException {
		Product product = validateProduct(id);
		product.setName(dto.getName());
		product.setBrand(dto.getBrand());
		product.setCategory(dto.getCategory());
		product.setModel(dto.getModel());
		product.setEan(dto.getEan());
		product.setVoltage(dto.getVoltage());
		product.setValue(dto.getValue());
		product.setImage(dto.getImage());
		product.setQuantity(dto.getQuantity());
		
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

	public void increaseStock(Long id, Integer quantity) throws RulesException {
		Product product = validateProduct(id);
		product.setQuantity(product.getQuantity() + quantity);

		repository.save(product);
	}

	public void decreaseStock(Long id, Integer quantity) throws RulesException {
		Product product = validateProduct(id);
		product.setQuantity(product.getQuantity() - quantity);

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
