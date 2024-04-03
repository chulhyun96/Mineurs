package com.newlecmineursprj.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class HomeController {
    @GetMapping(path = "/image/products/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(HttpServletRequest request, HttpServletResponse response, @PathVariable String fileName) {
        try {
            // Assuming that images are stored in "/images" directory on the server
            String basePath = request.getServletContext().getRealPath("/image/products");
            String filePath = Paths.get(basePath, fileName).toString();

            return Files.readAllBytes(Paths.get(filePath));
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
