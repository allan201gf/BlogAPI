package br.com.allangf.BlogAPI.rest.Service;

import br.com.allangf.BlogAPI.domain.entity.Post;
import br.com.allangf.BlogAPI.domain.entity.User;
import br.com.allangf.BlogAPI.rest.config.dto.PostDTO;
import br.com.allangf.BlogAPI.rest.config.dto.UserDTO;

import java.util.List;

public interface PostService {

    Post createNewPost(PostDTO postDTO);

    List<Post> allPost();

}
