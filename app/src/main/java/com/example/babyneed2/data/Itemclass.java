package com.example.babyneed2.data;

public class Itemclass {

    private int id;
    private String  ite;
    private String colr;
    private int    siz;
    private  int  qty;

    public Itemclass() {
    }

    public Itemclass(String ite, String colr, int siz, int qty) {
        this.ite = ite;
        this.colr = colr;
        this.siz = siz;
        this.qty = qty;
    }

    public Itemclass(int id, String ite, String colr, int siz, int qty) {
        this.id = id;
        this.ite = ite;
        this.colr = colr;
        this.siz = siz;
        this.qty = qty;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIte(String ite) {
        this.ite = ite;
    }

    public void setColr(String colr) {
        this.colr = colr;
    }

    public void setSiz(int siz) {
        this.siz = siz;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public String getIte() {
        return ite;
    }

    public String getColr() {
        return colr;
    }

    public int getSiz() {
        return siz;
    }

    public int getQty() {
        return qty;
    }

}
