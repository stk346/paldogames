package hello.paldogames.domain.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class CommentDto {

    private String uuid;

    private Long commentId;
    private String name;
    private LocalDateTime dateTime;
    private String content;
}
