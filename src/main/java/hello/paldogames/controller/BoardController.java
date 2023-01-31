package hello.paldogames.controller;

import hello.paldogames.domain.Board;
import hello.paldogames.domain.Member;
import hello.paldogames.domain.PageCriteria;
import hello.paldogames.domain.dto.CommentDto;
import hello.paldogames.domain.dto.PageNumberMakerDto;
import hello.paldogames.domain.form.BoardForm;
import hello.paldogames.service.BoardPageService;
import hello.paldogames.service.BoardService;
import hello.paldogames.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;
    private final BoardPageService boardPageService;

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
        boardService.publish(form, request.getSession());

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
        PageCriteria pc = new PageCriteria(currentPage, boardPerPage);
        PageNumberMakerDto pageNumberMakerDto = boardPageService.getPages(pc);
        model.addAttribute("pageNumberDto", pageNumberMakerDto);
        List<Board> boards = boardService.getPage(pc);
        log.info("boards= {}", boards);
        model.addAttribute("boards", boards);

        return "/board/boards";
    }

    /**
     * 게시글의 제목을 클릭하면 해당 게시글의 내용을 보여주는 메서드
     */
    @GetMapping("/boards")
    public String boardInfo(@RequestParam("boardId") Long id,
                            @Valid @ModelAttribute("commentForm") CommentDto commentForm,
                            Model model) {
        model.addAttribute("boardId", id);
        Board findBoard = boardService.findById(id);
        model.addAttribute("board", boardService.findById(id));
        Member findMember = findBoard.getMember();
        model.addAttribute("member", findMember);

        // 목록 페이지를 볼 수 있도록 해당 페이지의 url에 매핑되는 변수를 넣어줌
        //Todo
        // 목록으로 갈 때 내림차순 기준으로 urlVariable 을 설정했는데 오름차순으로 바꿔야함
        int currentUrlVariable = (int) Math.floor(id / 10) + 1;
        model.addAttribute("urlVariable", currentUrlVariable);

        List<CommentDto> comments = commentService.getComments(findBoard);
        model.addAttribute("comments", comments);

        return "/board/board";
    }
}

