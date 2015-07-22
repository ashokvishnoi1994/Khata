package com.example.abhishek.khata;

/**
 * Created by abhishek on 1/2/15.
 */
public class dataModel {
    private long id;
    private String name;
    private float amount;
    private float his1;
    private float his2;
    private float his3;

    public dataModel() {
    }

    public dataModel(long id, String name, float amount, float his1, float his2, float his3) {

        this.id = id;
        this.name = name;
        this.amount = amount;
        this.his1 = his1;
        this.his2 = his2;
        this.his3 = his3;
    }

    public dataModel(int id,String name,float amount) {
        this.id=id;
        this.name = name;
        this.amount = amount;
        this.his1 = 0;
        this.his2 = 0;
        this.his3 = 0;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getHis1() {
        return his1;
    }

    public void setHis1(float his1) {
        this.his1 = his1;
    }

    public float getHis2() {
        return his2;
    }

    public void setHis2(float his2) {
        this.his2 = his2;
    }

    public float getHis3() {
        return his3;
    }

    public void setHis3(float his3) {
        this.his3 = his3;
    }
}
