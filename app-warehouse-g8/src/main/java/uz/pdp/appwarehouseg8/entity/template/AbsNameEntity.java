package uz.pdp.appwarehouseg8.entity.template;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.MappedSuperclass;

/**
 * BY SIROJIDDIN on 18.11.2020
 */


@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public abstract class AbsNameEntity extends AbsEntity {
    private String nameUz;
    private String nameRu;
    private String nameEn;

}
