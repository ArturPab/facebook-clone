package pl.pabjan.facebookclone.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.pabjan.facebookclone.controller.dto.CommentResponse;
import pl.pabjan.facebookclone.controller.dto.PostRequest;
import pl.pabjan.facebookclone.controller.dto.PostResponse;
import pl.pabjan.facebookclone.model.Comment;
import pl.pabjan.facebookclone.model.Post;
import pl.pabjan.facebookclone.model.User;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class PostMapper {
    private final CommentMapper commentMapper;

    public PostResponse mapToDto(Post post) {
        PostResponse postResponse = new PostResponse();
        postResponse.setPostId(post.getPostId());
        postResponse.setContent(post.getContent());
        postResponse.setCreated(post.getCreated());
        postResponse.setLastName(post.getUser().getLastName());
        postResponse.setName(post.getUser().getName());
        List<CommentResponse> comments = post.getComment()
                .stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
        postResponse.setComments(comments);
        return postResponse;
    }


    public Post map(PostRequest postRequest, User user) {
        Post post = new Post();
        post.setContent(postRequest.getContent());
        post.setCreated(Instant.now());
        post.setUser(user);

        return post;
    }

    public List<PostResponse> mapToDtos(List<Post> posts, List<Comment> comments) {
        posts.forEach(post -> post.setComment(extractComments(comments, post.getPostId())));
        return posts
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private List<Comment> extractComments(List<Comment> comments, Long postId) {
        return comments.stream()
                .filter(comment -> comment.getPostId().equals(postId))
                .collect(Collectors.toList());
    }
}
