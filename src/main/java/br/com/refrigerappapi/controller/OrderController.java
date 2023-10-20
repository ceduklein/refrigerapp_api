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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.refrigerappapi.dto.OrderDTO;
import br.com.refrigerappapi.model.entity.Order;
import br.com.refrigerappapi.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/orders")
@Tag(name = "Order")
public class OrderController {

  @Autowired
  private OrderService service;

  @PostMapping()
	@Operation(summary = "Create")
	public ResponseEntity<?> save(@RequestBody OrderDTO dto) {
		try {
			Order order = service.save(dto);
			return new ResponseEntity<>(order, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

  @GetMapping()
	@Operation(summary = "List All")
	public ResponseEntity<?> list() {
		try {
			List<Order> orders = service.list();
			return new ResponseEntity<>(orders, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

  @GetMapping("{id}")
	@Operation(summary = "Find By Id")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		try {
			Order order = service.findById(id);
			return new ResponseEntity<>(order, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/user/{id}")
	@Operation(summary = "Find By User Id")
	public ResponseEntity<?> findByUserId(@PathVariable("id") Long id) {
		try {
			List<Order> orders = service.findByUserId(id);
			return new ResponseEntity<>(orders, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/customer/{id}")
	@Operation(summary = "Find By Customer Id")
	public ResponseEntity<?> findByCustomerId(@PathVariable("id") Long id) {
		try {
			List<Order> orders = service.findByCustomerId(id);
			return new ResponseEntity<>(orders, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

  @DeleteMapping("{id}")
	@Operation(summary = "Delete")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			service.delete(id);
			return new ResponseEntity<>("Pedido excluído.", HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}  
}
