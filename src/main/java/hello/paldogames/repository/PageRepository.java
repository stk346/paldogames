package hello.paldogames.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class PageRepository {

    private final EntityManager em;

    public int getPagesCount(int startPage, int endPage, int boardPerPage) {
        int startData = (startPage - 1) * boardPerPage;
        int endData = (endPage - 1) * boardPerPage;

        return em.createQuery(
                "select b from Board"
        ).setFirstResult(startData)
        .setMaxResults(endData)
        .getResultList()
        .size();
    }
}
