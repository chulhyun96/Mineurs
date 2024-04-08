package com.newlecmineursprj.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecmineursprj.entity.Product;
import com.newlecmineursprj.entity.ProductView;
import com.newlecmineursprj.repository.ProductRepository;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    @Override
    public List<ProductView> getList(Integer page, String searchMethod, String searchKeyword) {
        int size = 10;
        int offset = (page - 1) * size;
        return repository.findAll(searchMethod, searchKeyword, offset, size);
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
    public void edit(Product product) {
        repository.updateProductById(product);
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
    public int getCount(String searchMetod,String searchKeyword) {
        return repository.count(searchMetod,searchKeyword);
    }
}
