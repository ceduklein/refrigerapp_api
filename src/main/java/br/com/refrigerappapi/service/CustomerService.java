package br.com.refrigerappapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.refrigerappapi.exception.RulesException;
import br.com.refrigerappapi.model.entity.Customer;
import br.com.refrigerappapi.model.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository repository;
	
	public Customer save(Customer customer) throws RulesException {
		
		if (repository.existsByDoc(customer.getDoc()))
			throw new RulesException("Documento já cadastrado.");
		
		if (repository.existsByEmail(customer.getEmail()))
			throw new RulesException("Email já cadastrado.");
		
		Customer savedCustomer = new Customer();
		savedCustomer.setName(customer.getName());
		savedCustomer.setDoc(customer.getDoc());
		savedCustomer.setEmail(customer.getEmail());
		savedCustomer.setPhone(customer.getPhone());
		
		return repository.save(savedCustomer);
	}
	
	public List<Customer> list() throws RulesException {
		return repository.findAll();
	}
	
	public Customer findById(Long id) throws RulesException {
		return validateCustomer(id);
	}
	
	public Customer update(Long id, Customer customer) throws RulesException {
		Customer updatedCustomer = validateCustomer(id);
		
		if (repository.findByEmailWhere(id, customer.getEmail()).isPresent())
			throw new RulesException("Email já cadastrado.");
		
		updatedCustomer.setName(customer.getName());
		updatedCustomer.setEmail(customer.getEmail());
		updatedCustomer.setPhone(customer.getPhone());
		
		return repository.save(updatedCustomer);
	}
	
	public void delete(Long id) throws RulesException {
		Customer customer = validateCustomer(id);
		repository.delete(customer);
	}
	
	private Customer validateCustomer(Long id) throws RulesException {
		Optional<Customer> customer = repository.findById(id);
		if (!customer.isPresent())
			throw new RulesException("Cliente não encontrado.");
		
		return customer.get();
	}
}
