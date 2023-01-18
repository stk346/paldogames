package hello.paldogames.controller;

import hello.paldogames.domain.Member;
import hello.paldogames.domain.SessionMember;
import hello.paldogames.domain.form.LoginForm;
import hello.paldogames.domain.form.MemberForm;
import hello.paldogames.repository.SessionRepository;
import hello.paldogames.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final SessionRepository sessionRepository;

    @GetMapping("/login")
    public String login(@ModelAttribute("loginForm") LoginForm loginForm) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm form,
                        BindingResult bindingResult,
                        @RequestParam(defaultValue = "/board?currentPage=1&boardPerPage=10") String requestUrl,
                        HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }
        Member loginMember = loginService.login(form.getId(), form.getPassword());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디, 혹은 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute("loginMember", loginMember);
        SessionMember sessionMember = new SessionMember();
        sessionMember.setSession(request.getSession().getId());
        sessionMember.setMember(loginMember);
        sessionRepository.save(sessionMember);

        log.info("sessionMember: {}", (Member) session.getAttribute("loginMember"));
        log.info("session={} ", session.getId());
        return "redirect:" + requestUrl;
    }

    @PostMapping("/logout")
    public String logoutV3(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
