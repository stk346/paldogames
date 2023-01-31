package hello.paldogames.domain.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class BoardDto {

    private final String boardTitle;
    private final String content;
    private final LocalDateTime dateTime;

    private final MemberDto memberDto;
}
