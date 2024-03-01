package com.example.firstproject.post.service;

import com.example.firstproject.post.entity.Post;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Transactional
public class PostFileService {

    public Post FileUpload(MultipartFile file, Post post) throws IOException {
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);
        file.transferTo(saveFile);
        post.setFilename(fileName);
        post.setFilepath("/files/" + fileName);

        return post;
    }
}
