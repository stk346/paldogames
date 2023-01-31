package hello.paldogames.repository;

import hello.paldogames.domain.Board;
import hello.paldogames.domain.PageCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    public void save(Board board) {
        em.persist(board);
    }

    public Board findOne(Long boardId) {
        return em.find(Board.class, boardId);
    }

    public List<Board> findAll() {
        return em.createQuery("select b from Board b", Board.class)
                .getResultList();
    }

    public List<Board> findByName(String name) {
        return em.createQuery("select b from Board b where b.name = :name", Board.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Board> getPage(PageCriteria pc) {
        return em.createQuery(
                "select b from Board b" +
                        " order by b.id desc", Board.class
        ).setFirstResult(pc.getSkipPage())
        .setMaxResults(pc.getBoardPerPage())
        .getResultList();
    }
}
