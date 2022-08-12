package clone.colley.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MyPostDto {

    @NotNull
    private String nickname;
    private String introduce;
    private String profileUrl;

}