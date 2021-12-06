package br.com.allangf.BlogAPI.rest.controller;

import br.com.allangf.BlogAPI.domain.entity.Post;
import br.com.allangf.BlogAPI.rest.Service.PostService;
import br.com.allangf.BlogAPI.rest.dto.PostDTO;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
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
    public List<Post> searchPostByTimeInterval(@RequestParam String dateStart, @RequestParam String dateEnd) {
        return postService.searchPostByTimeInterval(dateStart, dateEnd);
    }

    @ApiOperation("Search post by id")
    @GetMapping("/v1/searchPostById/{id}")
    public Post searchPostById(@PathVariable int id) {
        return postService.getPostById(id);
    }

    @ApiOperation("Delete post by id")
    @DeleteMapping("/v1/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePostById(@PathVariable int id) {
        postService.deletePostById(id);
    }

    @ApiOperation("Return posts mos accessed")
    @GetMapping("/v1/postMostAccessed")
    public List<Post> postMostAccessed() {
        return postService.postMostAccessed();
    }

    @ApiOperation("Update post by id")
    @PatchMapping("/v1/updatePostById/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePostById(@PathVariable int id, @RequestBody PostDTO postDTO) {
        postService.updatePostById(id, postDTO);
    }

}
