package hello.paldogames.domain.form;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@RequiredArgsConstructor
public class LoginForm {

    @NotEmpty(message = "회원 아이디는 필수입니다.")
    private String id;
    @NotEmpty(message = "회원 비밀번호는 필수입니다.")
    private String password;
}
