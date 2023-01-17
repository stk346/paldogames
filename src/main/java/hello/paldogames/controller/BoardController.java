package hello.paldogames.controller;

import hello.paldogames.domain.Board;
import hello.paldogames.domain.SessionMember;
import hello.paldogames.domain.form.BoardForm;
import hello.paldogames.repository.MemberRepository;
import hello.paldogames.repository.SessionRepository;
import hello.paldogames.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final SessionRepository sessionRepository;
    private final MemberRepository memberRepository;

    @GetMapping("/board/publish")
    public String publish(@ModelAttribute("board") Board board) {
        return "board/boardPublish";
    }

    @PostMapping("/board/publish")
    public String publish(@Valid @ModelAttribute("board") BoardForm form,
                          HttpServletRequest request,
                          BindingResult result
    ) {
        if (result.hasErrors()) {
            return "board/boardPublish";
        }

        log.info("title = {}", form.getBoardTitle());
        log.info("content = {}", form.getBoardContent());

        Board board = new Board();
        board.setBoardTitle(form.getBoardTitle());
        board.setBoardContent(form.getBoardContent());

        HttpSession session = request.getSession();
        SessionMember findSession = sessionRepository.findMember(session.getId());
        board.setMember(findSession.getMember());
        boardService.publish(board);

        return "/board/board";
    }
}

