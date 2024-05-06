package lk.ijse.gdse66.backend.service;

import lk.ijse.gdse66.backend.dto.CustomerDTO;
import lk.ijse.gdse66.backend.dto.SuplierDTO;

import java.util.List;

public interface SuplierService {
    SuplierDTO saveSupplier(SuplierDTO suplierDTO);
    SuplierDTO updateSupplier(SuplierDTO suplierDTO);
    boolean deleteSupplier(String id);
    List<SuplierDTO> getAllSupplier();
    String generateNextId();
}
