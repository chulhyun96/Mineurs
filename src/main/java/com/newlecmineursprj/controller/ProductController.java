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
                    @RequestParam("productId") Long productId
                    ,@RequestParam("colorId") Long colorId
                    ,@RequestParam("sizeId") Long sizeId
                    ,@AuthenticationPrincipal WebUserDetails webUserDetails
                    // @RequestParam(defaultValue = "0", value = "qty") int qty,
                    ){

        long memberId = webUserDetails.getId();

        Product product = service.getById(productId);

        ProductItem productItem = productItemService.getByForeignKeys(productId, sizeId, colorId);
        
        Order order = new Order();
        order.setMemberId(memberId);
        order.setTotalProductPrice(product.getPrice());
        orderService.add(order);

        OrderItem orderItem = new OrderItem();
        orderItem.setQty(1);
        orderItem.setTotalPrice(product.getPrice());
        orderItem.setOrderId(order.getId());
        orderItem.setOrdetStateId((long)1);
        orderItem.setProductItemId(productItem.getId());
        
        // INSERT INTO 
        // order_item (qty, total_price, order_id, order_state_id, product_item_id) 
        // VALUES ('1', #{product.price}, #{order.id}, '1', #{productItem.id});

        return "redirect:pay";
    }

    @GetMapping("pay")
    public String pay(Model model){

        return "pay";
    }
}
