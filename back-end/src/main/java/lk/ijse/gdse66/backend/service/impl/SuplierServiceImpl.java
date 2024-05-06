package lk.ijse.gdse66.backend.service.impl;

import lk.ijse.gdse66.backend.dto.CustomerDTO;
import lk.ijse.gdse66.backend.dto.SuplierDTO;
import lk.ijse.gdse66.backend.entity.Customer;
import lk.ijse.gdse66.backend.entity.Suplier;
import lk.ijse.gdse66.backend.repositry.CustomerRepo;
import lk.ijse.gdse66.backend.repositry.SuplierRepo;
import lk.ijse.gdse66.backend.service.SuplierService;
import lk.ijse.gdse66.backend.service.exception.DuplicateRecordException;
import lk.ijse.gdse66.backend.service.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuplierServiceImpl implements SuplierService {

    @Autowired
    private SuplierRepo suplierRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public SuplierDTO saveSupplier(SuplierDTO suplierDTO) {
        if (suplierRepo.existsById(suplierDTO.getSupplierCode())){
            throw new DuplicateRecordException("Supplier Id is already exists !!");
        }
        return mapper.map(suplierRepo.save(mapper.map(suplierDTO, Suplier.class)), SuplierDTO.class);
    }

    @Override
    public SuplierDTO updateSupplier(SuplierDTO suplierDTO) {

        if (!suplierRepo.existsById(suplierDTO.getSupplierCode())){
            throw new NotFoundException("Can't find customer id !!");
        }

        Suplier suplier = suplierRepo.findById(suplierDTO.getSupplierCode()).get();
        System.out.println("customer is "+suplier);



        return mapper.map(suplierRepo.save(mapper.map(suplierDTO, Suplier.class)), SuplierDTO.class);
    }

    public boolean deleteSupplier(String id) {
        if (!suplierRepo.existsById(id)) {
            throw new NotFoundException("Supplier with id " + id + " not found!");
        }

        suplierRepo.deleteById(id);
        return true;
    }

    @Override
    public List<SuplierDTO> getAllSupplier() {
        return suplierRepo.findAll().stream().map(suplier -> mapper.map(suplier, SuplierDTO.class)).toList();
    }

    @Override
    public String generateNextId() {
        String prefix = "S";
        String id = "";

        Suplier lastSupplier = suplierRepo.findTopByOrderBySupplierCodeDesc();
        int nextNumericPart;
        if (lastSupplier != null) {
            String lastCode = lastSupplier.getSupplierCode();
            String numericPartString = lastCode.substring(prefix.length());
            try {
                int numericPart = Integer.parseInt(numericPartString);
                nextNumericPart = numericPart + 1;
            } catch (NumberFormatException e) {
                nextNumericPart = 1;
            }
        } else {
            nextNumericPart = 1;
        }
        id = prefix + String.format("%03d", nextNumericPart);

        return id;
    }
}
