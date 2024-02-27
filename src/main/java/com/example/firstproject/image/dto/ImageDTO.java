package com.example.firstproject.image.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class ImageDTO {
    private MultipartFile file;
}
