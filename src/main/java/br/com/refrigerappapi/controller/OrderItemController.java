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

import br.com.refrigerappapi.dto.ItemDTO;
import br.com.refrigerappapi.model.entity.OrderItem;
import br.com.refrigerappapi.service.OrderItemService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/items")
public class OrderItemController {
  
  @Autowired
  private OrderItemService service;

  @PostMapping()
	public ResponseEntity<?> save(@RequestBody ItemDTO dto) {
		try {
			OrderItem item = service.save(dto);
			return new ResponseEntity<>(item, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

  @GetMapping()
	public ResponseEntity<?> list(@RequestParam Long idOrder) {
		try {
			List<OrderItem> items = service.findByOrderId(idOrder);
			return new ResponseEntity<>(items, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

  @GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		try {
			OrderItem item = service.findById(id);
			return new ResponseEntity<>(item, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

  @DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			service.delete(id);
			return new ResponseEntity<>("Item excluído.", HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/pedido/{id}")
	public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id) {
		try {
			service.deleteByOrderId(id);
			return new ResponseEntity<>("Pedido excluído.", HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
