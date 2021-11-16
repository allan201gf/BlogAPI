package br.com.allangf.BlogAPI.rest.controller;

import br.com.allangf.BlogAPI.domain.entity.Post;
import br.com.allangf.BlogAPI.domain.entity.User;
import br.com.allangf.BlogAPI.rest.Service.PostService;
import br.com.allangf.BlogAPI.rest.Service.UserService;
import br.com.allangf.BlogAPI.rest.config.dto.PostDTO;
import br.com.allangf.BlogAPI.rest.config.dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@AllArgsConstructor
public class PostController {

    private PostService postService;

    @ApiOperation("Create new post")
    @PostMapping("/v1")
    public void createNewPost(@RequestBody PostDTO postDTO) {
        postService.createNewPost(postDTO);
    }

    @ApiOperation("All post")
    @GetMapping("/v1")
    public List<Post> allPost() {
        return postService.allPost();
    }


}
