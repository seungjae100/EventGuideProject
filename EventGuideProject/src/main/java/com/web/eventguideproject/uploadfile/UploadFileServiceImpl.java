package com.web.eventguideproject.uploadfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.UUID;

@Service
public class UploadFileServiceImpl implements UploadFileService {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private UploadFileRepository uploadFileRepository; // 업로드파일 레포지토리 주입

    @Transactional
    public UploadFile saveFile(MultipartFile file) throws IOException {
        validateFile(file); // 파일 유효성 검사

        // 고유 파일명 생성
        String uniqueFileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

        // 파일 저장 경로 설정
        Path fileDirectoryPath = Paths.get(uploadPath).toAbsolutePath().normalize(); // 기본 저장 경로
        Path filePath = fileDirectoryPath.resolve(uniqueFileName); // 파일 전체 경로

        // 디렉토리 존재 확인 및 생성
        if (!Files.exists(fileDirectoryPath)) {
            Files.createDirectories(fileDirectoryPath); // 디렉토리 생성
        }

        // 파일 저장
        Files.write(filePath, file.getBytes());

        // 업로드 파일 정보 생성 및 저장
        UploadFile uploadFile = new UploadFile();
        uploadFile.setFileName(uniqueFileName);
        uploadFile.setFileType(file.getContentType());
        uploadFile.setFilePath(filePath.toString()); // 파일 경로 저장
        uploadFile.setFileSize(file.getSize());

        uploadFileRepository.save(uploadFile);

        return uploadFile;
    }

    private void validateFile(MultipartFile file) throws IllegalStateException {
        // 허용된 파일 형식
        String[] allowedFileTypes = {"image/jpeg", "image/png", "video/mp4"};

        // 파일 크기 제한
        long maxFileSize = 50 * 1024 * 1024; // 50MB

        // 파일 형식 체크
        boolean isValidType = Arrays.stream(allowedFileTypes)
                .anyMatch(type -> type.equals(file.getContentType()));

        if (!isValidType) {
            throw new IllegalStateException("허용되지 않는 파일 형식입니다.");
        }

        if (file.getSize() > maxFileSize) {
            throw new IllegalStateException("파일 크기가 너무 큽니다.");
        }
    }

}