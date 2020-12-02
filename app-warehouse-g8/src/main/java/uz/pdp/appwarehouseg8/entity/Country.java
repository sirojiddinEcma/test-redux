package uz.pdp.appwarehouseg8.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.appwarehouseg8.entity.template.AbsNameEntity;

import javax.persistence.Entity;

/**
 * BY SIROJIDDIN on 02.12.2020
 */


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Country extends AbsNameEntity {
}
