package hello.paldogames.controller;

import hello.paldogames.domain.Board;
import hello.paldogames.domain.PageCriteria;
import hello.paldogames.domain.SessionMember;
import hello.paldogames.domain.form.BoardForm;
import hello.paldogames.repository.MemberRepository;
import hello.paldogames.repository.SessionRepository;
import hello.paldogames.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.h2.engine.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final SessionRepository sessionRepository;

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
        board.setDateTime(LocalDateTime.now());


        HttpSession session = request.getSession();
        SessionMember findSession = sessionRepository.findMember(session.getId());
        board.setMember(findSession.getMember());
        boardService.publish(board);

        return "redirect:/board?currentPage=1&boardPerPage=10";
    }

    /**
     * currentPage, boardPerPage를 파라미터로 받아 게시글을 나열하는 메서드
     * url: board?currentPage=1&boardPerPage=10
     */
    @GetMapping("/board")
    public String boardPage(@RequestParam("currentPage") int currentPage,
                            @RequestParam("boardPerPage") int boardPerPage,
                            Model model
    ) {
        PageCriteria pc = new PageCriteria((currentPage), (boardPerPage));
        List<Board> boards = boardService.getPage(pc);
        log.info("boards= {}", boards);
        model.addAttribute("boards", boards);

        return "/board/boards";
    }

    /**
     * 게시글의 제목을 클릭하면 해당 게시글의 내용을 보여주는 메서드
     */
    @GetMapping("/boards")
    public String boardInfo(@RequestParam("boardId") int id,
                            Model model) {
        model.addAttribute("boardId", id);

        // 목록 페이지를 볼 수 있도록 해당 페이지의 url에 매핑되는 변수를 넣어줌
        int currentUrlVariable = (int) Math.floor(id/10)*10 + 1;
        model.addAttribute("urlVariable", currentUrlVariable);

        return "/board/board";
    }
}

