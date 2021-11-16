package br.com.allangf.BlogAPI.rest.Service.impl;

import br.com.allangf.BlogAPI.domain.entity.Post;
import br.com.allangf.BlogAPI.domain.entity.Tag;
import br.com.allangf.BlogAPI.domain.exception.RuleOfException;
import br.com.allangf.BlogAPI.domain.repository.TagRepository;
import br.com.allangf.BlogAPI.domain.repository.UserRepository;
import br.com.allangf.BlogAPI.rest.Errors;
import br.com.allangf.BlogAPI.rest.Service.TagService;
import br.com.allangf.BlogAPI.rest.config.dto.TagDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    public Tag createNewTag(TagDTO tagDTO) {

        List<Tag> existTag = tagRepository.findByNameTag(tagDTO.getNameTag());
        if (!existTag.isEmpty()) {
            throw new RuleOfException(Errors.TAG_ALREADY_REGISTERED);
        }

        Tag tag = new Tag();
        tag.setNameTag(tagDTO.getNameTag());

        return tagRepository.save(tag);
    }

    @Override
    public List<Tag> allTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<Post> searchPostByTag(String nameTag) {
        try {
            Tag tag = tagRepository.findByNameTag(nameTag).get(0);
            return tag.getPost();
        } catch (IndexOutOfBoundsException e) {
            throw new RuleOfException(Errors.NO_POSTS_FOUND);
        }

    }

    @Override
    public void deleteTagById(int tagId) {
        try {
            tagRepository.deleteById(tagId);
        } catch (EmptyResultDataAccessException e) {
            throw new RuleOfException(Errors.TAG_NOT_FOUND);
        }

    }

}
