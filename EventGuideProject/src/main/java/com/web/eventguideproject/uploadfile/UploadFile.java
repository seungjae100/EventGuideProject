package com.web.eventguideproject.uploadfile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UploadFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName; // 파일 이름
    private String fileType; // 파일 타입 (사진, 동영상)
    private String filePath; // 파일이 저장된 경로
    private Long fileSize;   // 파일 크기
}
