package lk.ijse.gdse66.backend.repositry;

import lk.ijse.gdse66.backend.entity.Item;
import lk.ijse.gdse66.backend.entity.Suplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SuplierRepo extends JpaRepository<Suplier,String> {
   Suplier findTopByOrderBySupplierCodeDesc();
   List<Suplier> findBySupplierNameStartingWith(String name);
}
