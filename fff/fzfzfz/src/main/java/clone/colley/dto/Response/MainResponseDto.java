package clone.colley.dto.Response;

import clone.colley.model.Posts;
import clone.colley.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
public class MainResponseDto {

    // 게시글 ID
    private Long postId;

    // 이미지 URL
    private String imgUrl;

    // 게시글 제목
    private String title;

    // 댓글 수
    private Integer commentCnt;

    // 좋아요 수
    private Integer likeCnt;

    // 작성자 닉네임
    private String nickname;

    // 작성자 프로필 URL
    private String profileUrl;

    //글 작성 시간
    private LocalDateTime postDate;





//    public MainResponseDto(Posts posts, int likeCnt, int commentCnt)
//    {
//        this.postId = posts.getPostId();
//        this.title =posts.getTitle();
//        this.imgUrl =posts.getImgUrl();
//        this.likeCnt = likeCnt;
//        this.commentCnt = commentCnt;
//        this.nickname =posts.getUser().getNickname();
//    }

    //////////////////////////////////
    public MainResponseDto(Posts posts, User user)
    {
        this.postId = posts.getPostId();
        this.postDate = posts.getCreatedAt();
        this.title = posts.getTitle();
        this.imgUrl = posts.getImgUrl();
        this.nickname = posts.getUser().getNickname();
        this.likeCnt = posts.getLikeCnt();
        this.profileUrl = user.getProfileUrl();
        this.commentCnt = posts.getComments().size();
    }
}
