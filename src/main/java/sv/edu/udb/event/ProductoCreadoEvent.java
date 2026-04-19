package sv.edu.udb.event;

import org.springframework.context.ApplicationEvent;
import sv.edu.udb.entitty.Producto;

public class ProductoCreadoEvent extends ApplicationEvent {
    private Producto producto;

    public ProductoCreadoEvent(Object source, Producto producto) {
        super(source);
        this.producto = producto;
    }
    public Producto getProducto() {return producto;}
}