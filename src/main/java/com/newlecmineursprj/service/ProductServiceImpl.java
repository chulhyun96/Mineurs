package com.newlecmineursprj.service;

import java.io.IOException;
import java.util.List;

import com.newlecmineursprj.domain.file.ImgStore;
import com.newlecmineursprj.dto.ProductListDTO;
import com.newlecmineursprj.entity.ProductSubImg;
import com.newlecmineursprj.mapper.ProductMapper;
import com.newlecmineursprj.repository.ProductSubImgRepository;
import com.newlecmineursprj.util.CustomPageImpl;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private final ImgStore imgStore;

    @Override
    public CustomPageImpl<ProductListDTO> getList(
            Integer pageNumber
            , Integer pageSize
            , String sortMethod
            , Integer pageGroupSize
            , String searchMethod
            , String searchKeyword
            , long categoryId
            , String startDate
            , String endDate
            , String calendarStart
            , String calendarEnd
            , Integer displayStatusResult
            , Integer sellStatusResult
            , Long memberId
    ) {


        return getList(pageNumber
                , pageSize
                , sortMethod
                , "DESC"
                , pageGroupSize
                , searchMethod
                , searchKeyword
                , categoryId
                , startDate
                , endDate
                , calendarStart
                , calendarEnd
                , displayStatusResult
                , sellStatusResult
                , null
        );
    }

    @Override
    public CustomPageImpl<ProductListDTO> getList(Integer pageNumber, Integer pageSize, String sortMethod,
                                                  String sortDirection, Integer pageGroupSize, String searchMethod,
                                                  String searchKeyword, long categoryId, String startDate, String endDate,
                                                  String calendarStart, String calendarEnd, Integer displayStatusResult, Integer sellStatusResult, Long memberId) {
        Pageable pageRequest = PageRequest.of(pageNumber - 1, pageSize, Sort.by(Sort.Direction.fromString(sortDirection), sortMethod));

        List<ProductListDTO> content = repository.findAll(pageRequest, searchMethod, searchKeyword,
                        categoryId, startDate, endDate, calendarStart, calendarEnd, displayStatusResult, sellStatusResult, memberId)
                .stream().map(ProductMapper::toDto).toList();

        long count = repository.getCount(searchMethod, searchKeyword, categoryId);

        return new CustomPageImpl<>(content, pageRequest, count, pageGroupSize);
    }

    @Transactional
    @Override
    public void reg(Product newProduct, MultipartFile mainImg, List<MultipartFile> subImgs) throws IOException {
        //메인 이미지 저장
        String storageMainImgName = imgStore.getStorageMainImgName(mainImg);
        Product.saveNewImg(storageMainImgName, newProduct);
        repository.reg(newProduct);

        //서브 이미지 저장
        List<String> storageSubImgName = imgStore.getStorageSubImgName(subImgs);
        List<ProductSubImg> productSubImgs = ProductSubImg.saveSubImgs(storageSubImgName, newProduct);
        subImgRepository.reg(productSubImgs);
    }

    @Override
    public void update(Product updateProduct, MultipartFile updateFile, List<MultipartFile> updateSubImgs) throws IOException {
        //메인 이미지 업데이트
        Product foundProduct = repository.findById(updateProduct.getId());
        Product.saveNewImg(imgStore.updateMainImgFile(foundProduct, updateFile), updateProduct);
        repository.updateById(updateProduct);

        //서브 이미지 업데이트
        List<ProductSubImg> foundAll = subImgRepository.findAll(updateProduct.getId());
        for (ProductSubImg productSubImg : foundAll) {
            log.info("Product sub img: " + productSubImg);
        }
        List<String> storageSubImgName = imgStore.updateSubImgFiles(foundAll, updateSubImgs);
        updateSubImgs(updateProduct, updateSubImgs, foundAll, storageSubImgName);
    }

    private void updateSubImgs(Product updateProduct, List<MultipartFile> updateSubImgs, List<ProductSubImg> foundAll, List<String> storageSubImgName) {
        if (updateSubImgs.size() > foundAll.size()) {
            log.info("기존의 파일보다 요청이 많을 경우");
            List<String> extraSubImgNames = storageSubImgName.subList(foundAll.size(), updateSubImgs.size());
            List<ProductSubImg> productSubImgs = ProductSubImg.saveSubImgs(extraSubImgNames, updateProduct);
            subImgRepository.reg(productSubImgs);

            List<String> overWriteImgNames = storageSubImgName.subList(0, foundAll.size());
            List<ProductSubImg> overWriteSubImgList = ProductSubImg.updateSubImgs(overWriteImgNames, foundAll);
            subImgRepository.updatedImgs(overWriteSubImgList);
            return;
        }
        if (updateSubImgs.size() < foundAll.size()) {
            log.info("기존의 파일보다 요청이 적을 경우");
            List<ProductSubImg> remainingSubImgs = foundAll.subList(0, updateSubImgs.size());
            List<ProductSubImg> productSubImgs = ProductSubImg.updateSubImgs(storageSubImgName, remainingSubImgs);
            subImgRepository.updatedImgs(productSubImgs);

            List<ProductSubImg> extraSubImgsToDelete = foundAll.subList(updateSubImgs.size(), foundAll.size());
            subImgRepository.deleteAll(extraSubImgsToDelete);
            return;
        }
        List<ProductSubImg> productSubImgs = ProductSubImg.updateSubImgs(storageSubImgName, foundAll);
        subImgRepository.updatedImgs(productSubImgs);
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
        return repository.getCount(searchMethod, searchKeyword, categoryId);
    }

    @Override
    public int updateAll(List<Product> products) {
        return repository.updateAll(products);
    }

    @Override
    public CustomPageImpl<ProductListDTO> getWishList(Integer pageNumber, Integer pageSize, Integer pageGroupSize,
                                                      long memberId) {

        Pageable pageRequest = PageRequest.of(pageNumber - 1, pageSize);

        List<ProductListDTO> content = repository.findAllByMemberId(pageRequest, memberId)
                .stream().map(ProductMapper::toDto).toList();

        long count = repository.getCountByMemberId(memberId);

        return new CustomPageImpl<ProductListDTO>(content, pageRequest, count, pageGroupSize);
    }
}
