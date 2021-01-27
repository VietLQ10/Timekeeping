package com.training.timekeeping.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class RequestDTO {

    private Integer requestId;

    private LocalDateTime timeStart;

    private LocalDateTime timeEnd;

    private String content;

    private String name;

    public RequestDTO() {
    }

    public RequestDTO(Integer requestId, LocalDateTime timeStart, LocalDateTime timeEnd, String content, String name) {
        this.requestId = requestId;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.content = content;
        this.name = name;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public LocalDateTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalDateTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalDateTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalDateTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
