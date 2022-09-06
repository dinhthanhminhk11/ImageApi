package com.example.projectimage.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Image implements Serializable {
    private String id;
    private String url;
    private int width;
    private int height;

    public Image(String id, String url, int width, int height) {
        this.id = id;
        this.url = url;
        this.width = width;
        this.height = height;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
