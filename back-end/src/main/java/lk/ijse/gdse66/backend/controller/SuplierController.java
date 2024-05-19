package lk.ijse.gdse66.backend.controller;

import lk.ijse.gdse66.backend.dto.CustomerDTO;
import lk.ijse.gdse66.backend.dto.ItemDTO;
import lk.ijse.gdse66.backend.dto.SuplierDTO;
import lk.ijse.gdse66.backend.entity.Suplier;
import lk.ijse.gdse66.backend.service.CustomerService;
import lk.ijse.gdse66.backend.service.SuplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
@CrossOrigin(origins = "*")
public class SuplierController {

    @Autowired
    private SuplierService suplierService;

    public SuplierController() {
        System.out.println("supplier working !");
    }

    @PostMapping("/save")
    public SuplierDTO save(@RequestBody SuplierDTO suplierDTO){
        System.out.println(suplierDTO);

        return suplierService.saveSupplier(suplierDTO);
    }


    @PatchMapping ("/update")
    public SuplierDTO update(@RequestBody SuplierDTO suplierDTO){
        System.out.println(suplierDTO);
        return suplierService.updateSupplier(suplierDTO);
    }

    @DeleteMapping("/{id}")

    public String delete(@PathVariable(value = "id")String id){

        System.out.println(id);
        return String.valueOf(suplierService.deleteSupplier(id));

    }


    @GetMapping("/getAllSupplier")
    public List<SuplierDTO> getAllSupplier(){
        return suplierService.getAllSupplier();
    }

    @GetMapping("/nextId")
    public String nextId(){
        return suplierService.generateNextId();
    }

    @GetMapping("/search/{name}")
    public List<SuplierDTO> searchSupplier(@PathVariable(value = "name")String name){
        return suplierService.searchSuplier(name);
    }

}
