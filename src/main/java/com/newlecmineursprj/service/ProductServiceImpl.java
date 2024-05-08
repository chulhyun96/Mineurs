package com.newlecmineursprj.service;

import com.newlecmineursprj.domain.file.ImgStore;
import com.newlecmineursprj.dto.ProductListDTO;
import com.newlecmineursprj.entity.Product;
import com.newlecmineursprj.entity.ProductSubImg;
import com.newlecmineursprj.mapper.ProductMapper;
import com.newlecmineursprj.repository.ProductRepository;
import com.newlecmineursprj.repository.ProductSubImgRepository;
import com.newlecmineursprj.util.CustomPageImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
        List<String> storageSubImgName = imgStore.updateSubImgFiles(foundAll, updateSubImgs);
        log.info("파일 이름만 받고 나온 이미지 개수 : {}", storageSubImgName.size());
        updateSubImgs(updateProduct, updateSubImgs, foundAll, storageSubImgName);

        // 문제 -> 기존의 이미지가 2개 일 경우, 기존의 이미지를 손대지 않고 이미지 추가 1장, 2장, 3장
        // 문제 -> 기존의 이미지가 2개 일 경우, 기존의 이미지를 삭제 시 IndexOutOfBounds
        // 문제 -> 기존의 이미지가 2개 일 경우, 전혀 손대지 않고 폼 제출
        // 문제 -> 파일이름이 같은 이미지를 올리면 렌더링이 안됨. 데이터베이스에서 삭제한듯.
        // 공통적인 문제는 기존의 이미지를 손대지 않고 어떠한 작업시 문제가 발생.
    }

    private void updateSubImgs(Product updateProduct, List<MultipartFile> updateSubImgs, List<ProductSubImg> foundAll, List<String> storageSubImgName) {
        if (updateSubImgs.size() > foundAll.size()) {
            // 잘됨.
            log.info("기존의 파일보다 요청이 많을 경우");
            log.info("요청한 파일의 개수 : {}", updateSubImgs.size());
            log.info("기존의 파일의 개수 : {}", foundAll.size());
            List<String> extraSubImgNames = storageSubImgName.subList(foundAll.size(), updateSubImgs.size());
            List<ProductSubImg> productSubImgs = ProductSubImg.saveSubImgs(extraSubImgNames, updateProduct);
            subImgRepository.reg(productSubImgs);

            List<String> overWriteImgNames = storageSubImgName.subList(0, foundAll.size());
            List<ProductSubImg> overWriteSubImgList = ProductSubImg.updateSubImgs(overWriteImgNames, foundAll);
            subImgRepository.updatedImgs(overWriteSubImgList);
            return;
        }
        if (updateSubImgs.size() < foundAll.size()) {
            // 잘됨
            log.info("기존의 파일보다 요청이 적을 경우");
            log.info("요청한 파일의 개수 : {}", updateSubImgs.size());
            log.info("기존의 파일의 개수 : {}", foundAll.size());
            List<ProductSubImg> remainingSubImgs = foundAll.subList(0, updateSubImgs.size());
            List<ProductSubImg> productSubImgs = ProductSubImg.updateSubImgs(storageSubImgName, remainingSubImgs);
            subImgRepository.updatedImgs(productSubImgs);

            List<ProductSubImg> extraSubImgsToDelete = foundAll.subList(updateSubImgs.size(), foundAll.size());
            subImgRepository.deleteAll(extraSubImgsToDelete);
            return;
        }
        //잘됨
        log.info("기존의 파일과 요청한 파일의 개수가 같을 경우");
        log.info("요청한 파일의 개수 : {}", updateSubImgs.size());
        log.info("기존의 파일의 개수 : {}", foundAll.size());
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
