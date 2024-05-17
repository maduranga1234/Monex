package lk.ijse.gdse66.backend.repositry;

import lk.ijse.gdse66.backend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, String> {
    Customer findTopByOrderByCodeDesc();

    List<Customer> findByNameStartingWith(String name);


}
