package lk.ijse.gdse66.backend.service;

import lk.ijse.gdse66.backend.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    CustomerDTO updateCustomer(CustomerDTO customerDTO);
    boolean deleteCustomer(String id);
    List<CustomerDTO> getAllCustomers();
    String generateNextId();
    List<CustomerDTO> searchCustomer(String name);

    CustomerDTO searchCustomerById(String code);








}
