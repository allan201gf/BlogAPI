package br.com.allangf.BlogAPI.domain.repository;

import br.com.allangf.BlogAPI.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("select p from Post p where p.title like :title%")
    List<Post> searchPostByTitle(@Param("title") String title);

}
