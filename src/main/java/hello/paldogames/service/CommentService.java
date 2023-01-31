package hello.paldogames.service;

import hello.paldogames.domain.Board;
import hello.paldogames.domain.Comment;
import hello.paldogames.domain.dto.CommentDto;
import hello.paldogames.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    public List<Comment> findByBoardId(Board board) {
        List<Comment> comments = commentRepository.findByBoardId(board);
        log.info("findComment={} ", comments);
        return comments;
    }

    public List<CommentDto> getComments(Board board) {
        List<CommentDto> commentDtos = new ArrayList<>();
        List<Comment> comments = findByBoardId(board);
        for (Comment comment : comments) {
            CommentDto commentDto = new CommentDto();
            commentDto.setCommentId(comment.getId());
            commentDto.setName(comment.getMember().getName());
            commentDto.setDateTime(comment.getDateTime());
            commentDto.setContent(comment.getContent());
            commentDtos.add(commentDto);
        }
        return commentDtos;
    }
}
