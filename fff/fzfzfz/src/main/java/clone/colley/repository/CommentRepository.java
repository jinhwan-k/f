package clone.colley.repository;

import clone.colley.model.Comment;
import clone.colley.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPosts(Posts posts);
}
