package clone.colley.controller;

import clone.colley.dto.MyPostDto;
import clone.colley.dto.MyResponse;
import clone.colley.dto.Request.UserProfileRequestDto;
import clone.colley.dto.Response.MyPostResponse;
import clone.colley.model.User;
import clone.colley.security.UserDetailsImpl;
import clone.colley.service.MyPostService;
import clone.colley.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequiredArgsConstructor
public class MyPostController {

    private final MyPostService myPostService;
    private final S3Uploader s3Uploader;


    // 본인 작성 게시리스트
    @GetMapping("/user/mypost")
    public ResponseEntity<MyPostResponse> myPost(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(myPostService.myPost(userDetails));
    }


//    // 유저정보 수정_파일 x
//    @PutMapping("/user/mypost/update")
//    public MyResponse updateUser(
//            @Validated @RequestBody MyPostDto myPostDto,
//            @AuthenticationPrincipal UserDetailsImpl userDetails
//    ) {
//        myPostService.updateUser(myPostDto, userDetails);
//
//        MyResponse myResponse = new MyResponse();
//        myResponse.setResult(true);
//        return myResponse;
//    }


    // 유저 정보 수정_파일 업로드.ver
    @PatchMapping("/user/mypost/update")
    public MyResponse updateUser(
            @RequestPart(value = "image", required = false) MultipartFile multipartFile,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails,
                                 UserProfileRequestDto userProfileRequestDto) throws IOException {
        if(multipartFile==null){
            myPostService.updateUserNoImage(userProfileRequestDto, userDetails);
        }else {
            String image = s3Uploader.uploadFile(multipartFile, "profileImage");
            userProfileRequestDto.setProfileUrl(image);
            myPostService.updateUser(userProfileRequestDto, userDetails);
        }
        MyResponse myresponse = new MyResponse();
        myresponse.setResult(true);
        return myresponse;
    }
}