package hello.paldogames.controller;

import hello.paldogames.domain.Member;
import hello.paldogames.domain.MemberForm;
import hello.paldogames.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @RequestMapping("/members/new")
    public String join(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "/members/memberJoinForm";
    }

    @PostMapping("/members/new")
    public String join(@Valid MemberForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "members/memberJoinForm";
        }

        Member member = new Member();
        member.setName(form.getId());
        member.setPassword(form.getPassword());
        memberService.join(member);

        return "redirect:/";
    }
}
