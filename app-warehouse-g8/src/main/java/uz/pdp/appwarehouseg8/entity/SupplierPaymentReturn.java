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
 * BY SIROJIDDIN on 27.11.2020
 */


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SupplierPaymentReturn extends AbsEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private InputTrade inputTrade;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private PayType payType;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Double haqiqatda;

    private String description;

}
