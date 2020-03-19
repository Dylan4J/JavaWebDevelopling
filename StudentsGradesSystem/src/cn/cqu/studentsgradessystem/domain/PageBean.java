package cn.cqu.studentsgradessystem.domain;

import java.util.List;

/**
 * 分页对象
 */
public class PageBean {
    private int totalCount;//总记录数
    private int totalPage;//总页面数
    private int currentPage;//当前页面
    private List stuByPage;//当前页面的数据
    private int rows;//当前页面展示的记录数

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", currentPage=" + currentPage +
                ", stuByPage=" + stuByPage +
                ", rows=" + rows +
                '}';
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setStuByPage(List stuByPage) {
        this.stuByPage = stuByPage;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public List getStuByPage() {
        return stuByPage;
    }

    public int getRows() {
        return rows;
    }
}
