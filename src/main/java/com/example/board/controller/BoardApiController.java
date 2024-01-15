package com.example.board.controller;

import com.example.board.config.auth.PrincipalDetail;
import com.example.board.config.auth.PrincipalDetailService;
import com.example.board.dto.ReplySaveRequestDto;
import com.example.board.dto.ResponseDto;
import com.example.board.model.Board;
import com.example.board.model.User;
import com.example.board.service.BoardService;
import com.example.board.service.UserService;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardApiController {
    private final BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        boardService.writePost(board, principalDetail.getUser());
        return new ResponseDto<>(HttpStatus.OK, 1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id) {
        boardService.deletePost(id);
        return new ResponseDto<>(HttpStatus.OK, 1);
    }


    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board) {
        boardService.updatePost(id, board);
        return new ResponseDto<>(HttpStatus.OK, 1);
    }

    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> saveReply(@RequestBody ReplySaveRequestDto reply) {
        boardService.writeReply(reply);
        return new ResponseDto<>(HttpStatus.OK, 1);
    }

    @DeleteMapping("/api/board/reply/{replyId}")
    public ResponseDto<Integer> deleteReply(@PathVariable long replyId) {
        boardService.deleteReply(replyId);
        return new ResponseDto<>(HttpStatus.OK, 1);
    }
}
