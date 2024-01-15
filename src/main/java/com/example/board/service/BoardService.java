package com.example.board.service;

import com.example.board.dto.ReplySaveRequestDto;
import com.example.board.model.Board;
import com.example.board.model.Reply;
import com.example.board.model.User;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.ReplyRepository;
import com.example.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final ReplyRepository replyRepository;

    @Transactional
    public void writePost(Board board, User user) {
        board.setUser(user);
        boardRepository.save(board);
    }

    public Page<Board> getPostList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board getPost(long id) {
        return boardRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Failed to load post : cannot find post id"));
    }

    @Transactional
    public void deletePost(long id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void updatePost(long id, Board requestBoard) {
        Board board = boardRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Failed to load post : cannot find post id")); // 영속화
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        // 이때 더티 체킹 - 자동 업데이트
    }

    @Transactional
    public void writeReply(ReplySaveRequestDto replyDto) {
        Board board = boardRepository.findById(replyDto.getBoardId()).orElseThrow(()
                -> new IllegalArgumentException("Failed to write reply : cannot find post id"));

        User user = userRepository.findById(replyDto.getUserId()).orElseThrow(()
                -> new IllegalArgumentException("Failed to write reply : cannot find post id"));

        Reply reply = Reply.builder()
                .user(user)
                .board(board)
                .content(replyDto.getContent())
                .build();

        replyRepository.save(reply);
    }

    @Transactional
    public void deleteReply(long replyId) {
        replyRepository.deleteById(replyId);
    }
}
