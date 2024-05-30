package lk.ijse.gdse66.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lk.ijse.gdse66.backend.util.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
  @Id
    private String email ;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role accessRole;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    HashSet<GrantedAuthority> authorities = new HashSet<>();
    authorities.add(new SimpleGrantedAuthority(
            "Role_" + accessRole.name()
    ));
    return authorities;
  }

  @Override
  public String getUsername() {
    return email;
}

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
