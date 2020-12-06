package com.eccomerce.diegocebollero.eccomerce.Model;

public class Product {
    private static int idCount = 0;
    private int id;
    private String nombre;
    private double precio;
    private String img;

    public Product(){}

    public Product(String nombre, double precio, String img) {
        this.id = ++idCount;
        this.nombre = nombre;
        this.precio = precio;
        this.img = img;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
}
