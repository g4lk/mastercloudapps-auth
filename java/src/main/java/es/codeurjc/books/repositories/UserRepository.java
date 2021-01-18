package es.codeurjc.books.repositories;

import es.codeurjc.books.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByNick(String nick);

    Optional<User> findByNick(String nick);

}
