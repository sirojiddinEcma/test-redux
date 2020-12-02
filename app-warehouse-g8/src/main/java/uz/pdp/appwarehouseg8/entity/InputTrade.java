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
 * BY SIROJIDDIN on 24.11.2020
 */


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InputTrade extends AbsEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private CurrencyType currencyType;

    private String number;

    private String factureNumber;

    @Column(nullable = false)
    private Date date = new Date();

    @Column(columnDefinition = "text")
    private String description;

}
