package uz.pdp.appwarehouseg8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import uz.pdp.appwarehouseg8.entity.enums.SystemRoleEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

/**
 * BY SIROJIDDIN on 18.11.2020
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SystemRole implements GrantedAuthority {
    @Id
    @Enumerated(EnumType.STRING)
    private SystemRoleEnum roleSystemEnum;

    @Override
    public String getAuthority() {
        return roleSystemEnum.name();
    }
}
