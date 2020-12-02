package uz.pdp.appwarehouseg8.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.appwarehouseg8.entity.Country;

import java.util.UUID;

@Projection(name = "customCountry", types = Country.class)
public interface CustomCountry {
    UUID getId();

    String getNameUz();

    String getNameRu();

    String getNameEn();
}
