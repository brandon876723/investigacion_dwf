package sv.edu.udb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
