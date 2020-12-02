package uz.pdp.appwarehouseg8.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.appwarehouseg8.entity.Country;
import uz.pdp.appwarehouseg8.entity.Region;

import java.util.UUID;

@Projection(name = "customRegion", types = Region.class)
public interface CustomRegion {
    UUID getId();

    Country getCountry();

    String getNameUz();

    String getNameRu();

    String getNameEn();
}
