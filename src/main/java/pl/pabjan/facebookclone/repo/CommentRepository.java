package pl.pabjan.facebookclone.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pabjan.facebookclone.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}