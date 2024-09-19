package com.web.eventguideproject.publicapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    @Autowired
    private EventApi eventApi;

    // 데이터를 가져오는 API 엔드포인트
    @GetMapping("/api/exhibitions")
    public String getExhibitions() {
        return eventApi.getEvents("전시회");
    }

    @GetMapping("/api/expos")
    public String getExpos() {
        return eventApi.getEvents("박람회");
    }

    @GetMapping("/api/museums")
    public String getMuseums() {
        return eventApi.getEvents("미술관");

    }
}
