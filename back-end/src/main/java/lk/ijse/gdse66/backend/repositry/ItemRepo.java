package lk.ijse.gdse66.backend.repositry;

import lk.ijse.gdse66.backend.dto.MostSoldItemDTO;
import lk.ijse.gdse66.backend.entity.Customer;
import lk.ijse.gdse66.backend.entity.Employee;
import lk.ijse.gdse66.backend.entity.Item;
import lk.ijse.gdse66.backend.entity.Suplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.List;

public interface ItemRepo extends JpaRepository<Item,String> {


    List<Item> findByItemDesc(String name);

    Item findTopByOrderByItemCodeDesc();
    List<Item> findByItemDescIsStartingWith(String name);
    Item findByItemCode(String id);


    List<Item> findByUnitPriceSaleBetween(double minPrice, double maxPrice);

    List<Item> findByCategoryContaining(String value);

    @Query("SELECT i.itemCode FROM Item i")
    List<String> findAllItemCodes();

    @Transactional
    @Modifying
    @Query(value = "UPDATE Item " +
            "SET " +
            "status = :status, " +
            "size6 = CASE WHEN :size = '6' THEN :qty ELSE size6 END, " +
            "size8 = CASE WHEN :size = '8' THEN :qty ELSE size8 END, " +
            "size10 = CASE WHEN :size = '10' THEN :qty ELSE size10 END, " +
            "size11 = CASE WHEN :size = '11' THEN :qty ELSE size11 END " +
            "WHERE item_code = :itemCode", nativeQuery = true)
    void updateByItemCodeAndSize(int qty, String status, String itemCode, String size);

    @Query(value = "SELECT CASE " +
            "   WHEN :size = '6' THEN i.size6 " +
            "   WHEN :size = '8' THEN i.size8 " +
            "   WHEN :size = '10' THEN i.size10 " +
            "   WHEN :size = '11' THEN i.size11" +
            "   ELSE 0 " +
            "END " +
            "FROM Item i " +
            "WHERE i.item_code = :itemCode", nativeQuery = true)
    Integer findQtyByItemCodeAndSize(String itemCode, String size);


    @Query("SELECT new lk.ijse.gdse66.backend.dto.MostSoldItemDTO(i, SUM(od.itemQty)) " +
            "FROM Item i " +
            "JOIN i.orderDetails od " +
            "JOIN od.order_id o " +
            "WHERE DATE(o.orderDate) = :date " +
            "GROUP BY i " +
            "ORDER BY SUM(od.itemQty) DESC")
    List<MostSoldItemDTO> findMostSoldItemByDate(LocalDate date);



}
