package com.web.eventguideproject.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/community")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @PostMapping("/write")
    public ResponseEntity<Object> write(@RequestBody CommunityDTO communityDTO){
        communityService.write(communityDTO); // 커뮤니티 게시글 작성
        return ResponseEntity.ok().build();
    }


}
