package lk.ijse.gdse66.backend.service.impl;

import lk.ijse.gdse66.backend.auth.request.SignInRequest;
import lk.ijse.gdse66.backend.auth.request.SignUpRequest;
import lk.ijse.gdse66.backend.auth.response.JWTAuthResponse;
import lk.ijse.gdse66.backend.dto.SuplierDTO;
import lk.ijse.gdse66.backend.dto.UserDTO;
import lk.ijse.gdse66.backend.entity.Suplier;
import lk.ijse.gdse66.backend.entity.User;
import lk.ijse.gdse66.backend.repositry.ItemRepo;
import lk.ijse.gdse66.backend.repositry.SuplierRepo;
import lk.ijse.gdse66.backend.repositry.UserRepo;
import lk.ijse.gdse66.backend.service.JWTService;
import lk.ijse.gdse66.backend.service.UserService;
import lk.ijse.gdse66.backend.service.exception.DuplicateRecordException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        if (userRepo.existsById(userDTO.getEmail())){
            throw new DuplicateRecordException("User is already exists !!");
        }
        return mapper.map(userRepo.save(mapper.map(userDTO, User.class)), UserDTO.class);
    }

    @Override
    public UserDetailsService userDetailsService() {
        return username -> {
            return  userRepo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
};
    }






}
