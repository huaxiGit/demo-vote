package com.allen.vote.common.response;


public class BasePageParam  {
    private Integer pageNum;
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "BasePageParam{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
