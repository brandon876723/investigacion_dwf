package sv.edu.udb.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import sv.edu.udb.entitty.Producto;
import sv.edu.udb.event.ProductoCreadoEvent;
import sv.edu.udb.event.ProductoEliminadoEvent;
import sv.edu.udb.repository.ProductoRepository;

import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository producto;
    private final ApplicationEventPublisher publisher;

    public ProductoService(ProductoRepository producto, ApplicationEventPublisher publisher) {
        this.producto = producto;
        this.publisher = publisher;
    }

    public Producto crear(Producto p) {
        Producto nuevo = producto.save(p);
        publisher.publishEvent(new ProductoCreadoEvent(this, nuevo));
        return nuevo;
    }

    public Optional<Producto> obtener(Long id) {
        return producto.findById(id);
    }

    public Producto actualizar(Long id, Producto p) {
        return producto.findById(id).map(prod ->{
            prod.setNombre(p.getNombre());
            prod.setPrecio(p.getPrecio());
            return producto.save(prod);
        }).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public void eliminar(Long id) {
        producto.deleteById(id);
        publisher.publishEvent(new ProductoEliminadoEvent(this, id));
    }

}
