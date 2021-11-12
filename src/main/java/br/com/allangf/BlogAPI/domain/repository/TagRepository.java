package br.com.allangf.BlogAPI.domain.repository;

import br.com.allangf.BlogAPI.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Integer> {

    List<Tag> findByNameTag(String nameTag);

}
