package lk.ijse.gdse66.backend.service.impl;

import lk.ijse.gdse66.backend.dto.MostSoldItemDTO;
import lk.ijse.gdse66.backend.entity.Item;
import lk.ijse.gdse66.backend.repositry.CustomerRepo;
import lk.ijse.gdse66.backend.repositry.ItemRepo;
import lk.ijse.gdse66.backend.repositry.OrderDetailRepo;
import lk.ijse.gdse66.backend.repositry.OrderRepo;
import lk.ijse.gdse66.backend.service.DashBoardService;
import lk.ijse.gdse66.backend.util.CustomerLoyaltyLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashBoardServiceImpl implements DashBoardService {

    @Autowired
    private OrderRepo orderRepo;
    private OrderDetailRepo orderDetailRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private ItemRepo inventoryRepo;


    @Override
    public Integer getOrdersCountForDate(LocalDate date) {
        Timestamp startOfDay = Timestamp.valueOf(date.atStartOfDay());
        Timestamp endOfDay = Timestamp.valueOf(date.atTime(LocalTime.MAX));
        return orderRepo.countOrdersForDate(startOfDay, endOfDay);
    }



@Override
    public Double getTotalPriceForDate(LocalDate date) {
        Timestamp startOfDay = Timestamp.valueOf(date.atStartOfDay());
        Timestamp endOfDay = Timestamp.valueOf(date.atTime(LocalTime.MAX));
        return orderRepo.sumTotalPriceForDate(startOfDay, endOfDay);
    }



    @Override
    public Integer getGoldCustomerCount() {
        Integer count = customerRepo.countByLoyaltyLevel(CustomerLoyaltyLevel.GOLD);
        if (count == null){
            count = 0;
        }
        return count;
    }
    @Override
    public Map<String, Object> getMostSoldItemByDate(LocalDate date){
        List<MostSoldItemDTO> result = inventoryRepo.findMostSoldItemByDate(date);
        Map<String, Object> response = new HashMap<>();
        if (!result.isEmpty()) {
            MostSoldItemDTO mostSoldItemDTO = result.get(0);
            Item mostSoldItem = mostSoldItemDTO.getItem();
            Long totalQty = mostSoldItemDTO.getTotalQty();

            response.put("mostSoldItemName", mostSoldItem.getItemDesc());
            response.put("mostSoldItemPicture", mostSoldItem.getItemPicture());
            response.put("mostSoldItemQty", totalQty);
        } else {
            response.put("mostSoldItemName", null);
            response.put("mostSoldItemPicture", null);
            response.put("mostSoldItemQty", 0);
        }
        return response;
    }

    @Override
    public Double getTotalProfitForDate(LocalDate date) {


        return 45.6;
    }
}
