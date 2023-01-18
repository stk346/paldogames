package hello.paldogames.service;

import hello.paldogames.domain.Board;
import hello.paldogames.domain.PageCriteria;
import hello.paldogames.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long publish(Board board) {
        boardRepository.save(board);
        return board.getId();
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public List<Board> findByName(String name) {
        return boardRepository.findByName(name);
    }

    public List<Board> getPage(PageCriteria pc) {
        return boardRepository.getPage(pc);
    }
}
