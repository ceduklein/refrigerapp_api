package br.com.refrigerappapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.refrigerappapi.dto.PassDTO;
import br.com.refrigerappapi.dto.UserDTO;
import br.com.refrigerappapi.exception.RulesException;
import br.com.refrigerappapi.model.entity.User;
import br.com.refrigerappapi.model.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	PasswordEncoder encoder;
	
	public User save(UserDTO dto) throws RulesException {
		if (repository.existsByLogin(dto.getLogin()))
			throw new RulesException("Nome de usuário já cadastrado.");
		
		if (repository.existsByCpf(dto.getCpf()))
			throw new RulesException("CPF já cadastrado.");
		
		User savedUser = new User();
		savedUser.setName(dto.getName());
		savedUser.setLogin(dto.getLogin());
		savedUser.setCpf(dto.getCpf());
		
		String password = generateDefaultPassword(dto.getLogin(), dto.getCpf());
		savedUser.setPassword(encoder.encode(password));
		
		return repository.save(savedUser);
	}
	
	public User authenticate(String login, String password) throws RulesException {
		Optional<User> user = repository.findByLogin(login);
		if (!user.isPresent())
			
			throw new RulesException("Login ou senha incorretos.");

		if (encoder.matches(password, user.get().getPassword()))
			return user.get();
		
		throw new RulesException("Login ou senha incorretos.");
	}
	
	public User findById(Long id) throws RulesException {
		return validateUser(id);
	}
	
	public void setCredentials(Long user_id, Long admin_id) throws RulesException {
		validateCredentials(admin_id);
		
		User user = validateUser(user_id);
		user.setAdmin(!user.isAdmin());
		repository.save(user);
	}
	
	public List<User> list(Long admin_id) throws RulesException {
		validateCredentials(admin_id);
		return repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}
	
	public void delete(Long id) throws RulesException {
		User user = validateUser(id);
		repository.delete(user);
	}
	
	public void changePassword(Long id, PassDTO dto) throws RulesException {
		User user = validateUser(id);
		
		if (!encoder.matches(dto.getOldPassword(), user.getPassword())) 
			throw new RulesException("Senha incorreta.");
		
		if (!dto.getPassword().equals(dto.getPasswordConfirmation())) {
			throw new RulesException("A nova senha e sua confirmação não são iguais.");
		}
		
		user.setPassword(encoder.encode(dto.getPassword()));
		repository.save(user);
	}
	
	private void validateCredentials(Long admin_id)  throws RulesException {
		Optional<User> admin = repository.findById(admin_id);
		if (!admin.isPresent() || !admin.get().isAdmin())
			throw new RulesException("Operação não autorizada para este usuário.");
	}
	
	private User validateUser(Long user_id) throws RulesException {
		Optional<User> user = repository.findById(user_id);
		if (!user.isPresent())
			throw new RulesException("Usuário não encontrado.");
		
		return user.get();
	}
	
	private String generateDefaultPassword(String login, String cpf) {
		String pass1 = login;
		String pass2 = cpf.substring(0, 3);
		return pass1.concat(pass2);
	}
	
}
