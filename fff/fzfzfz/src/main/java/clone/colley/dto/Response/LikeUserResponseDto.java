package clone.colley.dto.Response;

import lombok.Data;

@Data
public class LikeUserResponseDto {
    private Boolean isLike;
    private Integer likeCnt;
}
