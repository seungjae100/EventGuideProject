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

    @PostMapping("/write")
    public ResponseEntity<CommunityDTO> write(
            @ModelAttribute CommunityDTO communityDTO,
            @RequestPart(value = "uploadFile", required = false) MultipartFile uploadFile) throws IOException {
        CommunityDTO result = communityService.write(communityDTO, uploadFile);
        return ResponseEntity.ok(result);
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
    // 게시글을 수정하는 메서드
    @PutMapping(value = "/update/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<Object> update(@PathVariable Long id,
                                         @ModelAttribute CommunityDTO communityDTO,
                                         @RequestPart("uploadFile") MultipartFile uploadFile) throws IOException {
        CommunityDTO updatedCommunity = communityService.update(id, communityDTO, uploadFile);
        return ResponseEntity.ok(updatedCommunity);
    }
    // 게시글을 삭제하는 메서드
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCommunity(@PathVariable Long id) {
        communityService.delete(id);
        return ResponseEntity.ok().build();
    }
}
