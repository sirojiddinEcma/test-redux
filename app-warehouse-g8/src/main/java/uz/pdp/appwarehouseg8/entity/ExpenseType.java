package uz.pdp.appwarehouseg8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouseg8.entity.template.AbsNameEntity;

import javax.persistence.*;

/**
 * BY SIROJIDDIN on 28.11.2020
 */


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nameUz", "company_id"}),
        @UniqueConstraint(columnNames = {"nameRu", "company_id"}),
        @UniqueConstraint(columnNames = {"nameEn", "company_id"})
})
public class ExpenseType extends AbsNameEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Company company;
}
