package com.web.eventguideproject.publicapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PerformanceDTO {

    @JsonProperty("TITLE")
    private String title;

    @JsonProperty("EVENT_SITE")
    private String eventSite;

    @JsonProperty("EVENT_PERIOD")
    private String eventPeriod;
}