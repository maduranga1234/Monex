package lk.ijse.gdse66.backend.controller;

import lk.ijse.gdse66.backend.dto.CustomerDTO;
import lk.ijse.gdse66.backend.dto.EmployeeDTO;
import lk.ijse.gdse66.backend.entity.Employee;
import lk.ijse.gdse66.backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {


    public EmployeeController(){
        System.out.println("Employee working");
    }

    @Autowired

    private EmployeeService employeeService;

    @PostMapping("/save")
    public EmployeeDTO save(@RequestBody EmployeeDTO employeeDTO){
        System.out.println(employeeDTO);
//        customerDTO.setCode(customerService.generateNextId());
        return employeeService.saveEmployee(employeeDTO);
    }

    @PatchMapping ("/update")
    public EmployeeDTO update(@RequestBody EmployeeDTO employeeDTO){
        System.out.println(employeeDTO);
        return employeeService.updateEmployee(employeeDTO);
    }

    @DeleteMapping("/{id}")

    public String delete(@PathVariable(value = "id")String id){

        System.out.println(id);
        return String.valueOf(employeeService.deleteEmployee(id));

    }

    @GetMapping("/getAllEmployees")
    public List<EmployeeDTO> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @GetMapping("/nextId")
    public String nextId(){
        return employeeService.generateNextId();
    }

    @GetMapping("/search/{name}")
    public List<EmployeeDTO> searchEmployee(@PathVariable(value = "name")String name){
        return employeeService.searchEmployee(name);
    }
}
