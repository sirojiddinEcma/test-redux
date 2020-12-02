package uz.pdp.appwarehouseg8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouseg8.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.List;

/**
 * BY SIROJIDDIN on 18.11.2020
 */


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CompanyRole extends AbsEntity {
    @Column(nullable = false)
    private String name;//omborchi,ketmon

    @Column(length = 500)
    private String description;//OMBOR EGASI

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Company company;//QAYSI COMPANY UCHUN LAVOZIMI

    @ManyToMany
    @JoinTable(name = "company_role_company_permission",
            joinColumns = @JoinColumn(name = "company_role_id"),
            inverseJoinColumns = @JoinColumn(name = "company_permission_id"))
    private List<CompanyPermission> companyPermissions;//qila oladigan  ishlari
}
