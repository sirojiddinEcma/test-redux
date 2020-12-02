package uz.pdp.appwarehouseg8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.appwarehouseg8.entity.Country;
import uz.pdp.appwarehouseg8.projection.CustomCountry;

import java.util.UUID;

@RepositoryRestResource(path = "country", excerptProjection = CustomCountry.class)
public interface CountryRepository extends JpaRepository<Country, UUID> {
}
