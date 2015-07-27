package com.example.abhishek.khata;

/**
 * Created by ashok on 22/7/15.
 */
public class AmountDescription {
    private String comment;
    private float amount;

    public AmountDescription(String cmt,float amt){
        comment = cmt;
        amount = amt;
    }

    public AmountDescription(){
        comment = "";
        amount = 0;
    }
    void setComment(String cmt){
        comment = cmt;
    }
    void setAmount(float amt){
        amount = amt;
    }
    String getComment(){
        return this.comment;
    }
    float getAmount(){
        return this.amount;
    }
}
