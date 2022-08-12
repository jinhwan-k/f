package clone.colley.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CommentResponseDto {

    private Long commentId;

    private String nickname;

    private String comment;

    private String username;

    private LocalDateTime commentDate;

    private String profileUrl;
}
