package com.clone.airbnb.domain.media.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class FileStore {

    @Value("${rule.file_dir}")
    private String fileDir;

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    public void storeFile(MultipartFile multipartFile) throws IOException {
        String fullPath = getFullPath(multipartFile.getOriginalFilename());
        multipartFile.transferTo(new File(fullPath));
    }
}
