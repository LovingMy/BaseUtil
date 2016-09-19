package com.che.test.rxjava.asyncwork.test;

public class Uri {
    String path;

    public Uri(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Uri{" +
                "path='" + path + '\'' +
                '}';
    }
}
