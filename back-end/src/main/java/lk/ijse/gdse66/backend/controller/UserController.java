package lk.ijse.gdse66.backend.controller;

import lk.ijse.gdse66.backend.auth.request.SignInRequest;
import lk.ijse.gdse66.backend.auth.request.SignUpRequest;
import lk.ijse.gdse66.backend.auth.response.JWTAuthResponse;
import lk.ijse.gdse66.backend.dto.SuplierDTO;
import lk.ijse.gdse66.backend.dto.UserDTO;
import lk.ijse.gdse66.backend.service.AuthenticationService;
import lk.ijse.gdse66.backend.service.SuplierService;
import lk.ijse.gdse66.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/save")
    public UserDTO save(@RequestBody UserDTO userDTO){
        System.out.println(userDTO);

        return userService.saveUser(userDTO);
    }

    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> signIn(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<JWTAuthResponse> signUp(@RequestBody SignUpRequest signUpRequest){
        System.out.println(signUpRequest);
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
    }
}
