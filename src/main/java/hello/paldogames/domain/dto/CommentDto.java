package hello.paldogames.domain.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class CommentDto {

    private String uuid;

    private Long commentId;
    private String name;
    private LocalDateTime dateTime;

//    @NotBlank(message = "댓글을 입력해주세요.")
    private String content;
}
