package com.newlecmineursprj.controller.admin;

import java.io.IOException;
import java.util.List;

import com.newlecmineursprj.dto.ProductListDTO;
import com.newlecmineursprj.entity.ProductSubImg;

import com.newlecmineursprj.util.CustomPageImpl;
import com.newlecmineursprj.util.SearchModuleUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.newlecmineursprj.entity.Category;
import com.newlecmineursprj.entity.Product;
import com.newlecmineursprj.service.CategoryService;
import com.newlecmineursprj.service.ProductService;
import com.newlecmineursprj.service.ProductSubImgService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("admin/products")
@Controller("adminProductController")
@RequiredArgsConstructor

@Slf4j
public class ProductController {
    private static final String PRODUCTS_VIEW = "/admin/products";
    private static final String REDIRECT = "redirect:";

    private final ProductService service;
    private final CategoryService categoryService;
    private final ProductSubImgService productSubImgService;

    @GetMapping
    public String list(
            @RequestParam(value = "p", defaultValue = "1") Integer page,
            @RequestParam(value = "s", defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String searchMethod,
            @RequestParam(defaultValue = "") String searchKeyword,
            @RequestParam(defaultValue = "0") Long categoryId,
            @RequestParam(defaultValue = "") String buttonRegDate,
            @RequestParam(defaultValue = "") String calendarStart,
            @RequestParam(defaultValue = "") String calendarEnd,
            @RequestParam(defaultValue = "") String selectedDisplayStatus,
            Model model) {

        int count = service.getCount(searchMethod, searchKeyword.trim(), categoryId);
        List<Category> categories = categoryService.getList();


        Integer displayStatusResult = SearchModuleUtil.searchByDisplayStatus(selectedDisplayStatus);
        String startDate = SearchModuleUtil.getStartDate();
        String endDate = SearchModuleUtil.searchByRegDate(buttonRegDate);
        CustomPageImpl<ProductListDTO> productPage = service.getList(
                page, pageSize, "reg_date", 5
                , searchMethod, searchKeyword.trim(), categoryId
                , startDate, endDate, calendarStart, calendarEnd,displayStatusResult
        );
        log.info("selectedDisplayStatus : {}", selectedDisplayStatus);

        model.addAttribute("productPage", productPage);
        model.addAttribute("count", count);
        model.addAttribute("categories", categories);
        model.addAttribute("calendarStart", calendarStart);
        model.addAttribute("calendarEnd", calendarEnd);
        model.addAttribute("startDate", startDate);
        model.addAttribute("displayStatusList", SearchModuleUtil.DisplayStatusList());
        model.addAttribute("regDates", SearchModuleUtil.regDateList());
        return PRODUCTS_VIEW + "/list";
    }

    @GetMapping("/reg")
    public String regForm(Model model) {
        List<Category> categories = categoryService.getList();
        model.addAttribute("categories", categories);
        model.addAttribute("product", new Product());
        return PRODUCTS_VIEW + "/reg";
    }

    @PostMapping
    public String reg(@Validated  Product product, BindingResult bindingResult,Model model,
                      MultipartFile mainImg, @RequestParam(value = "sub-imgs") List<MultipartFile> subImages) throws IOException {
        if (bindingResult.hasErrors()) {
            ifCategoryNull(product, model);
            log.error("Reg Form Error : {}", bindingResult + "\n");
            return PRODUCTS_VIEW + "/reg";
        }
        service.reg(product, mainImg, subImages);
        return REDIRECT + PRODUCTS_VIEW;
    }

    private void ifCategoryNull(Product product, Model model) {
        if (product.getCategoryId() == null) {
            List<Category> categories = categoryService.getList();
            model.addAttribute("categories", categories);
        }
    }


    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Product product = service.getById(id);
        List<Category> categories = categoryService.getList();
        List<ProductSubImg> subImgs = productSubImgService.getListByProductId(product.getId());

        model.addAttribute("categories", categories);
        model.addAttribute("product", product);
        model.addAttribute("subImgs", subImgs);
        return PRODUCTS_VIEW + "/detail";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam List<Long> deleteId) {
        service.deleteAllById(deleteId);
        return REDIRECT + PRODUCTS_VIEW;
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Product updateProduct, MultipartFile updateImg, List<MultipartFile> updateSubImgs) throws IOException {
        service.update(updateProduct, updateImg, updateSubImgs);
        return REDIRECT + PRODUCTS_VIEW;
    }
}
