package com.newlecmineursprj.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.newlecmineursprj.entity.Category;
import com.newlecmineursprj.entity.Product;
import com.newlecmineursprj.entity.ProductView;
import com.newlecmineursprj.service.CategoryService;
import com.newlecmineursprj.service.ProductSubImgService;
import com.newlecmineursprj.service.ProductService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

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
    public String list(@RequestParam(required = false) String searchMethod
            , @RequestParam(defaultValue = "") String searchKeyword
            , Model model) {
        List<ProductView> list = service.getList(searchMethod, searchKeyword.trim());
        model.addAttribute("list", list);
        return PRODUCTS_VIEW + "/list";
    }

    @GetMapping("/reg")
    public String regForm(Model model) {
        List<Category> categories = categoryService.getList();
        model.addAttribute("categories", categories);
        return PRODUCTS_VIEW + "/reg";
    }

    @PostMapping
    public String reg(@RequestParam("img") MultipartFile imgFile,
                      Product product,
                      Long categoryId,
                      HttpServletRequest req) throws IllegalStateException, IOException {
        {
            String fileName = "";
            fileName = imgFile.getOriginalFilename(); // 파일의 이름을 추출
            System.out.println("fileNameasdasdasdasdasdasdasdasdasdasd  = " + fileName);

            if (imgFile != null && !imgFile.isEmpty()) {
                String path = "/image/products";          // 루트 폴더에 절대경로로 해서 업로드될 파일 디렉토리 만들어줌
                String realPath = req.getServletContext() // 전체 경로를 추출
                        .getRealPath(path);

                File pathFile = new File(realPath);       // 파일을 만듦
                if (!pathFile.exists()) {                 // 경로에 관련 디렉토리가 있지않다면 없는 디렉토리 전체생성
                    pathFile.mkdirs();
                }
                File file = new File(realPath + File.separator + fileName);  //separtor 자바에서 제공하는 전체운영체제 구분자

                imgFile.transferTo(file);               //파일 생성
            }
        }
        product.setCategoryId(categoryId);
        product.setImgPath(imgFile.getOriginalFilename());
        service.reg(product);
        /*productSubImgService.regAll(paths, product.getId());*/
        return REDIRECT + PRODUCTS_VIEW;
    }




    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        ProductView product = service.getById(id);
        model.addAttribute("product", product);
        return PRODUCTS_VIEW + "/detail";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam List<Long> deleteId) {
        service.deleteAllById(deleteId);
        return REDIRECT + PRODUCTS_VIEW;
    }

    @PutMapping
    public String edit(@RequestBody Product product) {
        service.edit(product);
        return "success";
    }

}
