package br.com.allangf.BlogAPI.rest.Service;

import br.com.allangf.BlogAPI.domain.entity.Post;
import br.com.allangf.BlogAPI.rest.config.dto.PostDTO;

import java.util.List;

public interface PostService {

    void createNewPost(PostDTO postDTO);

    List<Post> allPost();

    List<Post> searchPostByTitle(String title);

    List<Post> allPostAbstract();

    List<Post> searchPostByTimeInterval(String dateStart, String dateEnd);

    Post getPostById(int id);

    void deletePostById(int id);

    List<Post> postMostAccessed();

    void updatePostById(int id, PostDTO postDTO);

}
