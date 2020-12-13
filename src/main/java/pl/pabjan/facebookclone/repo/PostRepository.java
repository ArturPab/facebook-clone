package pl.pabjan.facebookclone.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pabjan.facebookclone.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
