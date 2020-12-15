package pl.pabjan.facebookclone.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pabjan.facebookclone.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findByName(String name);

    List<User> findByLastName(String lastName);

    List<User> findByCity(String city);
}
