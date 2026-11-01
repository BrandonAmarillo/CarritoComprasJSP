package org.mycompany.logic.models;

public class ItemCarrito {
    private Producto producto;
    private int cantidad;

    public ItemCarrito() { }

    public ItemCarrito(Producto producto) {
        this.producto = producto;
        this.cantidad = 1;
    }

    public Producto getProducto() {return producto; }

    public void setProducto(Producto producto) { this.producto = producto; }

    public int getCantidad() { return cantidad; }

    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public Double getTotalPrecio() {
        if (producto != null) {
            return producto.getPrecioUnitario() * cantidad;
        }
        return 0.0;
    }
}