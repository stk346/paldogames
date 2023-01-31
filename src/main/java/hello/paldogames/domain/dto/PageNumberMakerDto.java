package hello.paldogames.domain.dto;

import hello.paldogames.domain.PageCriteria;
import hello.paldogames.repository.PageRepository;
import hello.paldogames.service.BoardPageService;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;

@Data
@RequiredArgsConstructor
public class PageNumberMakerDto {

    private int startPage;
    private int endPage;
    private int currentPage;
    private boolean prev = true, next = true;

    /* 표시된 페이지 버튼의 게시글 수 */
    private int totalBoardsCount;

    /* 현재 페이지, 페이지당 게시물 표시 수 정보 */
    private PageCriteria pageCriteria;

    public PageNumberMakerDto (PageCriteria pageCriteria) {
        this.pageCriteria = pageCriteria;
        this.currentPage = pageCriteria.getCurrentPage();
        this.endPage = (int) Math.ceil(currentPage/(double) 10) * 10;
        this.startPage = endPage - 9;
    }



    public void calcData() {
        int totalPage = (int) Math.ceil(totalBoardsCount / (double) pageCriteria.getBoardPerPage());
        if (10 > totalPage) {
            this.endPage = endPage - 10 + totalPage;
            this.next = false;
        }
        this.prev = this.startPage != 1;
    }
}
