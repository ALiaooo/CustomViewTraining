package com.aliao.cvtraining.entity;

/**
 * Created by ÀöË« on 2015/7/2.
 */
public class Option {

    private String title;

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    private boolean open;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
