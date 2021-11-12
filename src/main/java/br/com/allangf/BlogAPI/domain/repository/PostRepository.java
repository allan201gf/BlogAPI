package br.com.allangf.BlogAPI.domain.repository;

import br.com.allangf.BlogAPI.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
