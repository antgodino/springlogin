package com.ag.springlogin.Sevice;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

@Service
public class FileService {

    public void uploadFile(MultipartFile file, String path, String name) {
        try {
            file.transferTo(new File(path + File.separator + name));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void uploadFile(MultipartFile file, String path) {
        uploadFile(file, path, file.getOriginalFilename());
    }

    public void multipleUploadFile(MultipartFile[] files, String path) {
        Arrays.stream(files).forEach(f -> {
            uploadFile(f, path);
        });
    }

    public HttpEntity<byte[]> showFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            byte[] documentBody = Files.readAllBytes(file.toPath());
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.valueOf(Files.probeContentType(file.toPath())));
            header.set(HttpHeaders.CONTENT_DISPOSITION,
                    "inline; filename=" + file.getName());
            header.setContentLength(documentBody.length);
            return new HttpEntity<byte[]>(documentBody, header);
        }
        return null;
    }

    public HttpEntity<byte[]> showdownload(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            byte[] documentBody = Files.readAllBytes(file.toPath());
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.valueOf(Files.probeContentType(file.toPath())));
            header.set(HttpHeaders.CONTENT_DISPOSITION,
                    "attach; filename=" + file.getName());
            header.setContentLength(documentBody.length);
            return new HttpEntity<byte[]>(documentBody, header);
        }
        return null;
    }
}
