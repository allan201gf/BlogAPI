package br.com.allangf.BlogAPI.rest.Service.impl;

import br.com.allangf.BlogAPI.domain.entity.Post;
import br.com.allangf.BlogAPI.domain.entity.Tag;
import br.com.allangf.BlogAPI.domain.entity.User;
import br.com.allangf.BlogAPI.domain.exception.RuleOfException;
import br.com.allangf.BlogAPI.domain.repository.PostRepository;
import br.com.allangf.BlogAPI.domain.repository.TagRepository;
import br.com.allangf.BlogAPI.domain.repository.UserRepository;
import br.com.allangf.BlogAPI.rest.Errors;
import br.com.allangf.BlogAPI.rest.Service.PostService;
import br.com.allangf.BlogAPI.rest.config.dto.PostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;

    public void createNewPost(PostDTO postDTO) {

        if (postDTO.getPostBody() == null) {
            throw new RuleOfException(Errors.POST_BODY_IS_REQUIRED);
        }
        if (postDTO.getTag() == null) {
            throw new RuleOfException(Errors.TAG_IS_REQUIRED);
        }
        if (postDTO.getTitle() == null) {
            throw new RuleOfException(Errors.TITLE_IS_REQUIRED);
        }

        Post post = new Post();

        post.setPostBody(postDTO.getPostBody());
        post.setTitle(postDTO.getTitle());
        post.setPostDate(LocalDate.now());

        List<Tag> tags = new ArrayList<>();

        for (String tag : postDTO.getTag()) {
            if (tagRepository.findByNameTag(tag).size() == 0) {
                Tag newTag = new Tag();
                newTag.setNameTag(tag);
                tagRepository.save(newTag);
            }
            tags.add(tagRepository.findByNameTag(tag).get(0));
        }

        post.setTag(tags);

        User user = userRepository.findById(postDTO.getUser()).orElseThrow(
                () -> new RuleOfException(Errors.USER_NOT_FOUND)
        );

        post.setUser(user);

        postRepository.save(post);

    }

    @Override
    public List<Post> allPost() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> searchPostByTitle(String title) {
        List<Post> posts = postRepository.searchPostByTitle(title);
        return postAbstract(posts);
    }

    @Override
    public List<Post> allPostAbstract() {
        return postAbstract(allPost());
    }

    public List<Post> postAbstract(List<Post> posts) {
        for (int i = 1; i <= posts.size(); i++) {
            posts.get(i-1).setPostBody(posts.get(i-1).getPostBody().substring(0, 15).concat(" ..."));
        }
        return posts;
    }
}
