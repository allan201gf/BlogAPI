package br.com.allangf.BlogAPI.rest.controller;

import br.com.allangf.BlogAPI.domain.entity.Tag;
import br.com.allangf.BlogAPI.rest.Service.TagService;
import br.com.allangf.BlogAPI.rest.Service.impl.TagServiceImpl;
import br.com.allangf.BlogAPI.rest.config.dto.TagDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
@AllArgsConstructor
public class TagController {

    private TagService tagService;

    @PostMapping("/v1")
    public Tag createNewTag(@RequestBody TagDTO tagDTO) {
        return tagService.createNewTag(tagDTO);
    }

    @GetMapping("/v1")
    public List<Tag> allTag() {
        return tagService.allTag();
    }


}
