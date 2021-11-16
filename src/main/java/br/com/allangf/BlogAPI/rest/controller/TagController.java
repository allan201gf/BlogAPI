package br.com.allangf.BlogAPI.rest.controller;

import br.com.allangf.BlogAPI.domain.entity.Post;
import br.com.allangf.BlogAPI.domain.entity.Tag;
import br.com.allangf.BlogAPI.rest.Service.TagService;
import br.com.allangf.BlogAPI.rest.Service.impl.TagServiceImpl;
import br.com.allangf.BlogAPI.rest.config.dto.TagDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
@AllArgsConstructor
public class TagController {

    private TagService tagService;

    @ApiOperation("Create new tag")
    @PostMapping("/v1")
    public Tag createNewTag(@RequestBody TagDTO tagDTO) {
        return tagService.createNewTag(tagDTO);
    }

    @ApiOperation("All tag")
    @GetMapping("/v1")
    public List<Tag> allTag() {
        return tagService.allTag();
    }

    @GetMapping("/v1/searchPostByTag/{nameTag}")
    public List<Post> searchPostByTag(@PathVariable String nameTag) {
        return tagService.searchPostByTag(nameTag);
    }

    @DeleteMapping("/v1/delete/{tagId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteTag(@PathVariable int tagId) {
        tagService.deleteTagById(tagId);
    }
}
