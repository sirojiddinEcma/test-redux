package uz.pdp.appwarehouseg8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouseg8.entity.template.AbsNameEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * BY SIROJIDDIN on 20.11.2020
 */


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Packaging extends AbsNameEntity {

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Product product;

    @Column(nullable = false)
    private Double value;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Measurement measurement;
}
