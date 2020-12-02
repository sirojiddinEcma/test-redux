package uz.pdp.appwarehouseg8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouseg8.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * BY SIROJIDDIN on 23.11.2020
 */


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductPhoto extends AbsEntity {
    private Integer index;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Product product;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Attachment photo;
}
