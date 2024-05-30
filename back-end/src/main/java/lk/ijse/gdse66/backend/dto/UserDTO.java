package lk.ijse.gdse66.backend.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lk.ijse.gdse66.backend.util.Role;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserDTO {

    private String email ;
    private String password;
    private Role accessRole;
}
