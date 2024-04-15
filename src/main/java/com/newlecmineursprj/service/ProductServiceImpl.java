package com.newlecmineursprj.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.newlecmineursprj.dto.ProductListDTO;
import com.newlecmineursprj.mapper.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.newlecmineursprj.entity.Product;
import com.newlecmineursprj.entity.ProductView;
import com.newlecmineursprj.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    @Override
    public List<ProductListDTO> getList(Integer page, String searchMethod, String searchKeyword, long categoryId) {
        int size = 9;
        int offset = (page - 1) * size;
        return repository.findAll(searchMethod, searchKeyword, offset, size, categoryId).stream().map(ProductMapper::toDto).toList();
    }

    @Override
    public void reg(Product product) {
        repository.reg(product);
    }

    @Override
    public ProductView getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void edit(ProductView updateProduct) {
//        ProductView findProduct = repository.findById(updateProduct.getId());
//        log.info("found product: " + findProduct);
//        ProductView updateView = findProduct.update(updateProduct);
//        log.info("updated product: " + updateView);
//        repository.updateProductById(updateView);
    }

    @Override
    public void deleteAllById(List<Long> deleteId) {
        repository.deleteAll(deleteId);
    }

    @Override
    public String saveProductImg(MultipartFile img, String realPath) {
        String fileName = img.getOriginalFilename();
        if (img != null && !img.isEmpty()) {

            File pathFile = new File(realPath);
            if (!pathFile.exists()) {
                pathFile.mkdirs();
            }
            File file = new File(realPath + File.separator + fileName);
            try {
                img.transferTo(file);
                return fileName;
            } catch (IOException e) {
                log.error("Failed to save product image = {}", e.getMessage());
            }
        }
        return "Failed File Upload";
    }

    @Override
    public int getCount(String searchMethod, String searchKeyword, long categoryId) {
        return repository.count(searchMethod, searchKeyword, categoryId);
    }
}
