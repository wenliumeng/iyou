package org.iyou.common.model;

import java.util.List;

/**
 * Created by seyMour on 2016/4/14.
 * ☞120465271@qq.com☜
 */
public class PagerRecords {
    private List records;
    private int totalCount;
    private Pager pager;

    public PagerRecords(List records, int totalCount) {
        this.records = records;
        this.totalCount = totalCount;
    }

    public List getRecords() {
        return this.records;
    }

    public void setRecords(List records) {
        this.records = records;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public Pager getPager() {
        return this.pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }
}
