package com.web.eventguideproject.publicapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PerformanceController {

    private final PerformanceService performanceService;

    public PerformanceController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    @GetMapping("/performances")
    public ResponseEntity<List<PerformanceDTO>> getPerformances(
            @RequestParam(defaultValue = "6") int numOfRows,
            @RequestParam(defaultValue = "1") int pageNo
    ) {
        LocalDate today = LocalDate.now();

        // 공연 리스트 및 총 페이지 수 계산
        PaginatedResponse<PerformanceDTO> response = performanceService.getFilteredPerformances(today, pageNo, numOfRows);

        return ResponseEntity.ok(response.getItems());
    }
}