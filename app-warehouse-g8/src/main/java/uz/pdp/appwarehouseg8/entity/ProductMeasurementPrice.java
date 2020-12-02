package uz.pdp.appwarehouseg8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouseg8.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * BY SIROJIDDIN on 23.11.2020
 */


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductMeasurementPrice extends AbsEntity {
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Product product;//Alumin Profil

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Measurement measurement;//O'LCHOV BIRLIGI

//    //10%,20%
//    private Double percent;//MAXSULOT TANNARXNING USTIGA QO'YILADIGAN FOYDANING FOIZDAGI MIQDORI (RIBO EMAS)

    private Double inputProductPrice;//OMBORGA KIRGAN VA MAVJUD BO'LGAN MAXSULOTLAR ICHIDA ENG QIMMAT NARX

    private Double minPrice;//SOTISH MUMKIN BO'LGAN MIN SUMMA

}
