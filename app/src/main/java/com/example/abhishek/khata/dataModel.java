package com.example.abhishek.khata;

/**
 * Created by abhishek on 1/2/15.
 */
public class dataModel {
    private long id;
    private String name;
    private AmountDescription amount;
    private AmountDescription his1;
    private AmountDescription his2;
    private AmountDescription his3;
    private AmountDescription his4;
    private AmountDescription his5;

    public dataModel() {
        this.id = 0;
        this.name = "";
        this.amount = new AmountDescription();
        this.his1 = new AmountDescription();
        this.his2 = new AmountDescription();
        this.his3 = new AmountDescription();
        this.his4 = new AmountDescription();
        this.his5 = new AmountDescription();
    }

    public dataModel(long id, String name, AmountDescription amount, AmountDescription his1, AmountDescription his2,
                     AmountDescription his3,  AmountDescription his4,  AmountDescription his5) {

        this.id = id;
        this.name = name;
        this.amount = amount;
        this.his1 = his1;
        this.his2 = his2;
        this.his3 = his3;
        this.his4 = his4;
        this.his5 = his5;
    }

    public dataModel(int id,String name,AmountDescription amount) {
        this.id=id;
        this.name = name;
        this.amount = amount;
        this.his1 = new AmountDescription();
        this.his2 = new AmountDescription();
        this.his3 = new AmountDescription();
        this.his4 = new AmountDescription();
        this.his5 = new AmountDescription();
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

    public AmountDescription getAmount() {
        return amount;
    }

    public void setAmount(AmountDescription amount) {
        this.amount = amount;
    }

    public AmountDescription getHis1() {
        return his1;
    }

    public void setHis1(AmountDescription his1) {
        this.his1 = his1;
    }

    public AmountDescription getHis2() {
        return his2;
    }

    public void setHis2(AmountDescription his2) {
        this.his2 = his2;
    }

    public AmountDescription getHis3() {
        return his3;
    }

    public void setHis3(AmountDescription his3) {
        this.his3 = his3;
    }

    public AmountDescription getHis4() {
        return his4;
    }

    public void setHis4(AmountDescription his4) {
        this.his4 = his4;
    }

    public AmountDescription getHis5() {
        return his5;
    }

    public void setHis5(AmountDescription his5) {
        this.his5 = his5;
    }

}
