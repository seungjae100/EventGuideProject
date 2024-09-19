package com.web.eventguideproject.publicapi;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class EventApi {

    @Value("${api.service.key}")
    private String serviceKey;

    private final String API_URL = "http://api.kcisa.kr/openapi/API_CNV_066/request";

    // 카테고리별 데이터를 가져오는 메서드
    public String getEvents(String eventType) {
        RestTemplate restTemplate = new RestTemplate();

        // HTTP 헤더에 API 키를 추가
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", serviceKey);

        // 카테고리별 동적으로 URL 생성 (전시회, 박람회, 미술관에 따라 구분)
        String url = API_URL + "?evntNm=" + eventType;

        // HTTP 요청 객체 생성
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // API 호출 (GET 요청)
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response.getBody();
    }
}
