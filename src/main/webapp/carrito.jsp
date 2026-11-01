<%@ page contentType="text/html; chatset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Carrito de Compras</title>
    </head>
    <body>
        <h1>Compras</h1>
        <p><a href="SvCarrito?accion=listarProductos">Seguir Comprando</a></p>
        
        <c:choose>
    <c:when test="${empty carrito}">
        <p>No hay productos en el carrito.</p>
    </c:when>
    <c:otherwise>
        <table border="1" cellpadding="5" cellspacing="0">
            <thead>
            <tr>
                <th>Producto</th>
                <th>Precio unitario</th>
                <th>Cantidad</th>
                <th>Subtotal</th>
                <th>Acción</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${carrito}">
                <tr>
                    <td>${item.producto.nombre}</td>
                    <td>${item.producto.precio}</td>
                    <td>${item.cantidad}</td>
                    <td>${item.subtotal}</td>
                    <td>
                        <a href="SvCarrito?accion=eliminar&idProducto=${item.producto.id}">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <p><strong>Total:</strong> ${total}</p>

        <p>
            <a href="SvCarrito?accion=vaciar">Vaciar carrito</a>
        </p>
    </c:otherwise>
</c:choose>

<p><a href="index.jsp">Volver al inicio</a></p>
    </body>
</html>