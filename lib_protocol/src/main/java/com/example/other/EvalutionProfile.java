package com.example.other;

import java.util.List;

/**
 * Created by yakun on 2016/10/25.
 */

public class EvalutionProfile {
    private int page;
    private int pageCount;
    private StarLevelStatistics statistics;
    private List<Evalution> list;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public StarLevelStatistics getStatistics() {
        return statistics;
    }

    public void setStatistics(StarLevelStatistics statistics) {
        this.statistics = statistics;
    }

    public List<Evalution> getList() {
        return list;
    }

    public void setList(List<Evalution> list) {
        this.list = list;
    }
}
