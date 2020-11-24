package com.eccomerce.diegocebollero.eccomerce.Model;

public class OrderProduct {
    private static int idCount = 0;
    private int id;
    private int idorder;
    private int idproduct;
    private int cantidad;

    public OrderProduct(){}

    public OrderProduct(int idorder, int idproduct, int cantidad){
        this.id = ++idCount;
        this.idorder = idorder;
        this.idproduct = idproduct;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdorder() {
        return idorder;
    }
    public void setIdorder(int idorder) {
        this.idorder = idorder;
    }
    public int getIdproduct() {
        return idproduct;
    }
    public void setIdproduct(int idproduct) {
        this.idproduct = idproduct;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
