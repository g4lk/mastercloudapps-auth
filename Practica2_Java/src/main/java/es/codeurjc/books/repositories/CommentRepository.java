package es.codeurjc.books.repositories;

import es.codeurjc.books.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByBookIdAndId(Long bookId, Long commentId);

    Collection<Comment> findByUserId(long userId);
}
