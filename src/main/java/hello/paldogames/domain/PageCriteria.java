package hello.paldogames.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class PageCriteria {

    private final int currentPage;
    private final int boardPerPage;

    private final int skipPage;

    public PageCriteria(int currentPage, int boardPerPage) {
        this.currentPage = currentPage;
        this.boardPerPage = boardPerPage;
        this.skipPage = (currentPage - 1) * boardPerPage;
    }

    @Override
    public String toString() {
        return "Criteria[pageNum= " + currentPage + ", boardPerPage = " + boardPerPage + "]";
    }
}
