package hello.paldogames.repository;

import hello.paldogames.domain.Board;
import hello.paldogames.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class CommentRepository {

    private final EntityManager em;

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
