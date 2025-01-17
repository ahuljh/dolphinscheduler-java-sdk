package com.github.ahuljh.model.url;

import java.util.Collections;
import java.util.List;

public class UrlDataListResponse<T> {
    /**
     * totalList
     */
    private List<T> totalList = Collections.emptyList();
    /**
     * total
     */
    private Integer total = 0;
    /**
     * total Page
     */
    private Integer totalPage;
    /**
     * page size
     */
    private Integer pageSize ;
    /**
     * current page
     */
    private Integer currentPage;
    /**
     * pageNo
     */
    private Integer pageNo;

    private Integer start;

    public List<T> getTotalList() {
        return totalList;
    }

    public void setTotalList(List<T> totalList) {
        this.totalList = totalList;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    @Override
    public String toString() {
        return "UrlDataListResponse{" +
                "totalList=" + totalList +
                ", total=" + total +
                ", totalPage=" + totalPage +
                ", pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                ", pageNo=" + pageNo +
                '}';
    }
}
