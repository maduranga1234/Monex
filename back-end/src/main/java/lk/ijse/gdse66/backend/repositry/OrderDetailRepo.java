package lk.ijse.gdse66.backend.repositry;

import lk.ijse.gdse66.backend.entity.Employee;
import lk.ijse.gdse66.backend.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepo extends JpaRepository<OrderDetail,String> {


    @Query(value = "SELECT * FROM order_detail WHERE order_id = :orderId", nativeQuery = true)
    List<OrderDetail> findOrderDetailsByOrderId(String orderId);




}
