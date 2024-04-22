package com.newlecmineursprj.service;

import java.io.IOException;
import java.util.List;

import com.newlecmineursprj.domain.file.ImgStorage;
import com.newlecmineursprj.dto.ProductListDTO;
import com.newlecmineursprj.entity.ProductSubImg;
import com.newlecmineursprj.mapper.ProductMapper;
import com.newlecmineursprj.repository.ProductSubImgRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.newlecmineursprj.entity.Product;
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
    @Transactional
    @Override
    public void reg(Product newProduct, MultipartFile mainImg, List<MultipartFile> subImgs) throws IOException {
        //메인 이미지 저장
        String storageMainImgName = imgStorage.getStorageImgName(mainImg);
        Product.saveImg(storageMainImgName,newProduct);
        repository.reg(newProduct);

        //서브 이미지 저장
        List<String> storageSubImgName = imgStorage.getStorageSubImgName(subImgs);
        List<ProductSubImg> productSubImgs = ProductSubImg.saveSubImgs(storageSubImgName, newProduct);
        subImgRepository.reg(productSubImgs);
    }

    @Override
    public void update(Product updateProduct, MultipartFile updateFile, List<MultipartFile> updateSubImgs) throws IOException {
        //메인 이미지 업데이트
        Product foundProduct = repository.findById(updateProduct.getId());
        Product.saveImg(imgStorage.updateMainImg(foundProduct, updateFile), updateProduct);
        repository.updateById(updateProduct);

        //서브 이미지 업데이트
        List<ProductSubImg> foundAll = subImgRepository.findAll(updateProduct.getId());
        List<ProductSubImg> updateProductSubImgs = ProductSubImg.updateSubImgs(
                imgStorage.updateSubImgs(foundAll, updateSubImgs), foundAll
        );
        subImgRepository.updatedImgs(updateProductSubImgs);
    }

    @Override
    public Product getById(Long id) {
        return repository.findById(id);
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
