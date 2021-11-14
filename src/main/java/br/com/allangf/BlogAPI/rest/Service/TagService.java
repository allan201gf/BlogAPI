package br.com.allangf.BlogAPI.rest.Service;

import br.com.allangf.BlogAPI.domain.entity.Post;
import br.com.allangf.BlogAPI.domain.entity.Tag;
import br.com.allangf.BlogAPI.rest.config.dto.TagDTO;

import java.util.List;

public interface TagService {

    Tag createNewTag(TagDTO tagDTO);

    List<Tag> allTag();

    List<Post> postPelaTag(String nameTag);

}
