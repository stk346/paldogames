package hello.paldogames.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue
    private Long id;

    private String name;

    private String password;

    private int point;


    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Board> boards = new ArrayList<>();
}
