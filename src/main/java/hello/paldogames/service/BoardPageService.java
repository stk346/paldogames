package hello.paldogames.service;

import hello.paldogames.domain.Board;
import hello.paldogames.repository.PageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardPageService {

    private final PageRepository pageRepository;

    public int getPages(int startPage, int endPage, int boardPerPage) {
        return pageRepository.getPagesCount(startPage, endPage, boardPerPage);
    }
}
