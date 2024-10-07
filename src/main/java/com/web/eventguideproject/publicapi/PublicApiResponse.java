package com.web.eventguideproject.publicapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PublicApiResponse {

    @JsonProperty("response")
    private Response response;

    @Getter
    @Setter
    public static class Response {

        @JsonProperty("header")
        private Header header;

        @JsonProperty("body")
        private Body body;
    }

    @Getter
    @Setter
    public static class Header {

        @JsonProperty("resultCode")
        private String resultCode;

        @JsonProperty("resultMsg")
        private String resultMsg;
    }

    @Getter
    @Setter
    public static class Body {

        @JsonProperty("items")
        private Items items;

        @JsonProperty("totalCount")
        private int totalCount;
    }

    @Getter
    @Setter
    public static class Items {

        @JsonProperty("item")
        private List<PerformanceDTO> item;
    }
}