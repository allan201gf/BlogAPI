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
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;

    public Post createNewPost(PostDTO postDTO) {

        Post post = new Post();

        post.setPostBody(postDTO.getPostBody());
        post.setTitle(postDTO.getTitle());
        post.setPostDate(LocalDate.now());

        List<Tag> tags = new ArrayList<>();

        for (String tag : postDTO.getTag()) {
            tags.add(tagRepository.findByNameTag(tag).get(0));
        }

        post.setTag(tags);

        User user = userRepository.findById(postDTO.getUser()).orElseThrow(
                () -> new RuleOfException(Errors.USER_NOT_FOUND)
        );

        post.setUser(user);

        return postRepository.save(post);

    }

    @Override
    public List<Post> allPost() {
        return postRepository.findAll();
    }

}
