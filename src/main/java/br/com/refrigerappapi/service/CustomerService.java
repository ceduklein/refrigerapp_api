package br.com.refrigerappapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.refrigerappapi.dto.CustomerDTO;
import br.com.refrigerappapi.exception.RulesException;
import br.com.refrigerappapi.model.entity.Customer;
import br.com.refrigerappapi.model.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository repository;
	
	public Customer save(CustomerDTO dto) throws RulesException {
		
		if (repository.existsByDoc(dto.getDoc()))
			throw new RulesException("Documento já cadastrado.");
		
		if (repository.existsByEmail(dto.getEmail()))
			throw new RulesException("Email já cadastrado.");
		
		Customer customer = new Customer();
		customer.setName(dto.getName());
		customer.setDoc(dto.getDoc());
		customer.setEmail(dto.getEmail());
		customer.setPhone(dto.getPhone());
		
		return repository.save(customer);
	}
	
	public List<Customer> list() throws RulesException {
		return repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}
	
	public Customer findById(Long id) throws RulesException {
		return validateCustomer(id);
	}
	
	public Customer update(Long id, CustomerDTO dto) throws RulesException {
		Customer customer = validateCustomer(id);
		
		if (repository.findByEmailWhere(id, dto.getEmail()).isPresent())
			throw new RulesException("Email já cadastrado.");
		
		customer.setName(dto.getName());
		customer.setEmail(dto.getEmail());
		customer.setPhone(dto.getPhone());
		
		return repository.save(customer);
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
