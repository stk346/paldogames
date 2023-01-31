package hello.paldogames.service;

import hello.paldogames.domain.Board;
import hello.paldogames.domain.PageCriteria;
import hello.paldogames.domain.dto.PageNumberMakerDto;
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

    public PageNumberMakerDto getPages(PageCriteria pc) {
        PageNumberMakerDto pm = new PageNumberMakerDto(pc);
        int boardsCount = pageRepository.getPagesCount(pm.getStartPage(), pm.getEndPage(), pc.getBoardPerPage());
        pm.setTotalBoardsCount(boardsCount);
        pm.calcData();
        return pm;
    }
}
