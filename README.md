# Ejercicio 3 (Nivel Intermedio): Carrito de compras básico (sin base de datos)

Creá una mini aplicación web que simule un **carrito de compras**, usando **HttpSession** para guardar los productos seleccionados.

**1. Página de productos: productos.jsp**

Debe mostrar una lista fija de productos, por ejemplo:

| IP | Productos | Precio | Acción |
|---|---------|------|-------|
| 1 | Mouse | 10.000 | [agregar al carro] |
| 2 | Teclado | 15.000 | [agregar al carro] |
| 3 | Monitor | 80.000 | [agregar al carro] |

- Cada fila debe tener:
    - Un botón o link "**Agregar al carrito**"
    - Ese botón puede ser_
        - Un ```<form>``` con ```method="POST"``` que envíe ```idProducto```a un Servlet, por ejemplo
        CarritoServlet?accion=agregar.

**2. Servlet** ```CarritoServlet```

Este Servlet va a manejar toda la lógica del carrito.
- Debe usar la **HttpSession** para guardar el carrito, por ejemplo como:
    - Una  ```List<ItemCarrito> ```
    - O un ```Map<Integerm, ItemCarrito> ``` donde la clase sea el ID de producto.
    - **Acciones mínimas** (vía parámetro accion):
        - accion=agregar
            - Recibir idProducto.
            - Buscar los datos del producto (pueden estar hardcodeados en el servlet: nombre, precio).
            - Si el producto NO está en el carrito --> lo agrega con cantidad 1.
            - Si ya está --> incrementa la cantidad.
    - accion=ver
        - Solo hace forward a carrito.jsp con la lista de ítems ya cargada en la acción.
    - accion=eliminar
        - Recibir el idProducto.
        - Lo quita del carrito.
    - (Opcional) accion= vaciar
        - Quitar todos los productos del carrito (remueve el atributo de sesión o deja la lista vacía).

**3. Página del carrito:** ```carrito.jsp```

Debe mostrar:

- Tabla con:
    - Nombre del producto
    - Precio unitario
    - Cantidad
    - Subtotal (precio * cantidad)
    - Botón "Eliminar" por producto (llamando al ```CarritoServlet?accion=eliminar&idProducto=...```)
- Debajo, el **total generado** del carrito.
- Links/botones:
    - "Seguir comprando" --> vuelve a ```producto.jsp```.
    - "Vaciar carrito" --> llama a ```CarritoServlet?accion=vaciar```.

**4. Requisitos técnicos**

- Uso de:
    - **HttpSession** para guardar el carrito.
    - Un **Servlet** como controlador central (CarritoServlet).
    - forward entre Servlet y JSP (RequestDispatcher)
- Lógica mínima de negocio: sumas, totales, manejo de cantidades.
- El listado de productos puede estar:
    - Hardcodeado en el Servlet (por ahora, sin DB ni DAO).

**Objetivo didáctico**: practicar manejo de sesión, estructuras de datos en memoria (lista/map), múltiples acciones en un mismo Servlet y la interacción entre varias JSP (productos y carrito).