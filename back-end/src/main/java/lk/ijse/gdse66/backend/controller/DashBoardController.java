package lk.ijse.gdse66.backend.controller;

import lk.ijse.gdse66.backend.service.DashBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "*")
public class DashBoardController {

    @Autowired
    private DashBoardService dashBoardService;

    @GetMapping("/getSummeryForToday")
    public Map<String, Object> getSummaryForToday(@RequestParam("date") String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        Map<String, Object> response = new HashMap<>();
        response.put("ordersCount", dashBoardService.getOrdersCountForDate(date));
        response.put("totalPrice", dashBoardService.getTotalPriceForDate(date));
        response.put("goldCusCount", dashBoardService.getGoldCustomerCount());
        return response;
    }

    @GetMapping("/getSummeryForSelectedDate")
    public Map<String, Object> getSummeryForSelectedDate(@RequestParam("date")String dateString){
        LocalDate date = LocalDate.parse(dateString);
        Map<String, Object> response = new HashMap<>();
        response.put("totalPrice", dashBoardService.getTotalPriceForDate(date));

        Map<String, Object> mostSolidItem = dashBoardService.getMostSoldItemByDate(date);
        response.put("mostSoldItemName", mostSolidItem.get("mostSoldItemName"));
        response.put("mostSoldItemPicture", mostSolidItem.get("mostSoldItemPicture"));
        response.put("mostSoldItemQty", mostSolidItem.get("mostSoldItemQty"));

        /*adminPanelService.getTotalProfitForDate(date);*/
        return response;
    }
}
