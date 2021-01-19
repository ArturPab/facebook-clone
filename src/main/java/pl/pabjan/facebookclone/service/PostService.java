package pl.pabjan.facebookclone.service;

import lombok.AllArgsConstructor;
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

    public PostResponse findById(Long id) {
        return postMapper.mapToDto(postRepository.findById(id).orElseThrow(() -> new FacebookCloneException("Not found post")));
    }


    public List<PostResponse> findByUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new FacebookCloneException("Not found user"));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public void save(PostRequest postRequest) {
        postRepository.save(postMapper.map(postRequest, authService.getCurrentUser()));
    }

    @Transactional
    public void edit(PostRequest postRequest, Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new FacebookCloneException("Not found post"));
        User user = authService.getCurrentUser();
        if(user == post.getUser()) {
            post.setContent(postRequest.getContent());
            postRepository.save(post);
        }
    }
}
