package lk.ijse.gdse66.backend.service.impl;

import lk.ijse.gdse66.backend.dto.CustomerDTO;
import lk.ijse.gdse66.backend.dto.EmployeeDTO;
import lk.ijse.gdse66.backend.entity.Customer;
import lk.ijse.gdse66.backend.entity.Employee;
import lk.ijse.gdse66.backend.repositry.CustomerRepo;
import lk.ijse.gdse66.backend.repositry.EmployeeRepo;
import lk.ijse.gdse66.backend.service.EmployeeService;
import lk.ijse.gdse66.backend.service.exception.DuplicateRecordException;
import lk.ijse.gdse66.backend.service.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        if (employeeRepo.existsById(employeeDTO.getEmployeeCode())){
            throw new DuplicateRecordException("Customer Id is already exists !!");
        }
        return mapper.map(employeeRepo.save(mapper.map(employeeDTO, Employee.class)),EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        if (!employeeRepo.existsById(employeeDTO.getEmployeeCode())){
            throw new NotFoundException("Can't find Employee id !!");
        }

        Employee employee = employeeRepo.findById(employeeDTO.getEmployeeCode()).get();


//        customerDTO.setLoyaltyLevel(customer.getLoyaltyLevel());
//        customerDTO.setLoyaltyPoints(customer.getLoyaltyPoints());
//        customerDTO.setRecentPurchaseDate(customer.getRecentPurchaseDate());

        return mapper.map(employeeRepo.save(mapper.map(employeeDTO, Employee.class)), EmployeeDTO.class);
    }

    @Override
    public boolean deleteEmployee(String id) {
        if (!employeeRepo.existsById(id)) {
            throw new NotFoundException("Employee with id " + id + " not found!");
        }

        employeeRepo.deleteById(id);
        return true;
    }

    @Override
    public List<EmployeeDTO> getAllEmployee() {
        return employeeRepo.findAll().stream().map(employee -> mapper.map(employee,EmployeeDTO.class)).toList();
    }

    @Override
    public String generateNextId() {
        String prefix = "E";
        String id = "";

        Employee lastEmployee= employeeRepo.findTopByOrderByEmployeeCodeDesc();
        int nextNumericPart;
        if (lastEmployee != null) {
            String lastCode = lastEmployee.getEmployeeCode();
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

    @Override
    public List<EmployeeDTO> searchEmployee(String name) {
        List<Employee> foundEmplyees = employeeRepo.findByEmployeeNameStartingWith(name);

        if (foundEmplyees.isEmpty()) {
            throw new NotFoundException("No Employee found with the name: " + name);
        }

        return foundEmplyees.stream()
                .map(employee -> mapper.map(employee, EmployeeDTO.class))
                .toList();
    }
}
