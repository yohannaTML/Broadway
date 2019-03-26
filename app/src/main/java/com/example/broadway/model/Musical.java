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


    public String getDirector() {
        return director;
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


    public void setDirector(String director) {
        this.director = director;
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
    private String director;

}
