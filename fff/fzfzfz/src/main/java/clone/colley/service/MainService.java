package clone.colley.service;

import clone.colley.dto.Response.LikeUserResponseDto;
import clone.colley.dto.Response.MainResponseDto;
import clone.colley.model.LikeUser;
import clone.colley.model.Posts;
import clone.colley.model.Tag;
import clone.colley.repository.MainRepository;
import clone.colley.repository.PostRepository;
import clone.colley.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MainService {

    private final MainRepository mainRepository;
    private final PostRepository postRepository;
    private final TagRepository tagRepository;

    public List<MainResponseDto> getAllPageTimed() {
        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now();

        List<Posts> postsList = mainRepository.findAll();
        List<MainResponseDto> mainResponseDtoList = new ArrayList<>();
        for (Posts posts : postsList) {
            MainResponseDto mainResponseDto = new MainResponseDto(
                    posts.getPostId(),
                    posts.getImgUrl(),
                    posts.getTitle(),
                    posts.getComments().size(),
                    posts.getLikeCnt(),//LikeCnt
                    posts.getUser().getNickname(),
                    posts.getUser().getProfileUrl(),
                    posts.getCreatedAt()
            );
            mainResponseDtoList.add(mainResponseDto);
        }
        return mainResponseDtoList;
    }

    public List<MainResponseDto> getSearchWord(String findword) {
        List<Posts> postsList = postRepository.findPosts(findword);
        List<MainResponseDto> mainResponseDtoList = new ArrayList<>();
        for (Posts posts : postsList) {
            MainResponseDto mainResponseDto = new MainResponseDto(
                    posts.getPostId(),
                    posts.getImgUrl(),
                    posts.getTitle(),
                    posts.getComments().size(),
                    posts.getLikeCnt(),//LikeCnt
                    posts.getUser().getNickname(),
                    posts.getUser().getProfileUrl(),
                    posts.getCreatedAt()
            );
            mainResponseDtoList.add(mainResponseDto);
        }

        List<Tag> tagList = tagRepository.findTags(findword);
        for (Tag tag : tagList) {
            Posts posts = tag.getPosts();
            if (!postsList.contains(posts)) {
                MainResponseDto mainResponseDto = new MainResponseDto(
                        posts.getPostId(),
                        posts.getImgUrl(),
                        posts.getTitle(),
                        posts.getComments().size(),
                        posts.getLikeCnt(),//LikeCnt
                        posts.getUser().getNickname(),
                        posts.getUser().getProfileUrl(),
                        posts.getCreatedAt()
                );
                mainResponseDtoList.add(mainResponseDto);
            }
        }
        return mainResponseDtoList;
    }

    public List<MainResponseDto> getSearchTag(String findword) {
        List<MainResponseDto> mainResponseDtoList = new ArrayList<>();
        List<Tag> tagList = tagRepository.findTags(findword);
        for (Tag tag : tagList) {
            Posts posts = tag.getPosts();
            MainResponseDto mainResponseDto = new MainResponseDto(
                    posts.getPostId(),
                    posts.getImgUrl(),
                    posts.getTitle(),
                    posts.getComments().size(),
                    posts.getLikeCnt(),//LikeCnt
                    posts.getUser().getNickname(),
                    posts.getUser().getProfileUrl(),
                    posts.getCreatedAt()
            );
            if(!mainResponseDtoList.contains(mainResponseDto)){
                mainResponseDtoList.add(mainResponseDto);
            }
        }
        return mainResponseDtoList;
    }

}

//    public List<MainResponseDto> getAllPageLiked() {
//        List<Posts> postsList = mainRepository.OrderByLikeCntDesc();
//        List<MainResponseDto> mainResponseDtoList = new ArrayList<>();
//        for (Posts posts : postsList) {
//            MainResponseDto mainResponseDto = new MainResponseDto(
//                    posts.getPostId(),
//                    posts.getImgUrl(),
//                    posts.getTitle(),
//                    posts.getComments().size(),
////                    posts.getCommentCnt(),
////                    posts.getLikeCnt(),
//                    posts.getComments().size(),
//                    posts.getUser().getNickname(),
//                    posts.getUser().getProfileUrl(),
//                    posts.getCreatedAt()
//            );
//            mainResponseDtoList.add(mainResponseDto);
//        }
//        return mainResponseDtoList;
//    }

