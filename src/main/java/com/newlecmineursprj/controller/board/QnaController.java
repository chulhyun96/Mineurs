package com.newlecmineursprj.controller.board;

import com.newlecmineursprj.config.security.WebUserDetails;
import com.newlecmineursprj.entity.Qna;
import com.newlecmineursprj.entity.QnaView;
import com.newlecmineursprj.service.MemberService;
import com.newlecmineursprj.service.QnaService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("qna")
public class QnaController {

    private final QnaService service;
    private final MemberService MemberService;

    @GetMapping
    public String qnaList(@RequestParam(required = false) String searchMethod
            , @RequestParam(defaultValue = "1") Integer page
            , @RequestParam(defaultValue = "") String searchKeyword
            , @RequestParam(defaultValue = "0") Integer categoryId
            , Model model) {

        List<QnaView> list = service.getList(page, searchMethod, searchKeyword, categoryId, null);
        model.addAttribute("list", list);
        return "/board/qna/list";
    }

    @GetMapping("reg")
    public String regForm() {

        return "board/qna/new";
    }

    @PostMapping("reg")
    public String reg(Qna qna, SecurityContextHolder securityContextHolder) {
        //사용자 정보 조회
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WebUserDetails webUserDetails = getPrincipal(authentication);
        qna.setMemberId(webUserDetails.getId());
        service.reg(qna);


        return "redirect:/qna";
    }

    private static WebUserDetails getPrincipal(Authentication authentication) {
        return (WebUserDetails) authentication.getPrincipal();
    }

    @GetMapping("{id}")
    public String detail(Model model, @PathVariable Long id ,@CookieValue(value = "access_granted", defaultValue = "false") String accessGranted) {
        if (!accessGranted.equals("true")) {
            return "redirect:/error";
        }
        Qna qna = service.getById(id);
        service.increase(id);
        model.addAttribute("qna", service.getById(id));
        if (qna.getMemberId() != null)
            model.addAttribute("member", MemberService.getById(qna.getMemberId()));

        return "board/qna/detail";
    }

    @GetMapping("secretForm")
    public String secretForm(Long id, Model model, @ModelAttribute("errorMessage")Optional<String> errorMessage) {
        model.addAttribute("id", id);
        errorMessage.ifPresent(msg -> model.addAttribute("errorMessage",msg));
        return "board/qna/secretForm";
    }

    @PostMapping("secret")
    public String secret(Long id, String password, RedirectAttributes redirectAttributes, HttpServletResponse response) {

        int result = service.getByPassword(id, password);
        if (result == 1) {
            Cookie cookie = new Cookie("access_granted", "true");
            cookie.setMaxAge(60 * 30);
            response.addCookie(cookie);
            return "redirect:/qna/" + id;
        }
        else {
            redirectAttributes.addFlashAttribute("errorMessage", "비밀번호가 잘못되었습니다.");
            redirectAttributes.addAttribute("id", id);
            return "redirect:/qna/secretForm";
        }
    }
}
