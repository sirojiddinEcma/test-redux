package uz.pdp.appwarehouseg8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouseg8.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * BY SIROJIDDIN on 27.11.2020
 */


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ClientPaymentReturn extends AbsEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private OutputTrade outputTrade;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private CashBox cashBox;//NAQD SUM

    private Double amount;//30 000

    private Double haqiqatda;//300$

    private Date date = new Date();

    private String description;
}
