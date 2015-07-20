package com.score.models;

/**
 * Created by myves.stvictor on 2015-06-12.
 */
public class NavDrawer {
    public NavDrawer() { }

    public NavDrawer(boolean showNotify, String title) {
        this.showNotify = showNotify;
        this.title = title;
    }

    public boolean isShowNotify() {
        return showNotify;
    }

    public void setShowNotify(boolean showNotify) {
        this.showNotify = showNotify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private boolean showNotify;
    private String title;
}
