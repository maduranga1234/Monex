package lk.ijse.gdse66.backend.service.impl;

import lk.ijse.gdse66.backend.dto.CustomerDTO;
import lk.ijse.gdse66.backend.dto.EmployeeDTO;
import lk.ijse.gdse66.backend.dto.ItemDTO;
import lk.ijse.gdse66.backend.dto.SuplierDTO;
import lk.ijse.gdse66.backend.entity.Customer;
import lk.ijse.gdse66.backend.entity.Employee;
import lk.ijse.gdse66.backend.entity.Item;
import lk.ijse.gdse66.backend.entity.Suplier;
import lk.ijse.gdse66.backend.repositry.EmployeeRepo;
import lk.ijse.gdse66.backend.repositry.ItemRepo;
import lk.ijse.gdse66.backend.service.ItemService;
import lk.ijse.gdse66.backend.service.exception.DuplicateRecordException;
import lk.ijse.gdse66.backend.service.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper mapper;
    @Override
    public ItemDTO saveItem(ItemDTO itemDTO) {
        if (itemRepo.existsById(itemDTO.getItemCode())){
            throw new DuplicateRecordException("Item Id is already exists !!");
        }
        return mapper.map(itemRepo.save(mapper.map(itemDTO, Item.class)), ItemDTO.class);
    }

    @Override
    public ItemDTO updateItem(ItemDTO itemDTO) {
        if (!itemRepo.existsById(itemDTO.getItemCode())){
            throw new NotFoundException("Can't find Employee id !!");
        }


        return mapper.map(itemRepo.save(mapper.map(itemDTO, Item.class)), ItemDTO.class);
    }

    @Override
    public boolean deleteItem(String id) {
        if (!itemRepo.existsById(id)) {
            throw new NotFoundException("Item with id " + id + " not found!");
        }

        itemRepo.deleteById(id);
        return true;
    }

    @Override
    public List<ItemDTO> getAllItem() {
        return itemRepo.findAll().stream().map(item -> mapper.map(item, ItemDTO.class)).toList();
    }

    @Override
    public String generateNextId() {
        String prefix = "S";
        String id = "";

        Item lastItem = itemRepo.findTopByOrderByItemCodeDesc();
        int nextNumericPart;
        if (lastItem != null) {
            String lastCode = lastItem.getSupplierCode();
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

    @Override
    public List<ItemDTO> searchItem(String name) {
        List<Item> foundItem = itemRepo.findByItemDescIsStartingWith(name);

        if (foundItem.isEmpty()) {
            throw new NotFoundException("No Item found with the name: " + name);
        }

        return foundItem.stream()
                .map(item -> mapper.map(item, ItemDTO.class))
                .toList();
    }

    @Override
    public ItemDTO searchItemById(String id) {
        if (!itemRepo.existsById(id)){
            throw new NotFoundException("Customer Code does not exists!");
        }
        return mapper.map(itemRepo.findByItemCode(id),ItemDTO.class);
    }

    @Override
    public List<ItemDTO> getAllItemsByPrice(double minPrice, double maxPrice) {
        return itemRepo.findByUnitPriceSaleBetween(minPrice, maxPrice).stream().map(item -> mapper.map(item,ItemDTO.class)).toList();

    }

    @Override
    public List<ItemDTO> getAllItemsByGender(String gender) {
        return itemRepo.findByCategoryContaining(gender).stream().map(item -> mapper.map(item,ItemDTO.class)).toList();

    }

    @Override
    public List<ItemDTO> searchItemByName(String name) {
        return itemRepo.findByItemDesc(name).stream().map(item -> mapper.map(item,ItemDTO.class)).toList();
    }
}
