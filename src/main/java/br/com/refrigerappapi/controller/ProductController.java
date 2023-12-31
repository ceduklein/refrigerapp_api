package br.com.refrigerappapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.refrigerappapi.dto.ProductDTO;
import br.com.refrigerappapi.dto.UpdateProductDTO;
import br.com.refrigerappapi.model.entity.Product;
import br.com.refrigerappapi.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/products")
@Tag(name = "Products")
public class ProductController {

	@Autowired
	private ProductService service;
	
	@PostMapping()
	@Operation(summary = "Create")
	public ResponseEntity<?> save(@RequestBody ProductDTO dto) {
		try {
			Product product = service.save(dto);
			return new ResponseEntity<>(product, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping()
	@Operation(summary = "List All")
	public ResponseEntity<?> list() {
		try {
			List<Product> products = service.list();
			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/actives")
	@Operation(summary = "List Actives")
	public ResponseEntity<?> listActives() {
		try {
			List<Product> products = service.listActives();
			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("{id}")
	@Operation(summary = "Find By Id")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		try {
			Product product = service.findById(id);
			return new ResponseEntity<>(product, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("{id}")
	@Operation(summary = "Update")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductDTO dto) {
		try {
			Product product = service.update(id, dto);
			return new ResponseEntity<>(product, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("{id}")
	@Operation(summary = "Delete")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			service.delete(id);
			return new ResponseEntity<>("Produto excluído", HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PatchMapping("/update-quantity/{id}")
	@Operation(summary = "Update Quantity")
	public ResponseEntity<?> updateQuantity(@PathVariable Long id, @RequestBody UpdateProductDTO dto) {
		try {
			service.updateQuantity(id, dto.getAdmin_id(), dto.getQuantity());
			return new ResponseEntity<>("Estoque atualizado.", HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PatchMapping("/status/{id}")
	@Operation(summary = "Change Status")
	public ResponseEntity<?> changeStatus(@PathVariable Long id, @RequestBody UpdateProductDTO dto) {
		try {
			service.changeStatus(id, dto.getAdmin_id());
			return new ResponseEntity<>("Status atualizado.", HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
}
