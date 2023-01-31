package hello.paldogames.service;

import hello.paldogames.domain.Board;
import hello.paldogames.domain.PageCriteria;
import hello.paldogames.domain.SessionMember;
import hello.paldogames.domain.form.BoardForm;
import hello.paldogames.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final SessionService sessionService;

    @Transactional
    public Long publish(BoardForm form, HttpSession session) {

        log.info("title = {}", form.getBoardTitle());
        log.info("content = {}", form.getBoardContent());

        Board board = new Board();
        board.setBoardTitle(form.getBoardTitle());
        board.setContent(form.getBoardContent());
        board.setDateTime(LocalDateTime.now());
        SessionMember findSession = sessionService.findMember(session.getId());

        board.setMember(findSession.getMember());
        boardRepository.save(board);
        return board.getId();
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public List<Board> findByName(String name) {
        return boardRepository.findByName(name);
    }

    public Board findById(Long id) {
        Board findBoard = boardRepository.findOne(id);
        log.info("boardId= {}", findBoard.getId());
        return findBoard;
    }

    public List<Board> getPage(PageCriteria pc) {
        return boardRepository.getPage(pc);
    }
}
