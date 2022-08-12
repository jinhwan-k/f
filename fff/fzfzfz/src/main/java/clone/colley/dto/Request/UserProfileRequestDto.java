package clone.colley.dto.Request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileRequestDto {

    private String profileUrl;
    private String introduce;
    private String nickname;

}
