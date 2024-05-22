package com.newlecmineursprj.controller;

import com.newlecmineursprj.config.security.WebUserDetails;
import com.newlecmineursprj.dto.ProductListDTO;
import com.newlecmineursprj.entity.*;
import com.newlecmineursprj.service.*;
import com.newlecmineursprj.util.CustomPageImpl;
import com.newlecmineursprj.util.SearchModuleUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("myshop")
@Slf4j
public class MyShopController {

    private final OrderService orderService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final MemberService memberService;
    private final WishlistService wishlistService;
    // private final PostService postService;

    private final CouponService couponService;
    private final AddressService addressService;
    private final PointService pointService;

    @GetMapping("order/list")
    public String orderList(@RequestParam(value = "p", defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "s", defaultValue = "10") int pageSize,
            @RequestParam(value = "sm", defaultValue = "ordered_datetime") String sortMethod,
            @RequestParam(value = "sd", defaultValue = "DESC") String sortDirection,
            @RequestParam(defaultValue = "") String searchMethod, @RequestParam(defaultValue = "") String searchKeyword,
            @AuthenticationPrincipal WebUserDetails webUserDetails,
            @RequestParam(defaultValue = "") String buttonRegDate,
            @RequestParam(defaultValue = "") String calendarStart,
            @RequestParam(defaultValue = "") String calendarEnd, Model model) {

        long memberId = webUserDetails.getId();

        log.info("MemberId : {}", memberId);

        String startDate = SearchModuleUtil.getStartDate();

        List<Category> categoryList = categoryService.getList();

        CustomPageImpl<OrderView> list = orderService.getList(
                pageNumber, pageSize, "ordered_datetime", "DESC", 5,
                searchMethod, searchKeyword, memberId,
                calendarStart, calendarEnd, startDate);

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("orderPage", list);
        model.addAttribute("calendarStart", calendarStart);
        model.addAttribute("calendarEnd", calendarEnd);
        model.addAttribute("startDate", startDate);

        model.addAttribute("orderPage", list);

        return "myshop/order/list";
    }

    @GetMapping
    public String index(Model model) {
        List<Category> categoryList = categoryService.getList();
        model.addAttribute("categoryList", categoryList);
        return "myshop/index";
    }

    @GetMapping("wishlist")
    public String wishlist(@RequestParam(value = "p", defaultValue = "1") int pageNumber,
            @RequestParam(value = "s", defaultValue = "12") int pageSize,
            Model model, @AuthenticationPrincipal WebUserDetails webUserDetails) {

        long memberId = webUserDetails.getId();

        List<Category> categoryList = categoryService.getList();
        model.addAttribute("categoryList", categoryList);

        CustomPageImpl<ProductListDTO> productPage = productService.getWishList(pageNumber, pageSize, 5, memberId);
        model.addAttribute("productPage", productPage);

        return "myshop/wishlist";
    }

    @PostMapping("wishlist/delete")
    public String wishListDelete(@AuthenticationPrincipal WebUserDetails webUserDetails,
                                 @RequestParam(required = false) Long productId,
                                 @RequestParam(required = false) List<Long> productIds) {

        long memberId = webUserDetails.getId();

        if (productId != null) {
            // 단일 항목 삭제
            wishlistService.delete(memberId, productId);
        } else if (productIds != null && !productIds.isEmpty()) {
            // 다중 항목 삭제
            for (Long id : productIds) {
                wishlistService.delete(memberId, id);
            }
        }

        return "redirect:/myshop/wishlist";
    }

    @GetMapping("point")
    public String point(Model model, @AuthenticationPrincipal WebUserDetails webUserDetails) {
        List<Category> categoryList = categoryService.getList();
        model.addAttribute("categoryList", categoryList);

        long memberId = webUserDetails.getId();
        Member member = memberService.getById(memberId);
        List<PointView> pointList = pointService.getList(memberId);

        model.addAttribute("member", member);
        model.addAttribute("pointList", pointList);
        return "myshop/point";
    }

    @GetMapping("coupon")
    public String coupon(Model model, @AuthenticationPrincipal WebUserDetails webUserDetails) {

        long memberId = webUserDetails.getId();

        List<Coupon> coupons = couponService.getValidByMemberId(memberId);

        model.addAttribute("coupons", coupons);
        return "myshop/coupon";
    }

    @GetMapping("addr/list")
    public String addrList(@AuthenticationPrincipal WebUserDetails webUserDetails, Model model) {
        long memberId = webUserDetails.getId();

        List<Address> addresses = addressService.findAll(memberId);

        model.addAttribute("addresses", addresses);
        return "myshop/addr/list";
    }

    @GetMapping("addr/modify/{id}")
    public String addrModify(@PathVariable long id, @AuthenticationPrincipal WebUserDetails webUserDetails,
            Model model) {
        long memberId = webUserDetails.getId();

        Address address = addressService.getById(id, memberId);

        System.out.println("isdefault ===== " + address.getIsDefault());

        model.addAttribute("address", address);

        return "myshop/addr/modify";
    }
    @PostMapping("addr/modify/{id}")
    public String postModify(Address address){
        addressService.edit(address);
        log.info("address : {}", address);
        return "redirect:/myshop/addr/list";
    }

}
