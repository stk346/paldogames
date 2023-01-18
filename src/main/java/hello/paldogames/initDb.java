package hello.paldogames;

import hello.paldogames.domain.Board;
import hello.paldogames.domain.Member;
import hello.paldogames.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;

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
        initService.init();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class initService {
        private final EntityManager em;
        private final BoardRepository boardRepository;

        public void init() {
            Member member = new Member();
            member.setName("test");
            member.setPassword("test");

            em.persist(member);

            int boardCnt = 70;
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
    }
}
