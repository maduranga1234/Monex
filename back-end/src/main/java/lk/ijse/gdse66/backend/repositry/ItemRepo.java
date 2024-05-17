package lk.ijse.gdse66.backend.repositry;

import lk.ijse.gdse66.backend.entity.Employee;
import lk.ijse.gdse66.backend.entity.Item;
import lk.ijse.gdse66.backend.entity.Suplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepo extends JpaRepository<Item,String> {

    Item findTopByOrderByItemCodeDesc();
    List<Item> findByItemDescIsStartingWith(String name);
}
