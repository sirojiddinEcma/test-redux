package uz.pdp.appwarehouseg8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouseg8.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.List;

/**
 * BY SIROJIDDIN on 24.11.2020
 */


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OutputProduct extends AbsEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private OutputTrade outputTrade;//18,19

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Warehouse warehouse;

    private Double price;//150$,150$

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "outputProduct", cascade = CascadeType.ALL)
    private List<OutputProductAmount> outputProductAmounts;

    private String description;//bla

}
