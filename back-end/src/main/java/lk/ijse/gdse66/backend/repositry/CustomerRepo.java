package lk.ijse.gdse66.backend.repositry;

import lk.ijse.gdse66.backend.dto.CustomerDTO;
import lk.ijse.gdse66.backend.entity.Customer;
import lk.ijse.gdse66.backend.util.CustomerLoyaltyLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, String> {
    Customer findTopByOrderByCodeDesc();
    List<Customer> findByCodeStartingWith(String name);
    Customer findByCode(String id);

    CustomerDTO searchCustomerByCode(String code);

    Integer countByLoyaltyLevel(CustomerLoyaltyLevel level);

    @Query("SELECT c.code FROM Customer c")
    List<String> findAllCustomerCodes();

    @Query(value = "SELECT * FROM customer WHERE DAY(dob) = DAY(CURDATE()) AND MONTH(dob) = MONTH(CURDATE())", nativeQuery = true)
    List<Customer> findCustomersByBirthdayToday();



}
