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
public class ClientPayment extends AbsEntity {
    //so'mda(2)

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Client client;//ZIYOVUDDIN, ZIYOVUDDIN

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private PayType payType;//dollar(1),payme(2), rubl naqd(3)

    @Column(nullable = false)
    private Double amount;//100$,940 000, 10 000

    @Column(nullable = false)
    private Double haqiqatda;//1 060 000, 940 000, 1 000 000

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private OutputTrade outputTrade;//1

    private Date date = new Date();//bugun

    private String description;//bla
}
