package lk.ijse.gdse66.backend.service;

import java.time.LocalDate;
import java.util.Map;

public interface DashBoardService {



    Integer getOrdersCountForDate(LocalDate date);
    Double getTotalPriceForDate(LocalDate date);
    Integer getGoldCustomerCount();
    Map<String, Object> getMostSoldItemByDate(LocalDate date);
    Double getTotalProfitForDate(LocalDate date);

}
