package clone.colley.model;

import clone.colley.dto.Request.PostRequestDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Posts extends Timestamped{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long postId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String imgUrl;

   // 좋아요 갯수
    @Column
    private Integer likeCnt = 0;
//    //댓글 갯수
//    @Column
//    private Integer commentCnt;

    @JoinColumn(name = "userId")
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "posts",cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "posts")
    private List<Tag> tags;

    @OneToMany(mappedBy = "posts",cascade = CascadeType.ALL)
    private List<LikeUser> likeUserList;

    public Posts(PostRequestDto requestDto,User user){
        this.title=requestDto.getTitle();
        this.content=requestDto.getContent();
        this.imgUrl=requestDto.getImgUrl();
        this.user=user;
    }

    public void update(String content, String imgUrl, String title) {
        this.content = content;
        this.imgUrl = imgUrl;
        this.title = title;
    }

    public void update(String content, String title) {
        this.content = content;
        this.title = title;
    }
}
