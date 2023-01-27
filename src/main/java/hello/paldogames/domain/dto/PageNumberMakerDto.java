package hello.paldogames.domain.dto;

import hello.paldogames.domain.PageCriteria;
import hello.paldogames.repository.PageRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PageNumberMakerDto {

    private int startPage;
    private int endPage;
    private boolean prev = true, next = true;

    /* 표시된 페이지 버튼의 게시글 수 */
    private int totalBoardsCount;

    private PageRepository pageRepository;

    /* 현재 페이지, 페이지당 게시물 표시 수 정보 */
    private PageCriteria pageCriteria;

    public PageNumberMakerDto(PageCriteria pageCriteria) {
        this.pageCriteria = pageCriteria;
        int currentPage = pageCriteria.getCurrentPage();
        this.endPage = (int) Math.ceil(currentPage/10) * 10;
        this.startPage = endPage - 9;
        this.totalBoardsCount = pageRepository.getPagesCount(startPage, endPage, pageCriteria.getBoardPerPage());

        calcData();
    }

    public void calcData() {
        int showingEndPage = (int) Math.ceil(totalBoardsCount / pageCriteria.getBoardPerPage());
        if (10 < showingEndPage) {
            this.endPage = endPage + showingEndPage;
            this.next = false;
        }
        this.prev = this.startPage != 1;
    }
}
