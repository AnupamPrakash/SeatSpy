package com.example.seatspy;

public class IntroScreenItem {
    String title,description;
    int screenimage;

    public IntroScreenItem(String title, String description, int screenimage) {
        this.title = title;
        this.description = description;
        this.screenimage = screenimage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getScreenimage() {
        return screenimage;
    }

    public void setScreenimage(int screenimage) {
        this.screenimage = screenimage;
    }
}
