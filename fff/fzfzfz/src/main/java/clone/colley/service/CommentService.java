package clone.colley.service;

import clone.colley.dto.Request.CommentRequestDto;
import clone.colley.dto.Response.CommentResponseDto;
import clone.colley.model.Comment;
import clone.colley.model.Posts;
import clone.colley.model.User;
import clone.colley.repository.CommentRepository;
import clone.colley.repository.PostRepository;
import clone.colley.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor //롬북을 통해서 간단하게 생성자 주입 방식의 어노테이션으로 fjnal이 붙거나 @notNull이 붙은 생성자들을 자동 생성해준다.
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    //댓글 작성
    @Transactional
    public Long createComment(Long PostId, CommentRequestDto commentRequestDto, User user) throws IllegalStateException {

        try {
            Posts posts = postRepository.findById(PostId).orElseGet(null);

            Comment comment = new Comment(commentRequestDto, user, posts);
            return commentRepository.save(comment).getCommentId();
        } catch (Exception e) {
            throw new IllegalStateException("게시물이 존재하지 않습니다");
        }
    }

    //댓글 조회
    public List<CommentResponseDto> getComment(Long postId) {
        Posts posts = postRepository.findById(postId).orElseThrow(
                ()-> new NullPointerException("해당 게시물이 존재하지 않습니다")
        );

        List<Comment> comments = commentRepository.findAllByPosts(posts);

        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();
        for (Comment comment : comments) {
            CommentResponseDto commentResponseDto = new CommentResponseDto(
                    comment.getCommentId(),
                    comment.getUser().getNickname(),
                    comment.getComment(),
                    comment.getUser().getUsername(),
                    comment.getCreatedAt(),
                    comment.getUser().getProfileUrl()
            );
            commentResponseDtos.add(commentResponseDto);
        }

        return commentResponseDtos;
    }

    @Transactional
    public boolean deleteComment(Long id, UserDetailsImpl userDetails) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("댓글이 존재하지 않습니다")
        );
        if(comment.getUser().getUserId().equals(userDetails.getUser().getUserId())){
            commentRepository.deleteById(comment.getCommentId());
            return true;
        } else{
            return false;
        }

    }
}
