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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.refrigerappapi.dto.OrderDTO;
import br.com.refrigerappapi.model.entity.Order;
import br.com.refrigerappapi.service.OrderService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/orders")
public class OrderController {

  @Autowired
  private OrderService service;

  @PostMapping()
	public ResponseEntity<?> save(@RequestBody OrderDTO dto) {
		try {
			Order order = service.save(dto);
			return new ResponseEntity<>(order, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

  @GetMapping()
	public ResponseEntity<?> list() {
		try {
			List<Order> orders = service.list();
			return new ResponseEntity<>(orders, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

  @GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		try {
			Order order = service.findById(id);
			return new ResponseEntity<>(order, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<?> findByUserId(@PathVariable("id") Long id) {
		try {
			List<Order> orders = service.findByUserId(id);
			return new ResponseEntity<>(orders, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<?> findByCustomerId(@PathVariable("id") Long id) {
		try {
			List<Order> orders = service.findByCustomerId(id);
			return new ResponseEntity<>(orders, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

  @DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			service.delete(id);
			return new ResponseEntity<>("Pedido excluído.", HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}  
}
