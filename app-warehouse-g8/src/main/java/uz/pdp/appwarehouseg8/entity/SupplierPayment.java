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
import java.util.Date;

/**
 * BY SIROJIDDIN on 25.11.2020
 */


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SupplierPayment extends AbsEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Supplier supplier;//ABDULLOH

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private CashBox cashBox;//NAQD DOLLAR,SO'M

    @Column(nullable = false)
    private Double amount;//3000$,170 000 000

    @Column(nullable = false)
    private Double haqiqatda;//30 000 000, 170 000 000

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private InputTrade inputTrade;//1

    private Date date = new Date();//21.11

    private String description;//bla
}
