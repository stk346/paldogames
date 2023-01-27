package hello.paldogames.controller;

import hello.paldogames.domain.Comment;
import hello.paldogames.domain.Member;
import hello.paldogames.domain.SessionMember;
import hello.paldogames.domain.dto.CommentDto;
import hello.paldogames.repository.CommentRepository;
import hello.paldogames.repository.SessionRepository;
import hello.paldogames.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;
    private final SessionRepository sessionRepository;
    private final BoardService boardService;

    //TODO
    // 댓글을 작성하고 작성완료 버튼을 눌렀을 때 요청 방식은 get, post중 무엇이 돼야 하는지
    // 만약 post일 경우 댓글 내용과 유저의 세션은 어떻게 전달돼야 하는지
    // 만약 get일 경우 return은 무엇이 돼야 하는지

    /**
     * 댓글 작성 버튼을 누르면 Comment에 댓글 내용, 사용자 정보를 받아
     * ModelAttribute로 넘긴 후 원래 게시글로 리다이렉트하는 메소드
     */
    @GetMapping("boards/addComment")
    public String publish(@RequestParam("boardId") Long id,
                          @Valid @ModelAttribute CommentDto commentDto,
                          HttpServletRequest request) {
        log.info("findContent= {}", commentDto.getContent());

        log.info("댓글 작성 후 post 호출 완료");

        HttpSession currentSession = request.getSession();
        String session = currentSession.getId();
        log.info("currentSession= {}", session);
        SessionMember findSession = sessionRepository.findMember(session);
        Member member  = findSession.getMember();

        Comment comment = new Comment();
        comment.setDateTime(LocalDateTime.now());
        comment.setBoard(boardService.findById(id));
        comment.setMember(member);
        comment.setContent(commentDto.getContent());
        log.info("createdComment= {}", comment);
        commentRepository.save(comment);

        return "redirect:/boards?boardId=" + id;
    }
}
