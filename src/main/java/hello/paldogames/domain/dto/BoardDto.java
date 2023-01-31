package hello.paldogames.domain.dto;

import hello.paldogames.domain.Member;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Data
@RequiredArgsConstructor
public class BoardDto {

    private final String boardTitle;
    private final String content;
    private final LocalDateTime dateTime;

    private final MemberDto memberDto;
}
