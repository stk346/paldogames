package hello.paldogames.domain.dto;

import hello.paldogames.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long id;

    private String name;

    private String password;
    private String greetings;

    private int point;

    private List<Board> boards = new ArrayList<>();
}
