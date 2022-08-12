package clone.colley.controller;

import clone.colley.dto.Request.CommentRequestDto;
import clone.colley.dto.Response.CommentResponseDto;
import clone.colley.model.User;
import clone.colley.security.UserDetailsImpl;
import clone.colley.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //댓글 작성
    //게시글 id로 댓글 작성
    @PostMapping("/comment/{postId}")
    public Long createComment(@PathVariable Long postId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) throws IllegalAccessError {

        User user = userDetails.getUser();
        return commentService.createComment(postId, requestDto, user);
    }

    @DeleteMapping("/comment/{commentId}")
    public void deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.deleteComment(commentId, userDetails);
    }

    @GetMapping("/comment/{postId}")
    public List<CommentResponseDto> getComment(@PathVariable Long postId) {
        return commentService.getComment(postId);
    }
}
