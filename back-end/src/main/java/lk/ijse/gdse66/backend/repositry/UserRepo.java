package lk.ijse.gdse66.backend.repositry;

import lk.ijse.gdse66.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,String> {

    Optional<User> findByEmail(String email);
}
