package uz.pdp.appwarehouseg8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.appwarehouseg8.entity.Region;
import uz.pdp.appwarehouseg8.projection.CustomRegion;

import java.util.UUID;

@RepositoryRestResource(path = "region", excerptProjection = CustomRegion.class)
public interface RegionRepository extends JpaRepository<Region, UUID> {
}
