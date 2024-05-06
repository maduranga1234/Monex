package lk.ijse.gdse66.backend.controller;

import lk.ijse.gdse66.backend.dto.SuplierDTO;
import lk.ijse.gdse66.backend.dto.UserDTO;
import lk.ijse.gdse66.backend.service.SuplierService;
import lk.ijse.gdse66.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public UserDTO save(@RequestBody UserDTO userDTO){
        System.out.println(userDTO);

        return userService.saveUser(userDTO);
    }
}
