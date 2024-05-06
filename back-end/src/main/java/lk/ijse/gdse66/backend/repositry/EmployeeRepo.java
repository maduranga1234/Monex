package lk.ijse.gdse66.backend.repositry;

import lk.ijse.gdse66.backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee,String> {

     Employee findTopByOrderByEmployeeCodeDesc();
}
