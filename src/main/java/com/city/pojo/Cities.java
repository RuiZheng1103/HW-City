package com.city.pojo;

import java.util.Objects;

public class Cities {
    private String city1;
    private String city2;

    public Cities(String city1, String city2){
        this.city1 = city1;
        this.city2 = city2;
    }

    public String getCity1() {
        return city1;
    }

    public void setCity1(String city1) {
        this.city1 = city1;
    }

    public String getCity2() {
        return city2;
    }

    public void setCity2(String city2) {
        this.city2 = city2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cities that = (Cities) o;
        return Objects.equals(city1, that.city1) &&
                Objects.equals(city2, that.city2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city1, city2);
    }

    @Override
    public String toString() {
        return "Cities{" +
                "origin='" + city1 + '\'' +
                ", destination='" + city2 + '\'' +
                '}';
    }
}
