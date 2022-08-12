package clone.colley.controller;

import clone.colley.dto.Request.LikeUserRequestDto;
import clone.colley.dto.Response.LikeUserResponseDto;
import clone.colley.model.User;
import clone.colley.security.UserDetailsImpl;
import clone.colley.service.LikeUserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class LikeUserController {

    private final LikeUserService likeUserService;

    public LikeUserController(LikeUserService likeUserService) {
        this.likeUserService = likeUserService;
    }

    @GetMapping ("/like/{postId}")
    public LikeUserResponseDto likeCheck(
            @PathVariable Long postId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
//            @RequestBody LikeUserRequestDto requestDto
    ){

//        User user = userDetails.getUser();
        return likeUserService.likeCheck(postId, userDetails);
    }

//    @GetMapping("/like/{postId}")
//    public Boolean likeCheck(@PathVariable Long postId,
//                             @AuthenticationPrincipal UserDetailsImpl userDetails){
//        return likeUserService.likeCheck1(postId,userDetails);
//    }
}
