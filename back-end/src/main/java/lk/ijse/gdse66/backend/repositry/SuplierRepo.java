package lk.ijse.gdse66.backend.repositry;

import lk.ijse.gdse66.backend.entity.Suplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuplierRepo extends JpaRepository<Suplier,String> {
   Suplier findTopByOrderBySupplierCodeDesc();
}
