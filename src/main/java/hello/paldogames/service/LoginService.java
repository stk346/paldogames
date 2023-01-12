package hello.paldogames.service;

import hello.paldogames.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginService {

    private final EntityManager em;

    @Transactional
    public Member login(String loginId, String password) {
        List<Member> findMember = em.createQuery("select m from Member m where m.name = :loginId", Member.class)
                .setParameter("loginId", loginId)
                .getResultList();

        return findMember.stream()
                .filter(m -> m.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }
}
