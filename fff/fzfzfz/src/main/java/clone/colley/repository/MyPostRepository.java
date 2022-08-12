package clone.colley.repository;

import clone.colley.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyPostRepository extends JpaRepository<User, Long> {

}