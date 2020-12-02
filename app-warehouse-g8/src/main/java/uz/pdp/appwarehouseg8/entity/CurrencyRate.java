package uz.pdp.appwarehouseg8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouseg8.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * BY SIROJIDDIN on 25.11.2020
 */


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CurrencyRate extends AbsEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private CurrencyType fromCurrency;//SUM(2),USD(1),RUB(3)

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private CurrencyType toCurrency;//USD(1),SUM(2),SUM (2)

    @Column(nullable = false)
    private Double fromValue;//105000,1,1

    @Column(nullable = false)
    private Double toValue;//10,10600,100

}
