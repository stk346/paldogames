package hello.paldogames;

import hello.paldogames.domain.Board;
import hello.paldogames.domain.Comment;
import hello.paldogames.domain.Member;
import hello.paldogames.domain.SessionMember;
import hello.paldogames.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

/**
 * ID: test
 * PASSWORD: test
 * 게시글 수: 70개
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class initDb {

    private final initService initService;

    @PostConstruct
    private void init() {
        initService.initBoard();
        initService.initComments();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class initService {
        private final EntityManager em;
        private final BoardRepository boardRepository;

        public void initBoard() {
            Member member = new Member();
            member.setName("test");
            member.setPassword("test");
            em.persist(member);

            int boardCnt = 180;
            for (int i = 0; i < boardCnt; i++) {
                Board board = new Board();
                String boardTitle = "title" + i;
                String boardContent = "content" + i;
                board.setBoardTitle(boardTitle);
                board.setBoardContent(boardContent);
                board.setMember(member);
                board.setDateTime(LocalDateTime.now());
                em.persist(board);
            }
        }

        public void initComments() {
            Member member2 = new Member();
            member2.setName("userA");
            member2.setPassword("userA");

            Member member3 = new Member();
            member3.setName("userB");
            member3.setPassword("userB");

            em.persist(member2);
            em.persist(member3);

            List<Board> boards = boardRepository.findAll();
            for (Board board : boards) {
                for (int i = 0; i < 2; i++) {
                    Comment comment1 = new Comment();
                    comment1.setDateTime(LocalDateTime.now());
                    comment1.setBoard(board);
                    comment1.setMember(member2);
                    comment1.setContent(member2.getName() + "comment" + i+1);

                    Comment comment2 = new Comment();
                    comment2.setDateTime(LocalDateTime.now());
                    comment2.setBoard(board);
                    comment2.setMember(member2);
                    comment2.setContent(member2.getName() + "comment" + i+2);

                    em.persist(comment1);
                    em.persist(comment2);
                }
            }

        }
    }
}
