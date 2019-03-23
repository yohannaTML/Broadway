package com.example.broadway.model;

public class Musical {
    public Musical(String image_url, String title, String by) {
        this.image_url = image_url;
        this.title = title;
        this.by = by;

    }

    public String getImagecard_url() {
        return imagecard_url;
    }


    public String getTitle() {
        return title;
    }

    public String getBy() {
        return by;
    }

    public String getStory() {
        return story;
    }

    public String getTheatre() {
        return theatre;
    }

    public String getOpening_night() {
        return opening_night;
    }

    public String getTotal_performances() {
        return total_performances;
    }

    public void setImagecard_url(String imagecard_url) {
        this.imagecard_url = imagecard_url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public void setTheatre(String theatre) {
        this.theatre = theatre;
    }

    public void setOpening_night(String opening_night) {
        this.opening_night = opening_night;
    }

    public void setTotal_performances(String total_performances) {
        this.total_performances = total_performances;
    }
    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }


    private String imagecard_url;
    private String image_url;
    private String title;
    private String by;
    private String story;
    private String theatre;
    private String opening_night;
    private String total_performances;

}
