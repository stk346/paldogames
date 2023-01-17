package hello.paldogames.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
public class SessionMember {

    @Id
    @Column(name = "session_id")
    @GeneratedValue
    private Long id;

    private String session;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
