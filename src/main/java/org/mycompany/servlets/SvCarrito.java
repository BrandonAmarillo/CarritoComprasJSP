package org.mycompany.servlets;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mycompany.logic.models.Producto;
import org.mycompany.logic.models.ItemCarrito;

@WebServlet(name = "SvCarrito", value = "/SvCarrito")
public class SvCarrito extends HttpServlet {

    List<Producto> productos = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        super.init();
        productos.add(new Producto(1, "Mouse", 10.000));
        productos.add(new Producto(2, "Teclado", 15.000));
        productos.add(new Producto(3, "Monitor", 80.000));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        procesar(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        procesar(request, response);
    }

    private void procesar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if(accion == null) {
            accion = "listarProductos";
        }
        HttpSession session = request.getSession();
        List<ItemCarrito> carrito = (List<ItemCarrito>) session.getAttribute("carrito");
        if(carrito == null) {
            carrito = new ArrayList<>();
            session.setAttribute("carrito", carrito);
        }

        switch(accion) {
            case "agregar":
                agregarProducto(request, carrito);
                mostrarCarrito(request, response, carrito);
                break;
            case "eliminar":
                eliminarProducto(request, carrito);
                mostrarCarrito(request, response, carrito);
                break;
            case "vaciar":
                carrito.clear();
                mostrarCarrito(request, response, carrito);
                break;
            case "ver":
                mostrarCarrito(request, response, carrito);
                break;
            case "listarProductos":
            default:
                listarProductos(request, response);
        }
    }

    private void agregarProducto(HttpServletRequest request, List<ItemCarrito> carrito) throws ServletException, IOException {
        String idParam = request.getParameter("idProducto");
        if(idParam == null) return;

        int id = Integer.parseInt(idParam);
        Producto producto = productos.stream()
                                     .filter(p -> p.getId() == id)
                                     .findFirst()
                                     .orElse(null);
        
        // Verificamos si el producto ya está en el carrito
        for(ItemCarrito item : carrito) {
            if(item.getProducto().getId() == id) {
                item.setCantidad(item.getCantidad() + 1);
                return;
            }
        }

        // Si no está, lo agregamos
        carrito.add(new ItemCarrito(producto));
    }

    private void eliminarProducto(HttpServletRequest request, List<ItemCarrito> carrito) throws ServletException, IOException {
        String idParam = request.getParameter("idProducto");
        if(idParam == null) return;

        int id = Integer.parseInt(idParam);
        carrito.removeIf(item -> item.getProducto().getId() == id);
    }

    private void mostrarCarrito(HttpServletRequest request, HttpServletResponse response, List<ItemCarrito> carrito) throws ServletException, IOException {
        double total = 0.0;
        for(ItemCarrito item : carrito) {
            total += item.getTotalPrecio();
        }
        request.setAttribute("carrito", carrito);
        request.setAttribute("total", total);
        request.getRequestDispatcher("carrito.jsp").forward(request, response);
    }

    private void listarProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("productos", new ArrayList<>(productos));
        request.getRequestDispatcher("productos.jsp").forward(request, response);
    }
}