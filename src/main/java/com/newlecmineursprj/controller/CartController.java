package com.newlecmineursprj.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hpsf.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.newlecmineursprj.entity.Cart;
import com.newlecmineursprj.entity.Color;
import com.newlecmineursprj.entity.Product;
import com.newlecmineursprj.entity.ProductItem;
import com.newlecmineursprj.entity.Size;
import com.newlecmineursprj.service.CartService;
import com.newlecmineursprj.service.ColorService;
import com.newlecmineursprj.service.ProductItemService;
import com.newlecmineursprj.service.ProductService;
import com.newlecmineursprj.service.SizeService;

@RequestMapping("cart")
@Controller
public class CartController {
    
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductItemService productItemService;
    @Autowired
    private ProductService productService;
    @Autowired
    private SizeService sizeService;
    @Autowired
    private ColorService colorService;

    @GetMapping
    public String listByMid(@RequestParam(name = "mid", required = false) Long mid,
                             Model model){

        List<Cart> cartList = cartService.getByMid(mid);
        List<Long> productItemIds = new ArrayList<>();
        List<ProductItem> productItemList = new ArrayList<>();
        List<Product> productList = new ArrayList<>();
        List<Size> sizeList = new ArrayList<>();
        List<Color> colorList = new ArrayList<>();

        for(Cart cart : cartList)
            productItemIds.add(cart.getProductItemId());   
        for(Long productItemId : productItemIds)
            productItemList.add(productItemService.getById(productItemId));
        for(ProductItem productItem : productItemList){
            productList.add(productService.getById(productItem.getProductId()));
            sizeList.add(sizeService.getById(productItem.getSizeId()));
            colorList.add(colorService.getById(productItem.getColorId()));
        }
        
        model.addAttribute("cartList", cartList);
        model.addAttribute("productList", productList);
        model.addAttribute("sizeList", sizeList);
        model.addAttribute("colorList", colorList);
            
        return "cart/list";
    }

}
