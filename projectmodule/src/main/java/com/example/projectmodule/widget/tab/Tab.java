package com.example.projectmodule.widget.tab;

/**
 * Created by 吴城林 on 2017/8/28.
 */

public class Tab {
    public int imageRes;
    public String textRes;
    public int menuRes;
    public String title;
    public int badgeCount;
    public Class <? extends ITabFragment>tabFragmentClz;

    public Tab(int imageRes, String textRes) {
        this.imageRes = imageRes;
        this.textRes = textRes;
    }

    public Tab(int imageRes, String textRes, int badgeCount) {
        this.imageRes = imageRes;
        this.textRes = textRes;
        this.badgeCount = badgeCount;
    }

    public Tab(int imageRes, String textRes, Class tabFragmentClz) {
        this.imageRes = imageRes;
        this.textRes = textRes;
        this.menuRes = 0;
        this.tabFragmentClz = tabFragmentClz;
    }

    public Tab(int imageRes, String textRes, int menuRes, Class tabFragmentClz) {
        this.imageRes = imageRes;
        this.textRes = textRes;
        this.menuRes = menuRes;
        this.tabFragmentClz = tabFragmentClz;
    }
}
