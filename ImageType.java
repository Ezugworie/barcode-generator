package com.bw.utils;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
public enum ImageType {

    PNG("png");

    private final String type;

    ImageType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
