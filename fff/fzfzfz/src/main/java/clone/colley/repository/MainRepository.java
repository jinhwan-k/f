package clone.colley.repository;

import clone.colley.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MainRepository extends JpaRepository<Posts,Long> {
//    List<Posts> findAllByUpdatedAtBetweenOrderByUpdatedAtDesc(LocalDateTime start, LocalDateTime end);
//
//    List<Posts> OrderByLikeCntDesc();

//    List<Posts> findPosts();
}
