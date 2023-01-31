package hello.paldogames.service;

import hello.paldogames.domain.SessionMember;
import hello.paldogames.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;

    public void save(SessionMember sessionMember) {
        sessionRepository.save(sessionMember);
    }

    public SessionMember findMember(String sessionId) {
        return sessionRepository.findMember(sessionId);
    }
}
