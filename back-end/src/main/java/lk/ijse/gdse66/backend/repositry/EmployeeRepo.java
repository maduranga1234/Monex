package lk.ijse.gdse66.backend.repositry;

import lk.ijse.gdse66.backend.entity.Customer;
import lk.ijse.gdse66.backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee,String> {

     Employee findTopByOrderByEmployeeCodeDesc();
     List<Employee> findByEmployeeNameStartingWith(String name);
}
