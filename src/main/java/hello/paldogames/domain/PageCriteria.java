package hello.paldogames.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class PageCriteria {

    private final int currentPage;
    private final int boardPerPage;

    private final int skipPage;

    public PageCriteria(int currentPage, int boardCountsPerPage) {
        this.currentPage = currentPage;
        this.boardPerPage = boardCountsPerPage;
        this.skipPage = (currentPage - 1) * boardCountsPerPage;
    }

    @Override
    public String toString() {
        return "Criteria[pageNum= " + currentPage + ", boardPerPage = " + boardPerPage + "]";
    }
}
