package lk.ijse.gdse66.backend.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


public interface JWTService {

    String generateToken(UserDetails userDetail);
    String extractUsername(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
}
