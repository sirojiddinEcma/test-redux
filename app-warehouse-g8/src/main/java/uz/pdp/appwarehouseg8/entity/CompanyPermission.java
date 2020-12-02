package uz.pdp.appwarehouseg8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import uz.pdp.appwarehouseg8.entity.enums.CompanyPermissionEnum;
import uz.pdp.appwarehouseg8.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * BY SIROJIDDIN on 18.11.2020
 */


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CompanyPermission extends AbsEntity implements GrantedAuthority {

    @Enumerated(EnumType.STRING)
    private CompanyPermissionEnum companyPermissionEnum;

    @Override
    public String getAuthority() {
        return companyPermissionEnum.name();
    }
}
