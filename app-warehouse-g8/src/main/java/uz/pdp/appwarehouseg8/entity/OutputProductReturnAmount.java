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
 * BY SIROJIDDIN on 27.11.2020
 */


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OutputProductReturnAmount extends AbsEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private OutputProductReturn outputProductReturn;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Measurement measurement;

    @ManyToOne(fetch = FetchType.LAZY)
    private Packaging packaging;

    private Double amountPackaging;

    private Double amount;

}
