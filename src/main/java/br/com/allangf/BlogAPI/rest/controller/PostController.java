package br.com.allangf.BlogAPI.rest.controller;

import br.com.allangf.BlogAPI.domain.entity.Post;
import br.com.allangf.BlogAPI.domain.entity.User;
import br.com.allangf.BlogAPI.domain.exception.RuleOfException;
import br.com.allangf.BlogAPI.rest.Errors;
import br.com.allangf.BlogAPI.rest.Service.PostService;
import br.com.allangf.BlogAPI.rest.Service.UserService;
import br.com.allangf.BlogAPI.rest.config.dto.PostDTO;
import br.com.allangf.BlogAPI.rest.config.dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@AllArgsConstructor
public class PostController {

    private PostService postService;

    @ApiOperation("Create new post")
    @PostMapping("/v1")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createNewPost(@RequestBody PostDTO postDTO) {
        postService.createNewPost(postDTO);
    }

    @ApiOperation("All post")
    @GetMapping("/v1")
    public List<Post> allPost() {
        return postService.allPost();
    }

    @ApiOperation("Search post by title")
    @GetMapping("/v1/searchPostByTitle")
    public List<Post> searchPostByTitle(@RequestParam String title) {
        return postService.searchPostByTitle(title);
    }

    @ApiOperation("All post abstract")
    @GetMapping("/v1/allPostAbstract")
    public List<Post> allPostAbstract() {
        return postService.allPostAbstract();
    }

    @ApiOperation("Search post by time interval")
    @GetMapping("/v1/searchPostByTimeInterval")
    public List<Post> searchPostByTimeInterval(@RequestParam String dateStart,@RequestParam String dateEnd) {
        return postService.searchPostByTimeInterval(dateStart, dateEnd);
    }

    @GetMapping("/v1/searchPostById/{id}")
    public Post searchPostById(@PathVariable int id) {
        return postService.getPostById(id);
    }

    @DeleteMapping("/v1/{id}")
    public void deletePostById(@PathVariable int id) {
        postService.deletePostById(id);
    }

    @GetMapping("/v1/postMostAccessed")
    public List<Post> postMostAccessed() {
        return postService.postMostAccessed();
    }

}
