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
 * BY SIROJIDDIN on 24.11.2020
 */


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InputProductAmount extends AbsEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Measurement measurement;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private InputProduct inputProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    private Packaging packaging;

    private Double amountPackaging;

    private Double amount;


}
