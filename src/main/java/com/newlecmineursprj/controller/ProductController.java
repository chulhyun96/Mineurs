package com.newlecmineursprj.controller;

import com.newlecmineursprj.config.security.WebUserDetails;
import com.newlecmineursprj.entity.*;
import com.newlecmineursprj.service.*;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final ColorService colorService;
    private final SizeService sizeService;
    private final ProductSubImgService productSubImgService;
    private final CategoryService categoryService;
    private final ProductItemService productItemService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;
    private final CartService cartService;
    private final LikeService likeService;

    @GetMapping("{id}")
    public String list(@PathVariable long id
    , Model model){

        List<Category> categoryList = categoryService.getList();
        model.addAttribute("categoryList",categoryList);

        Product product = service.getById(id);
        model.addAttribute("product", product);

        List<ProductItem> productItemList = productItemService.getByProductId(id);
        Set<Color> colors = new HashSet<>();
        Set<Size> sizes = new HashSet<>();
        for(ProductItem productItem : productItemList){
            Long colorId = productItem.getColorId();
            colors.add(colorService.getById(colorId));
            Long sizeId = productItem.getSizeId();
            sizes.add(sizeService.getById(sizeId));
        }

        model.addAttribute("colors", colors);
        model.addAttribute("sizes", sizes);

        List<ProductSubImg> subImgs = productSubImgService.getListByProductId(id);
        model.addAttribute("subImgs", subImgs);

        return "detail";
    }

    @PostMapping("userAction")
    public String userAction(
                    @RequestParam("userAction") int userAction
                    ,@RequestParam("productId") Long productId
                    ,@RequestParam(value="colorId", defaultValue="0") Long colorId
                    ,@RequestParam(value="sizeId", defaultValue="0") Long sizeId
                    ,@AuthenticationPrincipal WebUserDetails webUserDetails
                    ){
        
        long memberId = webUserDetails.getId();

        Product product = service.getById(productId);

        ProductItem productItem = productItemService.getByForeignKeys(productId, sizeId, colorId);
        
        //유저가 구매 버튼 눌렀을때
        if(userAction == 1){
            //주문 수량
            int orderedQty = 1;
            //현재 재고
            int productStock = productItem.getQty();

            //재고 충분할때
            if(productStock >= orderedQty){
                //order 테이블에 데이터 추가
                Order order = new Order();
                order.setMemberId(memberId);
                order.setTotalProductPrice(product.getPrice());
                orderService.add(order);
        
                //orderItem 테이블에 데이터 추가
                OrderItem orderItem = new OrderItem();
                orderItem.setQty(orderedQty);
                orderItem.setTotalPrice(product.getPrice());
                orderItem.setOrderId(order.getId());
                orderItem.setOrderStateId((long)1);
                orderItem.setProductItemId(productItem.getId());
                orderItemService.add(orderItem);

                //주문한 갯수만큼 productItem 재고 감소 
                productItemService.stockDecrease(orderedQty, productItem.getId());

                return "redirect:pay";
            }
            //재고 불충분
            else{
                //주문이 안되야됨
                return "";
            }
    
        }
        //장바구니에 추가 버튼 눌렀을때
        else if(userAction == 2){

            Cart tempCart = cartService.getByForeignKeys(memberId, productItem.getId());
            
            if(tempCart == null){
                Cart cart = new Cart();
                cart.setMemberId(memberId);
                cart.setProductItemId(productItem.getId());
                cart.setQty(1);
                cartService.add(cart);
            }

            return "redirect:" + productId;
        }
        //wishList에 추가 버튼 눌렀을때
        else if(userAction == 3){

            Like tempLike = likeService.getByForeignKeys(memberId, productId);

            if(tempLike == null){
                Like like = new Like();
                like.setMemberId(memberId);
                like.setProductId(productId);
                likeService.add(like);
            }


            return "redirect:" + productId;
        }
        return "redirect:";
    }

    @GetMapping("pay")
    public String pay(Model model){

        return "pay";
    }
}
