package br.com.allangf.BlogAPI.rest.Service.impl;

import br.com.allangf.BlogAPI.domain.entity.Post;
import br.com.allangf.BlogAPI.domain.entity.Tag;
import br.com.allangf.BlogAPI.domain.entity.User;
import br.com.allangf.BlogAPI.domain.exception.RuleOfException;
import br.com.allangf.BlogAPI.domain.repository.PostRepository;
import br.com.allangf.BlogAPI.domain.repository.TagRepository;
import br.com.allangf.BlogAPI.domain.repository.UserRepository;
import br.com.allangf.BlogAPI.rest.Errors;
import br.com.allangf.BlogAPI.rest.Helpers;
import br.com.allangf.BlogAPI.rest.Service.PostService;
import br.com.allangf.BlogAPI.rest.config.dto.PostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
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
        post.setCounterHits(0);

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

    @Override
    public List<Post> searchPostByTimeInterval(String dateStart, String dateEnd) {
        LocalDate dateStartFormated = Helpers.stringForDate(dateStart);
        LocalDate dateEndFormated = Helpers.stringForDate(dateEnd);

        List<Post> posts = postRepository.searchPostByTimeInterval(dateStartFormated, dateEndFormated);
        return postAbstract(posts);
    }

    @Override
    public Post getPostById(int id) {
        try{
            Post post = postRepository.findByPostId(id);
            post.setCounterHits(post.getCounterHits()+1);
            postRepository.save(post);

            return post;
        } catch (EmptyResultDataAccessException e) {
            throw new RuleOfException(Errors.INVALID_POST_ID);
        }
    }

    @Override
    public void deletePostById(int id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<Post> postMostAccessed() {
        return postRepository.postMostAccessed();
    }

    public List<Post> postAbstract(List<Post> posts) {
        for (int i = 1; i <= posts.size(); i++) {
            posts.get(i-1).setPostBody(posts.get(i-1).getPostBody().substring(0, 120).concat(" ..."));
        }
        return posts;
    }


}
