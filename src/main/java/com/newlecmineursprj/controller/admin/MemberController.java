package com.newlecmineursprj.controller.admin;

import com.newlecmineursprj.entity.Coupon;
import com.newlecmineursprj.entity.Member;
import com.newlecmineursprj.entity.Qna;
import com.newlecmineursprj.entity.Review;
import com.newlecmineursprj.service.CouponService;
import com.newlecmineursprj.service.MemberService;
import com.newlecmineursprj.service.QnaService;
import com.newlecmineursprj.service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("admin")
@Controller("adminMemberController")
public class MemberController {

    private static final String MEMBERS_VIEW = "/admin/members";
    @Autowired
    private MemberService service;
    @Autowired
    private CouponService couponService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private QnaService qnaService;

    @GetMapping("members")
    public String list(@RequestParam(required = false) String searchMethod
            , @RequestParam(defaultValue = "") String searchKeyword
            , Model model) {


        List<Member> list = service.getList(searchMethod, searchKeyword.trim());
        model.addAttribute("list", list);
        return MEMBERS_VIEW + "/list";
    }

    @GetMapping("members/detail")
    public String detail(@RequestParam(name = "id", required = false) Long id,
                        Model model) {

        Member member = service.findById(id);
        List<Coupon> coupons = couponService.getListByMemberId(id);
        List<Review> reviews = reviewService.getListByMemberId(id);
        List<Qna> qnas = qnaService.getListByMemberId(id);

        model.addAttribute("member", member);
        model.addAttribute("coupons", coupons);
        model.addAttribute("reviews", reviews);
        model.addAttribute("qnas", qnas);
        return MEMBERS_VIEW + "/detail";
    }

    @PostMapping("members/delete")
    public String delete(@RequestParam("deleteId") List<Long> deleteIds){

        service.deleteAll(deleteIds);

        return "redirect:" + MEMBERS_VIEW;
    }
}
