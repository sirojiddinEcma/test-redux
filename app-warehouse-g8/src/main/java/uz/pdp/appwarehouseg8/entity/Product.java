package uz.pdp.appwarehouseg8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouseg8.entity.template.AbsNameEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * BY SIROJIDDIN on 20.11.2020
 */


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends AbsNameEntity {
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Category category;//QAYSI KATEGORIYAGA KIRADI

    private String code;

    private String article;

    @ManyToMany
    @JoinTable(name = "product_value",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "value_id"))
    private Set<Value> values;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Company company;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<Packaging> packagingList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductPhoto> productPhotos;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<ProductMeasurementPrice> productMeasurementPrices;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<ProductPriceType> productPriceTypes;
}
