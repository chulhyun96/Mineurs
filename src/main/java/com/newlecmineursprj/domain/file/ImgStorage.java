package com.newlecmineursprj.domain.file;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Component
@Getter
@Setter
@ConfigurationProperties("file")
public class ImgStorage {
    private String mainImg;
    private String subImg;


    public String getStorageImgName(MultipartFile imgFile) throws IOException {
        if (imgFile.isEmpty()) {
            throw new RuntimeException("File does not exist");
        }
        String fullMainPath = getFullMainPath(imgFile.getOriginalFilename());
        makeFileDir(fullMainPath);
        imgFile.transferTo(new File(fullMainPath));
        return imgFile.getOriginalFilename();
    }
    public List<String> getStorageSubImgName(List<MultipartFile> subImgs) throws IOException {
        if (subImgs.isEmpty()) {
            throw new RuntimeException("File does not exist");
        }
        String fullSubPath = getFullSubPath(subImgs.get(0).getOriginalFilename());
        makeFileDir(fullSubPath);
        for (MultipartFile img : subImgs) {
            img.transferTo(new File(getFullSubPath(img.getOriginalFilename())));
        }
        return subImgs.stream()
                .map(MultipartFile::getOriginalFilename)
                .toList();
    }
    public String updateMainImg(String foundImg, MultipartFile updateImg) throws IOException {
        // 이미지 업데이트
        if (updateImg.isEmpty()) {
            throw new RuntimeException("UpdateFile does not exist");
        }
        String updateMainImgPath = getFullMainPath(updateImg.getOriginalFilename());
        Path updateMainPath = Paths.get(updateMainImgPath);
        Files.write(updateMainPath, updateImg.getBytes());

        // 기존 이미지 삭제
        String pastImgName = getFullMainPath(foundImg);
        Path oldImgPath = Paths.get(pastImgName);
        /*Files.delete(oldImgPath);*/
        log.info("OldImgPath = {}" , oldImgPath);
        return updateImg.getOriginalFilename();
    }

    public String updateSubImgs(List<String> foundImgs, List<MultipartFile> updateImgs) {

        return "";
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

}
