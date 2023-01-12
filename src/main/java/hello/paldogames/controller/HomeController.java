package hello.paldogames.controller;

import hello.paldogames.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class HomeController {

//    @RequestMapping("/")
    public String home() {
        log.info("Home 이동");
        return "home";
    }

    /**
     * 로그인 유무에 따라서 각기 다른 페이지를 보여줌
     */
    @RequestMapping("/")
    public String loginHome(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if (session == null) {
            return "home";
        }

        Member loginMember = (Member) session.getAttribute("loginMember");
        if (loginMember == null) {
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}
