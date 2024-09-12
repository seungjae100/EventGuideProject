package com.web.eventguideproject.uploadfile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadFileDTO {
    private String fileName;
    private String filePath;
    private Long fileSize;
    private String fileType;
}