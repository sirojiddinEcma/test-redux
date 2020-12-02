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
public class InputProduct extends AbsEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Product product;//notebook

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private InputTrade inputTrade;//1

    @Column(nullable = false)
    private Double price;//100$

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Measurement incomeProductPriceMeasurement;

    @OneToMany(mappedBy = "inputProduct", cascade = CascadeType.ALL)
    private List<InputProductAmount> inputProductAmounts;

    private String description;


}
