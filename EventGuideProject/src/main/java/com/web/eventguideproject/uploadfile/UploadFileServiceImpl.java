package com.web.eventguideproject.uploadfile;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadFileServiceImpl {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private UploadFileRepository uploadFileRepository; // 업로드파일 레포지토리 주입

    public UploadFile saveFile(MultipartFile file) throws IOException {
        // 파일 형식 및 크기에 검증
        validateFile(file);

        // 파일명을 유니크하게 만들기 위해 UUID를 사용
        String uniqueFileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadPath + uniqueFileName);

        // 파일을 디렉토리에 저장
        Files.write(filePath, file.getBytes());

        // 저장된 파일 정보를 UploadFile 엔티티로 생성
        UploadFile uploadFile = new UploadFile();
        uploadFile.setFileName(uniqueFileName);
        uploadFile.setFileType(file.getContentType());
        uploadFile.setFilePath(filePath.toString());
        uploadFile.setFileSize(file.getSize());

        return uploadFile;
    }

    private void validateFile(MultipartFile file) throws IllegalStateException {
        // 허용된 파일 형식
        String[] allowedFileTypes = {"image/jpeg", "image/png", "video/mp4"};

        // 파일 크기 제한
        long maxFileSize = 50 * 1024 * 1024;

        // 파일 형식 체크
        boolean isValidType = false;
        for (String type : allowedFileTypes) {
            if (type.equals(file.getContentType())) {
                isValidType = true;
                break;
            }
        }
        if (!isValidType) {
            throw new IllegalStateException("허용되지 않는 파일 형식입니다.");
        }

        // 파일 크기 체크
        if (file.getSize() > maxFileSize) {
            throw new IllegalStateException("파일 크기가 너무 큽니다. 최대 50MB까지 허용됩니다.");
        }
    }
}
