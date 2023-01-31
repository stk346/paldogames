package hello.paldogames.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class PageRepository {

    private final EntityManager em;

    @Transactional
    public int getPagesCount(int startPage, int endPage, int boardPerPage) {
        int startData = (startPage - 1) * boardPerPage;
        int endData = (endPage) * boardPerPage ;

        return em.createQuery(
                "select b from Board b"
        ).setFirstResult(startData)
        .setMaxResults(endData)
        .getResultList()
        .size();
    }
}
