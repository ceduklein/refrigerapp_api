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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.refrigerappapi.dto.AdminDTO;
import br.com.refrigerappapi.dto.AuthDTO;
import br.com.refrigerappapi.dto.PassDTO;
import br.com.refrigerappapi.dto.UpdateUserDTO;
import br.com.refrigerappapi.dto.UserDTO;
import br.com.refrigerappapi.model.entity.User;
import br.com.refrigerappapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
@Tag(name = "User")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping()
	@Operation(summary = "Create")
	public ResponseEntity<?> save(@RequestBody UserDTO dto) {
		try {
			User user = service.save(dto);
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/login")
	@Operation(summary = "Authenticate")
	public ResponseEntity<?> authenticate(@RequestBody AuthDTO dto) {
		try {
			System.out.println(dto);
			User user = service.authenticate(dto.getLogin(), dto.getPassword());
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping()
	@Operation(summary = "List All")
	public ResponseEntity<?> list(@RequestParam Long admin_id) {
		try {
			List<User> users = service.list(admin_id);
			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping("{id}")
	@Operation(summary = "Find By Id")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		try {
			User user = service.findById(id);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("{id}")
	@Operation(summary = "Update")
	public ResponseEntity<?> atualizar(@PathVariable("id") Long id, @RequestBody UpdateUserDTO dto) {
		try {
			User user = service.update(id, dto);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	
	@PatchMapping("/credentials/{user_id}")
	@Operation(summary = "Change Credentials")
	public ResponseEntity<?> setAdmin(@PathVariable("user_id") Long user_id, @RequestBody AdminDTO dto) {
		try {
			service.setCredentials(user_id, dto.getAdmin_id());
			return ResponseEntity.ok("Operação realizada com sucesso.");
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
	
	@DeleteMapping("{id}")
	@Operation(summary = "Delete")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			service.delete(id);
			return new ResponseEntity<>("Cadastro de usuário excluído.", HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PatchMapping("/change-password/{id}")
	@Operation(summary = "Change Password")
	public ResponseEntity<?> changePassword(@PathVariable("id") Long id, @RequestBody PassDTO dto) {
		try {
			service.changePassword(id, dto);
			return new ResponseEntity<>("Senha alterada.", HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
