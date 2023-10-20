package br.com.refrigerappapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.refrigerappapi.dto.CustomerDTO;
import br.com.refrigerappapi.model.entity.Customer;
import br.com.refrigerappapi.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/customers")
@Tag(name = "Customer")
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@PostMapping()
	@Operation(summary = "Create")
	public ResponseEntity<?> save(@RequestBody CustomerDTO dto) {
		try {
			Customer customer = service.save(dto);
			return new ResponseEntity<>(customer, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping()
	@Operation(summary = "List All")
	public ResponseEntity<?> list() {
		try {
			List<Customer> customers = service.list();
			return new ResponseEntity<>(customers, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("{id}")
	@Operation(summary = "Find By Id")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		try {
			Customer customer = service.findById(id);
			return new ResponseEntity<>(customer, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("{id}")
	@Operation(summary = "Update")
	public ResponseEntity<?> atualizar(@PathVariable("id") Long id, @RequestBody CustomerDTO dto) {
		try {
			Customer customer = service.update(id, dto);
			return new ResponseEntity<>(customer, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("{id}")
	@Operation(summary = "Delete")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			service.delete(id);
			return new ResponseEntity<>("Cliente excluído.", HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
