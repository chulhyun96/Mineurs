package com.newlecmineursprj.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.newlecmineursprj.domain.file.ImgStorage;
import com.newlecmineursprj.dto.ProductListDTO;
import com.newlecmineursprj.entity.ProductSubImg;
import com.newlecmineursprj.mapper.ProductMapper;
import com.newlecmineursprj.mapper.SubImgMapper;
import com.newlecmineursprj.repository.ProductSubImgRepository;
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
    private final ProductSubImgRepository subImgRepository;
    private final ImgStorage imgStorage;

    @Override
    public List<ProductListDTO> getList(Integer page, String searchMethod, String searchKeyword, long categoryId) {
        int size = 9;
        int offset = (page - 1) * size;
        return repository.findAll(searchMethod, searchKeyword, offset, size, categoryId).stream().map(ProductMapper::toDto).toList();
    }

    @Override
    public void reg(Product product,MultipartFile mainImg ,List<MultipartFile> subImgs) throws IOException {
        //메인 이미지 저장
        String storageMainImgName = imgStorage.getStorageImgName(mainImg);
        product.setMainImgPath(storageMainImgName);
        Long savedProductId = repository.reg(product);

        //서브 이미지 저장
        List<String> storageSubImgName = imgStorage.getStorageSubImgName(subImgs);
        List<ProductSubImg> productSubImgs = ProductSubImg.saveSubImg(storageSubImgName, savedProductId);
        subImgRepository.reg(productSubImgs);

    }

    @Override
    public Product getById(Long id) {
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
    public int getCount(String searchMethod, String searchKeyword, long categoryId) {
        return repository.count(searchMethod, searchKeyword, categoryId);
    }
}
