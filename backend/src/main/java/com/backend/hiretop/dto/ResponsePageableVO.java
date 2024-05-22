package com.backend.hiretop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResponsePageableVO<T> {
    private List<T> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;

    public ResponsePageableVO() {

    }

    public ResponsePageableVO(List<T> content, int page, int size, long totalElements, int totalPages, boolean last) {
        setContent(content);
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }

    public List<T> getContent() {

        return content == null ? null : new ArrayList<>(content);
    }

    public final void setContent(List<T> content) {
        if (content == null) {
            this.content = null;
        } else {
            this.content = Collections.unmodifiableList(content);
        }
    }

    @JsonProperty("page")
    public int getPage() {
        return page;
    }

    @JsonProperty("size")
    public int getSize() {
        return size;
    }

    @JsonProperty("totalElements")
    public long getTotalElements() {
        return totalElements;
    }

    @JsonProperty("totalPages")
    public int getTotalPages() {
        return totalPages;
    }

    @JsonProperty("last")
    public boolean isLast() {
        return last;
    }
}
