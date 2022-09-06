package com.example.projectimage.model;

import java.util.ArrayList;
import java.util.List;

public class Root {
    public String id;
    public String url;
    public List<Breed> breeds;
    public int width;
    public int height;

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public List<Breed> getBreeds() {
        return breeds;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Root{" +"\n"+
                "id='" + id + '\'' + "\n"+
                ", url='" + url + '\'' +"\n"+
                ", breeds=" + breeds.toString() +"\n"+
                ", width=" + width +"\n"+
                ", height=" + height +"\n"+
                '}';
    }
}
