package com.tezbus.backend.pageable;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

public class PageRequest implements Pageable, Serializable {
    private static final long serialVersionUID = -4541509938956089562L;
    private int limit;
    private int offset;
    private Sort sort;

    PageRequest(int offset, int limit, String sortBy) {
        this.limit = limit;
        this.offset = offset;
        if (null == sortBy) {
            this.sort = Sort.by("createdAt");
        } else {
            this.sort = Sort.by(sortBy);
        }
    }

    @Override
    public int getPageNumber() {
        return 0;
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }
}