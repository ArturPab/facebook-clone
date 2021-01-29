package pl.pabjan.facebookclone.service;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.pabjan.facebookclone.controller.dto.PostRequest;
import pl.pabjan.facebookclone.controller.dto.PostResponse;
import pl.pabjan.facebookclone.exceptions.FacebookCloneException;
import pl.pabjan.facebookclone.mapper.PostMapper;
import pl.pabjan.facebookclone.model.Comment;
import pl.pabjan.facebookclone.model.Post;
import pl.pabjan.facebookclone.model.User;
import pl.pabjan.facebookclone.repo.CommentRepository;
import pl.pabjan.facebookclone.repo.PostRepository;
import pl.pabjan.facebookclone.repo.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CommentRepository commentRepository;
    private static final int PAGE_SIZE = 3;


    @Cacheable(cacheNames = "AllPosts")
    public List<PostResponse> findAll(Integer page) {
        int pageNumber = page != null && page >=0 ? page : 0;
        List<Post> posts = postRepository.findAllPosts(PageRequest.of(pageNumber, PAGE_SIZE));
        List<Long> ids = posts
                .stream()
                .map(Post::getPostId)
                .collect(Collectors.toList());
        List<Comment> comments = commentRepository.findAllByPostIdIn(ids);
        return postMapper.mapToDtos(posts, comments);

    }

    @Cacheable(cacheNames = "PostById", key = "#postId")
    public PostResponse findById(Long postId) {
        return postMapper.mapToDto(postRepository.findById(postId).orElseThrow(() -> new FacebookCloneException("Not found post")));
    }

    @Cacheable(cacheNames = "PostByUser", key = "#userId")
    public List<PostResponse> findByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new FacebookCloneException("Not found user"));
        List<Post> posts = postRepository.findByUser(user);
        List<Long> ids = posts
                .stream()
                .map(Post::getPostId)
                .collect(Collectors.toList());
        List<Comment> comments = commentRepository.findAllByPostIdIn(ids);
        return postMapper.mapToDtos(posts, comments);
    }

    public void save(PostRequest postRequest) {
        postRepository.save(postMapper.map(postRequest, authService.getCurrentUser()));
    }

    @Transactional
    @CachePut(cacheNames = "PostById", key = "#result.postId")
    public PostResponse edit(Long id, PostRequest post) {
        Post postEdited = postRepository.findById(id).orElseThrow(() -> new FacebookCloneException("Not found post"));
        User user = authService.getCurrentUser();
        if(user == postEdited.getUser()) {
            postEdited.setContent(post.getContent());
        }
        return postMapper.mapToDto(postEdited);
    }
}
