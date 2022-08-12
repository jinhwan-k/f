package clone.colley.service;


import clone.colley.dto.Request.PostRequestDto;
import clone.colley.dto.Response.LikeUserResponseDto;
import clone.colley.dto.Response.PostResponseDto;
import clone.colley.model.LikeUser;
import clone.colley.model.Posts;
import clone.colley.model.Tag;
import clone.colley.model.User;
import clone.colley.repository.LikeUserRepository;
import clone.colley.repository.PostRepository;
import clone.colley.repository.TagRepository;
import clone.colley.repository.UserRepository;
import clone.colley.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final LikeUserRepository likeUserRepository;
    private final UserRepository userRepository;

    //게시글 작성
    @Transactional
    public Long postRegister(PostRequestDto requestDto, UserDetailsImpl userDetails) {
        Posts posts = new Posts(requestDto, userDetails.getUser());
        List<String> tags = requestDto.getTags();
        for (String stringTag : tags) {
            Tag tag = new Tag();
            tag.setPosts(posts);
            tag.setTag(stringTag);
            tagRepository.save(tag);
        }
        return postRepository.save(posts).getPostId();
    }

    //게시글 수정 이미지없이
    @Transactional
    public Long PostUpdateNoImage(PostRequestDto requestDto, Long postId, UserDetailsImpl userDetails) {
        Posts posts = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("수정할 게시글이 없습니다.")
        );
        if (posts.getUser().getUserId().equals(userDetails.getUser().getUserId())) {
            List<String> updateTag = requestDto.getTags();
            List<Tag> dbTagList = posts.getTags();
            List<String> dbTagName = new ArrayList<>();
            for (Tag tag : dbTagList) {
                dbTagName.add(tag.getTag());
            }
            if (updateTag.size()==0) {
                for (Tag tag : dbTagList) {
                    tagRepository.deleteById(tag.getTagId());
                }
                posts.update(requestDto.getContent(), requestDto.getTitle());
                return posts.getPostId();
            } else if (updateTag.size() > dbTagList.size()) {
                for (String ut : updateTag) {
                    if (!dbTagName.contains(ut)) {
                        Tag tag = new Tag();
                        tag.setPosts(posts);
                        tag.setTag(ut);
                        tagRepository.save(tag);
                    }
                }
                posts.update(requestDto.getContent(), requestDto.getTitle());
                log.info("이거왜안됨?2");
                return posts.getPostId();
            } else if (updateTag.size() == dbTagList.size()) {
                for (String ut : updateTag) {
                    if (!dbTagName.contains(ut)) {
                        Tag tag = new Tag();
                        tag.setPosts(posts);
                        tag.setTag(ut);
                        tagRepository.save(tag);
                    }
                }
                for (String dt : dbTagName) {
                    if (!updateTag.contains(dt)) {
                        Tag tag= tagRepository.findTagByPostsAndTag(posts, dt);
                        tagRepository.deleteById(tag.getTagId());
                    }
                }
                posts.update(requestDto.getContent(), requestDto.getTitle());
                log.info("이거왜안됨?3");
                return posts.getPostId();
            }else if(updateTag.size()<dbTagList.size()){
                for(String dt:dbTagName){
                    if(!updateTag.contains(dt)){
                        Tag tag=tagRepository.findTagByPostsAndTag(posts,dt);
                        tagRepository.deleteById(tag.getTagId());
                    }
                }
                posts.update(requestDto.getContent(), requestDto.getTitle());
                return posts.getPostId();
            }
            log.info("아무것도 안드러있음22");
            posts.update(requestDto.getContent(), requestDto.getTitle());
            return 0L;
        } else {
            log.info("아무것도 안드러있음");
            return 0L;
        }
    }

    //게시글 수정
    @Transactional
    public Long postUpdate(PostRequestDto requestDto, Long postId, UserDetailsImpl userDetails) {
        Posts posts = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("수정할 게시글이 없습니다.")
        );
        if (posts.getUser().getUserId().equals(userDetails.getUser().getUserId())) {
            List<String> updateTag = requestDto.getTags();
            List<Tag> dbTagList = posts.getTags();
            List<String> dbTagName = new ArrayList<>();
            for (Tag tag : dbTagList) {
                dbTagName.add(tag.getTag());
            }
            if (updateTag.size()==0) {
                for (Tag tag : dbTagList) {
                    tagRepository.deleteById(tag.getTagId());
                }
                posts.update(requestDto.getContent(), requestDto.getImgUrl(), requestDto.getTitle());
                return posts.getPostId();
            } else if (updateTag.size() > dbTagList.size()) {
                for (String ut : updateTag) {
                    if (!dbTagName.contains(ut)) {
                        Tag tag = new Tag();
                        tag.setPosts(posts);
                        tag.setTag(ut);
                        tagRepository.save(tag);
                    }
                }
                posts.update(requestDto.getContent(), requestDto.getImgUrl(), requestDto.getTitle());
                log.info("이거왜안됨?2");
                return posts.getPostId();
            } else if (updateTag.size() == dbTagList.size()) {
                for (String ut : updateTag) {
                    if (!dbTagName.contains(ut)) {
                        Tag tag = new Tag();
                        tag.setPosts(posts);
                        tag.setTag(ut);
                        tagRepository.save(tag);
                    }
                }
                for (String dt : dbTagName) {
                    if (!updateTag.contains(dt)) {
                        Tag tag= tagRepository.findTagByPostsAndTag(posts, dt);
                        tagRepository.deleteById(tag.getTagId());
                    }
                }
                posts.update(requestDto.getContent(), requestDto.getImgUrl(), requestDto.getTitle());
                log.info("이거왜안됨?3");
                return posts.getPostId();
            }else if(updateTag.size()<dbTagList.size()){
                for(String dt:dbTagName){
                    if(!updateTag.contains(dt)){
                        Tag tag=tagRepository.findTagByPostsAndTag(posts,dt);
                        tagRepository.deleteById(tag.getTagId());
                    }
                }
                posts.update(requestDto.getContent(), requestDto.getTitle());
                return posts.getPostId();
            }
            log.info("아무것도 안드러있음22");
            posts.update(requestDto.getContent(), requestDto.getImgUrl(), requestDto.getTitle());
            return 0L;
        } else {
            log.info("아무것도 안드러있음");
            return 0L;
        }
    }

    //게시글 삭제
    @Transactional
    public Boolean deletePost(Long postId, UserDetailsImpl userDetails) {
        Posts posts = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("삭제할 게시글이 없습니다.")
        );
        if (posts.getUser().getUserId().equals(userDetails.getUser().getUserId())) {
            tagRepository.deleteAllByPosts(posts);
            postRepository.deleteById(posts.getPostId());
            return true;
        } else {
            return false;
        }
    }

    //게시글 상세조회
    @Transactional
    public PostResponseDto postDetail(Long postId,UserDetailsImpl userDetails) {
        Posts posts = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("게시글이 없습니다.")
        );
        User user=userRepository.findById(userDetails.getUser().getUserId()).orElseThrow(
                ()->new NullPointerException("로그인 해주세요")
        );
        LikeUser likeUser = likeUserRepository.findByUserAndPosts(userDetails.getUser(), posts).orElse(null);
        PostResponseDto responseDto = new PostResponseDto();
        responseDto.setPostId(posts.getPostId());
        responseDto.setTitle(posts.getTitle());
        responseDto.setContent(posts.getContent());
        responseDto.setPostDate(posts.getCreatedAt());
        responseDto.setLikeCnt(posts.getLikeCnt()); //바꿈
        responseDto.setCommentCnt(posts.getComments().size());
        responseDto.setImgUrl(posts.getImgUrl());
        responseDto.setUsername(posts.getUser().getUsername());
        responseDto.setProfileUrl(posts.getUser().getProfileUrl());
        responseDto.setNickname(posts.getUser().getNickname());
        List<Tag> tagList = tagRepository.findTagByPosts(posts);
        List<String> tags = new ArrayList<>();
        for (Tag tag : tagList) {
            tags.add(tag.getTag());
        }
        responseDto.setTags(tags);
        if (likeUser == null) {
            responseDto.setIsLike(false);
            log.info("널일경우");
        }else{
            responseDto.setIsLike(likeUser.getIsLike());
            log.info("널 아닐경우");
        }
        return responseDto;

    }
}
