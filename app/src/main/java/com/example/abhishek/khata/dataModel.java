package com.example.abhishek.khata;

/**
 * Created by abhishek on 1/2/15.
 */
public class dataModel {
    private long id;
    private String name;
    private int amount;
    private int his1;
    private int his2;
    private int his3;

    public dataModel() {
    }

    public dataModel(long id, String name, int amount, int his1, int his2, int his3) {

        this.id = id;
        this.name = name;
        this.amount = amount;
        this.his1 = his1;
        this.his2 = his2;
        this.his3 = his3;
    }

    public dataModel(int id,String name,int amount) {
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getHis1() {
        return his1;
    }

    public void setHis1(int his1) {
        this.his1 = his1;
    }

    public int getHis2() {
        return his2;
    }

    public void setHis2(int his2) {
        this.his2 = his2;
    }

    public int getHis3() {
        return his3;
    }

    public void setHis3(int his3) {
        this.his3 = his3;
    }
}
