package sv.edu.udb.event;

import org.springframework.context.ApplicationEvent;

public class ProductoEliminadoEvent extends ApplicationEvent {
    private final Long id;

    public ProductoEliminadoEvent(Object source, Long id) {
        super(source);
        this.id = id;
    }
    public Long getId() {return id;}
}