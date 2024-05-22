package lk.ijse.gdse66.backend.service.impl;


import lk.ijse.gdse66.backend.dto.CustomerDTO;
import lk.ijse.gdse66.backend.entity.Customer;
import lk.ijse.gdse66.backend.repositry.CustomerRepo;
import lk.ijse.gdse66.backend.service.CustomerService;
import lk.ijse.gdse66.backend.service.exception.DuplicateRecordException;
import lk.ijse.gdse66.backend.service.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper mapper;
    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        if (customerRepo.existsById(customerDTO.getCode())){
            throw new DuplicateRecordException("Customer Id is already exists !!");
        }
        return mapper.map(customerRepo.save(mapper.map(customerDTO, Customer.class)),CustomerDTO.class);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {

        if (!customerRepo.existsById(customerDTO.getCode())){
            throw new NotFoundException("Can't find customer id !!");
        }

        Customer customer = customerRepo.findById(customerDTO.getCode()).get();
        System.out.println("customer is "+customer);

        customerDTO.setLoyaltyLevel(customer.getLoyaltyLevel());
        customerDTO.setLoyaltyPoints(customer.getLoyaltyPoints());
        customerDTO.setRecentPurchaseDate(customer.getRecentPurchaseDate());

        return mapper.map(customerRepo.save(mapper.map(customerDTO, Customer.class)), CustomerDTO.class);
    }

    public boolean deleteCustomer(String id) {
        if (!customerRepo.existsById(id)) {
            throw new NotFoundException("Customer with id " + id + " not found!");
        }

        customerRepo.deleteById(id);
        return true;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepo.findAll().stream().map(customer -> mapper.map(customer, CustomerDTO.class)).toList();
    }

    @Override
    public List<CustomerDTO> searchCustomer(String name) {
        List<Customer> foundCustomers = customerRepo.findByCodeStartingWith(name);

        if (foundCustomers.isEmpty()) {
            throw new NotFoundException("No customers found with the name: " + name);
        }

        return foundCustomers.stream()
                .map(customer -> mapper.map(customer, CustomerDTO.class))
                .toList();
    }

    @Override
    public CustomerDTO searchCustomerById(String code) {
        if (!customerRepo.existsById(code)){
            throw new NotFoundException("Customer Code does not exists!");
        }
        return mapper.map(customerRepo.findByCode(code),CustomerDTO.class);
    }


    @Override
    public String generateNextId() {
        String prefix = "C";
        String id = "";

        Customer lastCustomer = customerRepo.findTopByOrderByCodeDesc();
        int nextNumericPart;
        if (lastCustomer != null) {
            String lastCode = lastCustomer.getCode();
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
