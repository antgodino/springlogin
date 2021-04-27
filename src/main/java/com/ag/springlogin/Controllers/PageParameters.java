package com.ag.springlogin.Controllers;

public class PageParameters {
    private String home;
    private String title;

    public PageParameters(String home) {
        this.home = home;
    }

    public PageParameters( String home, String title) {
        this.home = home;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }
}
