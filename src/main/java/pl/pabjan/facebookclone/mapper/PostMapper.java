package pl.pabjan.facebookclone.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.pabjan.facebookclone.controller.dto.PostRequest;
import pl.pabjan.facebookclone.controller.dto.PostResponse;
import pl.pabjan.facebookclone.model.Comment;
import pl.pabjan.facebookclone.model.Post;
import pl.pabjan.facebookclone.model.User;
import pl.pabjan.facebookclone.repo.CommentRepository;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class PostMapper {
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;

    public PostResponse mapToDto(Post post) {
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(post.getContent());
        postResponse.setCreated(post.getCreated());
        postResponse.setName(post.getUser().getName());
        postResponse.setLastName(post.getUser().getLastName());
        List<Comment> comments = commentRepository.findByPost(post);
        postResponse.setComments(comments.stream().map(commentMapper::mapToDto).collect(Collectors.toList()));

        return postResponse;
    }

    public Post map(PostRequest postRequest, User user) {
        Post post = new Post();
        post.setContent(postRequest.getContent());
        post.setCreated(Instant.now());
        post.setUser(user);

        return post;
    }
}
