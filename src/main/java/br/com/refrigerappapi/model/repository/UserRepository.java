package br.com.refrigerappapi.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.refrigerappapi.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByLogin(String login);
	
	boolean existsByLogin(String login);
	
	boolean existsByCpf(String cpf);

	@Query("from User u where u.id != :id and u.login = :login")
	Optional<User> findByLoginWhere(@Param("id") Long id, @Param("login") String login);
}
