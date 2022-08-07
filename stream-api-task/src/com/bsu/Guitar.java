package com.bsu;

import java.util.Objects;

public class Guitar {
    private String manufacturer;
    private String model;
    private int stringCount;
    private String bodyType;
    private String type;
    private int cost;

    public Guitar(String manufacturer, String model, int stringCount, String bodyType, String type, int cost) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.stringCount = stringCount;
        this.bodyType = bodyType;
        this.type = type;
        this.cost = cost;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getStringCount() {
        return stringCount;
    }

    public void setStringCount(int stringCount) {
        this.stringCount = stringCount;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guitar guitar = (Guitar) o;
        return getStringCount() == guitar.getStringCount() &&
                getCost() == guitar.getCost() &&
                Objects.equals(getManufacturer(), guitar.getManufacturer()) &&
                getModel().equals(guitar.getModel()) &&
                Objects.equals(getBodyType(), guitar.getBodyType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getManufacturer(), getModel(), getStringCount(), getBodyType(), getCost());
    }

    @Override
    public String toString() {
        return "Guitar{" +
                "manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", stringCount=" + stringCount +
                ", bodyType='" + bodyType + '\'' +
                ", cost=" + cost +
                '}';
    }

    public static Guitar read(String[] data){
        return new Guitar(data[0], data[1], Integer.parseInt(data[2]), data[3], data[4], Integer.parseInt(data[5]));
    }
}
