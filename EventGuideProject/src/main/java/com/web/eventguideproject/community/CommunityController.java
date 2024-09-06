package com.web.eventguideproject.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/community")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @PostMapping(value = "/write", consumes = {"multipart/form-data"})
    public ResponseEntity<Object> write(@ModelAttribute CommunityDTO communityDTO,
                                        @RequestPart("uploadFile") MultipartFile uploadFile) throws IOException {

        // communityService.write(communityDTO, file) 메서드를 호출하여 게시글과 파일을 처리하고 저장합니다.
        CommunityDTO savedCommunity = communityService.write(communityDTO, uploadFile);

        // 처리된 결과를 ResponseEntity 로 감싸서 클라이언트에게 응답을 받습니다.
        return ResponseEntity.ok(savedCommunity);
    }
    // 게시글 목록을 조회하는 메서드
    @GetMapping("/list")
    public ResponseEntity<List<CommunityDTO>> getCommunityList() {
        List<CommunityDTO> communityList = communityService.getAllCommunites();
        return ResponseEntity.ok(communityList);
    }
    // 게시글 상세 내용을 조회하는 메서드
    @GetMapping("/{id}")
    public ResponseEntity<CommunityDTO> getCommunityDetail(@PathVariable Long id) {
        CommunityDTO communityDTO = communityService.getCommunityById(id);
        return ResponseEntity.ok(communityDTO);
    }
}
