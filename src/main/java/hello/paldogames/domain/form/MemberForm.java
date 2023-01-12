package hello.paldogames.domain.form;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "회원 아이디는 필수입니다.")
    private String id;
    @NotEmpty(message = "회원 비밀번호는 필수입니다.")
    private String password;
}
