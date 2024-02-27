package com.example.firstproject.image.service;

import com.example.firstproject.image.dto.ImageDTO;
import com.example.firstproject.image.entity.Image;
import com.example.firstproject.image.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Value("${image.upload.dir}")
    private String uploadDir;

    public ImageService(ImageRepository imageRepository){
        this.imageRepository = imageRepository;
    }

    public Image uploadImage(ImageDTO imageDTO){
        try{
            Path root = Paths.get(uploadDir);
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }

            String filename = UUID.randomUUID() + "-" + imageDTO.getFile().getOriginalFilename();
            Path path = root.resolve(filename);
            Files.copy(imageDTO.getFile().getInputStream(), path);

            Image image = new Image();
            image.setUrl(filename);
            return imageRepository.save(image);
        }catch (IOException e){
            throw new RuntimeException("이미지 업로드 중 오류 발생.");
        }

    }
}
