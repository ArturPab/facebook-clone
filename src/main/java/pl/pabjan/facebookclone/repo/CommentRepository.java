package pl.pabjan.facebookclone.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pabjan.facebookclone.controller.dto.CommentResponse;
import pl.pabjan.facebookclone.model.Comment;
import pl.pabjan.facebookclone.model.Post;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
}
