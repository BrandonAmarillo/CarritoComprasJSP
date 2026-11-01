<%@ page contentType="text/html; chatset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Productos</title>
</head>
<body>
    <h1>Productos disponibles</h1>
    <thead>
        <th>ID</th>
        <th>Producto</th>
        <th>Precio</th>
        <th>Acción</th>
    </thead>
    <table border="1" cellpadding="5" cellspacing="0">
        <tbody>
        <c:forEach var="p" items="${productos}">
            <tr>
                <td>%{p.id}</td>
                <td>%{p.nombre}</td>
                <td>%{p.precioUnitario}</td>
                <td>
                    <form accion="SvCarrito" method="post">
                        <input type="hidden" name="accion" value="agregar">
                        <input type="hidden" name="idProducto" value="${p.id}">
                        <button type="submit">Agregar al carrito</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
    </table>
    <p><a href="index.jsp">Volver al inicio</a></p>
</body>
</html>