package hello.paldogames.controller;

import hello.paldogames.domain.Board;
import hello.paldogames.domain.Member;
import hello.paldogames.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class HomeController {

    @Autowired
    private BoardService boardService;

    //    @RequestMapping("/")
    public String home() {
        log.info("Home 이동");
        return "home";
    }

    /**
     * 로그인 유무에 따라서 각기 다른 페이지를 보여줌
     */
    @GetMapping("/")
    public String loginHome(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if (session == null) {
            return "home";
        }

        Member loginMember = (Member) session.getAttribute("loginMember");
        if (loginMember == null) {
            return "home";
        }

        return "redirect:/board?currentPage=1&boardPerPage=10";
    }
}
