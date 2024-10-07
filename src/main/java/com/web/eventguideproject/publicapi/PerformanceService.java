package com.web.eventguideproject.publicapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerformanceService {

    private final RestTemplate restTemplate;

    @Value("${api.service.key}")
    private String serviceKey;

    public PerformanceService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public PaginatedResponse<PerformanceDTO> getFilteredPerformances(LocalDate today, int pageNo, int numOfRows) {
        String url = "http://api.kcisa.kr/openapi/API_CCA_144/request"
                + "?serviceKey=" + serviceKey
                + "&numOfRows=" + 100
                + "&pageNo=" + pageNo;

        System.out.println("API 요청 URL : " + url);

        // API 호출
        ResponseEntity<PublicApiResponse> response = restTemplate.getForEntity(url, PublicApiResponse.class);

        if (response.getBody() != null && response.getBody().getResponse() != null &&
                response.getBody().getResponse().getBody() != null &&
                response.getBody().getResponse().getBody().getItems() != null) {

            List<PerformanceDTO> filteredPerformances = response.getBody().getResponse().getBody().getItems().getItem().stream()
                    .filter(performance -> {
                        try {
                            // PERIOD와 EVENT_PERIOD 각각 날짜와 시간을 추출
                            String period = performance.getPeriod();  // 날짜 (yyyy-MM-dd)
                            String eventPeriod = performance.getEventPeriod();  // 시간 (HH:mm)

                            // 날짜와 시간 포맷터 생성
                            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

                            // 날짜와 시간을 각각 파싱
                            LocalDate eventDate = LocalDate.parse(period, dateFormatter);
                            LocalTime eventTime = LocalTime.parse(eventPeriod, timeFormatter);

                            // LocalDate와 LocalTime을 결합하여 LocalDateTime 생성
                            LocalDateTime eventDateTime = LocalDateTime.of(eventDate, eventTime);

                            // 현재 시간과 비교하여 이후의 공연만 필터링
                            return eventDateTime.isAfter(LocalDateTime.now());

                        } catch (Exception e) {
                            System.out.println("Error parsing date or time for performance: " + performance.getTitle());
                            return false;
                        }
                    })
                    .collect(Collectors.toList());

            // 페이징 처리
            int start = (pageNo - 1) * numOfRows;
            int end = Math.min(start + numOfRows, filteredPerformances.size());
            List<PerformanceDTO> paginatedPerformances = filteredPerformances.subList(start, end);

            return new PaginatedResponse<>(paginatedPerformances, (int) Math.ceil((double) filteredPerformances.size() / numOfRows));
        } else {
            System.out.println("API 응답에 문제가 있습니다.");
            return new PaginatedResponse<>(List.of(), 0);
        }
    }
}