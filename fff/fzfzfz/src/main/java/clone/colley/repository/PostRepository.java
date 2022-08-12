package clone.colley.repository;

import clone.colley.model.Posts;
import clone.colley.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Posts,Long> {
    List<Posts> findAllByUpdatedAtBetweenOrderByUpdatedAtDesc(LocalDateTime start, LocalDateTime end);
    List<Posts> findAllByUser(User user);

    @Modifying
    @Query("select p from Posts p where p.title like %:findword%")
    List<Posts> findPosts(String findword);
}
