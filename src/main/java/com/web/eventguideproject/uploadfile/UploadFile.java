package com.web.eventguideproject.uploadfile;

import jakarta.persistence.*;
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

    @Column(columnDefinition = "TEXT") // 파일 경로가 길어질 수 있으므로 텍스트로 설정
    private String filePath; // 파일이 저장된 경로
    private Long fileSize;   // 파일 크기
}
