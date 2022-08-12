package clone.colley.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Setter
@Getter
@NoArgsConstructor
@Entity
public class Tag {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long tagId;

    @JoinColumn(name = "postId")
    @ManyToOne
    private Posts posts;

    @Column(nullable = false)
    private String tag;
}
