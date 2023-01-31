package hello.paldogames.repository;

import hello.paldogames.domain.SessionMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Slf4j
@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SessionRepository {

    private final EntityManager em;

    @Transactional
    public void save(SessionMember sessionMember) {
        em.persist(sessionMember);
    }

    public String findBySession(String session) {
        log.info("findedSessionId= {}", session);
        return (String) em.createQuery("select s from SessionMember s where session = :session")
                .setParameter("session", session)
                .getResultList().get(0).toString();
    }

    public SessionMember findMember(String sessionId) {
        return em.createQuery("select s from SessionMember s" +
                        " join fetch s.member m" +
                        " where session = :session", SessionMember.class)
                .setParameter("session", sessionId)
                .getResultList().get(0);
    }
}
