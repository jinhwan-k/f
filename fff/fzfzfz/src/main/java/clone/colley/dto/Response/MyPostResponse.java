package clone.colley.dto.Response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class MyPostResponse
{
    private int status;
    private String nickName;
    private String profileUrl;
    private String introduce;

    private List<MainResponseDto> mainResponseDtoList;

}