package pl.pabjan.facebookclone.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.pabjan.facebookclone.model.Post;
import pl.pabjan.facebookclone.model.User;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user);
    @Query("Select p from Post p" +
            " left join fetch p.comment")
    List<Post> findAllPosts();
}
