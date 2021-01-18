package pl.pabjan.facebookclone.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.pabjan.facebookclone.controller.dto.CommentRequest;
import pl.pabjan.facebookclone.controller.dto.CommentResponse;
import pl.pabjan.facebookclone.exceptions.FacebookCloneException;
import pl.pabjan.facebookclone.model.Comment;
import pl.pabjan.facebookclone.model.User;
import pl.pabjan.facebookclone.repo.PostRepository;;import java.time.Instant;


@Component
@AllArgsConstructor
public class CommentMapper {

    private final PostRepository postRepository;

    public CommentResponse mapToDto(Comment comment) {
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setContent(comment.getContent());
        commentResponse.setCreated(comment.getCreated());
        commentResponse.setName(comment.getUser().getName());
        commentResponse.setLastName(comment.getUser().getLastName());

        return commentResponse;
    }

    public Comment map(CommentRequest commentRequest, User user) {
        Comment comment = new Comment();
        comment.setCreated(Instant.now());
        comment.setContent(commentRequest.getContent());
        comment.setPostId(commentRequest.getPostId());
        comment.setUser(user);

        return comment;
    }
}
