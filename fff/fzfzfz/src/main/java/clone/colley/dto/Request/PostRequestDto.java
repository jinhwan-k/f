package clone.colley.dto.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostRequestDto {

    //글 작성시 전달받은 URL
    private String imgUrl;

    //글 제목
    private String title;

    //글 내용
    private String content;

    //태그 리스트트
   private List<String> tags;
}
