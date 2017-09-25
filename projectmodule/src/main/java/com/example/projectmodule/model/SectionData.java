package com.example.projectmodule.model;

/**
 * Created by 吴城林 on 2017/8/20.
 */

public class SectionData<T> {
    public boolean isHeader;
    public String headerName;
    public int spanCount;
    public T t;

    public SectionData(String headerName) {
        this.isHeader = true;
        this.headerName = headerName;
        this.t = null;
        this.spanCount =1;
    }

    public SectionData(String headerName,int spanCount) {
        this.isHeader = true;
        this.headerName = headerName;
        this.t = null;
        this.spanCount =spanCount;
    }

    public SectionData(T t) {
        this.isHeader = false;
        this.headerName = "";
        this.t = t;
        this.spanCount = 1;
    }
}
