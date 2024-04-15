package com.newlecmineursprj.controller.admin;

import java.util.List;

import com.newlecmineursprj.dto.ProductListDTO;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.newlecmineursprj.entity.Category;
import com.newlecmineursprj.entity.Product;
import com.newlecmineursprj.entity.ProductView;
import com.newlecmineursprj.service.CategoryService;
import com.newlecmineursprj.service.ProductService;
import com.newlecmineursprj.service.ProductSubImgService;

import jakarta.servlet.http.HttpServletRequest;
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
            @RequestParam(defaultValue = "1") Integer page, 
            @RequestParam(required = false) String searchMethod,
            @RequestParam(defaultValue = "") String searchKeyword,
            @RequestParam(defaultValue = "0") Long categoryId,
            Model model) {

        int count = service.getCount(searchMethod, searchKeyword.trim(),categoryId);
        List<ProductListDTO> list = service.getList(page, searchMethod, searchKeyword.trim(), categoryId);
        List<Category> categories = categoryService.getList();

        model.addAttribute("list", list);
        model.addAttribute("count", count);
        model.addAttribute("categories",categories);
        return PRODUCTS_VIEW + "/list";
    }

    @GetMapping("/reg")
    public String regForm(Model model) {
        List<Category> categories = categoryService.getList();
        model.addAttribute("categories", categories);
        return PRODUCTS_VIEW + "/reg";
    }

    @PostMapping
    public String reg(MultipartFile img,
            Product product,
            Long categoryId,
            HttpServletRequest req,
            @RequestParam(value = "sub-imgs") MultipartFile[] subImages) throws FileUploadException {

        String mainImgPath = "/image/products";
        String fileUploadResult = saveToDir(img, req, mainImgPath);

        String subImgPath = "/image/subImg";
        saveSubImages(subImages, req, subImgPath);

        product.setCategoryId(categoryId);
        product.setImgPath(fileUploadResult);

        service.reg(product);
        productSubImgService.regAll(subImages, product.getId());
        return REDIRECT + PRODUCTS_VIEW;
    }

    private void saveSubImages(MultipartFile[] subImages, HttpServletRequest req, String subImgPath) {
        for (MultipartFile img : subImages) {
            saveToDir(img, req, subImgPath);
        }
    }

    private String saveToDir(MultipartFile img, HttpServletRequest req, String path) {
        String realPath = req.getServletContext().getRealPath(path);
        return service.saveProductImg(img, realPath);
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        List<Category> categories = categoryService.getList();
        ProductView product = service.getById(id);
        model.addAttribute("categories", categories);
        model.addAttribute("product", product);
        return PRODUCTS_VIEW + "/detail";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam List<Long> deleteId) {
        service.deleteAllById(deleteId);
        return REDIRECT + PRODUCTS_VIEW;
    }

    @PostMapping("/{id}/edit")
    public String edit(MultipartFile img,HttpServletRequest req,ProductView updateProduct) {
//        log.info("Updating product {}", updateProduct);
//        log.info("Updating product.getImg {}", updateProduct.getImg());
//        log.info("MultipartFile img = {}", img.getOriginalFilename());
//        String mainImgPath = "/image/products";
//        String fileUploadResult = saveToDir(img, req, mainImgPath);
//
//        updateProduct.setImg(fileUploadResult);
        /*service.edit(updateProduct);*/
        return REDIRECT + PRODUCTS_VIEW;
    }
}