package clone.colley.service;

import clone.colley.dto.Request.LikeUserRequestDto;
import clone.colley.dto.Response.LikeUserResponseDto;
import clone.colley.model.LikeUser;
import clone.colley.model.Posts;
import clone.colley.model.User;
import clone.colley.repository.LikeUserRepository;
import clone.colley.repository.PostRepository;
import clone.colley.repository.UserRepository;
import clone.colley.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LikeUserService {
    private final PostRepository postRepository;
    private final LikeUserRepository likeUserRepository;
    private final UserRepository userRepository;

    @Transactional
    public LikeUserResponseDto likeCheck(Long postId, UserDetailsImpl userDetails){
//        LikeUserRequestDto likeUserRequestDto = new LikeUserRequestDto();
//
        LikeUserResponseDto likeUserResponseDto = new LikeUserResponseDto();

        Posts posts = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("좋아요 할 게시글이 없습니다")
        );
        User user = userRepository.findById(userDetails.getUser().getUserId()).orElseThrow(
                () -> new IllegalArgumentException("유저가 존재하지 않습니다")
        );

        LikeUser likesCheck = likeUserRepository.findByUserAndPosts(userDetails.getUser(), posts).orElse(null);

        if (likesCheck == null) {
            log.info("널일경우");
            LikeUser likeUser = new LikeUser(posts, user, true);
            likeUserRepository.save(likeUser);
//            return true;
            likeUserResponseDto.setIsLike(true);
            posts.setLikeCnt(posts.getLikeCnt()+1);
            likeUserResponseDto.setLikeCnt(posts.getLikeCnt());
        } else {
            log.info("널 아닐 경우");
            if (likesCheck.getIsLike()==true) {
                likesCheck.setIsLike(false);
                likeUserResponseDto.setIsLike(false);
                posts.setLikeCnt(posts.getLikeCnt()-1);
                likeUserResponseDto.setLikeCnt(posts.getLikeCnt());

            } else if(likesCheck.getIsLike()==false) {
                likesCheck.setIsLike(true);
                likeUserResponseDto.setIsLike(true);
                posts.setLikeCnt(posts.getLikeCnt()+1);
                likeUserResponseDto.setLikeCnt(posts.getLikeCnt());
            }
        }
            return likeUserResponseDto;
//        return likesCheck.getIsLike();

    }

}
