package hello.paldogames.repository;

import hello.paldogames.domain.Board;
import hello.paldogames.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final EntityManager em;

    @Transactional
    public void save(Comment comment) {
        em.persist(comment);
    }

    public List<Comment> findByBoardId(Board board) {

        return em.createQuery(
                        "select c from Comment c" +
                                " join fetch c.member" +
                                " where c.board = :board" +
                                " order by c.id desc", Comment.class
                ).setParameter("board", board)
                .getResultList();
    }
}

