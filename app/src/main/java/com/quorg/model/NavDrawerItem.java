/**
 * @author valkesh patel
 */
package com.quorg.model;

public class NavDrawerItem {
    private boolean showNotify;
    private String title;
    public int count;

    public NavDrawerItem() {

    }

    public NavDrawerItem(boolean showNotify, String title, int count) {
        this.showNotify = showNotify;
        this.title = title;
        this.count = count;
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
}
