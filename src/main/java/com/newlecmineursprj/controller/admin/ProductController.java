package com.newlecmineursprj.controller.admin;

import java.util.Arrays;
import java.util.List;

import com.newlecmineursprj.dto.ProductListDTO;
import com.newlecmineursprj.dto.ProductRegDTO;
import com.newlecmineursprj.entity.ProductSubImg;
import com.newlecmineursprj.mapper.ProductMapper;
import com.newlecmineursprj.mapper.SubImgMapper;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.http.ResponseEntity;
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
    public String reg(
            ProductRegDTO productRegDTO,
            HttpServletRequest req,
            @RequestParam(value = "sub-imgs") List<MultipartFile> subImages) throws FileUploadException {

        log.debug("productRegDTO: {}", productRegDTO);

        String subImgPath = "/image/subImg";
        saveSubImages(subImages, req, subImgPath);

        String mainImgPath = "/image/products";
        saveToDir(productRegDTO.getMainImg(), req, mainImgPath);

        Product product = ProductMapper.toProduct(productRegDTO);
        service.reg(product);

        //  service.reg 이후에 product에 id가 생긴다

        List<ProductSubImg> subImgs = subImages.stream().map(subImg -> SubImgMapper.toSubImg(subImg, product.getId())).toList();
        log.debug("subImgs: {}", subImgs);
        productSubImgService.regAll(subImgs);
        return REDIRECT + PRODUCTS_VIEW;
    }

    private void saveSubImages(List<MultipartFile> subImages, HttpServletRequest req, String subImgPath) {
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
        Product product = service.getById(id);
        List<ProductSubImg> subImgs = productSubImgService.getListByProductId(id);
        model.addAttribute("categories", categories);
<<<<<<< HEAD
        Product product = service.getById(id);
        log.debug("product = {}", product);
=======
>>>>>>> d7383771fbef972c8fa13e77a4c7fe46ade525db
        model.addAttribute("product", product);
        model.addAttribute("subImgs", subImgs);
        return PRODUCTS_VIEW + "/detail";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam List<Long> deleteId) {
        service.deleteAllById(deleteId);
        return REDIRECT + PRODUCTS_VIEW;
    }

    @PutMapping("/{productId}")
    public ResponseEntity<String> edit(@PathVariable long productId, ProductRegDTO productRegDTO, List<MultipartFile> subImgs) {
        log.debug("productId: {}", productId);
        log.debug("productRegDTO: {}", productRegDTO);
        log.debug("subImgs: {}", subImgs);

        productRegDTO.setId(productId);


        return ResponseEntity.ok("상품 수정 성공");
    }


}