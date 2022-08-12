package clone.colley.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostResponseDto {

    //post PK
    private Long postId;
    //글의 제목
    private String title;
    //글의 내용
    private String content;
    //글 작성 시간
    private LocalDateTime postDate;
    //글의 imageUrl
    private String imgUrl;

    //좋아요 갯수
    private Integer likeCnt;
    //댓글 갯수
    private Integer commentCnt;

    //태그
    private List<String> tags;

    //작성자의 username
    private String username;
    //작성자의 프로필 img
    private String profileUrl;
    //작성자의 nickname
    private String nickname;

    //로그인한 유저의 해당 글 좋아요 여부
    private Boolean isLike;

}
