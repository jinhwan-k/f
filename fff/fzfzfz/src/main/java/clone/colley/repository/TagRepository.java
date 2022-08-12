package clone.colley.repository;

import clone.colley.model.Posts;
import clone.colley.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Long> {
    List<Tag> findTagByPosts(Posts posts);

    Tag findTagByPostsAndTag(Posts posts, String dt);

    void deleteAllByPosts(Posts posts);

    @Modifying
    @Query("select t from Tag t where t.tag like %:findword%")
    List<Tag> findTags(String findword);
}
