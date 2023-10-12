package br.com.refrigerappapi.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.refrigerappapi.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByLogin(String login);
	
	boolean existsByLogin(String login);
	
	boolean existsByCpf(String cpf);
}
