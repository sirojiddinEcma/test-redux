package uz.pdp.appwarehouseg8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouseg8.entity.template.AbsNameEntity;

import javax.persistence.*;

/**
 * BY SIROJIDDIN on 20.11.2020
 */


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nameUz", "detail_id"}),
        @UniqueConstraint(columnNames = {"nameRu", "detail_id"}),
        @UniqueConstraint(columnNames = {"nameEn", "detail_id"})
})
public class Value extends AbsNameEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Detail detail;
}
