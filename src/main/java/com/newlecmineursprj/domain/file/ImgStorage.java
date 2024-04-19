package com.newlecmineursprj.domain.file;

import com.newlecmineursprj.entity.Product;
import com.newlecmineursprj.entity.ProductSubImg;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.ssl.SslProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Slf4j
@Component
@Getter
@Setter
@ConfigurationProperties("file")
public class ImgStorage {
    private String mainImg;
    private String subImg;


    public String getStorageImgName(MultipartFile imgFile) throws IOException {
        if (imgFile.isEmpty())
            throw FileDoesNotExist("MainImg File dose not exist");

        String fullMainPath = getFullMainPath(imgFile.getOriginalFilename());
        makeFileDir(fullMainPath);
        imgFile.transferTo(new File(fullMainPath));
        return imgFile.getOriginalFilename();
    }

    public List<String> getStorageSubImgName(List<MultipartFile> subImgs) throws IOException {
        if (subImgs.isEmpty())
            throw FileDoesNotExist("SubImgs File dose not exist");

        String fullSubPath = getFullSubPath(subImgs.get(0).getOriginalFilename());
        makeFileDir(fullSubPath);
        for (MultipartFile img : subImgs) {
            img.transferTo(new File(getFullSubPath(img.getOriginalFilename())));
        }
        return subImgs.stream()
                .map(MultipartFile::getOriginalFilename)
                .toList();
    }

    public String updateMainImg(Product foundImg, MultipartFile updateImg) throws IOException {
        if (updateImg.isEmpty())
            throw FileDoesNotExist("UpdateMainFile does not exist");
        // 메인 이미지 업데이트
        String updateMainImgPath = getFullMainPath(updateImg.getOriginalFilename());
        Path updateMainPath = Paths.get(updateMainImgPath);
        Files.write(updateMainPath, updateImg.getBytes());
        // 기존 이미지 삭제
        String pastImgName = getFullMainPath(foundImg.getMainImgPath());
        Path oldImgPath = Paths.get(pastImgName);
        log.info("oldImgPath = {}", oldImgPath);
        Files.delete(oldImgPath);
        return updateImg.getOriginalFilename();
    }
    public List<String> updateSubImgs(List<ProductSubImg> foundImgs, List<MultipartFile> updateImgs) throws IOException {
        if(updateImgs.isEmpty())
            throw FileDoesNotExist("UpdateSubFile does not exist");

        List<String> updateImgNames = new ArrayList<>();
        for (MultipartFile updateImg : updateImgs) {
            String updatedSubImgName = getFullSubPath(updateImg.getOriginalFilename());
            Path updatedSubImgPath = Paths.get(updatedSubImgName);
            log.info("updatedSubImgPath = {}", updatedSubImgPath);
            /*Files.write(updatedSubImgPath, updateImg.getBytes());*/
            updateImgNames.add(updateImg.getOriginalFilename());
        }

        for (ProductSubImg foundImg : foundImgs) {
            String pastImgName = getFullSubPath(foundImg.getPath());
            Path oldImgPath = Paths.get(pastImgName);
            /*Files.delete(oldImgPath);*/
            log.info("Update subImgs Delete");
        }
        return updateImgNames;
    }

    private void makeFileDir(String fullPath) throws IOException {
        Path path = Paths.get(fullPath);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }

    private String getFullMainPath(String fileName) {
        return System.getProperty("user.dir") + mainImg + fileName;
    }

    private String getFullSubPath(String fileName) {
        return System.getProperty("user.dir") + subImg + fileName;
    }

    private RuntimeException FileDoesNotExist(String errorMessage) {
        return new RuntimeException(errorMessage);
    }
}
