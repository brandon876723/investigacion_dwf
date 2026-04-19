package sv.edu.udb.listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import sv.edu.udb.event.ProductoCreadoEvent;
import sv.edu.udb.event.ProductoEliminadoEvent;

@Component
public class ProductoEventListener {
    @Async
    @EventListener
    public void manejarCreacion(ProductoCreadoEvent event) {
        System.out.println("Producto creado: " + event.getProducto().getNombre());
    }

    @Async
    @EventListener
    public void manejarEliminacion(ProductoEliminadoEvent event) {
        System.out.println("Producto eliminado con ID: " + event.getId());
    }
}
