package clone.colley.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity // DB 테이블 역할을 합니다.
@AllArgsConstructor
@NoArgsConstructor
//        (access = AccessLevel.PROTECTED)
public class LikeUser {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Posts posts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @Column
    private Boolean isLike;


    public LikeUser(Posts posts, User user, boolean requestDto) {
        this.posts = posts;
        this.user = user;
        this.isLike = requestDto;
    }
}
