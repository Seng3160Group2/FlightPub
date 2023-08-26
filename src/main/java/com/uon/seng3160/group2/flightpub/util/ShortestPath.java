package com.uon.seng3160.group2.flightpub.util;

import java.util.List;

import com.uon.seng3160.group2.flightpub.entity.Destination;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class ShortestPath {
    private List<Destination> path;
    private Integer length;

    public ShortestPath() {
    }

    public ShortestPath(List<Destination> path, Integer length) {
        this.path = path;
        this.length = length;
    }

    public List<Destination> getPath() {
        return this.path;
    }

    public void setPath(List<Destination> path) {
        this.path = path;
    }

    public Integer getLength() {
        return this.length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "{" +
                " path='" + getPath() + "'" +
                ", length='" + getLength() + "'" +
                "}";
    }

}
