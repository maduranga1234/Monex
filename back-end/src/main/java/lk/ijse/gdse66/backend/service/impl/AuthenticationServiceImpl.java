package lk.ijse.gdse66.backend.service.impl;

import lk.ijse.gdse66.backend.auth.request.SignInRequest;
import lk.ijse.gdse66.backend.auth.request.SignUpRequest;
import lk.ijse.gdse66.backend.auth.response.JWTAuthResponse;
import lk.ijse.gdse66.backend.dto.UserDTO;
import lk.ijse.gdse66.backend.entity.Employee;
import lk.ijse.gdse66.backend.entity.User;
import lk.ijse.gdse66.backend.repositry.EmployeeRepo;
import lk.ijse.gdse66.backend.repositry.UserRepo;
import lk.ijse.gdse66.backend.service.AuthenticationService;
import lk.ijse.gdse66.backend.service.JWTService;
import lk.ijse.gdse66.backend.service.exception.DuplicateRecordException;
import lk.ijse.gdse66.backend.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired

    private EmployeeRepo employeeRepo;

    private final PasswordEncoder passwordEncoder;


    @Override
    public JWTAuthResponse signIn(SignInRequest signInRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
        User user = userRepo.findByEmail(signInRequest.getEmail()).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
        String generatedToken = jwtService.generateToken((UserDetails) user);
        String role = user.getAccessRole().name();

        return JWTAuthResponse.builder().token(generatedToken).accessRole(role).build();
    }

    @Override
    public JWTAuthResponse signUp(SignUpRequest signUpRequest) {
        System.out.println(signUpRequest);


        User savedUser;
        UserDTO userDTO = UserDTO.builder()
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .accessRole(signUpRequest.getAccessRole())
                .build();
        savedUser = userRepo.save(mapper.map(userDTO, User.class));

        String generatedToken = jwtService.generateToken( savedUser);
        return JWTAuthResponse.builder().token(generatedToken).build();
}
}
