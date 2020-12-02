package uz.pdp.appwarehouseg8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouseg8.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.List;

/**
 * BY SIROJIDDIN on 27.11.2020
 */


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InputProductReturn extends AbsEntity {

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private InputProduct inputProduct;

    @OneToMany(mappedBy = "inputProductReturn")
    private List<InputProductReturnAmount> inputProductReturnAmounts;

    private boolean defect;

    @Column(nullable = false)
    private String description;


}
