package sv.edu.udb.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import sv.edu.udb.entity.Producto;
import sv.edu.udb.event.ProductoCreadoEvent;
import sv.edu.udb.event.ProductoEliminadoEvent;
import sv.edu.udb.repository.ProductoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final ApplicationEventPublisher publisher;

    public ProductoService(ProductoRepository productoRepository, ApplicationEventPublisher publisher) {
        this.productoRepository = productoRepository;
        this.publisher = publisher;
    }

    public Producto crear(Producto p) {
        Producto nuevo = productoRepository.save(p);
        publisher.publishEvent(new ProductoCreadoEvent(this, nuevo));
        return nuevo;
    }

    public Optional<Producto> obtener(Long id) {
        return productoRepository.findById(id);
    }

    public Producto actualizar(Long id, Producto p) {
        return productoRepository.findById(id).map(prod ->{
            prod.setNombre(p.getNombre());
            prod.setPrecio(p.getPrecio());
            return productoRepository.save(prod);
        }).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public void eliminar(Long id) {
        productoRepository.deleteById(id);
        publisher.publishEvent(new ProductoEliminadoEvent(this, id));
    }

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }
}
