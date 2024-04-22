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
import java.util.Arrays;
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
            return "Non-Img";

        String fullMainPath = getFullMainPath(imgFile.getOriginalFilename());
        makeFileDir(fullMainPath);
        imgFile.transferTo(new File(fullMainPath));
        return imgFile.getOriginalFilename();
    }

    public List<String> getStorageSubImgName(List<MultipartFile> subImgs) throws IOException {
        if (subImgs.stream().allMatch(MultipartFile::isEmpty))
            return List.of("Non-Img");

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
            return "Non-Img";

        // 메인 이미지 업데이트
        String updateMainImgPath = getFullMainPath(updateImg.getOriginalFilename());
        updateImg(updateImg, updateMainImgPath);

        // 기존 이미지 삭제
        String pastImgName = getFullMainPath(foundImg.getMainImgPath());
        deleteImg(pastImgName);
        return updateImg.getOriginalFilename();
    }

    public List<String> updateSubImgs(List<ProductSubImg> foundImgs, List<MultipartFile> updateImgs) throws IOException {
        if (updateImgs.stream().allMatch(MultipartFile::isEmpty))
            return List.of("Non-Img");

        // 서브 이미지 업데이트
        List<String> updateImgNames = new ArrayList<>();
        for (MultipartFile updateImg : updateImgs) {
            String updatedSubImgName = getFullSubPath(updateImg.getOriginalFilename());
            updateImg(updateImg, updatedSubImgName);
            updateImgNames.add(updateImg.getOriginalFilename());
        }
        //서브 이미지 삭제
        for (ProductSubImg foundImg : foundImgs) {
            String pastImgName = getFullSubPath(foundImg.getPath());
            deleteImg(pastImgName);
        }
        return updateImgNames;
    }

    private void updateImg(MultipartFile updateImg, String updatedSubImgName) throws IOException {
        Path updatedSubImgPath = Paths.get(updatedSubImgName);
        Files.write(updatedSubImgPath, updateImg.getBytes());
    }

    private void deleteImg(String pastImgName) throws IOException {
        Path oldImgPath = Paths.get(pastImgName);
        if (Files.exists(oldImgPath)) {
            Files.delete(oldImgPath);
        }
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
