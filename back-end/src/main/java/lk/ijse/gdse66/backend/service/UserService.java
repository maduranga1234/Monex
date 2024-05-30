package lk.ijse.gdse66.backend.service;

import lk.ijse.gdse66.backend.auth.request.SignInRequest;
import lk.ijse.gdse66.backend.auth.request.SignUpRequest;
import lk.ijse.gdse66.backend.auth.response.JWTAuthResponse;
import lk.ijse.gdse66.backend.dto.CustomerDTO;
import lk.ijse.gdse66.backend.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    UserDTO saveUser(UserDTO userDTO);

    UserDetailsService userDetailsService();



}
