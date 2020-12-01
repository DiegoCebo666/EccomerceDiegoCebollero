package com.eccomerce.diegocebollero.eccomerce.Model;

public class ProductQuantity {
    private int idproduct;
    private int cantidad;

    public ProductQuantity(){}

    public ProductQuantity(int idproduct, int cantidad){
        this.idproduct = idproduct;
        this.cantidad = cantidad;
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
