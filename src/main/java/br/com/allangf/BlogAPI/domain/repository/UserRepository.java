package br.com.allangf.BlogAPI.domain.repository;

import br.com.allangf.BlogAPI.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByEmail(String email);

    Optional<User> findByLogin(String login);


}
