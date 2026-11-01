package org.mycompany.logic.models;

public class Producto {
    private int id;
    private String nombre;
    private Double precioUnitario;

    public Producto(){ }

    public Producto(int id, String nombre, Double precioUnitario) {
        this.id = id;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
    }

    public int getId() { return id; }
    
    public void setId(int id) { this.id = id; }
    
    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public Double getPrecioUnitario() { return precioUnitario; }

    public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }

    @Override
    public String toString() {
        return "ItemProductos{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precioUnitario=" + precioUnitario +
                '}';
    }

}
