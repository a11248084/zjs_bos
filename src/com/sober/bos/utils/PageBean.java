package com.sober.bos.utils;

import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 *
 * 分页封装信息
 */
public class PageBean {
    private int currentPage;//当前页
    private int pageSize;//每页显示的条数
    private DetachedCriteria detachedCriteria;//分页查询需要的条件
    private int total;//总记录数
    private List rows;//当前页要展示的数据的集合

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public DetachedCriteria getDetachedCriteria() {
        return detachedCriteria;
    }

    public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
        this.detachedCriteria = detachedCriteria;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
