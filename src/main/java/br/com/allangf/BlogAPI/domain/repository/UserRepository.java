package br.com.allangf.BlogAPI.domain.repository;

import br.com.allangf.BlogAPI.domain.entity.Tag;
import br.com.allangf.BlogAPI.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
