package com.web.eventguideproject.uploadfile;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadFileService {
    UploadFile saveFile(MultipartFile file) throws IOException;
}
