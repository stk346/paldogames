package hello.paldogames.service;

import hello.paldogames.domain.Board;
import hello.paldogames.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

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
}
