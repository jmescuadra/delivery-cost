package com.joem.java;

public class parcelModel {
    private double weight;
    private double height;
    private double width;
    private double length;
    private String voucher;

    public parcelModel(double weight, double height, double width, double length, String voucher) {
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.length = length;
        this.voucher = voucher;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return this.width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return this.length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getVoucher() {
        return this.voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }
}
