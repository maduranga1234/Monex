package lk.ijse.gdse66.backend.service;

import lk.ijse.gdse66.backend.dto.CustomerDTO;
import lk.ijse.gdse66.backend.dto.EmployeeDTO;
import lk.ijse.gdse66.backend.dto.ItemDTO;
import lk.ijse.gdse66.backend.entity.Item;

import java.util.List;

public interface ItemService {

    ItemDTO saveItem(ItemDTO itemDTO);
    ItemDTO updateItem(ItemDTO itemDTO);
    boolean deleteItem(String id);
    List<ItemDTO> getAllItem();
    String generateNextId();
    List<ItemDTO> searchItem(String name);

    ItemDTO searchItemById(String id);
}
