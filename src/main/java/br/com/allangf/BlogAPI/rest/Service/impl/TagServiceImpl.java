package br.com.allangf.BlogAPI.rest.Service.impl;

import br.com.allangf.BlogAPI.domain.entity.Post;
import br.com.allangf.BlogAPI.domain.entity.Tag;
import br.com.allangf.BlogAPI.domain.repository.TagRepository;
import br.com.allangf.BlogAPI.domain.repository.UserRepository;
import br.com.allangf.BlogAPI.rest.Service.TagService;
import br.com.allangf.BlogAPI.rest.config.dto.TagDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    public Tag createNewTag(TagDTO tagDTO) {
        Tag tag = new Tag();
        tag.setNameTag(tagDTO.getNameTag());

        return tagRepository.save(tag);
    }

    @Override
    public List<Tag> allTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<Post> postPelaTag(String nameTag) {
        Tag tag = tagRepository.findByNameTag(nameTag).get(0);
        return tag.getPost();
    }

}
